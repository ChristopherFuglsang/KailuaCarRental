import java.sql.*;
import java.util.Scanner;

public class CarRentalApp {
    private static Scanner scanner = new Scanner(System.in);


    public static void addCar() {
        try (Connection conn = Database.getConnection()) {
            System.out.print("Indtast mærke: ");
            String brand = scanner.nextLine();
            System.out.print("Indtast model: ");
            String model = scanner.nextLine();
            System.out.print("Indtast brændstoftype: ");
            String fuelType = scanner.nextLine();
            System.out.print("Indtast registreringsnummer: ");
            String regNumber = scanner.nextLine();
            System.out.print("Indtast registreringsdato (YYYY-MM-DD): ");
            String regDate = scanner.nextLine();
            System.out.print("Indtast kilometertal: ");
            int odometer = scanner.nextInt();
            System.out.print("Indtast biltype ID (1 = Luxury, 2 = Family, 3 = Sport): ");
            int cartypeId = scanner.nextInt();
            scanner.nextLine(); // Ryd buffer

            String sql = "INSERT INTO car (brand, model, fuel_type, reg_number, reg_date, odometer, cartype_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, brand);
            stmt.setString(2, model);
            stmt.setString(3, fuelType);
            stmt.setString(4, regNumber);
            stmt.setString(5, regDate);
            stmt.setInt(6, odometer);
            stmt.setInt(7, cartypeId);

            stmt.executeUpdate();
            System.out.println("Bil tilføjet!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void listCars() {
        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car");

            System.out.println("\n=== Liste over biler ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("car_id") + ", " +
                        "Mærke: " + rs.getString("brand") + ", " +
                        "Model: " + rs.getString("model") + ", " +
                        "Reg.nr: " + rs.getString("reg_number") + ", " +
                        "Kilometer: " + rs.getInt("odometer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteCar() {
        try (Connection conn = Database.getConnection()) {
            System.out.print("Indtast bil-ID, der skal slettes: ");
            int carId = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM car WHERE car_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, carId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bil slettet!");
            } else {
                System.out.println("Ingen bil fundet med det ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateCar() {
        try (Connection conn = Database.getConnection()) {
            System.out.print("Indtast bil-ID, der skal opdateres: ");
            int carId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Indtast nyt kilometertal: ");
            int newOdometer = scanner.nextInt();
            scanner.nextLine();

            String sql = "UPDATE car SET odometer = ? WHERE car_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newOdometer);
            stmt.setInt(2, carId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bil opdateret!");
            } else {
                System.out.println("Ingen bil fundet med det ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
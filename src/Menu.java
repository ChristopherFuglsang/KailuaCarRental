import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n Kailua CarRental Menu");
            System.out.println("1. Tilføj bil");
            System.out.println("2. Vis alle biler");
            System.out.println("3. Slet bil");
            System.out.println("4. Opdater bil");
            System.out.println("5. Afslut");
            System.out.print("Vælg en mulighed (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CarRentalApp.addCar();
                    break;
                case 2:
                    CarRentalApp.listCars();
                    break;
                case 3:
                    CarRentalApp.deleteCar();
                    break;
                case 4:
                    CarRentalApp.updateCar();
                    break;
                case 5:
                    running = false;
                    System.out.println("Farvel!");
                    break;
                default:
                    System.out.println("Ugyldigt valg! Prøv igen.");
            }
        }
    }

    public static void main(String[] args) {
        Menu.showMenu();
    }
}
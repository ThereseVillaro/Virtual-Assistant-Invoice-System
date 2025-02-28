package invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Invoice_System {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Clients> clients = new ArrayList<>();
    private static final List<Services> services = new ArrayList<>();
    private static final List<Invoices> invoices = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();
            handleMainMenu(choice);
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Virtual Assistant Invoice System ===");
        System.out.println("1. Client Management");
        System.out.println("2. Service Management");
        System.out.println("3. Invoice Management");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static void handleMainMenu(int choice) {
        switch (choice) {
            case 1 -> handleClientManagement();
            case 2 -> handleServiceManagement();
            case 3 -> handleInvoiceManagement();
            case 4 -> exitApplication();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void handleClientManagement() {
        System.out.println("1. Add Client\n2. View Clients\n3. Update Client\n4. Delete Client");
        int clientChoice = getUserChoice();
        switch (clientChoice) {
            case 1 -> addClient();
            case 2 -> viewClients();
            case 3 -> updateClient();
            case 4 -> deleteClient();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addClient() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        clients.add(new Clients(clients.size() + 1, name, email));
        System.out.println("Client added successfully.");
    }

    private static void viewClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            clients.forEach(c -> System.out.println(c.getId() + ". " + c.getName() + " - " + c.getEmail()));
        }
    }

    private static void updateClient() {
        System.out.print("Enter Client ID to update: ");
        int id = getUserChoice();
        for (Clients c : clients) {
            if (c.getId() == id) {
                System.out.print("Enter new name: ");
                c.setName(scanner.nextLine());
                System.out.print("Enter new email: ");
                c.setEmail(scanner.nextLine());
                System.out.println("Client updated successfully.");
                return;
            }
        }
        System.out.println("Client not found.");
    }

    private static void deleteClient() {
        System.out.print("Enter Client ID to delete: ");
        int id = getUserChoice();
        clients.removeIf(c -> c.getId() == id);
        System.out.println("Client deleted successfully.");
    }

    private static void handleServiceManagement() {
        System.out.println("1. Add Service\n2. View Services\n3. Update Service\n4. Delete Service");
        int serviceChoice = getUserChoice();
        switch (serviceChoice) {
            case 1 -> addService();
            case 2 -> viewServices();
            case 3 -> updateService();
            case 4 -> deleteService();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addService() {
        System.out.print("Enter service name: ");
        String serviceName = scanner.nextLine();
        System.out.print("Enter service rate: ");
        double rate = scanner.nextDouble();
        services.add(new Services(services.size() + 1, serviceName, rate));
        System.out.println("Service added successfully.");
    }

    private static void viewServices() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            services.forEach(s -> System.out.println(s.getId() + ". " + s.getName() + " - $" + s.getRate()));
        }
    }

    private static void updateService() {
        System.out.print("Enter Service ID to update: ");
        int id = getUserChoice();
        for (Services s : services) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                s.setName(scanner.nextLine());
                System.out.print("Enter new rate: ");
                s.setRate(scanner.nextDouble());
                System.out.println("Service updated successfully.");
                return;
            }
        }
        System.out.println("Service not found.");
    }

    private static void deleteService() {
        System.out.print("Enter Service ID to delete: ");
        int id = getUserChoice();
        services.removeIf(s -> s.getId() == id);
        System.out.println("Service deleted successfully.");
    }

    private static void handleInvoiceManagement() {
        System.out.println("1. Create Invoice\n2. View Invoices");
        int invoiceChoice = getUserChoice();
        switch (invoiceChoice) {
            case 1 -> createInvoice();
            case 2 -> viewInvoices();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void createInvoice() {
        if (clients.isEmpty() || services.isEmpty()) {
            System.out.println("Ensure clients and services exist before creating an invoice.");
            return;
        }
        
        viewClients();
        System.out.print("Enter Client ID: ");
        int clientId = getUserChoice();
        Clients client = clients.stream().filter(c -> c.getId() == clientId).findFirst().orElse(null);
        if (client == null) {
            System.out.println("Invalid client ID.");
            return;
        }

        viewServices();
        System.out.print("Enter Service IDs (comma-separated): ");
        List<Services> selectedServices = new ArrayList<>();
        for (String id : scanner.nextLine().split(",")) {
            services.stream().filter(s -> s.getId() == Integer.parseInt(id.trim())).findFirst().ifPresent(selectedServices::add);
        }

        if (selectedServices.isEmpty()) {
            System.out.println("No valid services selected.");
            return;
        }

        invoices.add(new Invoices(invoices.size() + 1, client, selectedServices));
        System.out.println("Invoice successfully created!");
    }

    private static void viewInvoices() {
        if (invoices.isEmpty()) System.out.println("No invoices available.");
        else invoices.forEach(System.out::println);
    }

    private static void exitApplication() {
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }
}

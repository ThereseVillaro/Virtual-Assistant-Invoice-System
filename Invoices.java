package invoice;

import java.util.ArrayList;
import java.util.List;

class Invoices {
    private final int id;
    private final Clients client;
    private final List<Services> services;
    private final double totalAmount;

    public Invoices(int id, Clients client, List<Services> services) {
        this.id = id;
        this.client = client;
        this.services = new ArrayList<>(services);
        this.totalAmount = calculateTotalAmount(services);
    }

    private double calculateTotalAmount(List<Services> services) {
        return services.stream().mapToDouble(Services::getRate).sum();
    }

    public int getId() { return id; }
    public Clients getClient() { return client; }
    public List<Services> getServices() { return new ArrayList<>(services); }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return String.format("Invoice ID: %d\nClient: %s\nTotal: $%.2f", id, client.getName(), totalAmount);
    }
}

class InvoiceManager {
    private final List<Invoices> invoices = new ArrayList<>();
    private int invoiceIdCounter = 1;

    public void createInvoice(Clients client, List<Services> services) {
        invoices.add(new Invoices(invoiceIdCounter++, client, services));
    }

    public void viewInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices available.");
        } else {
            invoices.forEach(System.out::println);
        }
    }
}

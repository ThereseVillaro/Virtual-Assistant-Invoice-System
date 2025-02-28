package invoice;

import java.util.ArrayList;
import java.util.List;

class Services {
    private final int id;
    private String name;
    private double rate;

    public Services(int id, String name, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getRate() { return rate; }

    public void setName(String name) { this.name = name; }
    public void setRate(double rate) { this.rate = rate; }

    @Override
    public String toString() {
        return String.format("Service ID: %d, Name: %s, Rate: $%.2f per hour", id, name, rate);
    }
}

class ServiceManager {
    private final List<Services> services = new ArrayList<>();
    private int serviceIdCounter = 1;

    public void addService(String name, double rate) {
        services.add(new Services(serviceIdCounter++, name, rate));
    }

    public void viewServices() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            services.forEach(System.out::println);
        }
    }

    public void updateService(int id, String name, double rate) {
        services.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresentOrElse(s -> {
                    s.setName(name);
                    s.setRate(rate);
                }, () -> System.out.println("Service not found."));
    }

    public void deleteService(int id) {
        if (!services.removeIf(s -> s.getId() == id)) {
            System.out.println("Service not found.");
        }
    }

    public List<Services> getServices() {
        return new ArrayList<>(services);
    }
}
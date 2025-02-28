package invoice;

import java.util.ArrayList;
import java.util.List;

class Clients {
    private final int id;
    private String name;
    private String email;

    public Clients(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("Client ID: %d, Name: %s, Email: %s", id, name, email);
    }
}

class ClientManager {
    private final List<Clients> clients = new ArrayList<>();
    private int clientIdCounter = 1;

    public void addClient(String name, String email) {
        clients.add(new Clients(clientIdCounter++, name, email));
    }

    public void viewClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            clients.forEach(System.out::println);
        }
    }

    public void updateClient(int id, String name, String email) {
        clients.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .ifPresentOrElse(c -> {
                    c.setName(name);
                    c.setEmail(email);
                }, () -> System.out.println("Client not found."));
    }

    public void deleteClient(int id) {
        if (!clients.removeIf(c -> c.getId() == id)) {
            System.out.println("Client not found.");
        }
    }

    public List<Clients> getClients() {
        return new ArrayList<>(clients);
    }
}
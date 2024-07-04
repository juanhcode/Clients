package edu.tienda.core.controllers;

import edu.tienda.core.domain.Client;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientRestController {

    private final List<Client> clients = new ArrayList<>(List.of(
            new Client("Juan", "12345", "Juan Manuel")
    ));

    @GetMapping("/clients")
    public List<Client> getClients() {
        return this.clients;
    }

    @GetMapping("/client/{userName}")
    public Client getClient(@PathVariable String userName){
        return this.clients.stream().filter(client -> client.getUsername().equals(userName)).findFirst().orElseThrow();
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client){
        clients.add(client);
        return client;
    }

    @PutMapping("/clients")
    public Client putClient (@RequestBody Client client){
        Client findClient = this.clients.stream().filter(client1 -> client1.getUsername().equalsIgnoreCase(client.getUsername()))
                .findFirst()
                .orElseThrow();
        findClient.setPassword(client.getPassword());
        findClient.setName(client.getName());
        return findClient;
    }

    @DeleteMapping("/clients/{userName}")
    public void deleteClient(@PathVariable String userName){
        Client findClient = this.clients.stream().filter(client1 -> client1.getUsername().equalsIgnoreCase(userName))
                .findFirst()
                .orElseThrow();
        this.clients.remove(findClient);
    }

}

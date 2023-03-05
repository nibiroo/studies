package io.github.nibiroo.service;

import io.github.nibiroo.model.Client;
import io.github.nibiroo.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ANNOTATION
//   Service: Stereotype to say that this Java class will do the function that will use Database(via Repository). SpringBoot scan the annotation.
@Service
public class ClientsServices {

    private ClientsRepository clientRepository;

    // Autowired indicates a point where automatic injection should be applied. This can be used in methods, attributes and constructors.
    //  Will find the annotation of the Java Class used in the parameter to make Injection.
    @Autowired
    public ClientsServices( ClientsRepository repositoryParam ) {
        this.clientRepository = repositoryParam;
    }

    public void saveClients(Client client) {
        validateClient(client);
        // Is not feasible{viável} to perform{para realizar} instances/connections with the database, this could overflow{estourar} the database
        // ClientsRepository clientsRepository = new ClientsRepository();
        // clientsRepository.persist(client);
        this.clientRepository.persist(client);
    }

    public void validateClient(Client client) {
        // Apply validate

    }

    // GETTERS AND SETTERS
    public ClientsRepository getRepository() {
        return clientRepository;
    }

    public void setRepository(ClientsRepository repository) {
        this.clientRepository = repository;
    }
}

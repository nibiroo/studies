package io.github.nibiroo.repository;

import io.github.nibiroo.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// ANNOTATION
//   Repository: Stereotype to say that this Java class will access the Database (CRUD)
@Repository
@Component
public class ClientsRepository {
    public void persist(Client client) {
        // Connect the database and save the client
    }
}

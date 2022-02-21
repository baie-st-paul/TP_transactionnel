package library.persistence;

import library.model.client.Client;

public interface JDBCLibrary {
     void createTableClient();
     void save(Client client);
     Client getClient(int clientId);
}

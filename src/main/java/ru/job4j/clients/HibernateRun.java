package ru.job4j.clients;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.GregorianCalendar;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 21.04.2020
 */

public class HibernateRun {
    private static Session session;
    private static SessionFactory factory;

    private static void createHibernateSession() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = factory.openSession();
        session.beginTransaction();
    }

    private static void closeHibernateSession() {
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void main(String[] args) {
        //start Hibernate
        createHibernateSession();
        addClient();
        getClients();
        closeHibernateSession();

        //update user and select this client
        createHibernateSession();
        updateClient();
        getClients();
        closeHibernateSession();

        //delete user and select all, but list is empty
        createHibernateSession();
        deleteClient();
        getClients();
        closeHibernateSession();

    }

    private static void addClient() {
        Client client = new Client();
        client.setId(1);
        client.setName("Виталий");
        client.setExpired(new GregorianCalendar(2020, 3, 7));
        session.save(client);
    }

    private static void updateClient() {
        Client updatedClient = null;

        List<Client> clients = session.createQuery("from Client where name = 'Виталий'", Client.class).list();
        for (Client client : clients) {
            client.setName("Иннокентий");
            client.setExpired(new GregorianCalendar(2012, 11, 21));
            updatedClient = client;
        }

        session.update(updatedClient);
    }

    private static void deleteClient() {
        Client deletedClient = null;

        List<Client> clients = session.createQuery("from Client where name = 'Иннокентий'", Client.class).list();
        for (Client client : clients) {
            deletedClient = client;
        }

        session.delete(deletedClient);
    }

    private static void getClients() {
        List<Client> sample = session.createQuery("from Client", Client.class).list();
        System.out.println(sample.toString());
    }
}

package ru.job4j.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.GregorianCalendar;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 08.03.2020
 */

public class HibernateRun {
    private static final DateFormat DATE_FORMAT_DASH_SEPARATED = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Метод реализует основные операции в базе данных при использовании Hibernate
     */

    public static void main(String[] args) {
        //start HQL
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Client client = new Client();
        //create user and select this client
        client.setId(1);
        client.setName("Виталий");
        client.setExpired(new GregorianCalendar(2020, 3, 7));
        session.save(client);
        List<Client> sample1 = session.createQuery("from Client where name = 'Виталий'").list();
        for (Client user : sample1) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(DATE_FORMAT_DASH_SEPARATED.format(user.getExpired().getTime()));
        }
        //update user and select this client
        client.setName("Иннокентий");
        client.setExpired(new GregorianCalendar(2012, 11, 21));
        session.update(client);
        List<Client> sample2 = session.createQuery("from Client where name = 'Иннокентий'").list();
        for (Client user : sample2) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(DATE_FORMAT_DASH_SEPARATED.format(user.getExpired().getTime()));
        }
        //delete user and select all, but list is empty
        session.delete(client);
        Query sample3 = session.createQuery("from Client");
        List<Client> clients = sample3.list();
        for (Client user : clients) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(DATE_FORMAT_DASH_SEPARATED.format(user.getExpired().getTime()));
        }
        //destroy HQL
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}

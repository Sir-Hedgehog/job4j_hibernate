package ru.job4j.items;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.04.2020
 */

public class ItemDatabase implements Store {
    private static final ItemDatabase INSTANCE = new ItemDatabase();
    private Session session;

    /**
     * В конструкторе происходит инициализация соединения и начало транзакции через конфигурационные файлы Hibernate
     */

    public ItemDatabase() {
        SessionFactory factory = new Configuration().configure("items.cfg.xml").buildSessionFactory();
        session = factory.openSession();
        session.beginTransaction();
    }

    /**
     * Метод дает право создать единственный экзепляр БД
     * @return - экземпляр класса ItemDatabase
     */

    public static ItemDatabase getInstance() {
        return INSTANCE;
    }

    /**
     * Добавление нового задания
     * @param description - суть задания (содержание)
     */

    @Override
    public void addItem(String description) {
        Item item = new Item();
        item.setDescription(description);
        item.setDone(false);
        session.save(item);
    }

    /**
     * Метод обновляет статус задания
     * @param id - идентификатор существующего задания
     * @param done - смена статуса
     */

    @Override
    public void updateItem(String id, String done) {
        List<Item> elect = session.createQuery("from Item where id = " + id, Item.class).list();
        boolean result = false;
        if (done.equals("1")) {
            result = true;
        }
        elect.get(0).setDone(result);
        session.update(elect.get(0));
        session.getTransaction().commit();
        session.beginTransaction();
    }

    /**
     * Метод формирует список существующих заданий в зависимости от регулятора списка
     * @param regulator - регулятор списка
     * @return - список заданий
     */

    @Override
    public List<Item> getItems(String regulator) {
        List<Item> result;
        if (regulator.equals("1")) {
            result = session.createQuery("from Item", Item.class).list();
        } else {
            result = session.createQuery("from Item where done = false", Item.class).list();
        }
        return result;
    }
}

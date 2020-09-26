package ru.job4j.items;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.function.Function;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 14.04.2020
 */

public class ItemDatabase implements Store {
    private static final ItemDatabase INSTANCE = new ItemDatabase();
    private final SessionFactory factory;

    /**
     * В конструкторе происходит инициализация соединения и начало транзакции через конфигурационные файлы Hibernate
     */

    public ItemDatabase() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    /**
     * Метод дает право создать единственный экземпляр БД
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
        this.template(session -> session.save(item));
    }

    /**
     * Метод обновляет статус задания
     * @param id - идентификатор существующего задания
     * @param done - смена статуса
     */

    @Override
    public void updateItem(String id, String done) {
        this.template(
                session -> {
                    List<Item> elect = session.createQuery("from Item where id = " + id, Item.class).list();
                    boolean result = false;
                    if (done.equals("1")) {
                        result = true;
                    }
                    elect.get(0).setDone(result);
                    session.update(elect.get(0));
                    return true;
        });
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
            result = this.template(session -> session.createQuery("from Item", Item.class).list());
        } else {
            result = this.template(session -> session.createQuery("from Item where done = false", Item.class).list());
        }
        return result;
    }

    /**
     * Метод формирует общий шаблон для редактирования БД
     * @param command - данные созданной сессии с получением на выходе отредактированную таблицу или успешность операции
     * @return - отредактированная таблица или успешность операции (в зависимости от контекста)
     */

    private <T> T template(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }
}

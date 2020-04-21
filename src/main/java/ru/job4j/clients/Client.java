package ru.job4j.clients;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 21.04.2020
 */

public class Client {
    private int id;
    private String name;
    private Calendar expired;
    private static final DateFormat DATE_FORMAT_DASH_SEPARATED = new SimpleDateFormat("yyyy-MM-dd");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpired() {
        return expired;
    }

    public void setExpired(Calendar expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", expired=" + DATE_FORMAT_DASH_SEPARATED.format(expired.getTime())
                + '}';
    }
}

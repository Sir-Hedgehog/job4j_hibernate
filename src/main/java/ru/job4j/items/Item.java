package ru.job4j.items;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.04.2020
 */

public class Item {
    private int id;
    private String description;
    private String created;
    private boolean done;

    public Item() {
        Date date = new Date();
        this.created = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

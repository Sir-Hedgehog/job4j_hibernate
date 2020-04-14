package ru.job4j.items;

import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.04.2020
 */

public interface Store {
    void addItem(String description);
    void updateItem(String id, String done);
    List<Item> getItems(String regulator);
}

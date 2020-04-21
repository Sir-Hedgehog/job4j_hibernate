package ru.job4j.automobiles;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 21.04.2020
 */

@EqualsAndHashCode(exclude = "owners")
@ToString(exclude = "owners")
public class Car {
    private int id;
    private String model;
    private String yearOfRelease;
    private String bodyType;
    private Engine engine;
    private Set<Driver> owners = new HashSet<>(0);

    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    public Car(Engine engine) {
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getOwners() {
        return owners;
    }

    public void setOwners(Set<Driver> owners) {
        this.owners = owners;
    }

    public void addOwner(Driver driver) {
        this.owners.add(driver);
    }
}

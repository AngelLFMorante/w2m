package com.w2m.app.domino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

/**
 * Represents a spacecraft entity with details such as name, type, and origin.
 * This class is mapped to the database table for storing spacecraft information.
 *
 * @author Angel Lf Morante
 * @version 1.0
 */
@Entity
public class Spacecraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String origin;

    /**
     * Constructs a new Spacecraft with the specified details.
     *
     * @param id the unique identifier of the spacecraft
     * @param name the name of the spacecraft
     * @param type the type of the spacecraft (e.g., Fighter, Transport)
     * @param origin the origin of the spacecraft (e.g., Earth, Outer Space)
     */
    public Spacecraft(Long id, String name, String type, String origin) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.origin = origin;
    }

    /**
     * Default constructor for creating a new Spacecraft without initial values.
     */
    public Spacecraft() {
    }

    /**
     * Gets the unique identifier of the spacecraft.
     *
     * @return the unique id of the spacecraft
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the spacecraft.
     *
     * @param id the new unique identifier for the spacecraft
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the spacecraft.
     *
     * @return the name of the spacecraft
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the spacecraft.
     *
     * @param name the new name of the spacecraft
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the spacecraft.
     *
     * @return the type of the spacecraft
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the spacecraft.
     *
     * @param type the new type of the spacecraft
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the origin of the spacecraft.
     *
     * @return the origin of the spacecraft
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of the spacecraft.
     *
     * @param origin the new origin of the spacecraft
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Compares this spacecraft to the specified object. The result is true if the
     * object is a spacecraft with the same id, name, type, and origin.
     *
     * @param o the object to compare to
     * @return true if this spacecraft is equal to the specified object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spacecraft that = (Spacecraft) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(origin, that.origin);
    }

    /**
     * Returns a hash code value for the spacecraft, based on its id, name, type, and origin.
     *
     * @return a hash code value for the spacecraft
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, origin);
    }

    /**
     * Returns a string representation of the spacecraft, including its id, name, type, and origin.
     *
     * @return a string representation of the spacecraft
     */
    @Override
    public String toString() {
        return "Spacecraft{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}

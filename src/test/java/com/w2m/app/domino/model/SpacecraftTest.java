package com.w2m.app.domino.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpacecraftTest {

    @Test
    void testConstructorAndGetters() {
        Spacecraft spacecraft = new Spacecraft(1L, "Enterprise", "Fighter", "Earth");

        assertEquals(1L, spacecraft.getId());
        assertEquals("Enterprise", spacecraft.getName());
        assertEquals("Fighter", spacecraft.getType());
        assertEquals("Earth", spacecraft.getOrigin());
    }

    @Test
    void testSetters() {
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setId(2L);
        spacecraft.setName("Voyager");
        spacecraft.setType("Transport");
        spacecraft.setOrigin("Outer Space");

        assertEquals(2L, spacecraft.getId());
        assertEquals("Voyager", spacecraft.getName());
        assertEquals("Transport", spacecraft.getType());
        assertEquals("Outer Space", spacecraft.getOrigin());
    }

    @Test
    void testEqualsAndHashCode() {
        Spacecraft spacecraft1 = new Spacecraft(1L, "Discovery", "Explorer", "Earth");
        Spacecraft spacecraft2 = new Spacecraft(1L, "Discovery", "Explorer", "Earth");
        Spacecraft spacecraft3 = new Spacecraft(2L, "Galactica", "Battleship", "Mars");

        assertEquals(spacecraft1, spacecraft2);
        assertNotEquals(spacecraft1, spacecraft3);

        assertEquals(spacecraft1.hashCode(), spacecraft2.hashCode());
        assertNotEquals(spacecraft1.hashCode(), spacecraft3.hashCode());
    }

    @Test
    void testToString() {
        Spacecraft spacecraft = new Spacecraft(3L, "Nostromo", "Cargo", "Outer Space");
        String expected = "Spacecraft{id=3, name='Nostromo', type='Cargo', origin='Outer Space'}";
        assertEquals(expected, spacecraft.toString());
    }
}

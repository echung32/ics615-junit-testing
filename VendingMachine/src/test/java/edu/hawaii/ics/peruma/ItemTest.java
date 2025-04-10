package edu.hawaii.ics.peruma;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Ethan Chung
public class ItemTest {
    Item item;

    @Before
    public void setUp() {
        item = new Item("Apple Juice", 2.25, 10, new Location(11, 11));
    }

    @Test
    public void getName() {
        assertEquals("item name is apple juice", "Apple Juice", item.getName());
    }

    @Test
    public void getLocation() {
        assertEquals("item location is (11,11)", new Location(11, 11), item.getLocation());
    }

    @Test
    public void getPrice() {
        assertEquals("item price is 2.25", 2.25, item.getPrice(), 0);
    }

    @Test
    public void getQuantity() {
        assertEquals("item quantity is 10", 10, item.getQuantity());
    }

    @Test
    public void setQuantity() {
        item.setQuantity(20);
        assertEquals("item quantity is 20", 20, item.getQuantity(), 0);
    }
}
//*******************************************************************
//  __description__ = "Assignment 01 - Unit Testing"
//  __course__ = "ics615"
//  __organization__ = "Information and Computer Sciences Department, University of Hawai‘i at Mānoa"
//  __author__ = "Anthony Peruma"
//  __email__ = "peruma@hawaii.edu"
//  __web__ = "https://www.peruma.me"
//  __version__ = "1.0"
//  __created__ = "2022-08-01"
//  __modified__ = "2023-03-01"
//  __maintainer__ = "Anthony Peruma"
//*******************************************************************
package edu.hawaii.ics.peruma;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

// Ethan Chung
public class VendingMachineTest {

    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.addItem(new Item("Soda", 1.25, 10, new Location(1, 1)));
        vendingMachine.addItem(new Item("Chips", 1.50, 5, new Location(1, 2)));
    }

    @Test
    public void getItems() {
        List<Item> items = vendingMachine.getItems();
        assertEquals("Item size is correct", 2, items.size());
    }

    @Test
    public void addItem_Successful() {
        AddStatus addStatus = vendingMachine.addItem(new Item("Apple Juice", 2.25, 10, new Location(3, 1)));
        assertTrue("Could not add item", addStatus.isSuccess());
    }

    @Test
    public void addItem_InsufficientSpace() {
        // case where quantity > 50, new item.
        AddStatus addStatus = vendingMachine.addItem(new Item("Apple Juice", 2.25, 50, new Location(3, 1)));
        assertFalse("Not enough space to add item", addStatus.isSuccess());

        // case with existing item, quantity = 5, add 3 more.
        AddStatus addStatus2 = vendingMachine.addItem(new Item("Chips", 1.50, 3, new Location(1, 2)));
        assertTrue("Not enough space to add item", addStatus2.isSuccess());

        // case with existing item, quantity = 10, add 10 more.
        AddStatus addStatus3 = vendingMachine.addItem(new Item("Soda", 1.25, 10, new Location(1, 1)));
        assertFalse("Not enough space to add item", addStatus3.isSuccess());
    }

    @Test
    public void addItem_InvalidLocation() {
        // case where row > 10
        AddStatus addStatus1 = vendingMachine.addItem(new Item("Apple Juice", 2.25, 10, new Location(11, 5)));
        assertFalse("Not valid location to add item", addStatus1.isSuccess());

        // case where col > 10
        AddStatus addStatus2 = vendingMachine.addItem(new Item("Apple Juice", 2.25, 10, new Location(5, 11)));
        assertFalse("Not valid location to add item", addStatus2.isSuccess());

        // case where row > 10 and col > 10
        AddStatus addStatus3 = vendingMachine.addItem(new Item("Apple Juice", 2.25, 10, new Location(11, 11)));
        assertFalse("Not valid location to add item", addStatus3.isSuccess());
    }

    @Test
    public void vendItem_Successful() {
        VendingStatus vendingStatus = vendingMachine.vendItem(new Location(1, 1), 1.25);
        assertTrue("Could vend item", vendingStatus.isSuccess());
    }

    @Test
    public void vendItem_InsufficientFunds() {
        VendingStatus vendingStatus = vendingMachine.vendItem(new Location(1, 1), 1);
        assertFalse("Could vend item", vendingStatus.isSuccess());
    }

    @Test
    public void vendItem_OutOfStock() {
        vendingMachine.addItem(new Item("Apple Juice", 2.25, 0, new Location(3, 1)));

        VendingStatus vendingStatus = vendingMachine.vendItem(new Location(3, 1), 2.25);
        assertFalse("Could not vend item", vendingStatus.isSuccess());
    }

    @Test
    public void vendItem_UnknownLocation() {
        VendingStatus vendingStatus = vendingMachine.vendItem(new Location(3, 1), 2.25);
        assertFalse("Could not vend item", vendingStatus.isSuccess());
        assertEquals("Should have no change", 0, vendingStatus.getChange(), 0.0001f);
        assertEquals("Should be unknown location", VendingStatus.VendingMessage.UNKNOWN_LOCATION, vendingStatus.getMessage());
    }
}

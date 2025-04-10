package edu.hawaii.ics.peruma;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Ethan Chung
public class LocationTest {

    Location location;

    @Before
    public void setUp() {
        location = new Location(5, 6);
    }

    @Test
    public void getRow() {
        assertEquals(5, location.getRow());
    }

    @Test
    public void getColumn() {
        assertEquals(6, location.getColumn());
    }

    @Test
    public void testEquals() {
        Location sameLocation = new Location(5, 6);
        assertEquals("Locations are exact same", location, sameLocation);

        Location diffRow = new Location(4, 6);
        assertNotEquals("Compared location has different row", diffRow, location);

        Location diffCol = new Location(5, 5);
        assertNotEquals("Compared location has different col", diffCol, location);

        Location diffLoc = new Location(1, 1);
        assertNotEquals("Compared location has different location", diffLoc, location);

        assertFalse("Compared location is null", location.equals(null));

        String diffClass = new String("1");
        assertFalse("Compared location is a different class", location.equals(diffClass));
    }
}
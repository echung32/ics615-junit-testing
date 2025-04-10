package edu.hawaii.ics.peruma;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Ethan Chung
public class AddStatusTest {

    AddStatus addStatus;

    @Before
    public void setUp() {
        addStatus = new AddStatus(true, AddStatus.AddMessage.SUCCESS);
    }

    @Test
    public void isSuccess() {
        assertTrue("add status is success", addStatus.isSuccess());
    }

    @Test
    public void getAddMessage() {
        assertEquals("get message is success", AddStatus.AddMessage.SUCCESS, addStatus.getAddMessage());
    }
}
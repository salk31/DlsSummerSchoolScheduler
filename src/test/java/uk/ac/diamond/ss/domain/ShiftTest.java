/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

public class ShiftTest {

  /*  @Test
    public void testOverlap() {
        Facility fac = Facility.getOrCreate("test",100);
        Shift sh1 = new Shift(fac,0);
        sh1.setStartTime(2);
        sh1.setEndTime(4);

        Shift sh2 = new Shift(fac,1);
        sh2.setStartTime(4);
        sh2.setEndTime(6);

        assertFalse(sh1.checkOverlap(sh2));
        assertFalse(sh2.checkOverlap(sh1));
        assertTrue(sh1.checkOverlap(sh1));

        Shift sh3 = new Shift(fac,2);
        sh3.setStartTime(2);
        sh3.setEndTime(3);

        assertTrue(sh1.checkOverlap(sh3));
        assertTrue(sh3.checkOverlap(sh1));
        assertFalse(sh3.checkOverlap(sh2));

        Shift sh4 = new Shift(fac,3);
        sh4.setStartTime(2);
        sh4.setEndTime(5);

        assertTrue(sh1.checkOverlap(sh4));
        assertTrue(sh4.checkOverlap(sh1));
    }*/

}

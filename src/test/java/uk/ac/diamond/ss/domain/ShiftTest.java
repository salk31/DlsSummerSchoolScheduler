/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ShiftTest {

	@Test
	public void testGetSimilar() {
		Facility fac1 = Facility.getOrCreate("test1", 101);
		Facility fac2 = Facility.getOrCreate("test2", 102);

		Shift sh1 = new Shift(fac1, 0);
		Shift sh11 = new Shift(fac1, 1);

		Shift sh2 = new Shift(fac2, 0);
		Shift sh21 = new Shift(fac2, 1);
		sh2.setPair(sh21);
		sh21.setPair(sh2);

		assertTrue(sh1.getSimilar(sh11));
		assertFalse(sh2.getSimilar(sh21));
		assertFalse(sh1.getSimilar(sh2));
	}

	@Test
	public void testGetCorrelated() {
		Facility fac1 = Facility.getOrCreate("test1", 101);
		Facility fac2 = Facility.getOrCreate("test2", 102);
		Set<Facility> sf = new HashSet<Facility>();
		sf.add(fac1);
		sf.add(fac2);
		int rate = 2;
		Correlation c1 = new Correlation(sf, rate);
		fac1.addCorrelation(c1);
		fac2.addCorrelation(c1);

		Shift sh1 = new Shift(fac1, 0);
		Shift sh11 = new Shift(fac1, 1);
		Shift sh2 = new Shift(fac2, 0);

		assertEquals(sh1.getCorrelated(sh2), 2);
		assertEquals(sh2.getCorrelated(sh1), 2);
		assertEquals(sh1.getCorrelated(sh1), 0);
		assertEquals(sh11.getCorrelated(sh1), 0);
	}

	@Test
	public void testOverlap() {
		Facility fac = Facility.getOrCreate("test", 100);
		Shift sh1 = new Shift(fac, 0);
		sh1.setStartTime(2);
		sh1.setEndTime(4);

		Shift sh2 = new Shift(fac, 1);
		sh2.setStartTime(4);
		sh2.setEndTime(6);

		assertFalse(sh1.checkOverlap(sh2));
		assertFalse(sh2.checkOverlap(sh1));
		assertTrue(sh1.checkOverlap(sh1));

		Shift sh3 = new Shift(fac, 2);
		sh3.setStartTime(2);
		sh3.setEndTime(3);

		assertTrue(sh1.checkOverlap(sh3));
		assertTrue(sh3.checkOverlap(sh1));
		assertFalse(sh3.checkOverlap(sh2));

		Shift sh4 = new Shift(fac, 3);
		sh4.setStartTime(2);
		sh4.setEndTime(5);

		assertTrue(sh1.checkOverlap(sh4));
		assertTrue(sh4.checkOverlap(sh1));
	}

}

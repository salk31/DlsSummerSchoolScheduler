package uk.ac.diamond.ss.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FacilityTest {

	@Test
	public void addCorrelationTest() {
		Set<Facility> sf = new HashSet<Facility>();
		Facility frac1 = Facility.getOrCreate("I11", 101);
		Facility frac2 = Facility.getOrCreate("I06", 102);
		sf.add(frac1);
		sf.add(frac2);

		Correlation c = new Correlation(sf, 3);
		frac1.addCorrelation(c);
		assertEquals(frac1.getCorrelations().size(), 1);
		Set<Facility> sf1 = new HashSet<Facility>();
		sf1.add(frac2);
		sf1.add(frac1);

		Correlation c1 = new Correlation(sf1, 3);
		frac1.addCorrelation(c1);
		assertEquals(frac1.getCorrelations().size(), 1);// c1==c2 so there is
														// still 1 correlation

		Correlation c2 = new Correlation(sf1, 5);
		frac1.addCorrelation(c2);
		assertEquals(frac1.getCorrelations().size(), 2);
		// different correlation rate means the two correlations are not the
		// same..hmmm
	}

	public void checkCorrelationTest() {
		Set<Facility> sf = new HashSet<Facility>();
		Facility frac1 = Facility.getOrCreate("I11", 101);
		Facility frac2 = Facility.getOrCreate("I06", 102);
		Facility frac3 = Facility.getOrCreate("I05", 103);
		sf.add(frac1);
		sf.add(frac2);

		Correlation c = new Correlation(sf, 3);
		frac1.addCorrelation(c);
		assertEquals(frac1.checkCorrelations(frac2), 3);
		assertEquals(frac1.checkCorrelations(frac3), 0);
	}

}

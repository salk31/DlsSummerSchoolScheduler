package uk.ac.diamond.ss.domain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import uk.ac.diamond.ss.domain.in.KeyValuesReader;
import uk.ac.diamond.ss.domain.in.WeightsReader;

public class PersonTest {
	private Map<Facility, Integer> preferences = new HashMap<Facility, Integer>();
	Shift sh, sh1, sh2;
	Person p;

	@Test
	public void checkPreferenceTest() {
		createPreferences();
		p = new Person("Aureliusz");
		p.setPereferences(preferences);
		assertEquals(p.checkPreference(sh), 3);
		assertEquals(p.checkPreference(sh1), 0);
		Shift sh2 = new Shift(null, 3);
		assertEquals(p.checkPreference(sh2), 0);
	}

	public void isPreferenceTest() {
		assertFalse(p.isPreference(sh1));
		assertTrue(p.isPreference(sh));
	}

	public void isFirstPreference() {
		assertTrue(p.isFirstPreference(sh2));
		assertFalse(p.isFirstPreference(sh));
	}

	public void sumPreferenceTest() {
		assertEquals(p.getSumPreference(), 20);
	}

	public void preferenceIncludeTest() {
		assertFalse(p.preferencesInclude(0));
		assertFalse(p.preferencesInclude(1));
		assertTrue(p.preferencesInclude(2));
	}

	public void mapPreferencesTest() {
		assertEquals(p.mapPreference(0), 0);
		assertEquals(p.mapPreference(KeyValuesReader.MAX_PREFERENCES),
				KeyValuesReader.MAX_PREFERENCES + 1);
		assertEquals(p.mapPreference(10),
				KeyValuesReader.MAX_PREFERENCES + 1 - 10);
		assertEquals(p.mapPreference(4), WeightsReader.PREFERENCE2 * 2);
	}

	private void createPreferences() {
		Facility frac1 = Facility.getOrCreate("I11", 101);
		Facility frac2 = Facility.getOrCreate("I06", 102);
		Facility frac3 = Facility.getOrCreate("I07", 103);
		Facility frac4 = Facility.getOrCreate("I08", 104);
		preferences.put(frac1, 3);
		preferences.put(frac2, 2);
		preferences.put(frac3, 5);
		sh = new Shift(frac1, 0, 0, 1);
		sh1 = new Shift(frac4, 1, 0, 1);
		sh2 = new Shift(frac3, 2, 0, 1);
	}

}

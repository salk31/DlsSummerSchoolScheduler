/*
 * Diamond User Administration System
 * Copyright � 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.HashMap;
import java.util.Map;

import uk.ac.diamond.ss.domain.in.KeyValuesReader;
import uk.ac.diamond.ss.domain.in.WeightsReader;

public class Person {

	private String name;
	private int ID;
	private Map<Facility, Integer> preferences = new HashMap<Facility, Integer>();

	public Person() {
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Person) {
			Person that = (Person) other;
			result = (this.getID() == that.getID());
		}
		return result;
	}

	@Override
	public int hashCode() {
		return ID;
	}

	public Person(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int anID) {
		this.ID = anID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Map<Facility, Integer> getPreferences() {
		return preferences;
	}

	public void setPereferences(Map<Facility, Integer> pref) {
		preferences = pref;
	}

	public int checkPreference(Shift s) {
		Facility k = s.getFacility();
		if (preferences.containsKey(k)) {
			int pref = preferences.get(k);
			return pref;
		}
		return 0;
	}

	public boolean isPreference(Shift s) {
		return (checkPreference(s) > 0 ? true: false);	
	}

	public boolean isFirstPreference(Shift s) {
		return checkPreference(s)  == KeyValuesReader.MAX_PREFERENCES;
	}

	public int getSumPreference() {
		int sum = 0;
		for (int i : preferences.values()) {
			sum = sum + i;
		}
		return 2 * sum;// 2 is needed because of the long experiments
	}

	public boolean preferencesInclude(int num) {
		for (int i : preferences.values()) {
			if (i == num) {
				return true;
			}
		}
		return false;
	}

	public static int mapPreference(int pref) {
		// invert
		int mappedPreference = 0;
		if (pref != 0) {
			mappedPreference = 1 + KeyValuesReader.MAX_PREFERENCES - pref;
		}
		// scale
		if (mappedPreference == KeyValuesReader.MAX_PREFERENCES) {
			mappedPreference = WeightsReader.PREFERENCE1 * mappedPreference;
		}
		if (mappedPreference == KeyValuesReader.MAX_PREFERENCES - 1) {
			mappedPreference = WeightsReader.PREFERENCE2 * mappedPreference;
		}
		if (mappedPreference == KeyValuesReader.MAX_PREFERENCES - 2) {
			mappedPreference = WeightsReader.PREFERENCE3 * mappedPreference;
		}
		if (mappedPreference == KeyValuesReader.MAX_PREFERENCES - 3) {
			mappedPreference = WeightsReader.PREFERENCE4 * mappedPreference;
		}
		if (mappedPreference == KeyValuesReader.MAX_PREFERENCES - 4) {
			mappedPreference = WeightsReader.PREFERENCE5 * mappedPreference;
		}
		return mappedPreference;
	}

}

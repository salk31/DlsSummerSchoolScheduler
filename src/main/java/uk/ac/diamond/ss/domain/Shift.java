/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import uk.ac.diamond.ss.domain.in.KeyValuesReader;

public class Shift {

	private final int ID;
	private int sTime;
	private int eTime;
	private final Facility facility;
	private Shift pair = null;// long experiments

	public Shift(Facility fa, int count) {
		this.facility = fa;
		this.ID = count;
	}

	public Shift(Facility fa, int count, int st, int end) {// used in tests
		this.facility = fa;
		this.ID = count;
		this.sTime = st;
		this.eTime = end;
	}

	public int getStudentsPerShift() {
		return KeyValuesReader.STUDENTS_PER_SHIFT;
	}

	public int getID() {
		return ID;
	}

	public int getStartTime() {
		return sTime;
	}

	public void setStartTime(int startTime) {
		sTime = startTime;
	}

	public boolean getSimilar(Shift s) {
		if (s.getFacility().getName().equals(facility.getName())) {
			if (pair != null && s == pair) {
				return false;
			}
			return true;
		}
		return false;
	}

	public int getCorrelated(Shift s) {
		int corrCheck = facility.checkCorrelations(s.getFacility());
		if (corrCheck != 0) {
			return corrCheck;
		}
		return 0;
	}

	public Shift getPair() {
		return pair;
	}

	public void setPair(Shift sh) {
		pair = sh;
	}

	public boolean checkOverlap(Shift s) {
		if ((s.getStartTime() >= eTime || s.getEndTime() <= sTime)) {
			return false;
		}
		return true;
	}

	public int getEndTime() {
		return eTime;
	}

	public void setEndTime(int endTime) {
		eTime = endTime;
	}

	public Facility getFacility() {
		return facility;
	}

	@Override
	public String toString() {
		return facility.getName() + " " + sTime;
	}
}

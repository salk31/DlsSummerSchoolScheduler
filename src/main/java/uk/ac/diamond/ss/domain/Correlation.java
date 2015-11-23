/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.Set;

public class Correlation {

	private Set<Facility> f;
	private int rate;

	public Correlation(Set<Facility> a, int cor) {
		f = a;
		rate = cor;
	}

	public Set<Facility> getSetFacility() {
		return f;
	}

	public int getRate() {
		return rate;
	}

	public void setSetFacility(Set<Facility> af) {
		f = af;
	}

	@Override
	public boolean equals(Object o) {
		Correlation f1 = (Correlation) o;
		return (f1.getRate() == rate && f1.getSetFacility().containsAll(f));
	}

}

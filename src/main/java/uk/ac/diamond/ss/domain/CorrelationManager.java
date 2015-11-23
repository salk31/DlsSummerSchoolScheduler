/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.List;

public class CorrelationManager {

	public CorrelationManager() {
	}

	public void assign(List<Correlation> corList) {
		for (Facility f : Facility.returnAll().values()) {
			for (Correlation c : corList) {
				if (c.getSetFacility().contains(f)) {
					f.addCorrelation(c);
				}
			}
		}
	}
}

/*
 * Diamond User Administration System
 * Copyright © 2014 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

/**
 * Wrapper around optiplanner.
 */

public class PlannerEngine {

	private final Solver solver;

	public PlannerEngine() {
		// System.setProperty("drools.dialect.java.compiler", "JANINO");

		SolverFactory solverFactory = SolverFactory
				.createFromXmlResource("solverConfig.xml");

		solver = solverFactory.buildSolver();
	}

	public Solver getSolver() {
		return solver;
	}
}

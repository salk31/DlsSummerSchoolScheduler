/*
 * Diamond User Administration System
 * Copyright © 2014 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.termination.TerminationCompositionStyle;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

import uk.ac.diamond.ss.domain.in.KeyValuesReader;

/**
 * Wrapper around optiplanner.
 */

public class PlannerEngine {

	private final Solver solver;

	public PlannerEngine() {
		// System.setProperty("drools.dialect.java.compiler", "JANINO");

		SolverFactory solverFactory = SolverFactory
				.createFromXmlResource("solverConfig.xml");
	        
	    solverFactory.getSolverConfig().setTerminationConfig(setTermination());

		solver = solverFactory.buildSolver();
	}
	
	private TerminationConfig setTermination(){
		//termiantion settings
		 TerminationConfig terminationConfig = new TerminationConfig();
	     terminationConfig.setTerminationCompositionStyle(TerminationCompositionStyle.OR);
		 terminationConfig.setMinutesSpentLimit(KeyValuesReader.TERMINATION_TOTAL_TIME);
	     terminationConfig.setUnimprovedMinutesSpentLimit(KeyValuesReader.TERMINATION_TIME_UNIMPROVED);
	     return terminationConfig;
	}

	public Solver getSolver() {
		return solver;
	}
}

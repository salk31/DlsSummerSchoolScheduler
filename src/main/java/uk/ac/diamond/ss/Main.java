package uk.ac.diamond.ss;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.optaplanner.core.api.solver.Solver;

import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.PersonReader;
import uk.ac.diamond.ss.domain.ShiftReader;

public class Main {

    public static void main(String[] args) throws Exception {
        String filename = "problem.xlsx";
        if (args.length > 0) {
            filename = args[0];
        }

        InputStream inputStream = new FileInputStream(filename);
        Workbook wb = WorkbookFactory.create(inputStream);
        PersonReader pr = new PersonReader(wb.getSheet("candidate_preferences"));

        ShiftReader sr = new ShiftReader();

        PlannerEngine pe = new PlannerEngine();
        Solver solver = pe.getSolver();


        PlannerSolution prob = new PlannerSolution();
        prob.setPeople(pr.read());
        prob.setShifts(sr.load());
        prob.setAllocations();
        System.out.println("Solver start!");
        solver.solve(prob);

        PlannerSolution ps = (PlannerSolution) solver.getBestSolution();
        for (Allocation p : ps.getAllocations()) {
            System.out.println("Person: " + p.getPerson() + " shift: " + p.getShift().getID());
        }
    }

}

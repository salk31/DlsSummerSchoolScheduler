package uk.ac.diamond.ss;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.optaplanner.core.api.solver.Solver;

import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.readers.PersonReader;
import uk.ac.diamond.ss.domain.readers.ShiftReader;

public class Main {

    public static void main(String[] args) throws Exception {
        String filename = "problem1.xlsx";//problem
        if (args.length > 0) {
            filename = args[0];
        }

        InputStream inputStream = new FileInputStream(filename);
        Workbook wb = WorkbookFactory.create(inputStream);
        PersonReader pr = new PersonReader(wb.getSheet("candidate_preferences"));

        ShiftReader sr = new ShiftReader(wb.getSheet("periods"));

        PlannerEngine pe = new PlannerEngine();
        Solver solver = pe.getSolver();


        PlannerSolution prob = new PlannerSolution();
        prob.setPeople(pr.read());
        prob.setShifts(sr.read());
        sr.read();
        prob.setAllocations();

        System.out.println("Solver started!");
        solver.solve(prob);

        PlannerSolution ps = (PlannerSolution) solver.getBestSolution();

        Sheet solutionSheet = null;
        if(wb.getSheet("solution")==null){
            solutionSheet = wb.createSheet("solution");
        }
        solutionSheet = wb.getSheet("solution");
        new SolutionWriter(solutionSheet).write(ps);
        wb.write(new FileOutputStream(filename));

        for (Allocation p : ps.getAllocations()) {
            System.out.println("Person: " + p.getPerson() + ", shift: " + p.getShift().getID());
        }
    }

}

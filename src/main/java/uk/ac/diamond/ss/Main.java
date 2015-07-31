package uk.ac.diamond.ss;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.optaplanner.core.api.solver.Solver;

import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.PersonReader;

public class Main {

    public static void main(String[] args) throws Exception {
        String filename = "problem.xlsx";
        if (args.length > 0) {
            filename = args[0];
        }

        InputStream inputStream = new FileInputStream(filename);
        Workbook wb = WorkbookFactory.create(inputStream);
        PersonReader pr = new PersonReader(wb.getSheet("candidate_preferences"));

        PlannerEngine pe = new PlannerEngine();
        Solver solver = pe.getSolver();


        PlannerSolution prob = new PlannerSolution();
        prob.setPeople(pr.read());

        solver.solve(prob);

        PlannerSolution ps = (PlannerSolution) solver.getBestSolution();
        for (Person p : ps.getPeople()) {
            System.out.println("X=" + p);
        }
    }

}

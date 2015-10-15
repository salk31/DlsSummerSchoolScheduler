package uk.ac.diamond.ss;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.optaplanner.core.api.solver.Solver;

import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.Shift;
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
        java.util.List<Person> people = pr.read();
        prob.setPeople(people);
        java.util.List<Shift> shifts = sr.read();
        prob.setShifts(shifts);
        prob.setAllocations();

        System.out.println("Solver started!");
        solver.solve(prob);

        PlannerSolution ps = (PlannerSolution) solver.getBestSolution();

        Sheet solutionSheet = null;

        if (wb.getSheet("solution") == null){
            solutionSheet = wb.createSheet("solution");
        }
        solutionSheet = wb.getSheet("solution");

        Sheet summarySheet = null;
        if (wb.getSheet("summary") == null){
            summarySheet = wb.createSheet("summary");
        }
        summarySheet = wb.getSheet("summary");

        CellStyle style = wb.createCellStyle();

        Font font = wb.createFont();
        font.setColor(IndexedColors.GREEN.getIndex());
        style.setFont(font);

        new SolutionWriter(solutionSheet,people).write(ps,style);

        new SummaryWriter(summarySheet, people).writeAvg(ps);
        wb.write(new FileOutputStream(filename));



        for (Allocation p : ps.getAllocations()) {
            System.out.println("Person: " + p.getPerson() + ", shift: " + p.getShift().getID());
        }

        new ScorePrinter(solver).print(ps);
    }
}

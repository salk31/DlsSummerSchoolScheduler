package uk.ac.diamond.ss;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.domain.Allocation;



public class SolutionWriter {
    private Sheet sheet;

    public SolutionWriter(Sheet sheet) {
        this.sheet = sheet;
    }

    public void write(PlannerSolution solution) {
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Shift ID");
        headerRow.createCell(2).setCellValue("Facility");
        headerRow.createCell(3).setCellValue("Start");
        headerRow.createCell(4).setCellValue("End");

        List<Allocation> allocations = solution.getAllocations();

        for (int i = 0; i < allocations.size(); i++) {
            Row row = sheet.createRow(1 + i);
            Allocation allocation = allocations.get(i);
            row.createCell(0).setCellValue(allocation.getPerson().getName());
            row.createCell(1).setCellValue(allocation.getShift().getID());
            //TODO add facility name after the test are written
           // Facility f =allocation.getShift().getFacility();
            //String name = f.getName();
            row.createCell(2).setCellValue(allocation.getShift().getFacility().getName());
            row.createCell(3).setCellValue(allocation.getShift().getStartTime());
            row.createCell(4).setCellValue(allocation.getShift().getEndTime());
        }
    }
}

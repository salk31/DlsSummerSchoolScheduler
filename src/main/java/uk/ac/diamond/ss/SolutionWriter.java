package uk.ac.diamond.ss;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.domain.Person;

public class SolutionWriter {
    private Sheet sheet;

    public SolutionWriter(Sheet sheet) {
        this.sheet = sheet;
    }

    public void write(PlannerSolution solution) {
        Row headerRow = sheet.createRow(0);

        List<Person> people = solution.getPeople();
        for (int i = 0; i < people.size(); i++) {
            Row row = sheet.createRow(1 + i);
            Person person = people.get(i);
            row.createCell(0).setCellValue(person.getName());
        }
    }
}

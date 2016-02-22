package uk.ac.diamond.ss.domain.out;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.PlannerSolution;
import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.in.KeyValuesReader;

public class SolutionWriter {
	private Sheet sheet;
	private List<Person> people;
	private List<Allocation> allocations = new ArrayList<Allocation>();

	public SolutionWriter(Sheet sheet, List<Person> people) {
		this.people = people;
		this.sheet = sheet;
	}

	public SolutionWriter() {// tests only
	}

	public void setAllocations(List<Allocation> alo) {
		allocations = alo;
	}

	public void write(PlannerSolution solution, CellStyle style) {

		allocations = solution.getAllocations();
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Person");
		for(int j = 1; j <= KeyValuesReader.SHIFTS_PER_STUDENT; j++){
		    headerRow.createCell(j).setCellValue("Period"+ Integer.toString(j));
		}

		List<Allocation> allocations = solution.getAllocations();
		int i = 1;

		for (Person p : people) {
			Row row = sheet.createRow(i);

			Cell c1 = row.createCell(0);
			c1.setCellValue(p.getName());

			for(int j = 1; j <= KeyValuesReader.SHIFTS_PER_STUDENT; j++){
			    Cell a0 = row.createCell(j);
	            a0.setCellValue(findPeriod(p, j-1));
	            if (checkPreference(p, j-1)) {
	                a0.setCellStyle(style);
	            }
	        }
			i = i + 1;
		}
	}

	public String findPeriod(Person p, int num) {
		for (Allocation a : allocations) {
			if (a.getPerson().getID() == p.getID()
					&& a.getShift().getStartTime() == num
							* KeyValuesReader.SHIFTS_LENGHT) {
				return a.getShift().getFacility().getName();
			}
		}
		return null;
	}

	public boolean checkPreference(Person p, int num) {
		for (Allocation a : allocations) {
			if (a.getPerson().getID() == p.getID()
					&& a.getShift().getStartTime() == num
							* KeyValuesReader.SHIFTS_LENGHT
					&& p.isPreference(a.getShift())) {
				return true;
			}
		}
		return false;
	}

}

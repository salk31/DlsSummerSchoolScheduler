/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.out;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.PlannerSolution;
import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.Shift;
import uk.ac.diamond.ss.domain.in.KeyValuesReader;

public class SummaryWriter {
	private Sheet sheet;
	private List<Person> people;
	private List<Shift> shifts;

	public SummaryWriter(Sheet sheet, List<Person> sr, List<Shift> shs) {
		this.people = sr;
		this.sheet = sheet;
		this.shifts = shs;
	}

	public void writeAvg(PlannerSolution solution) {

	    Row headerRow = sheet.createRow(0);
		headerRow.createCell(1).setCellValue(
				"Percentage of people doing that preference");
		headerRow.createCell(0).setCellValue("Choice");

		for(int i = 1; i <= KeyValuesReader.MAX_PREFERENCES; i++){
		    Row row = sheet.createRow(i);
	        Cell c1 = row.createCell(0);
	        Cell c11 = row.createCell(1);
	        c1.setCellValue("choice "+ i);
	        double percentage = percentage(solution, i);
	        if (percentage != -1) {
	            c11.setCellValue(percentage);
	        }
		}
		Row headerRow1 = sheet.createRow(KeyValuesReader.MAX_PREFERENCES + 1);
		headerRow1.createCell(0).setCellValue("Size of Group");
		headerRow1.createCell(1).setCellValue("How Many Groups");
		// calculates size of group for each shift
		Map<Integer, Integer> shift_size = groupSize(solution);
		// how many shifts of a particular size there ware
		nuberOfShiftsOfsize(shift_size);
	}

	/**
	 *
	 * @param shift_size
	 */
	private void nuberOfShiftsOfsize(Map<Integer, Integer> shift_size) {
		Collection<Integer> col = shift_size.values();
		int ma = Collections.max(col, null);
		System.out.println(ma);
		int row_num = 0;
		for (int i = 1; i <= ma; i++) {
			int num = 0;
			for (Integer it : col) {
				if (it == i) {
					num++;
				}
			}
			if (num > 0) {
				row_num++;
				Row headerRow1 = sheet.createRow(KeyValuesReader.MAX_PREFERENCES + 1 + row_num);
				headerRow1.createCell(0).setCellValue(i);
				headerRow1.createCell(1).setCellValue(num);
			}
		}
	}

	private Map<Integer, Integer> groupSize(PlannerSolution ps) {
		// maps shift if to size
		Map<Integer, Integer> colect = new HashMap<Integer, Integer>();
		for (Shift sh : shifts) {
			int id = sh.getID();
			int calc = 0;
			for (Allocation p : ps.getAllocations()) {
				if (p.getShift().getID() == id) {
					calc++;
				}
			}
			if (calc > 0) {
				// add only the shifts which are used
				colect.put(id, calc);
			}
		}
		return colect;
	}

	private double percentage(PlannerSolution ps, int num1) {
		int calc = 0;
		int used_num = 0;
		int num = Person.mapPreference(num1);
		for (Person pl : people) {
			for (Allocation p : ps.getAllocations()) {
				if (p.getPerson().getID() == pl.getID() && pl.checkPreference(p.getShift()) == num) {
					calc++;
					break;
				}
			}
			// how many people actually included this preference
			if (pl.preferencesInclude(num)) {
				used_num++;
			}
		}
		if (used_num > 0) {
		    return 100 * ((double) calc / used_num);
		}
		return -1;
	}

}

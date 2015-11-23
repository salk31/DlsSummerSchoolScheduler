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

		Row row = sheet.createRow(1);
		Cell c1 = row.createCell(0);
		Cell c11 = row.createCell(1);
		c1.setCellValue("1 choice");
		c11.setCellValue(percentage(solution, 1));

		Row row1 = sheet.createRow(2);
		Cell c2 = row1.createCell(0);
		Cell c21 = row1.createCell(1);
		c2.setCellValue("2 choice");
		c21.setCellValue(percentage(solution, 2));

		Row row2 = sheet.createRow(3);
		Cell c3 = row2.createCell(0);
		Cell c31 = row2.createCell(1);
		c3.setCellValue("3 choice");
		c31.setCellValue(percentage(solution, 3));

		Row row3 = sheet.createRow(4);
		Cell c4 = row3.createCell(0);
		Cell c41 = row3.createCell(1);
		c4.setCellValue("4 choice");
		c41.setCellValue(percentage(solution, 4));

		Row row4 = sheet.createRow(5);
		Cell c5 = row4.createCell(0);
		Cell c51 = row4.createCell(1);
		c5.setCellValue("5 choice");
		c51.setCellValue(percentage(solution, 5));

		Row headerRow1 = sheet.createRow(6);
		headerRow1.createCell(0).setCellValue("Size");
		headerRow1.createCell(1).setCellValue("How Many Shifts");
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
				Row headerRow1 = sheet.createRow(6 + row_num);
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

	private double percentage(PlannerSolution ps, int num) {
		int calc = 0;
		int used_num = 0;
		int long_flag = 0;
		num = Person.mapPreference(num);
		for (Person pl : people) {
			for (Allocation p : ps.getAllocations()) {
				if (p.getPerson().getName().equals(pl.getName())
						&& pl.checkPreference(p.getShift()) == num) {
					calc++;
					if (p.getShift().getPair() != null) {
						long_flag = 1;// long experiments counted once
					}
				}
			}
			// how many people actually included this preference
			if (pl.preferencesInclude(num)) {
				used_num++;
			}
		}
		// long experiments counted once
		calc = calc - long_flag;
		return 100 * ((double) calc / used_num);
	}

}

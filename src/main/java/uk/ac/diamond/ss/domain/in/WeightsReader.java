/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.in;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class WeightsReader {

    private Sheet sheet;
	public static int FIRST_SECOND = 1;
    public static int GROUP_SIZE_SOFT = 1;
    public static int GROUP_SIZE_HARD = 1;
    public static int NO_OVERLAPPING = 1;
    public static int NO_SIMILOAR = 1;
    public static int CORRELATION = 1;
    public static int LONG_EXPERIMANTS = 1;
    public static int PREFERENCES = 1;
    public static int PREFERENCE1 = 1;
    public static int PREFERENCE2 = 1;
    public static int PREFERENCE3 = 1;
    public static int PREFERENCE4 = 1;
    public static int PREFERENCE5 = 1;

    public WeightsReader(Sheet sheet) {
        this.sheet = sheet;
    }

    public void read() {
    	Row group_soft = sheet.getRow(0);
        if (sheet.getRow(0) != null){
        	Cell cell_group_soft = group_soft.getCell(1);
        		if(cell_group_soft != null) {
        			GROUP_SIZE_SOFT = (int) cell_group_soft.getNumericCellValue();
        		}
        }
        Row group_hard = sheet.getRow(1);
        if(group_hard != null){
        	Cell cell_group_hard = group_hard.getCell(1);
        	if (cell_group_hard != null) {
        		GROUP_SIZE_HARD = (int) cell_group_hard.getNumericCellValue();
        	}
        }
        Row no_overlapping = sheet.getRow(2);
        if(no_overlapping != null){
        	Cell cell_no_overlaping = no_overlapping.getCell(1);
        	if (cell_no_overlaping != null) {
        		NO_OVERLAPPING = (int) cell_no_overlaping.getNumericCellValue();
        	}
        }
        Row no_similar = sheet.getRow(3);
        if(no_similar != null){
        	Cell cell_no_similar = no_similar.getCell(1);
        	if(cell_no_similar != null){
        		NO_SIMILOAR = (int) cell_no_similar.getNumericCellValue();
        	}      
        }
        Row corr = sheet.getRow(4);
        if(corr != null){
        	Cell cell_corr = corr.getCell(1);
        	if(cell_corr != null){
        		CORRELATION = (int) cell_corr.getNumericCellValue();
        	}
        }
        Row long_exp = sheet.getRow(5);
        if (long_exp != null) {
        	Cell cell_long_exp = long_exp.getCell(1);
        	if (cell_long_exp != null){
        		LONG_EXPERIMANTS = (int) cell_long_exp.getNumericCellValue();
        	}
        }
        Row pref = sheet.getRow(6);
        if(pref != null){
        	Cell cell_pref = pref.getCell(1);
        	if(cell_pref != null){
        		PREFERENCES = (int) cell_pref.getNumericCellValue();
        	}
        }
        Row pref1 = sheet.getRow(7);
        if(pref1 != null){
        	Cell cell_pref1 = pref1.getCell(1);
        	if(cell_pref1 != null){
        		PREFERENCE1 = (int) cell_pref1.getNumericCellValue();
        	}
        }
        
        Row pref2 = sheet.getRow(8);
        if(pref2 != null){
        	Cell cell_pref2 = pref2.getCell(1);
        	if(cell_pref2 != null){
        		PREFERENCE2 = (int) cell_pref2.getNumericCellValue();
        	}
        }
        
        Row pref3 = sheet.getRow(9);
        if(pref3 != null){
        	Cell cell_pref3 = pref3.getCell(1);
        	if(cell_pref3 != null){
        		PREFERENCE3 = (int) cell_pref3.getNumericCellValue();
        	}
        }
      
        Row pref4 = sheet.getRow(10);
        if(pref4 != null){
        	Cell cell_pref4 = pref4.getCell(1);
        	if(cell_pref4 != null){
        		PREFERENCE4 = (int) cell_pref4.getNumericCellValue();
        	}
        }
        
        Row pref5 = sheet.getRow(11);
        if(pref5 != null){
        	Cell cell_pref5 = pref5.getCell(1);
        	if(cell_pref5 != null){
        		PREFERENCE5 = (int) cell_pref5.getNumericCellValue();
        	}
        }
        
        Row first_second_corr = sheet.getRow(12);
        if(first_second_corr != null){
        	Cell cell_first_second_corr = first_second_corr.getCell(1);
        	if(cell_first_second_corr != null){
        		FIRST_SECOND  = (int) cell_first_second_corr.getNumericCellValue();
        	}
        }
    }
}

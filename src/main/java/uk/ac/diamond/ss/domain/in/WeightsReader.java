/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.in;

import org.apache.poi.ss.usermodel.Sheet;

public class WeightsReader {

    private Sheet sheet;
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
        if (sheet.getRow(0).getCell(1) != null) {
            GROUP_SIZE_SOFT = (int) sheet.getRow(0).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(1).getCell(1) != null) {
            GROUP_SIZE_HARD = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(2).getCell(1) != null) {
            NO_OVERLAPPING = (int) sheet.getRow(2).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(3).getCell(1) != null) {
            NO_SIMILOAR = (int) sheet.getRow(3).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(4).getCell(1) != null) {
            CORRELATION = (int) sheet.getRow(4).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(5).getCell(1) != null) {
            LONG_EXPERIMANTS = (int) sheet.getRow(5).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(6).getCell(1) != null) {
            PREFERENCES = (int) sheet.getRow(6).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(7).getCell(1) != null) {
            PREFERENCE1 = (int) sheet.getRow(7).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(8).getCell(1) != null) {
            PREFERENCE2 = (int) sheet.getRow(8).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(9).getCell(1) != null) {
            PREFERENCE3 = (int) sheet.getRow(9).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(10).getCell(1) != null) {
            PREFERENCE4 = (int) sheet.getRow(10).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(11).getCell(1) != null) {
            PREFERENCE5 = (int) sheet.getRow(11).getCell(1).getNumericCellValue();
        }
    }
}

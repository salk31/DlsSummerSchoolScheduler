/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.readers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.domain.Correlation;
import uk.ac.diamond.ss.domain.Facility;

/**
 *
 *
 */
public class CorrelationReader {

    private Sheet sheet;

    public CorrelationReader(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<Correlation> read() {
        List<Correlation> result = new ArrayList<Correlation>();
        for (short r = 0; r <=sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            Set<Facility> para = new HashSet();
            Cell n1 = row.getCell(0);
            Cell n2 = row.getCell(1);
            Cell rn = row.getCell(2);
            if (n1 == null || n2 == null || rn == null){//skips uncompleted rows
                continue;
            }
            String name1 = n1.getStringCellValue();
            String name2 = n2.getStringCellValue();
            int rate = (int)rn.getNumericCellValue();
            para.add(Facility.getOrCreate(name1,r));
            para.add(Facility.getOrCreate(name2,r+1));
            Correlation co = new Correlation(para,rate);
            result.add(co);
        }
        return result;
    }

}

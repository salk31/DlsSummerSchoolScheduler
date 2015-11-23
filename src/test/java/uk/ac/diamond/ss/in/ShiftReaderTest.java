/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.in;


import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import uk.ac.diamond.ss.domain.Shift;
import uk.ac.diamond.ss.domain.in.ShiftReader;


public class ShiftReaderTest {
	
	@Test
	public void readTest() throws EncryptedDocumentException, InvalidFormatException, IOException{
	  InputStream inputStream = getClass().getResourceAsStream("/minimalProblemAndSolution.xlsx");
      Workbook wb = WorkbookFactory.create(inputStream);
      //List<Shift> stest = new ShiftReader(wb.getSheet("")).read();
      //assertEquals(stest.size(),);
      
	}

}

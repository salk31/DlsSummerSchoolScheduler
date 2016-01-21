/*
 * Diamond User Administration System
 * Copyright © 2016 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.in;

import static org.junit.Assert.*;

import javax.xml.stream.XMLStreamException;

import org.junit.Test;

import uk.ac.diamond.ss.domain.in.SolverConfigXMLParser;

/**
 *
 *
 */
public class SolverConfigXMLParserTest {

    @Test
    public void testGetRandomSeedValue() throws XMLStreamException, Throwable{
        SolverConfigXMLParser parser = new SolverConfigXMLParser();
        parser.setRandomSeedValue(10,"src/test/resources/solverConfigTest.xml","src/test/resources/solverConfigTest1.xml");
        assertEquals("10",parser.getRandomSeedValue("src/test/resources/solverConfigTest1.xml"));
   }





}

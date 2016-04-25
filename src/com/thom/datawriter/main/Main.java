package com.thom.datawriter.main;

import java.io.IOException;

import com.thom.datawriter.DataFile;
import com.thom.datawriter.DataWriter;

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		DataWriter dw = new DataWriter();
		
		// Calls the DataFile constructor, if the file already exists it is not replaced with a new one.
		DataFile dataFile = new DataFile("standard.th-data");
		
		/**
		 * Managing the data file should be done in something like a data handler class.
		 * But for the sake of testing, I will be doing all that here in this method.
		 */
		
		
		// Prints the amount of lines in the file.
		dw.countLines(dataFile);
	}
}
package com.thom.datawriter.main;

import java.io.File;
import java.io.IOException;

import com.thom.datawriter.DataFile;
import com.thom.datawriter.DataWriter;

public class Main 
{
	static DataFile dataFile;
	static File file;
	
	public static void main(String[] args) throws IOException 
	{
		DataWriter dw = new DataWriter();
		
		dataFile = new DataFile(file = new File("standard.th-data"));
		
		// Tells me the amount of lines in the file
		dw.countLines(file);
	}
}
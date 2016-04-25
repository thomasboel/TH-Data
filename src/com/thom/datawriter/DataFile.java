package com.thom.datawriter;

import java.io.File;

public class DataFile
{
	DataWriter dw = new DataWriter();
	
	public DataFile(File file) 
	{	
		if (!dw.doesFileExist(file))
		dw.generateDataFile(file);
	}
}
package com.thom.datawriter;

import java.io.File;

public class DataFile extends File
{		
	DataWriter dw = new DataWriter();
	
	public DataFile(String fileName)
	{
		super(fileName);
		
		if (!dw.doesFileExist(this))
			dw.generateDataFile(this);
	}
}
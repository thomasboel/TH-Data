package com.thom.datawriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.thom.datawriter.formatting.Category;

public class DataFile extends File
{		
	DataWriter dw = new DataWriter();
	
	public ArrayList<Category> categories = new ArrayList<Category>();
	
	public DataFile(String fileName)
	{
		super(fileName);
		
		if (!dw.doesFileExist(this))
			dw.generateDataFile(this);
		
		try {
			System.out.println("Initializing Categories for DataFile: " + fileName);
			dw.initializeCategories(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addCategory(Category category)
	{
		categories.add(category);
	}
}
package com.thom.datawriter.main;

import java.io.IOException;

import com.thom.datawriter.DataFile;
import com.thom.datawriter.DataWriter;
import com.thom.datawriter.formatting.Category;
import com.thom.datawriter.formatting.Listing;
import com.thom.datawriter.formatting.SubCategory;

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		DataWriter dw = new DataWriter();
		
		// Calls the DataFile constructor, if the file already exists it isn't replaced with a new one.
		DataFile dataFile = new DataFile("standard.th-data");
		DataFile formattedDataFile = new DataFile("formatted datafile.th-data");
		
		/**
		 * Managing the data file should be done in something like a data handler class.
		 * But for the sake of testing, I will be doing all that here, in this method.
		 */
		
		
		// Getting the Category that the SubCategory is part of
		System.out.println(dataFile.map.get(0).get(dataFile.subCategories.get(0)).getCategoryName());
		//System.out.println(new SubCategory("Sub").getCategory().getCategoryName());
		
		dw.countLines(dataFile);
		dw.countLines(formattedDataFile);
	}
}
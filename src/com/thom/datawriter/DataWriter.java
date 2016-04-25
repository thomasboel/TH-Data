package com.thom.datawriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.util.List;

public class DataWriter 
{
	public void generateDataFile(File file)
	{
		try 
		{
			FileWriter fw = new FileWriter(file.getName());
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean doesFileExist(File file)
	{
		if (file.exists() && !file.isDirectory())
		return true;
		else return false;
	}

	public void writeLine(File file, String text) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		lines.add(text);
		
		Files.write(file.toPath(), lines);
	}
	
	public void setLine(File file, int line, String text) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		lines.set(line-1, text);
		
		Files.write(file.toPath(), lines);
	}

	public int getLineAmount(File file) throws IOException 
	{
	    LineNumberReader reader = null;
	    
	    try 
	    {
	        reader = new LineNumberReader(new FileReader(file));
	        
	        while ((reader.readLine()) != null);
	        return reader.getLineNumber();
	    } 
	    catch (Exception e) 
	    {
	    	// This way i'll know if something went wrong, since the amount can't actually go below 0.
	        return -1;
	    } 
	    finally 
	    { 
	        if (reader != null) 
	        {
	        	reader.close();
	        }
	    }
	}
	
	public void countLines(File file) throws IOException
	{
		System.out.println("Line amount: " + getLineAmount(file));
	}
}
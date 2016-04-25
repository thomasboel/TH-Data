package com.thom.datawriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataWriter 
{
	/**
	 * Generates a data file, no path. This means it will be created in the workspace directory.
	 */
	public void generateDataFile(DataFile file)
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
	
	/**
	 * Checks to see if a DataFile exists, no path. This means it will check in the workspace directory.
	 */
	public boolean doesFileExist(DataFile file)
	{
		if (file.exists() && !file.isDirectory())
		return true;
		else return false;
	}

	/**
	 * Writes, or rather adds a new line of parameter 'text' to a specified DataFile.
	 */
	public void writeLine(DataFile file, String text) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		lines.add(text);
		
		Files.write(file.toPath(), lines);
	}
	
	/**
	 * Sets the contents of a specified line in a DataFile to be parameter 'text'.
	 */
	public void setLine(DataFile file, int line, String text) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		lines.set(line-1, text);
		
		Files.write(file.toPath(), lines);
	}
	
	/**
	 * Returns a specific line as a string in the specified DataFile.
	 */
	public String readLine(DataFile file, int line) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		return lines.get(line-1);
	}
	
	/**
	 * Returns an ArrayList of integers which are all the digits found on the specific line in the specified DataFile.
	 */
	public ArrayList<Integer> getDigitsFromLine(DataFile file, int line) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		String lineContents = lines.get(line-1);
		ArrayList<Integer> digits = new ArrayList<Integer>();
		
		for (int i = 0; i < lineContents.length(); i++)
		{
			if (Character.isDigit(lineContents.charAt(i)))
			{
				digits.add(Integer.valueOf(String.valueOf(lineContents.charAt(i))));
			}
		}
		
		return digits;
	}
	
	/**
	 * Returns an integer which is equal to putting all numbers in an ArrayList<Integer>, in a series of numbers.
	 * Example: "Player Health: 10hp". This example would return 10.
	 */
	public int getValueFromArrayList(ArrayList<Integer> arrayList)
	{
		String line = arrayList.toString().replace('[', ' ').replace(']', ' ').replace(',', ' ').replaceAll(" ", "");
		
		return Integer.valueOf(line);
	}

	/**
	 * Returns the amount of lines in a specified DataFile.
	 */
	public int getLines(DataFile file) throws IOException 
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		return lines.size();
	}
	
	/**
	 * Prints the amount of lines in a specified DataFile.
	 */
	public void countLines(DataFile file) throws IOException
	{
		System.out.println("Line amount: " + getLines(file));
	}
}
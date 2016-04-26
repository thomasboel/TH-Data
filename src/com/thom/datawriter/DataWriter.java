package com.thom.datawriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.thom.datawriter.formatting.Category;
import com.thom.datawriter.formatting.SubCategory;

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
			writeLine(file, "@th-data file\n");
			fw.close();
		} 
		catch (IOException e) 
		{
			System.out.println("There was an error whilst trying to generate the data file: " + file.getName());
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
		String line = arrayList.toString().replace('[', ' ').replace(']', ' ').replace(',', ' ').replaceAll("\\s", "");
		
		if (line.isEmpty()) 	return 0; // The program crashes otherwise if getDigitsFromLine() is called on an empty line. This fixes it.
		else 					return Integer.valueOf(line); 
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
		System.out.println("Total lines: " + getLines(file) + ", in DataFile: " + file.getName());
	}
	
	/**
	 * =========================================== START OF ORGANIZED DATA WRITING ===========================================
	 */
	
	/**
	 * Initializes the category ArrayList<Category> with all the categories found in the specified DataFile.
	 * Also does this for all the sub categories as well as mapping what category the sub category is a child of.
	 */
	public void initializeCategories(DataFile file) throws IOException
	{
		List<String> lines = Files.readAllLines(file.toPath());
		
		for (int i = 0; i < lines.size(); i++)
		{
			if (lines.get(i).startsWith("#"))
			{
				file.addCategory(new Category(lines.get(i).substring(2, lines.get(i).length())));
			}
			
			if (lines.get(i).startsWith("-"))
			{
				HashMap<SubCategory, Category> categorySet = new HashMap<SubCategory, Category>();
				SubCategory subCateogry = new SubCategory(lines.get(i).substring(2, lines.get(i).length()));
				
				file.addSubCategory(subCateogry);
				categorySet.put(subCateogry, new Category(lines.get(i-2)));
				file.map.add(categorySet);
			}
		}
	}
	
	/**
	 * Adds a category to the ArrayList<Category> in the specified DataFile.
	 * This also does the actual writing in the DataFile.
	 */
	public void addCategory(DataFile file, Category category) throws IOException
	{
		file.addCategory(category);
		this.writeLine(file, "# " + category.getCategoryName() + "\n");
	}
	
	/**
	 * Returns whether or not a category exists within the specified DataFile.
	 */
	public boolean doesCategoryWithNameExist(DataFile file, String categoryName) throws IOException
	{
		for (int i = 0; i < getLines(file); i++)
		{
			if (readLine(file, i+1).startsWith("#"))
			{
				String currentCategoryName = readLine(file, i+1).substring(2, readLine(file, i+1).length());
				
				if (currentCategoryName.equalsIgnoreCase(categoryName)) return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a sub category to the ArrayList<SubCategory> in the specified DataFile. As well as adds the sub category to a category.
	 * This also does the actual writing in the DataFile.
	 */
	public void addSubCategory(DataFile file, Category category, SubCategory subCategory) throws IOException
	{
		subCategory.setCategory(category);
		file.addSubCategory(subCategory);
		this.writeLine(file, "- " + subCategory.getCategoryName() + "\n");
	}
}
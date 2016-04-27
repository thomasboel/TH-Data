package com.thom.datawriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.thom.datawriter.formatting.Category;
import com.thom.datawriter.formatting.Listing;
import com.thom.datawriter.formatting.SubCategory;
import com.thom.datawriter.formatting.TableListing;

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
		else 
			return false;
	}

	/**
	 * Writes, or rather adds a new line of parameter 'text' to a specified DataFile.
	 */
	public void writeLine(DataFile file, String text) throws IOException
	{
		List<String> lines = getDataFileContents(file);
		
		lines.add(text);
		
		Files.write(file.toPath(), lines);
	}
	
	/**
	 * Sets the contents of a specified line in a DataFile to be parameter 'text'.
	 */
	public void setLine(DataFile file, int line, String text) throws IOException
	{
		List<String> lines = getDataFileContents(file);
		
		lines.set(line-1, text);
		
		Files.write(file.toPath(), lines);
	}
	
	/**
	 * Returns a specific line as a string in the specified DataFile.
	 */
	public String readLine(DataFile file, int line) throws IOException
	{
		return getDataFileContents(file).get(line-1);
	}
	
	/**
	 * Returns an ArrayList of integers which are all the digits found on the specific line in the specified DataFile.
	 */
	public ArrayList<Integer> getDigitsFromLine(DataFile file, int line) throws IOException
	{
		List<String> lines = getDataFileContents(file);
		
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
		
		if (line.isEmpty()) 	return 0; // The program crashes otherwise if getDigitsFromLine() is called on with an empty line as a parameter.
		else 					return Integer.valueOf(line); 
	}
	
	public List<String> getDataFileContents(DataFile file) throws IOException
	{
		return Files.readAllLines(file.toPath());
	}

	/**
	 * Returns the amount of lines in a specified DataFile.
	 */
	public int getLines(DataFile file) throws IOException 
	{
		return getDataFileContents(file).size();
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
			
			//Creates a HashMap with the SubCategory as the key, the user can then retrieve the Category which the SubCategory is part of.
			if (lines.get(i).startsWith("-"))
			{
				HashMap<SubCategory, Category> categorySet = new HashMap<SubCategory, Category>();
				SubCategory subCategory = new SubCategory(lines.get(i).substring(2, lines.get(i).length())); 
		
				file.addSubCategory(subCategory);
				
				// Adds a <K,V> set, to the HashMap by first taking SubCategory found, and then the Category from that SubCategory is retrieved via the line number of the SubCategory using the getCategoryLineFromSubCategory() Method.
				categorySet.put(subCategory, new Category(lines.get(getCategoryLineFromSubCategory(file, subCategory, i))));
				// Adds the HashMap to the DataFile's ArrayList of SubCategory, Category HashMaps.
				file.map.add(categorySet); 
			}
		}
	}
	
	/**
	 * Returns the line number (index) of a Category, retrieved using the SubCategory's line number in the specified DataFile.
	 * This method is used when initializing the read Data from the DataFile. See initializeCategories(DataFile file) for more.
	 */
	public int getCategoryLineFromSubCategory(DataFile file, SubCategory subCategory, int subCategoryLineNumber) throws IOException
	{
		List<String> lines = getDataFileContents(file);
	
		for (int i = 0; i < subCategoryLineNumber; i++)
		{
			if (lines.get(subCategoryLineNumber-i).startsWith("#"))
			{
				return subCategoryLineNumber-i;
			}
		}
		return 0;
	}
	
	/**
	 * Adds a category to the ArrayList<Category> in the specified DataFile.
	 * This also does the actual writing in the DataFile.
	 */
	public void addCategory(DataFile file, Category category) throws IOException
	{
		if (!doesCategoryWithNameExist(file, category.getCategoryName()))
		{
			file.addCategory(category);
			this.writeLine(file, "# " + category.getCategoryName() + "\n");
		}
		else
		{
			System.out.println("The Category '" + category.getCategoryName() + "' already exists within the DataFile: " + file.getName());
		}
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
				
				if (currentCategoryName.equalsIgnoreCase(categoryName)) 
				{
					return true;
				}
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
		if (!doesSubCategoryWithNameExist(file, subCategory.getCategoryName()))
		{
			subCategory.setCategory(category);
			file.addSubCategory(subCategory);
			
			this.setLine(file, getLineForNewListingInCategory(file, category, null), "\n- " + subCategory.getCategoryName() + "\n");
		}
		else
		{
			System.out.println("The Sub-Category '" + subCategory.getCategoryName() + "' already exists within the DataFile: " + file.getName());
		}
	}
	
	/**
	 * Returns whether or not a sub category exists within the specified DataFile.
	 */
	public boolean doesSubCategoryWithNameExist(DataFile file, String subCategoryName) throws IOException
	{
		for (int i = 0; i < getLines(file); i++)
		{
			if (readLine(file, i+1).startsWith("-"))
			{
				String currentSubCategoryName = readLine(file, i+1).substring(2, readLine(file, i+1).length());
				
				if (currentSubCategoryName.equalsIgnoreCase(subCategoryName)) 
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Creates a Listing under a Category or SubCategory in the specified DataFile.
	 */
	public void addListing(DataFile file, Category category, SubCategory subCategory, Listing listing) throws IOException
	{
		setLine(file, getLineForNewListingInCategory(file, category, subCategory), listing.getContents() + "\n");
	}
	
	/**
	 * Creates a Listing under a Category or SubCategory inside a TableListing in the specified DataFile.
	 */
	public void addListing(DataFile file, Category category, SubCategory subCategory, TableListing tableListing, Listing listing) throws IOException
	{
		setLine(file, getLineForNewListingInCategory(file, category, subCategory), listing.getContents() + "\n");
	}
	
	/**
	 * Returns a line number (index) for a new Listing to be created in a Category or SubCategory in the specified DataFile.
	 */
	public int getLineForNewListingInCategory(DataFile file, Category category, SubCategory subCategory) throws IOException
	{
		List<String> lines = getDataFileContents(file);
		
		if (subCategory != null)
		{
			for (int i = 0; i < lines.size(); i++)
			{
				if (lines.get(i).equals("- " + subCategory.getCategoryName()))
				{
					/**
					 * Instead of setting it to just 2 lines after, It must search through the whole sub category, through TableListing's and or Listings and then add it to the bottom
					 */
					return i+2;
				}
			}
		}
		else
		{
			for (int i = 0; i < lines.size(); i++)
			{
				if (lines.get(i).equals("# " + category.getCategoryName()))
				{
					/**
					 * Instead of setting it to just 2 lines after, It must search through the whole category, through TableListing's and or Listings and then add it to the bottom
					 */
					return i+2;
				}
			}
		}
		return 0;
	}
	
	/*public void addTableListing(DataFile file, Category category, SubCategory subCategory, TableListing tableListing)
	{
		setLine(file, getLineForNewListingInCategory(file, category, subCategory), listing.getContents() + "\n");
	}*/
}
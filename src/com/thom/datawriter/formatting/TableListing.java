package com.thom.datawriter.formatting;

import java.io.IOException;
import java.util.ArrayList;

public class TableListing 
{
	private int[] table = new int[2];
	private String name;
	
	public ArrayList<Listing> contents = new ArrayList<Listing>();
	
	public TableListing(String name, int rows, int columns) 
	{
		this.table[0] = rows;
		this.table[1] = columns;
	}
	
	public TableListing(String name)
	{
		this(name, 1, 1);
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public Listing getListingAt(int row, int column)
	{
		return null;
	}
	
	public void setTable(int[] table) 
	{
		this.table = table;
	}
	
	public int[] getTable() 
	{
		return this.table;
	}
	
	public void setRows(int amount)
	{
		this.table[0] = amount;
	}
	
	public int getRows()
	{
		return this.table[0];
	}
	
	public void setColumns(int amount)
	{
		this.table[1] = amount;
	}
	
	public int getColumns()
	{
		return this.table[1];
	}
	
	public int getAmountOfListings()
	{
		return contents.size();
	}
	
	public void setContents(ArrayList<Listing> contents) throws IOException
	{
		if (!(contents.size() > getCapableListings()))
			this.contents = contents;
		else
			System.out.println("Error occured whilst trying to set contents of TableListing: " + this.getName());
	}

	public int getCapableListings() 
	{
		return this.table[0] * this.table[1];
	}
}
package com.thom.datawriter.formatting;

public class Category 
{
	String categoryName;
	
	public Category(String name)
	{
		setCategoryName(name);
		System.out.println("Category Initialized: " + name);
	}
	
	public void setCategoryName(String name)
	{
		this.categoryName = name;
	}
	
	public String getCategoryName() 
	{
		return categoryName;
	}
}
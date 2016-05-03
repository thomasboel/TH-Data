package com.thom.datawriter.formatting;

public class SubCategory
{
	Category category;
	String name;
	
	public SubCategory(String name) 
	{
		this.name = name;
	}
	
	public void setCategory(Category category)
	{
		this.category = category;
	}
	
	public Category getCategory() 
	{
		return category;
	}
	
	public void setSubCategoryName(String name)
	{
		this.name = name;
	}
	
	public String getSubCategoryName() 
	{
		return name;
	}
}
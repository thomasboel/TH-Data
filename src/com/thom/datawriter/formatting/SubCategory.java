package com.thom.datawriter.formatting;

public class SubCategory extends Category
{
	Category category;
	
	public SubCategory(String name) 
	{
		super(name);
	}
	
	public void setCategory(Category category)
	{
		this.category = category;
	}
	
	public Category getCategory() 
	{
		return category;
	}
}
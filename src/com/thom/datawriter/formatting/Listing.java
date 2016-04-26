package com.thom.datawriter.formatting;

public class Listing 
{
	private String contents;
	
	public Listing(String content)
	{
		setContents(content);
	}
	
	public Listing() 
	{
		this("");
	}
	
	public void setContents(String contents) 
	{
		this.contents = contents;
	}
	
	public String getContents() 
	{
		return contents;
	}
}
package com.thom.datawriter.main;

import java.io.IOException;

import com.thom.datawriter.DataFile;
import com.thom.datawriter.DataWriter;
import com.thom.datawriter.formatting.Category;
import com.thom.datawriter.formatting.Listing;
import com.thom.datawriter.formatting.SubCategory;

public class Example 
{
	DataFile file;

	Category blocks, items, armor;

	SubCategory machineBlocks, decorationBlocks;
	SubCategory tools, weapons, food;

	public Example() throws IOException 
	{
		file = new DataFile("example.th-data");
		init();
		main();
	}

	public void init()
	{
		blocks = new Category("Blocks");
		items = new Category("Items");
		armor = new Category("Armor");

		machineBlocks = new SubCategory("Machine Blocks");
		decorationBlocks = new SubCategory("Decoration Blocks");

		tools = new SubCategory("Tools");
		weapons = new SubCategory("Weapons");
		food = new SubCategory("Food");
	}
	
	public void main() throws IOException
	{
		DataWriter dw = new DataWriter();
		
		dw.addCategory(file, blocks);
		dw.addCategory(file, items);
		dw.addCategory(file, armor);
		
		dw.addSubCategory(file, blocks, machineBlocks);
		dw.addSubCategory(file, blocks, decorationBlocks);
		
		dw.addSubCategory(file, items, tools);
		dw.addSubCategory(file, items, weapons);
		dw.addSubCategory(file, items, food);
		
		dw.addCategory(file, new Category("Lol"));
		
		dw.addSubCategory(file, blocks, new SubCategory("Test"));
		
		//System.out.println(dw.getLineForNewObject(file, new Listing("Lol"), blocks, machineBlocks, null));
		for (int i = 0; i < file.map.size(); i++)
		{
			System.out.println(file.map.get(i).toString());
		}
		
		dw.countLines(file);
	}
}
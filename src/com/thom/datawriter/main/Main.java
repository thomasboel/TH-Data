package com.thom.datawriter.main;

import java.io.IOException;

import com.thom.datawriter.DataFile;
import com.thom.datawriter.DataWriter;
import com.thom.datawriter.formatting.*;
import com.thom.encrypter.Decrypter;
import com.thom.encrypter.Encrypter;
import com.thom.encrypter.EncryptionKey;

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		Encrypter encrypter = new Encrypter();
		Decrypter decrypter = new Decrypter();
		
		System.out.println(encrypter.encrypt(new StringBuffer("Jeg er for fanden manden"), new EncryptionKey(3)));
		
		//Example exampleClass = new Example();
		
		/*============= Testing =============*/
		
//		DataWriter dw = new DataWriter();
//		
//		// Calls the DataFile constructor, if the file already exists it isn't replaced with a new one.
//		DataFile dataFile = new DataFile("standard.th-data");
//		DataFile formattedDataFile = new DataFile("formatted datafile.th-data");
//		
//		/**
//		 * Managing the data file should be done in something like a data handler class.
//		 * But for the sake of testing, I will be doing all that here, in this method.
//		 */
//		
//		
//		dw.countLines(dataFile);
//		dw.countLines(formattedDataFile);
	}
}
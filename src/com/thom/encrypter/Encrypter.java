package com.thom.encrypter;

public class Encrypter 
{
	public StringBuffer encrypt(StringBuffer input, EncryptionKey key)
	{
		StringBuffer output = new StringBuffer(input);
		
		for (int i = 0; i < input.length(); i++)
		{
			int temp = 0;
			temp = (int)input.charAt(i);
			temp *= 2;
			if (i > input.length()/2) temp += key.getSetting();
			output.setCharAt(i, (char)temp);
		}
		
		return output;
	}
}
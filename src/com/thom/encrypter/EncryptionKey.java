package com.thom.encrypter;

public class EncryptionKey 
{
	private int setting;
	
	public EncryptionKey(int setting) 
	{
		this.setting = setting;
	}
	
	public void setSetting(int setting) 
	{
		this.setting = setting;
	}
	
	public int getSetting() 
	{
		return setting;
	}
}
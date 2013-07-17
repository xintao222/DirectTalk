package com.github.marco9999.directtalk;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import android.util.Log;

public class MessageHandlerWorker extends Thread
{
	String _ip;
	String _port;
	
	public MessageHandlerWorker(String ip, String port)
	{
		_ip = ip;
		_port = port;
	}
	public void run()
	{
		InetAddress ip = null;
		int port = 0;
		
		try
		{
			ip = InetAddress.getByName(_ip);
			port = Integer.parseInt(_port);
		} 
		catch (Exception e)
		{
			Log.e("DirectTalk", "Error: Host or port invalid!");
			e.printStackTrace();
		}
		
		InputStream input = null;
		Socket connection = null;
		int ch = 0;

		try
		{
			connection = new Socket(ip, port);
		} 
		catch (IOException e)
		{
			Log.e("DirectTalk", "Error: connection failed!");
			e.printStackTrace();
			System.exit(1);
		}

		try
		{
			input = connection.getInputStream();
		} 
		catch (IOException e)
		{
			Log.e("DirectTalk", "Error: couldn't get input stream!");
			e.printStackTrace();
			System.exit(1);
		}
		
		try
		{
			while ((ch = input.read()) != -1)
			{
				Log.i("DirectTalk", "Read a byte: " + ch);
			}
		} 
		catch (IOException e)
		{
			Log.e("DirectTalk", "Error: couldn't read stream!");
			e.printStackTrace();
			System.exit(1);
		}
	}
}

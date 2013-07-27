package com.github.marco9999.directtalk;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class MessageHandlerWorker extends Thread
{
	String ip;
	String port;
	Socket connection;

	public MessageHandlerWorker(String _ip, String _port)
	{
		ip = _ip;
		port = _port;
		connection = null;
	}

	@Override
	public void run()
	{
		// Create socket to communicate
		createMessageSocket();

		// Read byte and log it
		readByteFromSocketStream();
	}

	public void createMessageSocket()
	{
		InetAddress v_ip = null;
		int v_port = 0;

		try
		{
			v_ip = InetAddress.getByName(ip);
			v_port = Integer.parseInt(port);
		}
		catch (UnknownHostException e)
		{
			Log.e("DirectTalk", "Error: Unknown host!");
			e.printStackTrace();
			return;
		}
		catch (NumberFormatException e)
		{
			Log.e("DirectTalk", "Error: Invalid port!");
			e.printStackTrace();
			return;
		}

		try
		{
			connection = new Socket(v_ip, v_port);
		}
		catch (IOException e)
		{
			Log.e("DirectTalk", "Error: I/O connection failed!");
			e.printStackTrace();
			return;
		}
	}

	public void readByteFromSocketStream()
	{
		InputStream input = null;
		int ch = 0;

		// Get input stream
		try
		{
			input = connection.getInputStream();
		}
		catch (IOException e)
		{
			Log.e("DirectTalk", "Error: couldn't get input stream!");
			e.printStackTrace();
			return;
		}

		// Read byte
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
			return;
		}
	}
}

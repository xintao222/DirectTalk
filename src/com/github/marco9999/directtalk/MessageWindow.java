package com.github.marco9999.directtalk;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MessageWindow extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (android.os.Build.VERSION.SDK_INT > 9)
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_window);

		Intent menuintent = getIntent();
		InetAddress address = null;
		int dport = 0;
		String hoststring = null;
		String portstring = null;

		Bundle ConnectInfo = menuintent.getExtras();
		if (ConnectInfo != null)
		{
			hoststring = ConnectInfo
					.getString("com.github.marco9999.hoststring");
			portstring = ConnectInfo
					.getString("com.github.marco9999.portstring");
		}
		else
		{
			Log.e("DirectTalk", "Error: intent extra's empty!");
			System.exit(1);
		}

		if (hoststring == null && portstring == null)
		{
			Log.e("DirectTalk", "Error: Host or port empty!");
			System.exit(1);
		}

		TextView host = (TextView) findViewById(R.id.message_window_host);
		TextView port = (TextView) findViewById(R.id.message_window_port);
		host.setText(hoststring);
		port.setText(portstring);

		try
		{
			address = InetAddress.getByName(hoststring);
			dport = Integer.parseInt(portstring);
		} catch (Exception e)
		{
			Log.e("DirectTalk", "Error: Host or port invalid!");
			e.printStackTrace();
			System.exit(1);
		}
		connectToHostPort(address, dport);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_window, menu);
		return true;
	}

	private void connectToHostPort(InetAddress address, int dport)
	{
		TextView status = (TextView) findViewById(R.id.message_window_status);
		InputStream input = null;
		Socket connection = null;
		int ch = 0;

		try
		{
			connection = new Socket(address, dport);
		} catch (IOException e)
		{
			Log.e("DirectTalk", "Error: connection failed!");
			e.printStackTrace();
			System.exit(1);
		}

		if (connection != null)
		{
			status.setText(R.string.connected);
			try
			{
				input = connection.getInputStream();
			} catch (IOException e)
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
			} catch (IOException e)
			{
				Log.e("DirectTalk", "Error: couldn't read stream!");
				e.printStackTrace();
				System.exit(1);
			}
		}

	}
}

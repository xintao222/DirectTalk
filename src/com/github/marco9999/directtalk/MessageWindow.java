package com.github.marco9999.directtalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MessageWindow extends Activity
{
	final Handler mHandler;
	TextView lastmessage;

	public MessageWindow()
	{
		// Setup message handler
		mHandler = new Handler(Looper.getMainLooper())
		{
			@Override
			public void handleMessage(Message inputMessage)
			{
				switch (inputMessage.what)
				{
				case HandlerConstants.MESSAGE_RECIEVED:
					lastmessage.setText((String) inputMessage.obj);
					break;
				default:
					super.handleMessage(inputMessage);
					break;
				}
			}
		};
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_window);

		// Get intent & setup
		Intent menuintent = getIntent();
		String hoststring = null;
		String portstring = null;
		TextView status = (TextView) findViewById(R.id.message_window_status);
		TextView host = (TextView) findViewById(R.id.message_window_host);
		TextView port = (TextView) findViewById(R.id.message_window_port);
		lastmessage = (TextView) findViewById(R.id.message_window_lastmessage);

		// Get data (host and port)
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
			// Oops! This shouldn't happen.
			Log.e("DirectTalk", "Error: intent extra's empty!");
			status.setText(R.string.connection_failed);
			return;
		}

		// Check if strings are empty
		if (hoststring.isEmpty() || portstring.isEmpty())
		{
			Log.e("DirectTalk", "Error: Host or port empty!");
			status.setText(R.string.connection_failed);
			return;
		}

		// Set status on UI
		host.setText(hoststring);
		port.setText(portstring);

		// Start Connection
		MessageHandlerWorker connection = new MessageHandlerWorker(hoststring,
				portstring, this);
		connection.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_window, menu);
		return true;
	}
}

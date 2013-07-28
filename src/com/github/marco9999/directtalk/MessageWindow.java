package com.github.marco9999.directtalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MessageWindow extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_window);
		
		Log.i("DirectTalk", "onCreate Called");
		
		initUI();
		
		if (DirectTalkGlobalRefs.get_isSetup() == false)
		{
			// Setup message handler
			DirectTalkGlobalRefs.set_handler(new DirectTalkHandlerSub(Looper.getMainLooper()));
			
			Log.i("DirectTalk", "Setting up connection.");
			
			setupInitialConnectionState();
		}
	}

	private void initUI()
	{
		// Get textview's
		DirectTalkGlobalRefs.set_status((TextView) findViewById(R.id.message_window_status));
		DirectTalkGlobalRefs.set_host((TextView) findViewById(R.id.message_window_host));
		DirectTalkGlobalRefs.set_port((TextView) findViewById(R.id.message_window_port));
		DirectTalkGlobalRefs.set_lastmessage((TextView) findViewById(R.id.message_window_lastmessage));
	}
	
	private void setupInitialConnectionState()
	{
		// Get intent & setup
		Intent menuintent = getIntent();
		String hoststring = null;
		String portstring = null;
		
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
			DirectTalkGlobalRefs.get_status().setText(R.string.connection_failed);
			return;
		}

		// Check if strings are empty
		if (hoststring.isEmpty() || portstring.isEmpty())
		{
			Log.e("DirectTalk", "Error: Host or port empty!");
			DirectTalkGlobalRefs.get_status().setText(R.string.connection_failed);
			return;
		}

		// Set status on UI
		DirectTalkGlobalRefs.get_host().setText(hoststring);
		DirectTalkGlobalRefs.get_port().setText(portstring);

		// Start Connection
		MessageHandlerWorker connection = new MessageHandlerWorker(hoststring, portstring);
		connection.start();
		
		// Global var telling app that the connection is already set up (to guard against orientation changes etc)
		DirectTalkGlobalRefs.set_isSetup(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_window, menu);
		return true;
	}
	
	@Override
	public void onDestroy()
	{
		Log.i("DirectTalk", "onDestroy Called");
		super.onDestroy();
	}
}

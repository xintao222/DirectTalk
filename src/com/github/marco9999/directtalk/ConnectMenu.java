package com.github.marco9999.directtalk;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ConnectMenu extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
			        new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect_menu, menu);
		return true;
	}
	
	public void connect_onClick(View view) 
	{
		Intent intent = new Intent(this, MessageWindow.class);
		EditText host = (EditText) findViewById(R.id.connect_menu_hosttext);
		EditText port = (EditText) findViewById(R.id.connect_menu_porttext);
		String hoststring = host.getText().toString();
		String portstring = port.getText().toString();
		intent.putExtra("com.github.marco9999.hoststring", hoststring);
		intent.putExtra("com.github.marco9999.portstring", portstring);
		startActivity(intent);
	}

}

package com.github.marco9999.directtalk;

import android.widget.TextView;

public class DirectTalkGlobalRefs
{
	// App already setup?
	private static boolean isSetup = false;
	
	// TextViews
	private static TextView lastmessage = null;
	private static TextView status = null;
	private static TextView host = null;
	private static TextView port = null;
	
	// Handler
	private static DirectTalkHandlerSub handler = null;
	
	synchronized static void set_lastmessage(TextView _lastmessage)
	{
		lastmessage = _lastmessage;
	}
	
	synchronized static void set_status(TextView _status)
	{
		status = _status;
	}
	
	synchronized static void set_host(TextView _host)
	{
		host = _host;
	}
	
	synchronized static void set_port(TextView _port)
	{
		port = _port;
	}
	
	synchronized static void set_isSetup(boolean _isSetup)
	{
		isSetup = _isSetup;
	}
	
	synchronized static void set_handler(DirectTalkHandlerSub _handler)
	{
		handler = _handler;
	}
	
	synchronized static TextView get_lastmessage()
	{
		return lastmessage;
	}
	
	synchronized static TextView get_status()
	{
		return status;
	}
	
	synchronized static TextView get_host()
	{
		return host;
	}
	
	synchronized static TextView get_port()
	{
		return port;
	}
	
	synchronized static boolean get_isSetup()
	{
		return isSetup;
	}
	
	synchronized static DirectTalkHandlerSub get_handler()
	{
		return handler;
	}
}

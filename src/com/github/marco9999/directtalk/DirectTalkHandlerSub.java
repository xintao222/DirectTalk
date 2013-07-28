package com.github.marco9999.directtalk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class DirectTalkHandlerSub extends Handler
{
	
	DirectTalkHandlerSub(Looper loop)
	{
		super(loop);
	}
	
	@Override
	public void handleMessage(Message inputMessage)
	{
		switch (inputMessage.what)
		{
		case DirectTalkHandlerConstants.MESSAGE_RECIEVED:
			DirectTalkGlobalRefs.get_lastmessage().setText((String) inputMessage.obj);
			break;
		default:
			super.handleMessage(inputMessage);
			break;
		}
	}
}

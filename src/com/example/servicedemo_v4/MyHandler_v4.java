package com.example.servicedemo_v4;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MyHandler_v4 extends Handler
{
	public static final int NUMONE = 1;

	private static MyHandler_v4 handler_v4 = new MyHandler_v4();

	private MyHandler_v4()
	{
	}

	public static MyHandler_v4 getHandlerInstance()
	{
		return handler_v4;
	}

	@Override
	public void handleMessage(Message msg)
	{
		switch (msg.what)
		{
		case NUMONE:
			Log.d("", "÷¥––¡Àhandler");
			break;

		default:
			super.handleMessage(msg);
			break;
		}
	}
}

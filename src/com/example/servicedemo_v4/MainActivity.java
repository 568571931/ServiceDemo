package com.example.servicedemo_v4;

import com.example.servicedemo_v4.Myservice_v4.MyBinder_v4;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity
{

	private ServiceConnection conn = new ServiceConnection()
	{

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			Log.d("", "MainActivity-onServiceDisconnected");
			isBinder = false;
			if (binder_v4 != null)
				binder_v4 = null;

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			Log.d("", "MainActivity-onServiceConnected");
			isBinder = true;
			binder_v4 = (MyBinder_v4) service;
			binder_v4.setMessenger(messenger);
		}
	};
	private boolean isBinder;
	private Intent service;
	private Myservice_v4.MyBinder_v4 binder_v4 = null;
	private Messenger messenger;
	private MyHanler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		service = new Intent(this, Myservice_v4.class);
		mHandler = new MyHanler();
		messenger = new Messenger(mHandler);
		startService(service);
		bindService(service, conn, BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy()
	{
		Log.d("","onDestroy()");
		super.onDestroy();
		if (isBinder)
		{
			unbindService(conn);
		}
	}

	@Override
	protected void onStop()
	{
		Log.d("","onStop()");
		super.onStop();

	}

	public static final int NUMONE = 1;

	class MyHanler extends Handler
	{

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
}

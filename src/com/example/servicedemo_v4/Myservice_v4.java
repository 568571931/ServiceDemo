package com.example.servicedemo_v4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class Myservice_v4 extends Service
{
	Messenger mMessenger;
	private MyBinder_v4 binder_v4;
	private boolean isResume = true;

	@Override
	public IBinder onBind(Intent intent)
	{
		Log.d("", "Myservice_v4-onBind");
		return binder_v4;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		binder_v4 = new MyBinder_v4();
		thread.start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.d("", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		isResume = false;
	}

	class MyBinder_v4 extends Binder
	{
		public void setMessenger(Messenger messenger)
		{
			mMessenger = messenger;
		}

		// public void startMyT()
		// {
		// thread.start();
		// }

		public void setFlag(boolean b)
		{
			isResume = b;
		}

	}

	Thread thread = new Thread(new Runnable()
	{

		@Override
		public void run()
		{

			while (isResume)
			{
				Log.w("", "循环体");

				sendObj(MainActivity.NUMONE, null);

				try
				{
					Thread.sleep(3000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
					Log.e("", e.toString());
				}
			}
		}

		private void sendObj(int numone, Object object)
		{
			// TODO Auto-generated method stub
			if (mMessenger != null)
			{
				try
				{
					Log.i("", "非空");
					mMessenger.send(Message.obtain(null, numone, null));
				} catch (RemoteException e)
				{
					Log.e("error", e.toString());
					mMessenger = null;
					e.printStackTrace();
				}
			} else
			{
				Log.i("", "为空");
			}

		}
	});
}

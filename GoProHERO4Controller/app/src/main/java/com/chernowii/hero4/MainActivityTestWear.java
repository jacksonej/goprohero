package com.chernowii.hero4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.app.Notification;

import android.support.v4.app.NotificationManagerCompat;

public class MainActivityTestWear extends Activity {
    NotificationCompat.WearableExtender wearableExtender =
            new NotificationCompat.WearableExtender()
                    .setHintShowBackgroundOnly(true);


    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    private int numMessages = 0;

	Spinner spnr;
	  String[] vidreslist = {
		  "Select",
		  "4K",
	      "2.7K",
	      "1440p",
	      "1080p",
	      "720p",
	      "WVGA"
	  };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_main);
		    spnr = (Spinner)findViewById(R.id.spinner);
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		        this, android.R.layout.simple_spinner_item, vidreslist);
		    spnr.setAdapter(adapter);
		    spnr.setOnItemSelectedListener(
		              new OnItemSelectedListener() {
		                  @Override
		                  public void onItemSelected(AdapterView<?> arg0, View arg1,
		                          int arg2, long arg3) {
		                    int position = spnr.getSelectedItemPosition();
		                    Toast.makeText(getApplicationContext(),"Resolution: "+vidreslist[+position],Toast.LENGTH_SHORT).show();
		                  

		                    // TODO Auto-generated method stub
		                  }
		                  
		                  @Override
		                  public void onNothingSelected(AdapterView<?> arg0) {
		                      // TODO Auto-generated method stub
		                  }
		              }
		          );
		 
					
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void sendTriggerTwo() {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Trigger!", Toast.LENGTH_SHORT).show();
//GET("http://10.5.5.9/gp/gpControl/command/shutter?p=1");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/shutter?p=1");

    }


    /**
	 * A placeholder fragment containing a simple view.
	 */
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	//START OF VOLUME ROCKER ayy lmao!
	
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
	            //Do something:
                Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                v.vibrate(200);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v.vibrate(300);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v.vibrate(300);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v.vibrate(300);
                Toast.makeText(getApplicationContext(),
                        "Stopped!", Toast.LENGTH_SHORT).show();
	            new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/shutter?p=0");
	        }
	        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
	            //Do something:
                Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                v.vibrate(600);
                Toast.makeText(getApplicationContext(),
                        "Trigger!", Toast.LENGTH_SHORT).show();
	            new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/shutter?p=1");
	        }
	        return true;
	    }
	// END OF VOLUME ROCKER ROFLMAO!!
	//SOME GOOD NOTIFICATION LMAOs


        //START OF THE WIFI HERO4 COMMANDS:

	public void sendTrigger(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Trigger!", Toast.LENGTH_SHORT).show();
//GET("http://10.5.5.9/gp/gpControl/command/shutter?p=1");
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/shutter?p=1");
    }
    public void sendTriggerGPS(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Trigger!", Toast.LENGTH_SHORT).show();
//GET("http://10.5.5.9/gp/gpControl/command/shutter?p=1");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/shutter?p=1");
    }
    public void sendWear(View view) {
        Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
        intent.setAction( MediaPlayerService.ACTION_PLAY );
        startService( intent );
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(1000);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Be a HERO.")
                        .setContentText("Swipe left to reveal the magic >")
                        .addAction(R.drawable.ic_trigger, "Trigger", pIntent)
                        .addAction(R.drawable.ic_stop, "Stop", pIntent)
                        .addAction(R.drawable.ic_tag, "Tag", pIntent)
                        .addAction(R.drawable.ig_video, "Video", pIntent)
                        .addAction(R.drawable.ic_photo, "Photo", pIntent)
                        .addAction(R.drawable.ic_ms, "MultiShot", pIntent)
                        .setContentIntent(pIntent)
                                // .addAction(R.drawable.ic_launcher, "Stop", wearStop)
                                //.addAction(R.drawable.ic_launcher, "Tag", wearTag)
                        .extend(wearableExtender)
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        int notificationId = 1;
        notificationManager.notify(notificationId, notification);
    }


    public void sendStop(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(200);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.vibrate(300);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.vibrate(300);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.vibrate(300);
		Toast.makeText(getApplicationContext(), 
                "Stopped!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/shutter?p=0");

	}
	public void sendTag(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(300);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.vibrate(300);
		Toast.makeText(getApplicationContext(), 
                "Tagged!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/storage/tag_moment");
	}
	public void sendVideo(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
		Toast.makeText(getApplicationContext(), 
                "Set to Video mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/mode?p=0");
	}
	public void sendPhoto(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
		Toast.makeText(getApplicationContext(), 
                "Set to Photo mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/mode?p=1");
	}
	public void sendMultishot(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
		Toast.makeText(getApplicationContext(), 
                "Set to MultiShot mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/mode?p=2");
	}
	public void sendHD(View view) {
		Toast.makeText(getApplicationContext(), 
                "HD Resolution!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/2/1");
	}
	public void sendMidres(View view) {
		Toast.makeText(getApplicationContext(), 
                "1080p FTW!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/2/9");
	}
	public void sendSlowMo(View view) {
		Toast.makeText(getApplicationContext(), 
                "That epic slow mo!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/2/12");
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/0");
	}
 //RISKY ZONE:
	public void sendModeVideoVideo(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Video mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/68/0");
	}
public void sendModeVideoLooping(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Looping video!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/68/3");
	}
public void sendModeVideoVideoPhoto(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Video plus photo!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/68/2");
	}
public void sendModePhotoSingle(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Photo mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/69/0");
	}

public void sendModePhotoContinuous(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Continuous photo mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/69/1");
	}
public void sendModePhotoNight(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Night photo mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/69/2");
	}

public void sendModeMSBurst(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Burst mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/70/0");
	}

public void sendModeMSNightLapse(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Nightlapse!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/70/1");
	}
public void sendModeMSTimeLapse(View view) {
		Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
		Toast.makeText(getApplicationContext(), 
                "Timelapse mode!", Toast.LENGTH_SHORT).show();
		new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/70/2");
	}
//FOVs

public void sendNarrowFOV(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "2sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/4/2");
}
public void sendMediumFOV(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "5sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/4/1");
}
public void sendWideFOV(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "10sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/4/0");
}
//EXPOSURE TIMES FOR NIGHT PHOTO:

public void sendETNP2(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "2sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/19/1");
}
public void sendETNP5(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "5sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/19/2");
}
public void sendETNP10(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "10sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/19/3");
}
public void sendETNP15(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "15sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/19/4");
}
public void sendETNP20(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "20sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/19/5");
}
public void sendETNP30(View view) {
	Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(80);
	Toast.makeText(getApplicationContext(), 
            "30sec", Toast.LENGTH_SHORT).show();
	new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/19/6");
}

    public void send12MPW(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "12MP W", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/17/0");
    }
    public void send7MPW(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "12MP W", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/17/1");
    }

    public void send7MPM(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "12MP W", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/17/2");
    }

    public void send5MPW(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "12MP W", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/17/3");
    }

    public void sendETNI2(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "2sec", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/31/1");
    }
    public void sendETNI5(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "5sec", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/31/2");
    }
    public void sendETNI10(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "10sec", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/31/3");
    }
    public void sendETNI15(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "15sec", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/31/4");
    }
    public void sendETNI20(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "20sec", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/31/5");
    }
    public void sendETNI30(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(80);
        Toast.makeText(getApplicationContext(),
                "30sec", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/31/6");
    }
    public static int myVar = 1;
    public void send120FPS(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "120FPS", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/0");
    }
    public void send60FPS(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(400);
        Toast.makeText(getApplicationContext(),
                "60FPS", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/5");
    }
    public void send30FPS(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(200);
        Toast.makeText(getApplicationContext(),
                "30FPS", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/8");
    }

    public void sendProtuneON(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Protune ON!", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/10/1");
    }
    public void sendProtuneOFF(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Protune OFF!", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/10/0");
    }
    public void send05TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 0.5 seconds", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/0");
    }
    public void send1TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 1 second", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/1");
    }
    public void send2TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 2 seconds", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/2");
    }
    public void send5TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 5 seconds", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/5");
    }
    public void send10TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 10 seconds", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/10");
    }
    public void send30TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 30 seconds", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/30");
    }
    public void send60TLI(View view) {
        Toast.makeText(getApplicationContext(),
                "Timelapse 60 seconds", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/30/60");
    }
    public void sendWBAuto(View view) {
        Toast.makeText(getApplicationContext(),
                "WB Auto", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/11/0");
    }
    public void sendWB3000K(View view) {
        Toast.makeText(getApplicationContext(),
                "WB 3000K", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/11/1");
    }
    public void sendWB5500K(View view) {
        Toast.makeText(getApplicationContext(),
                "WB 5500K", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/11/2");
    }
    public void sendWB6500K(View view) {
        Toast.makeText(getApplicationContext(),
                "WB 6500K", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/11/3");
    }

    public void sendQKOn(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "QuikCapture ON", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/54/1");
    }
    public void sendQKOff(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "QuikCapture OFF", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/54/0");
    }

    public void sendLEDOff(View view) {
        Toast.makeText(getApplicationContext(),
                "LEDs OFF", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/55/0");
    }
    public void sendLED2(View view) {
        Toast.makeText(getApplicationContext(),
                "LEDs 2", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/55/1");
    }
    public void sendLED4(View view) {
        Toast.makeText(getApplicationContext(),
                "LEDs 4", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/55/2");
    }
    public void sendBuzzerOff(View view) {
        Toast.makeText(getApplicationContext(),
                "Buzzer Off", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/56/2");
    }
    public void sendBuzzer70(View view) {
        Toast.makeText(getApplicationContext(),
                "Buzzer 70%", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/56/1");
    }
    public void sendBuzzer100(View view) {
        Toast.makeText(getApplicationContext(),
                "Buzzer 100%", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/56/0");
    }

    public void sendActionProfile(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Be A HERO", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/2/9");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/5");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/4/0");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/10/0");
    }
    public void sendCinemaProfile(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(300);
        Toast.makeText(getApplicationContext(),
                "Be A HERO", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/2/4");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/10");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/4/0");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/10/1");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/11/4");
    }
    public void sendGPS(View view) {

        }

    public void sendIndoorProfile(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(400);
        Toast.makeText(getApplicationContext(),
                "Be A HERO", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/2/9");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/3/8");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/4/0");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/10/0");
    }

    public void sendWiFiOff(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Wifi Off", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/63/0");
    }
    public void sendCamOff(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Turned off", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/system/sleep");
    }
    public void sendFullOff(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "WiFi+Camera OFF", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/system/sleep");
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/setting/63/0");
    }
    public void sendDeleteAll(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Camera media empty", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/storage/delete/all");
    }
    public void sendDeleteLast(View view) {
        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        v.vibrate(600);
        Toast.makeText(getApplicationContext(),
                "Deleted last file", Toast.LENGTH_SHORT).show();
        new HttpAsyncTask().execute("http://10.5.5.9/gp/gpControl/command/storage/delete/last");
    }
    // END OF RISKY ZONE; CARRY ON.


	 public static String GET(String url){
	        InputStream inputStream = null;
	        String result = "";
	        try {
	 
	            // create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	 
	            // make GET request to the given URL
	            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
	 
	            // receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";
	 
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        }
	 
	        return result;
	    }
	 
	    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        return result;
	 
	    }
	 
	    public boolean isConnected(){
	        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
	            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	            if (networkInfo != null && networkInfo.isConnected())
	                return true;
	            else
	                return false;  
	    }
	    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	        @Override
	        protected String doInBackground(String... urls) {
	 
	            return GET(urls[0]);
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	            Toast.makeText(getBaseContext(), "Done!", Toast.LENGTH_SHORT).show();
	           ;
	       }
	    }
}


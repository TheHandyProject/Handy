package com.project.handyproject;

//import com.android.swipe.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.view.*;
import android.content.Intent;

public class MainActivity extends Activity implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
    public final static String EXTRA_MESSAGE = "com.project.handyproject.MESSAGE";
    public static final int MY_REQUEST_CODE = 123;
    

//	TextView title,tv,tv1,tv2,tv3,tv4;
	LinearLayout layout;
	
	
	public final void onCreate(Bundle savedInstanceState)
	 {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_main); //refer layout file code below
	   //get the sensor service
	   mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	   //get the accelerometer sensor
	   mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	   //get layout
	   layout = (LinearLayout)findViewById(R.id.linearLayout1);
	      
	 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy)
     {
       // Do something here if sensor accuracy changes.
     }
  @Override
   public final void onSensorChanged(SensorEvent event)
     {
      // Many sensors return 3 values, one for each axis.
     float x = event.values[0];
     float y = event.values[1];
     float z = event.values[2];
     
   if (x<1&&x>-1&&y<1&&y>-0.06&&z>10&&z<11)
     {
    	// tv3.setText("Horizontal Position");
       // sendMessage("0");
     }
     if (x<1&&x>-1&&y<-1&&y>-5&&z>8&&z<11)
     {
    //	 tv3.setText("Up Position");
         sendMessage("0");
     }
     if (x<1&&x>-1&&y<9&&y>1.5&&z>6&&z<9)
     {
    //	 tv3.setText("Down Position");
          sendMessage("1");
     }
     if (x<9&&x>2&&y<1&&y>-1&&z>9&&z<11)
     {
    //	 tv4.setText("Right Position");
         sendMessage("2");
     }
     if (x<-1&&x>-5&&y<1&&y>-1&&z>10&&z<11)
     {
    //	 tv4.setText("Left Position");
          sendMessage("3");
     }
     if (x<1&&x>-1&&y<1&&y>-1&&z>10&&z<11)
     {
    	// tv4.setText("Horizontal Position");
        // sendMessage("0");
     }
     
 
     } 
  public void sendMessage(String data) {
	    Intent intent = new Intent(this, SwipeScreenExample.class);
	    intent.putExtra(EXTRA_MESSAGE, data);
	    startActivityForResult(intent, MY_REQUEST_CODE);
	}

  protected void onActivityResult(
          int requestCode, 
          int resultCode,
          Intent pData) 
  {
       if ( requestCode == MY_REQUEST_CODE ) 
       {
           if (resultCode == Activity.RESULT_OK ) 
           {
                String zData = pData.getExtras().getString( SwipeScreenExample.EXTRA_STRING_NAME );
                EditText etxt = (EditText) findViewById(R.id.editText1);
                String existing = String.valueOf(etxt.getText());
                String newValue = existing + zData;
                CharSequence cs = newValue;
                etxt.setText(cs);
           
           }
       }
       
   }
  
  
  @Override
  protected void onResume()
  {
  super.onResume();
  mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
  }
  @Override
  protected void onPause()
  {
  super.onPause();
  mSensorManager.unregisterListener(this);
  }
    
}


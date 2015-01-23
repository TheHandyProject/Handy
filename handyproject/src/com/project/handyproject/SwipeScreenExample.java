package com.project.handyproject;

//import com.android.swipe.R;
import java.util.HashMap;

import com.project.handyproject.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;
import android.view.*;
 
public class SwipeScreenExample extends Activity implements SimpleGestureListener{
            private SimpleGestureFilter detector;
            public String message;
            public int num;
            public static final String EXTRA_STRING_NAME = "extraStringName";
            
        @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Intent intent = getIntent();
                message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                num = Integer.parseInt(message);
                switch (num)
                {
                case 0 : setContentView(R.layout.swipe_screen_up);
                case 1 : setContentView(R.layout.swipe_screen_down);
                case 2 : setContentView(R.layout.swipe_screen_right);
                case 3 : setContentView(R.layout.swipe_screen_left);
                }
                	
                // Detect touched area 
                detector = new SimpleGestureFilter(this,this);
        }
          
    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
         this.detector.onTouchEvent(me);
       return super.dispatchTouchEvent(me);
    }
    @Override
     public void onSwipe(int direction, float slope) {
      int x = direction + num*7 + 95;
      String asciiValue = String.valueOf(((char) x));
      Toast.makeText(this, asciiValue, Toast.LENGTH_SHORT).show();
      
      Intent iData = new Intent();
      iData.putExtra( EXTRA_STRING_NAME, asciiValue );
      
      setResult( android.app.Activity.RESULT_OK,iData );
      
      finish();
     
    }
      
     @Override
     public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
     }
          
  }

package com.project.handyproject;

import com.project.handyproject.SimpleGestureFilter.SimpleGestureListener;

import android.app.*;
import android.view.*;
import android.os.*;
import android.widget.*;
import android.content.*;

public class SwipeScreenExample extends Activity implements SimpleGestureListener
{
    public static final String EXTRA_STRING_NAME = "extraStringName";
    private SimpleGestureFilter detector;
    public int num;
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        this.detector.onTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    	int layoutNum;
        String message = this.getIntent().getStringExtra("com.project.handyproject.MESSAGE");
        Toast.makeText((Context)this, message, 0).show();
        switch ( Integer.parseInt(message)) {
            case 0: {
                this.setContentView(R.layout.swipe_screen_up);
                break;
            }
            case 1: {
                this.setContentView(R.layout.swipe_screen_right);
                break;
            }
            case 2: {
                this.setContentView(R.layout.swipe_screen_down);
                break;
            }
            case 3: {
                this.setContentView(R.layout.swipe_screen_left);
                break;
            }
        }
        this.detector = new SimpleGestureFilter(this, (SimpleGestureFilter.SimpleGestureListener)this);
    }
    
    public void onDoubleTap() {
        Toast.makeText((Context)this, (CharSequence)"Double Tap", 0).show();
    }
    
    public void onSwipe(final int n, final float n2) {
        String value = "";
        if (n == 8) {
            this.finish();
        }
        else if (n != 8) {
            value = String.valueOf((char)(96 + (n + 7 * this.num)));
        }
        Toast.makeText((Context)this, (CharSequence)value, 0).show();
        final Intent intent = new Intent();
        intent.putExtra("extraStringName", value);
        this.setResult(-1, intent);
        this.finish();
    }
}
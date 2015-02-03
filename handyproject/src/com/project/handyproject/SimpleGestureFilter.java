package com.project.handyproject;

import android.app.*;
import android.content.*;
import android.view.*;
import android.view.GestureDetector.SimpleOnGestureListener;

public class SimpleGestureFilter extends SimpleOnGestureListener
{
    private static final int ACTION_FAKE = -13;
    public static final int MODE_DYNAMIC = 2;
    public static final int MODE_SOLID = 1;
    public static final int MODE_TRANSPARENT = 0;
    public static final int SWIPE_EAST = 4;
    public static final int SWIPE_NORTH = 2;
    public static final int SWIPE_NORTH_EAST = 3;
    public static final int SWIPE_NORTH_WEST = 1;
    public static final int SWIPE_SOUTH = 6;
    public static final int SWIPE_SOUTH_EAST = 5;
    public static final int SWIPE_SOUTH_WEST = 7;
    public static final int SWIPE_WEST = 8;
    private Activity context;
    private GestureDetector detector;
    private SimpleGestureListener listener;
    private int mode;
    private boolean running;
    private int swipe_Max_Distance;
    private int swipe_Min_Distance;
    private int swipe_Min_Velocity;
    private boolean tapIndicator;
    
    public SimpleGestureFilter(final Activity context, final SimpleGestureListener listener) {
        this.swipe_Min_Distance = 100;
        this.swipe_Max_Distance = 700;
        this.swipe_Min_Velocity = 100;
        this.mode = 2;
        this.running = true;
        this.tapIndicator = false;
        this.context = context;
        this.detector = new GestureDetector((Context)context, (SimpleOnGestureListener)this);
        this.listener = listener;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public int getSwipeMaxDistance() {
        return this.swipe_Max_Distance;
    }
    
    public int getSwipeMinDistance() {
        return this.swipe_Min_Distance;
    }
    
    public int getSwipeMinVelocity() {
        return this.swipe_Min_Velocity;
    }
    
    public boolean onDoubleTap(final MotionEvent motionEvent) {
        this.listener.onDoubleTap();
        return true;
    }
    
    public boolean onDoubleTapEvent(final MotionEvent motionEvent) {
        return true;
    }
    
    public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
        final float abs = Math.abs(motionEvent.getX() - motionEvent2.getX());
        final float abs2 = Math.abs(motionEvent.getY() - motionEvent2.getY());
        if (abs <= this.swipe_Max_Distance && abs2 <= this.swipe_Max_Distance) {
            final float abs3 = Math.abs(n);
            final float abs4 = Math.abs(n2);
            if (abs3 > this.swipe_Min_Velocity && abs > this.swipe_Min_Distance && abs4 > this.swipe_Min_Velocity && abs2 > this.swipe_Min_Distance) {
                final float x = motionEvent.getX();
                final float n3 = -motionEvent.getY();
                final float n4 = motionEvent2.getX() - x;
                final float n5 = -motionEvent2.getY() - n3;
                final float n6 = n5 / n4;
                if (n6 > -0.614 && n6 <= 0.614) {
                    if (n4 > 0.0f) {
                        this.listener.onSwipe(4, n6);
                    }
                    this.listener.onSwipe(8, n6);
                }
                else if (n6 > 0.614 && n6 <= 2.0f) {
                    if (n4 > 0.0f) {
                        this.listener.onSwipe(3, n6);
                    }
                    this.listener.onSwipe(7, n6);
                }
                else if (n6 <= -2.0f || n6 > 2.0f) {
                    if (n5 > 0.0f) {
                        this.listener.onSwipe(2, n6);
                    }
                    this.listener.onSwipe(6, n6);
                }
                else if (n6 > -2.0f && n6 <= -0.614) {
                    if (n5 > 0.0f) {
                        this.listener.onSwipe(1, n6);
                    }
                    this.listener.onSwipe(5, n6);
                }
            }
        }
        return false;
    }
    
    public boolean onSingleTapConfirmed(final MotionEvent motionEvent) {
        if (this.mode == 2) {
            motionEvent.setAction(-13);
            this.context.dispatchTouchEvent(motionEvent);
        }
        return false;
    }
    
    public boolean onSingleTapUp(final MotionEvent motionEvent) {
        this.tapIndicator = true;
        return false;
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        if (this.running) {
            final boolean onTouchEvent = this.detector.onTouchEvent(motionEvent);
            if (this.mode == 1) {
                motionEvent.setAction(3);
            }
            if (this.mode == 2) {
                if (motionEvent.getAction() == -13) {
                    motionEvent.setAction(1);
                }
                if (onTouchEvent) {
                    motionEvent.setAction(3);
                }
                if (this.tapIndicator) {
                    motionEvent.setAction(0);
                    this.tapIndicator = false;
                }
            }
        }
    }
    
    public void setEnabled(final boolean running) {
        this.running = running;
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
    }
    
    public void setSwipeMaxDistance(final int swipe_Max_Distance) {
        this.swipe_Max_Distance = swipe_Max_Distance;
    }
    
    public void setSwipeMinDistance(final int swipe_Min_Distance) {
        this.swipe_Min_Distance = swipe_Min_Distance;
    }
    
    public void setSwipeMinVelocity(final int swipe_Min_Velocity) {
        this.swipe_Min_Velocity = swipe_Min_Velocity;
    }
    
    interface SimpleGestureListener
    {
        void onDoubleTap();
        
        void onSwipe(int p0, float p1);
    }
}
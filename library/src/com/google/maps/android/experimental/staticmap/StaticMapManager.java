package com.google.maps.android.experimental.staticmap;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.android.gms.maps.MapView;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Coordinates rendering of multiple StaticMapViews.
 *
 * All calls should be made on the main thread.
 */
public class StaticMapManager {

    private Queue<StaticMapView> mQueue = new LinkedList<StaticMapView>();

    private boolean mConnected;

    private StaticMapRenderer mRenderer;

    public void connect(Context context) {
        if (mConnected) {
            throw new IllegalStateException("Already connected.");
        }
        mRenderer = new StaticMapRenderer(context, this);
        mConnected = true;
    }

    public void add(StaticMapView staticMapView) {
        mQueue.add(staticMapView);
    }

    public void remove(StaticMapView staticMapView) {
        mQueue.remove(staticMapView);
    }

    public void disconnect() {
        mRenderer = null;
        mConnected = false;
    }
}

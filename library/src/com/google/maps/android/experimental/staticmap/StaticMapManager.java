package com.google.maps.android.experimental.staticmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Coordinates rendering of multiple StaticMapViews.
 *
 * All calls should be made on the main thread.
 */
public class StaticMapManager {

    private Queue<StaticMapView> mQueue;

    private boolean mConnected;

    private StaticMapRenderer mRenderer;

    private Handler mHandler;

    public void connect(Context context) {
        if (mConnected) {
            throw new IllegalStateException("Already connected.");
        }
        mRenderer = new StaticMapRenderer(context, this);
        mConnected = true;
        mQueue = new LinkedList<StaticMapView>();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                removeMessages(0);
                renderNext();
            }
        };
    }

    public void add(StaticMapView staticMapView) {
        if (!mConnected) {
            throw new IllegalStateException("Need to be connected before adding maps to the queue.");
        }
        mQueue.add(staticMapView);
        renderNext();
    }

    private void renderNext() {
        if (mRenderer.isBusy() || mQueue.isEmpty()) {
            return;
        }
        final StaticMapView staticMapView = mQueue.poll();
        mRenderer.render(staticMapView, new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                staticMapView.setBitmap(bitmap);
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    public void remove(StaticMapView staticMapView) {
        mQueue.remove(staticMapView);
        mRenderer.stopRendering(staticMapView);
    }

    public void disconnect() {
        mRenderer.stopRendering();
        mQueue = null;
        mRenderer = null;
        mHandler = null;
        mConnected = false;
    }
}

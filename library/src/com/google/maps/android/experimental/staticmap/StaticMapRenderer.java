package com.google.maps.android.experimental.staticmap;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.MarkerOptions;

class StaticMapRenderer implements GoogleMap.OnMapLoadedCallback, GoogleMap.SnapshotReadyCallback {
    private MapView mMapView;
    private GoogleMap mMap;
    private GoogleMap.SnapshotReadyCallback mCallback;
    private boolean mBusy;
    private StaticMapView mStaticMapView;

    public StaticMapRenderer(Context context, StaticMapManager staticMapManager) {
        mMapView = new MapView(context);
        MapsInitializer.initialize(context); // TODO: check return value?
    }

    public void render(StaticMapView staticMapView, GoogleMap.SnapshotReadyCallback callback) {
        if (mBusy) {
            throw new IllegalStateException("Already rendering a map. Something went wrong.");
        }
        mBusy = true;
        mStaticMapView = staticMapView;
        mCallback = callback;
        staticMapView.setMapView(mMapView);
        StaticMapOptions options = staticMapView.getOptions();
        mMap = mMapView.getMap();
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(options.getCameraPosition()));
        for (MarkerOptions marker : options.getMarkers()) {
            mMap.addMarker(marker);
        }
        mMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onMapLoaded() {
        mMap.snapshot(this);
    }

    @Override
    public void onSnapshotReady(Bitmap bitmap) {
        mBusy = false;
        mCallback.onSnapshotReady(bitmap);
    }

    public boolean isBusy() {
        return mBusy;
    }

    /**
     * Cancels rendering of a particular view.
     * If the view does not match, then this is a no-op.
     */
    public void stopRendering(StaticMapView staticMapView) {
        if (staticMapView != mStaticMapView) {
            return;
        }
        stopRendering();
    }

    /**
     * Cancels the current render.
     */
    public void stopRendering() {
        mBusy = false;
        mStaticMapView = null;
        mCallback = null;
    }
}

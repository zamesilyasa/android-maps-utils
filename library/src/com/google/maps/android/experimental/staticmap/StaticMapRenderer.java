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

    public StaticMapRenderer(Context context, StaticMapManager staticMapManager) {
        mMapView = new MapView(context);
        MapsInitializer.initialize(context); // TODO: check return value?
    }

    public void render(StaticMapView staticMapView, GoogleMap.SnapshotReadyCallback callback) {
        StaticMapOptions options = staticMapView.getOptions();
        mBusy = true;
        mCallback = callback;
        staticMapView.setMapView(mMapView);
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
}

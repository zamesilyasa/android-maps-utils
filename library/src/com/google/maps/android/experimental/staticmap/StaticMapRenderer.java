package com.google.maps.android.experimental.staticmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.MarkerOptions;

class StaticMapRenderer implements GoogleMap.OnMapLoadedCallback {
    private MapView mMapView;
    private GoogleMap mMap;
    private GoogleMap.SnapshotReadyCallback mCallback;

    public StaticMapRenderer(Context context, StaticMapManager staticMapManager) {
        mMapView = new MapView(context);
        MapsInitializer.initialize(context); // TODO: check return value?
    }

    public void render(StaticMapView viewGroup, StaticMapOptions options, GoogleMap.SnapshotReadyCallback callback) {
        mCallback = callback;
        viewGroup.setMapView(mMapView);
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
        mMap.snapshot(mCallback);
    }
}

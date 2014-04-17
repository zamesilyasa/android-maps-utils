package com.google.maps.android.experimental.staticmap;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

// TODO: make this a builder and immutable
public class StaticMapOptions {
    private CameraPosition mCameraPosition;
    private ArrayList<MarkerOptions> mMarkers;

    public CameraPosition getCameraPosition() {
        return mCameraPosition;
    }

    public void setCameraPosition(CameraPosition cameraPosition) {
        mCameraPosition = cameraPosition;
    }

    public void addMarker(MarkerOptions markerOptions) {
        mMarkers.add(markerOptions);
    }
}

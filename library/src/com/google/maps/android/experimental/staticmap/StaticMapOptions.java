package com.google.maps.android.experimental.staticmap;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Options for the static map.
 */
public class StaticMapOptions {
    private CameraPosition mCameraPosition;
    private ArrayList<MarkerOptions> mMarkers;

    public CameraPosition getCameraPosition() {
        return mCameraPosition;
    }

    public List<MarkerOptions> getMarkers() {
        return Collections.unmodifiableList(mMarkers);
    }

    public StaticMapOptions setCameraPosition(CameraPosition cameraPosition) {
        mCameraPosition = cameraPosition;
        return this;
    }

    public StaticMapOptions addMarker(MarkerOptions markerOptions) {
        mMarkers.add(markerOptions);
        return this;
    }
}

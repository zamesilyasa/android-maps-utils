package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.experimental.staticmap.StaticMapManager;
import com.google.maps.android.experimental.staticmap.StaticMapOptions;
import com.google.maps.android.experimental.staticmap.StaticMapView;

/**
 * Basic usage of the static map class.
 */
public class SimpleStaticDemoActivity extends Activity {

    private StaticMapManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_simple);
        mManager = new StaticMapManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mManager.connect(this);
        final StaticMapView staticMapView = (StaticMapView) findViewById(R.id.static_map);

        staticMapView.setOptions(new StaticMapOptions()
                .setCameraPosition(CameraPosition.builder()
                        .target(new LatLng(-33.8674869, 151.2069902))
                        .tilt(30f)
                        .zoom(12f)
                        .build()));

        mManager.add(staticMapView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mManager.disconnect();
    }
}

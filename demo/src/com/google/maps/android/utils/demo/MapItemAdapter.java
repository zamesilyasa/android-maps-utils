package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.experimental.staticmap.StaticMapManager;
import com.google.maps.android.experimental.staticmap.StaticMapOptions;
import com.google.maps.android.experimental.staticmap.StaticMapView;
import com.google.maps.android.utils.demo.model.MapItem;

public class MapItemAdapter extends ArrayAdapter<MapItem> {
    private final MapItem[] mMaps;
    private final int mResource;
    private final StaticMapManager mMapManager;

    public MapItemAdapter(Activity context, int resource, MapItem[] objects, StaticMapManager mapManager) {
        super(context, resource, objects);
        mMaps = objects;
        mResource = resource;
        mMapManager = mapManager;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        MapItem map = mMaps[position];

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            view = inflater.inflate(mResource, parent, false);
        } else {
            view = convertView;
        }

        assert view != null;
        ((TextView) view.findViewById(R.id.map_label)).setText(map.name);
        ((TextView) view.findViewById(R.id.map_description)).setText(map.description);

        LinearLayout mapContainer = (LinearLayout) view.findViewById(R.id.map_container);
        StaticMapView myMap = new StaticMapView(getContext());
        myMap.setManager(mMapManager);
        myMap.setOptions(new StaticMapOptions()
            .setCameraPosition(CameraPosition.builder()
                .target(map.point)
                .tilt(30f)
                .zoom(12f)
                .build()));

        mapContainer.addView(myMap);

        return view;
    }
}

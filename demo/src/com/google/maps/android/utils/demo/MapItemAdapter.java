package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.maps.android.utils.demo.model.MapItem;

public class MapItemAdapter extends ArrayAdapter<MapItem> {
    private final MapItem[] maps;
    private final int resource;

    public MapItemAdapter(Activity context, int resource, MapItem[] objects) {
        super(context, resource, objects);
        this.maps = objects;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        MapItem map = maps[position];

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        assert view != null;
        ((TextView) view.findViewById(R.id.map_label)).setText(map.name);
        ((TextView) view.findViewById(R.id.map_description)).setText(map.description);

        return view;
    }
}

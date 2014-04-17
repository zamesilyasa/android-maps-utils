package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.utils.demo.model.MapItem;

import java.util.ArrayList;
import java.util.List;

public class StaticMapDemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staticmap_demo);

        List<MapItem> maps = getMapItems();

        ListView list = (ListView) findViewById(R.id.map_list_view);

        MapItemAdapter adapter = new MapItemAdapter(this, R.layout.map_list_item,
                maps.toArray(new MapItem[maps.size()]));

        list.setAdapter(adapter);
    }

    private List<MapItem> getMapItems() {
        List<MapItem> maps = new ArrayList<MapItem>();

        MapItem perth = new MapItem();
        perth.name = "Perth";
        perth.description = "Capital of WA";
        perth.point = new LatLng(-31.9522, 115.8589);
        perth.zoom = 10;
        maps.add(perth);

//        MapItem brisbane = new MapItem();
//        brisbane.name = "Brisbane";
//        brisbane.description = "Brissssvegas";
//        brisbane.point = new LatLng(-27.4679, 153.0278);
//        brisbane.zoom = 10;
//        maps.add(brisbane);
//
//        MapItem sydney = new MapItem();
//        sydney.name = "Sydney";
//        sydney.description = "Capital city of NSW";
//        sydney.point = new LatLng(-33.8600, 151.2111);
//        sydney.zoom = 10;
//        maps.add(sydney);
//
//        MapItem nyc = new MapItem();
//        nyc.name = "New York City";
//        nyc.description = "NY, NY";
//        nyc.point = new LatLng(40.6700, -73.9400);
//        nyc.zoom = 8;
//        maps.add(nyc);
//
//        MapItem pyramids = new MapItem();
//        pyramids.name = "Great Pyramid of Giza";
//        pyramids.description = "Egypt.";
//        pyramids.point = new LatLng(29.9792, 31.1344);
//        pyramids.zoom = 15;
//        maps.add(pyramids);

        return maps;
    }

}

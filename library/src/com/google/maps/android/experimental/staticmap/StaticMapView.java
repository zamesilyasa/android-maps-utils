package com.google.maps.android.experimental.staticmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.MapView;

public class StaticMapView extends FrameLayout {
    private ImageView mImageView;
    private StaticMapOptions mOptions;
    private StaticMapManager mManager;

    public StaticMapView(Context context) {
        super(context);
        init();
    }

    public StaticMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StaticMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mImageView = new ImageView(getContext());
        reset();
    }

    public void reset() {
        mOptions = null;
        // TODO: set the placeholder bitmap for the image view
        mManager.remove(this);
    }

    public void setManager(StaticMapManager manager) {
        mManager = manager;
    }

    public void setOptions(StaticMapOptions options) {
        mOptions = options;
        mManager.add(this);
    }

    public StaticMapOptions getOptions() {
        return mOptions;
    }

    public void setMapView(MapView mMapView) {
        // TODO: Push to the back
        // Resize the map?
        addView(mMapView, 0);
    }

    public void setBitmap(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }
}

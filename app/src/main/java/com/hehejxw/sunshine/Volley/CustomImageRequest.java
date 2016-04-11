package com.hehejxw.sunshine.Volley;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

/**
 * Created by XiaowenJiang on 4/10/16.
 */
public class CustomImageRequest extends ImageRequest {
    private Priority mPriority;

    public CustomImageRequest(String url, Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, Bitmap.Config decodeConfig, Response.ErrorListener errorListener) {
        super(url, listener, maxWidth, maxHeight, scaleType, decodeConfig, errorListener);
    }


    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        return (mPriority==null)?Priority.NORMAL:mPriority;
    }
}

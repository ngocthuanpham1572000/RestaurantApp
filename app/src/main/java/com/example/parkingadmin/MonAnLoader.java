package com.example.parkingadmin;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MonAnLoader extends AsyncTaskLoader<String> {
    public MonAnLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUnit.getMoAn();
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

}

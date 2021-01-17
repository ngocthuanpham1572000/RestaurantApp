package com.example.parkingadmin.asynctaskloader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.parkingadmin.function.NetworkUnit;

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

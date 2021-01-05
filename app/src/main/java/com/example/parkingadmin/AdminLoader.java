package com.example.parkingadmin;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class AdminLoader extends AsyncTaskLoader<String> {
    public AdminLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUnit.getDangNhap();
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

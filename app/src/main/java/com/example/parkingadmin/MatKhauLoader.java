package com.example.parkingadmin;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;


public class MatKhauLoader extends AsyncTaskLoader<String> {
    int id;
    String mk;
    public MatKhauLoader(@NonNull Context context, int Id, String Mk) {
        super(context);
        this.id=Id;
        this.mk=Mk;

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUnit.DoiMK(this.id,this.mk);
    }
}

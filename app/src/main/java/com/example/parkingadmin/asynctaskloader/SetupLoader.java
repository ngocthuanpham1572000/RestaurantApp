package com.example.parkingadmin.asynctaskloader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.parkingadmin.function.NetworkUnit;

public class SetupLoader extends AsyncTaskLoader<String> {
    int SoNguoi;
    int id;
    public SetupLoader(@NonNull Context context,int Id,int sn) {
        super(context);
        this.id=Id;
        this.SoNguoi=sn;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUnit.SetupBan(this.id,this.SoNguoi);
    }
}

package com.winkim.itime.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TimingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is timing fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
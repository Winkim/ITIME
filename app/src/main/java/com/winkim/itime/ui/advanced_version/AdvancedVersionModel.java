package com.winkim.itime.ui.advanced_version;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvancedVersionModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvancedVersionModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is change_font fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
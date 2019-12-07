package com.winkim.itime.ui.theme_color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThemeColorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ThemeColorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is theme_color fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
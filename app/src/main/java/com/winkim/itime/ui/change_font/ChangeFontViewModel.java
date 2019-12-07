package com.winkim.itime.ui.change_font;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChangeFontViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChangeFontViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is change_font fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.winkim.itime.ui.help_and_feedback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpAndFeedbackViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpAndFeedbackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
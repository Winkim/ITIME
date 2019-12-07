package com.winkim.itime.ui.help_and_feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.winkim.itime.R;

public class HelpAndFeedbackFragment extends Fragment {

    private HelpAndFeedbackViewModel helpAndFeedbackViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpAndFeedbackViewModel =
                ViewModelProviders.of(this).get(HelpAndFeedbackViewModel.class);
        View root = inflater.inflate(R.layout.fragment_help_and_feedback, container, false);
        final TextView textView = root.findViewById(R.id.text_help_and_feecback);
        helpAndFeedbackViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
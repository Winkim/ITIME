package com.winkim.itime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class TimingFragment extends Fragment {

    private TimingViewModel timingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        timingViewModel =
                ViewModelProviders.of(this).get(TimingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timing, container, false);
        final TextView textView = root.findViewById(R.id.text_view_time);
        timingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
package com.winkim.itime.ui.advanced_version;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.winkim.itime.R;

public class AdvancedVersionFragment extends Fragment {

    private AdvancedVersionModel advancedVersionModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        advancedVersionModel =
                ViewModelProviders.of(this).get(AdvancedVersionModel.class);
        View root = inflater.inflate(R.layout.fragment_advanced_version, container, false);
        final TextView textView = root.findViewById(R.id.text_advanced_version);
        advancedVersionModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
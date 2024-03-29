package com.winkim.itime.ui.theme_color;

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

public class ThemeColorFragment extends Fragment {

    private ThemeColorViewModel themeColorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        themeColorViewModel =
                ViewModelProviders.of(this).get(ThemeColorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_theme_color, container, false);
        final TextView textView = root.findViewById(R.id.text_theme_color);
        themeColorViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
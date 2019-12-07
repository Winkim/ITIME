package com.winkim.itime.ui.change_font;

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

public class ChangeFontFragment extends Fragment {

    private ChangeFontViewModel changeFontViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        changeFontViewModel =
                ViewModelProviders.of(this).get(ChangeFontViewModel.class);
        View root = inflater.inflate(R.layout.fragment_change_font, container, false);
        final TextView textView = root.findViewById(R.id.text_change_font);
        changeFontViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
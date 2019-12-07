package com.winkim.itime.ui.label;

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

public class LabelFragment extends Fragment {

    private LabelViewModel labelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        labelViewModel =
                ViewModelProviders.of(this).get(LabelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_label, container, false);
        final TextView textView = root.findViewById(R.id.text_label);
        labelViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
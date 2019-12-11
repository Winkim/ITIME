//package com.winkim.itime;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//public class TimingFragment extends Fragment {
//
//
//    AddTimingActivity timingsArrayAdapter;
//
//    public TimingFragment(AddTimingActivity timingsArrayAdapter){
//        this.timingsArrayAdapter=timingsArrayAdapter;
//    }
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//            ViewGroup container, Bundle savedInstanceState) {
//        //timingViewModel =
//                //ViewModelProviders.of(this).get(TimingViewModel.class);
//        View root = inflater.inflate(R.layout.list_item_timings, container, false);
//        ListView listViewSuper = (ListView) root.findViewById(R.id.list_view_timings);
//        listViewSuper.setAdapter(timingsArrayAdapter);
//
//        this.registerForContextMenu(listViewSuper);
//
//        return root;
//    }
//}
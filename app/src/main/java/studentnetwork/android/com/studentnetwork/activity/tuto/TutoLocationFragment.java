package studentnetwork.android.com.studentnetwork.activity.tuto;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import studentnetwork.android.com.studentnetwork.R;

public class TutoLocationFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "TutoLocationFragment";

    public TutoLocationFragment() {
    }

    public static TutoLocationFragment newInstance(int sectionNumber) {
        TutoLocationFragment fragment = new TutoLocationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tuto_location_fragment, container, false);
        return rootView;
    }
}

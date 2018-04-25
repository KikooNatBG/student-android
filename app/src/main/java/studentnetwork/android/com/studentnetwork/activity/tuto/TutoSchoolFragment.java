package studentnetwork.android.com.studentnetwork.activity.tuto;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;


import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bll.SchoolService;

public class TutoSchoolFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "TutoSchoolFragment =>" ;
    public static final String FRAGMENT_NAME = "School" ;
    private SearchableSpinner spinner;

    public TutoSchoolFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TutoSchoolFragment newInstance(int sectionNumber) {
        TutoSchoolFragment fragment = new TutoSchoolFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tuto_school_fragment, container, false);

        SchoolService service = new SchoolService(getActivity(), FRAGMENT_NAME);
        service.getList(getActivity());

//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}

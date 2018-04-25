package studentnetwork.android.com.studentnetwork.activity.tuto;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import studentnetwork.android.com.studentnetwork.R;

public class TutoExplainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public TutoExplainFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TutoExplainFragment newInstance(int sectionNumber) {
        TutoExplainFragment fragment = new TutoExplainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tuto_explain_fragment, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}

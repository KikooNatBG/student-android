package studentnetwork.android.com.studentnetwork.activity.core;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.activity.tuto.TutoEndFragment;
import studentnetwork.android.com.studentnetwork.bll.ContentService;

/**
 * Created by Administrateur on 26/04/2018.
 */

public class CommunityFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String FRAGMENT_NAME = "Community" ;

    public static CommunityFragment newInstance() {
        CommunityFragment fragment = new CommunityFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.community_fragment, container, false);
        ContentService contentService = new ContentService(getActivity(), FRAGMENT_NAME);
        contentService.getContents(getActivity());
        return rootView;
    }
}

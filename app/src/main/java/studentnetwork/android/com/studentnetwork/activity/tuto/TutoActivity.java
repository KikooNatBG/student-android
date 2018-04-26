package studentnetwork.android.com.studentnetwork.activity.tuto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.activity.core.NetworkActivity;
import studentnetwork.android.com.studentnetwork.bll.SchoolService;
import studentnetwork.android.com.studentnetwork.bo.School;
import studentnetwork.android.com.studentnetwork.utils.NoSwipeableViewPager;

public class TutoActivity extends AppCompatActivity implements SchoolService.SchoolListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ImageButton nextButton;

    ImageView zero, one, two, three;
    ImageView[] indicators;
    int lastLeftValue = 0;

    static final String TAG = "PagerActivity";

    int page = 0;   //  to track page position


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        nextButton = (ImageButton) findViewById(R.id.intro_btn_next);

        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        three = (ImageView) findViewById(R.id.intro_indicator_3);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (NoSwipeableViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        indicators = new ImageView[]{zero, one, two, three};

        mViewPager.setCurrentItem(page);
        updateIndicators(page);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page == indicators.length - 1) {
                    Intent i = new Intent(TutoActivity.this, NetworkActivity.class);
                    startActivity(i);
                } else {
                    page += 1;
                    mViewPager.setCurrentItem(page, true);
                }
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                page = position;

                updateIndicators(page);

                //nextButton.setVisibility(position == indicators.length - 1 ? View.GONE : View.VISIBLE);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TutoExplainFragment.newInstance(position);
                case 1:
                    return TutoSchoolFragment.newInstance(position);
                case 2:
                    return TutoLocationFragment.newInstance(position);
                case 3:
                    return TutoEndFragment.newInstance(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "EXPLICATION";
                case 1:
                    return "SCHOOL";
                case 2:
                    return "CITY";
                case 3:
                    return "END";
            }
            return null;
        }
    }

    @Override
    public void onResultOne(School school) {
        //TODO
    }

    @Override
    public void onFragmentResultList(ArrayList<School> schools, String fragment) {
        Log.d(TAG, schools.toString());
        if (fragment != null) {
            switch (fragment) {
                case TutoSchoolFragment.FRAGMENT_NAME:
                    SearchableSpinner spinner = (SearchableSpinner) findViewById(R.id.school_spinner);
                    ArrayAdapter<School> adapter = new ArrayAdapter<>(this,
                            R.layout.searchable_spinner, schools);
                    adapter.setDropDownViewResource(R.layout.searchable_spinner);
                    spinner.setAdapter(adapter);
                    spinner.setTitle("Sélectionner votre école");
                    spinner.setPositiveButton("OK");
                    break;
                default:
                    Log.d(TAG, "Fragment inconnu");
            }
        }
    }
}

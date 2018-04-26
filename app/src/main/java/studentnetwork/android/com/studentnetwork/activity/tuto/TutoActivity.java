package studentnetwork.android.com.studentnetwork.activity.tuto;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.activity.core.NetworkActivity;
import studentnetwork.android.com.studentnetwork.bll.SchoolService;
import studentnetwork.android.com.studentnetwork.bll.UserService;
import studentnetwork.android.com.studentnetwork.bo.Location;
import studentnetwork.android.com.studentnetwork.bo.School;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;
import studentnetwork.android.com.studentnetwork.utils.NoSwipeableViewPager;

public class TutoActivity extends AppCompatActivity implements SchoolService.SchoolListener, UserService.UserServiceListener {
    static final String TAG = "PagerActivity";
    private User user = SharedPreferencesManager.getInstance(this).getUser();
    private ViewPager mViewPager;
    private int page = 0;
    private ImageView[] indicators;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView zero, one, two, three;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ImageButton nextButton = (ImageButton) findViewById(R.id.intro_btn_next);
        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        three = (ImageView) findViewById(R.id.intro_indicator_3);
        mViewPager = (NoSwipeableViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(page);
        indicators = new ImageView[]{zero, one, two, three};
        updateIndicators(page);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page == indicators.length - 1) {
                    SharedPreferencesManager.getInstance(TutoActivity.this).setUser(user);
                    UserService service = new UserService(TutoActivity.this);
                    service.update(TutoActivity.this);
                } else {
                    if (validateDataByPage(page)) {
                        page += 1;
                        mViewPager.setCurrentItem(page, true);
                        initData(page);
                    } else {
                        Toast.makeText(TutoActivity.this, "Renseignement obligatoire", Toast.LENGTH_SHORT).show();
                    }
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserResult(User user) {
        Log.d(TAG, "onUserResult");
        SharedPreferencesManager.getInstance(this).setUser(user);
        Intent i = new Intent(TutoActivity.this, NetworkActivity.class);
        startActivity(i);
    }

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
    }

    @Override
    public void onFragmentResultList(final ArrayList<School> schools, String fragment) {
        Log.d(TAG, schools.toString());
        if (fragment != null) {
            switch (fragment) {
                case TutoSchoolFragment.FRAGMENT_NAME:
                    final SearchableSpinner spinner = (SearchableSpinner) findViewById(R.id.school_spinner);
                    ArrayAdapter<School> adapter = new ArrayAdapter<>(this,
                            R.layout.searchable_spinner, schools);
                    adapter.setDropDownViewResource(R.layout.searchable_spinner);
                    spinner.setAdapter(adapter);
                    spinner.setTitle("Sélectionner votre école");
                    spinner.setPositiveButton("OK");
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.d(TAG, spinner.getSelectedItem().toString());
                            ArrayList<School> schools = new ArrayList<>();
                            schools.add((School) spinner.getSelectedItem());
                            user.setSchools(schools);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            Log.d(TAG, "No Selected Item");
                        }
                    });
                    break;
                default:
                    Log.d(TAG, "Fragment inconnu");
            }
        }
    }

    public boolean validateDataByPage(int page) {
        Boolean ok = true;
        switch (page) {
            case 1:
                ok = user.getSchools().size() > 0;
                break;
            case 2:
                ok = user.getLocation() != null;
                break;
            default:
                Log.d(TAG, String.valueOf(page));
        }
        return ok;
    }

    public void initData(int page) {
        Log.d(TAG, "initData");
        switch (page) {
            case 1:
                SearchableSpinner spinner = (SearchableSpinner) findViewById(R.id.school_spinner);
                ArrayList<School> schools = new ArrayList<>();
                schools.add((School) spinner.getSelectedItem());
                user.setSchools(schools);
                Log.d(TAG, schools.toString());
                break;
            case 2:
                PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                        getFragmentManager().findFragmentById(R.id.city_input);

                autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        Log.d(TAG, place.toString());
                        double lat = place.getLatLng().latitude;
                        double lon = place.getLatLng().longitude;
                        try {
                            Location location = getLocation(lat, lon);
                            Log.d(TAG, location.toString());
                            user.setLocation(location);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "An error occurred: " + status);
                    }
                });
                break;
            default:
                Log.d(TAG, String.valueOf(page));
        }
    }

    private Location getLocation(double lat, double lon) throws IOException {
        Location location = null;
        Geocoder mGeocoder = new Geocoder(TutoActivity.this);
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            location = new Location(address.getLocality(),
                    address.getAddressLine(0),
                    address.getPostalCode());
            ;
            Log.d(TAG, address.toString());
        }
        return location;
    }
}

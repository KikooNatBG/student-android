package studentnetwork.android.com.studentnetwork.activity.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.activity.LoginActivity;
import studentnetwork.android.com.studentnetwork.activity.tuto.TutoSchoolFragment;
import studentnetwork.android.com.studentnetwork.bll.ContentService;
import studentnetwork.android.com.studentnetwork.bo.Content;
import studentnetwork.android.com.studentnetwork.bo.School;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;
import studentnetwork.android.com.studentnetwork.fragment.CardItemFragment;
import studentnetwork.android.com.studentnetwork.fragment.MyCardItemRecyclerViewAdapter;
import studentnetwork.android.com.studentnetwork.fragment.dummy.CommunityCardContents;

public class NetworkActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ContentService.ContentListener {

    private static final String TAG = "NetworkActivity => ";
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ImageView picture;
    private TextView txtIdentity;
    private TextView txtMail;
    private BottomNavigationView bottomNavigationView;
   // private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View menuHeaderView = navigationView.getHeaderView(0);
        picture = (ImageView) menuHeaderView.findViewById(R.id.menu_image);
        txtIdentity = (TextView) menuHeaderView.findViewById(R.id.menu_name);
        txtMail = (TextView) menuHeaderView.findViewById(R.id.menu_mail);
//        rv = (RecyclerView) findViewById(R.id.recycler_card_view);
//
//        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
//        rv.setLayoutManager(llm);

        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_community:
                                Log.i(TAG,"Go to Community View");
                                selectedFragment = CommunityFragment.newInstance();
                                setTitle("Communauté");
                                break;
                            case R.id.action_tips:
                                Log.i(TAG,"Go to Tips View");
                                selectedFragment = TipsFragment.newInstance();
                                setTitle("Bons plans");
                                break;
                            case R.id.action_settings:
                                Log.i(TAG,"Go to Settings View");
                                selectedFragment = SettingsFragment.newInstance();
                                setTitle("Réglages");
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.core_frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.core_frame_layout, CommunityFragment.newInstance());
        transaction.commit();
        bottomNavigationView.setSelectedItemId(R.id.action_community);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = SharedPreferencesManager.getInstance(this).getUser();
        String pictureUrl = user.getPictureUrl();
        picture.setImageResource(pictureUrl != null  ? R.drawable.user_default : R.drawable.user_default);
        txtIdentity.setText(user.getFirstName() + " " + user.getLastName());
        txtMail.setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.network, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
         if (id == R.id.nav_exit){
            SharedPreferencesManager.getInstance(this).setUser(null);
            Intent i = new Intent(NetworkActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onContentResult(ArrayList<Content> contents, String fragment) {
        Log.d(TAG, contents.toString());
        if (fragment != null) {
            switch (fragment) {
                case CommunityFragment.FRAGMENT_NAME:
                    final SearchableSpinner spinner = (SearchableSpinner) findViewById(R.id.school_spinner);
                    MyCardItemRecyclerViewAdapter adapter = new MyCardItemRecyclerViewAdapter(contents,
                            new CardItemFragment.OnListFragmentInteractionListener() {
                                @Override
                                public void onListFragmentInteraction(Content content) {
                                    Log.i(TAG,"Touch");
                                }
                            }, getApplicationContext());
                    CommunityCardContents.ITEMS = contents;
                    //adapter.setDropDownViewResource(R.layout.searchable_spinner);
                    //spinner.setAdapter(adapter);
                    //spinner.setTitle("Sélectionner votre école");
                    //spinner.setPositiveButton("OK");

                    break;
                default:
                    Log.d(TAG, "Fragment inconnu");
            }
        }
    }

    @Override
    public void onContentResultByType(ArrayList<Content> contents, String fragment) {

    }
}

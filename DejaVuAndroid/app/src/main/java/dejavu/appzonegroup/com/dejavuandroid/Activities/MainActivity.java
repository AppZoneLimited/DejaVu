package dejavu.appzonegroup.com.dejavuandroid.Activities;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.Contact;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Interface.FlowSynchronisationListener;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.ContactSyncService;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Service.FlowSyncService;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.Utils.ZoneDataUtils;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.HistorytabFragment;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.MessageTabFragment;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.PeopleTabFragment;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class MainActivity extends ActionBarActivity implements FlowSynchronisationListener {

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private DrawerLayout drawerLayoutt;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] navigationDrawerItems;

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ZONE");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.light_blue));
        setSupportActionBar(toolbar);
        navigationDrawerItems = getResources().getStringArray(R.array.menu);
        drawerLayoutt = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        drawerLayoutt.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navigationDrawerItems));
        //listView.setOnItemClickListener(new DrawerItemClickListener());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayoutt, toolbar, R.string.app_name, R.string.app_name);
        drawerLayoutt.setDrawerListener(actionBarDrawerToggle);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

        ZoneDataUtils.syncDB(MainActivity.this);
        if (!new UserDetailsSharePreferences(this).isFirstFlowDownloaded()) {

            if (ZoneDataUtils.isNetworkAvailable(MainActivity.this)) {
                FlowSyncService.startActionSync(MainActivity.this,
                        new UserDetailsSharePreferences(MainActivity.this).getUserPhoneNumber(),
                        MainActivity.this);
                new UserDetailsSharePreferences(this).setFirstFlowDownloaded(true);
            } else {
//                new ShowMessage(MainActivity.this, "confirm failure", Toast.LENGTH_LONG);
            }
        }
        FlowSyncService.startActionSync(MainActivity.this,
                new UserDetailsSharePreferences(MainActivity.this).getUserPhoneNumber(),
                MainActivity.this);

        ContactSyncService.startContactSync(this);
        PeopleTabFragment.mZones = Contact.getZoneContact(this);
        PeopleTabFragment.mNotZones = Contact.getNotZoneContact(this);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        tabs.setIndicatorHeight(5);
        tabs.setIndicatorColor(getResources().getColor(android.R.color.white));
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDownloadSuccess() {
//        new ShowMessage(this, "category success", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDownloadFailure() {
//        new ShowMessage(MainActivity.this, "category failure", Toast.LENGTH_SHORT);
    }

    @Override
    public void onFlowDownloadSuccess(String guid) {
//        new ShowMessage(MainActivity.this, "flow success", Toast.LENGTH_SHORT);
    }

    @Override
    public void onFlowDownloadFailure(String guid) {
//        new ShowMessage(MainActivity.this, "flow failure", Toast.LENGTH_SHORT);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {


        private final int[] ICON = {R.drawable.tabbar_home_selector, R.drawable.tabbar_following_selector, R.drawable.tabbar_profile_selector, R.drawable.tabbar_message_selector, R.drawable.tabbar_notifications_selector};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public int getCount() {
            return 5;
        }


        @Override
        public Fragment getItem(int position) {

            if (position == 0)
                return new FlipActivity();
            else if (position == 1)
                return new CategoryFragment();
            else if (position == 2)
                return new PeopleTabFragment();
            else if (position == 3)
                return new MessageTabFragment();
            else if (position == 4)
                return new HistorytabFragment();
            return new SuperAwesomeCardFragment().newInstance(position);

        }


        @Override
        public int getPageIconResId(int i) {
            return ICON[i];
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
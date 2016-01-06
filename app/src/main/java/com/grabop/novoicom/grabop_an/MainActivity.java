package com.grabop.novoicom.grabop_an;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BlankFragment.OnFragmentInteractionListener, View.OnClickListener {

    //tag associated with the FAB menu button that sorts by name
    private static final String TAG_SORT_NAME = "sortName";
    //tag associated with the FAB menu button that sorts by date
    private static final String TAG_SORT_DATE = "sortDate";
    //tag associated with the FAB menu button that sorts by ratings
    private static final String TAG_SORT_RATINGS = "sortRatings";

    private FloatingActionButton mFAB;
    private FloatingActionMenu mFABMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        setupFAB();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        final ImageView imageView = (ImageView) findViewById(R.id.ivUserProfilePhoto);
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.user_profile_avatar_size);
//        this.profilePhoto = getString(R.string.user_profile_photo);

        Picasso.with(this)
                .load(R.drawable.cheese_1)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(imageView);

//        Picasso.with(this).load(R.drawable.cheese_1).into(imageView);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        int icons[] = {R.drawable.ic_place_white,
                R.drawable.ic_label_white,
                R.drawable.ic_list_white};

        tabLayout.getTabAt(0).setIcon(icons[0]);
        tabLayout.getTabAt(1).setIcon(icons[1]);
        tabLayout.getTabAt(2).setIcon(icons[2]);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.style_color_accent));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        switch (item.getItemId()) {
            case R.id.action_search:
                // get the icon's location on screen to pass through to the search screen
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                View searchMenuView = toolbar.findViewById(R.id.menu_search);
//                int[] loc = new int[2];
//                searchMenuView.getLocationOnScreen(loc);
//                startActivityForResult(SearchActivity.createStartIntent(this, loc[0], loc[0] +
//                        (searchMenuView.getWidth() / 2)), RC_SEARCH, ActivityOptions
//                        .makeSceneTransitionAnimation(this).toBundle());
//                searchMenuView.setAlpha(0f);
                return true;
//            case R.id.menu_dribbble_login:
//                if (!dribbblePrefs.isLoggedIn()) {
//                    dribbblePrefs.login(HomeActivity.this);
//                } else {
//                    dribbblePrefs.logout();
//                    // TODO something better than a toast!!
//                    Toast.makeText(getApplicationContext(), R.string.dribbble_logged_out, Toast
//                            .LENGTH_SHORT).show();
//                }
//                return true;
//            case R.id.menu_designer_news_login:
//                if (!designerNewsPrefs.isLoggedIn()) {
//                    startActivity(new Intent(this, DesignerNewsLogin.class));
//                } else {
//                    designerNewsPrefs.logout();
//                    // TODO something better than a toast!!
//                    Toast.makeText(getApplicationContext(), R.string.designer_news_logged_out,
//                            Toast.LENGTH_SHORT).show();
//                }
//                return true;
            case R.id.action_settings:
//                startActivity(new Intent(HomeActivity.this, AboutActivity.class),
//                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
//            Context context = v.getContext();
            Intent intent = new Intent(this, LoginActivity.class);

            this.startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(BlankFragment.newInstance("", ""), "People Nearby");
        adapter.addFragment(BlankFragment.newInstance("", ""), "My Profile");
        adapter.addFragment(BlankFragment.newInstance("", ""), "My Connection");
        viewPager.setAdapter(adapter);
    }

    private void setupFAB() {
        //define the icon for the main floating action button
        ImageView iconFAB = new ImageView(this);
        iconFAB.setImageResource(R.drawable.ic_action_new);

        //set the appropriate background for the main floating action button along with its icon
        mFAB = new FloatingActionButton.Builder(this)
                .setContentView(iconFAB)
                .setBackgroundDrawable(R.drawable.selector_button_red)
                .build();

        //define the icons for the sub action buttons
        ImageView iconSortName = new ImageView(this);
        iconSortName.setImageResource(R.drawable.ic_action_alphabets);
        ImageView iconSortDate = new ImageView(this);
        iconSortDate.setImageResource(R.drawable.ic_action_calendar);
        ImageView iconSortRatings = new ImageView(this);
        iconSortRatings.setImageResource(R.drawable.ic_action_important);

        //set the background for all the sub buttons
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));


        //build the sub buttons
        SubActionButton buttonSortName = itemBuilder.setContentView(iconSortName).build();
        SubActionButton buttonSortDate = itemBuilder.setContentView(iconSortDate).build();
        SubActionButton buttonSortRatings = itemBuilder.setContentView(iconSortRatings).build();

        //to determine which button was clicked, set Tags on each button
        buttonSortName.setTag(TAG_SORT_NAME);
        buttonSortDate.setTag(TAG_SORT_DATE);
        buttonSortRatings.setTag(TAG_SORT_RATINGS);

        buttonSortName.setOnClickListener(this);
        buttonSortDate.setOnClickListener(this);
        buttonSortRatings.setOnClickListener(this);

        //add the sub buttons to the main floating action button
        mFABMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonSortName)
                .addSubActionView(buttonSortDate)
                .addSubActionView(buttonSortRatings)
                .attachTo(mFAB)
                .build();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals(TAG_SORT_NAME)) {
            //call the sort by name method on any Fragment that implements sortlistener
        }
        if (v.getTag().equals(TAG_SORT_DATE)) {
            //call the sort by date method on any Fragment that implements sortlistener
        }
        if (v.getTag().equals(TAG_SORT_RATINGS)) {
            //call the sort by ratings method on any Fragment that implements sortlistener
        }

    }

    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

//        private Drawable getIcon(int position) {
//            return getResources().getDrawable(icons[position]);
//        }

    }

}

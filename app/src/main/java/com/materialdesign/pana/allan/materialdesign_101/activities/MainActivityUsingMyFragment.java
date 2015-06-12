package com.materialdesign.pana.allan.materialdesign_101.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.materialdesign.pana.allan.materialdesign_101.R;
import com.materialdesign.pana.allan.materialdesign_101.fragments.MyFragment;
import com.materialdesign.pana.allan.materialdesign_101.fragments.NavigationDrawerFragment;
import com.materialdesign.pana.allan.materialdesign_101.tabWithLibrary.TabsWithLibraryActivity;
import com.materialdesign.pana.allan.materialdesign_101.tabs.SlidingTabLayout;


public class MainActivityUsingMyFragment extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SlidingTabLayout mTabs;
    private ViewPager mPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager()
                                                    .findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer,drawerLayout,toolbar);

        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.textView_tab);
        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
        mTabs.setViewPager(mPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(this, item.getTitle(),Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_navigate) {
            Intent intent = new Intent(this,SubActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_tabsWithLibrary) {
            Intent intent = new Intent(this, TabsWithLibraryActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_vectorTestActivity) {
            Intent intent = new Intent(this, VectorTestActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * MyPageAdapter class
     */
    class MyPagerAdapter extends FragmentStatePagerAdapter {

        String []tabs;
        /*int [] tabIcons = {R.mipmap.ic_account_box_black_18dp,
                           R.mipmap.ic_accessibility_black_18dp,
                           R.mipmap.ic_add_shopping_cart_black_18dp};*/
        int [] tabIcons = {R.drawable.vectorized_android,
                R.drawable.vectorized_android,
                R.drawable.vectorized_android};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {

            MyFragment myFragment = MyFragment.getInstance(position);
            return myFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            //Drawable drawable = getResources().getDrawable(tabIcons[position]);  //Deprecated
            Drawable drawable = ResourcesCompat.getDrawable(getResources(),tabIcons[position], null);
            drawable.setBounds(0,0,72,72);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
    }


}

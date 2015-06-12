package com.materialdesign.pana.allan.materialdesign_101.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesign.pana.allan.materialdesign_101.adapters.MyRecycleViewAdapter;
import com.materialdesign.pana.allan.materialdesign_101.R;
import com.materialdesign.pana.allan.materialdesign_101.activities.SubActivity;
import com.materialdesign.pana.allan.materialdesign_101.model.Information;
import com.materialdesign.pana.allan.materialdesign_101.util.CustomTagNToast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements MyRecycleViewAdapter.MyItemClickListener {

    private static final String PREF_FILE_NAME = "testpref";
    private static final String KEY_USER_LEARNED_DAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private RecyclerView recyclerView;
    private MyRecycleViewAdapter recycleViewAdapter;
    private MyRecycleViewAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewInNavDRawer);
        adapter = new MyRecycleViewAdapter(getActivity(),getData());
        adapter.setMyItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    /**
     * Instantiate new Object of class Information, add them to the List
     * @return list of Information object
     */
    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int [] itemIds = {R.drawable.ic_domain_black_18dp, R.drawable.ic_group_add_black_18dp, R.drawable.ic_mood_black_18dp, R.drawable.ic_cake_black_18dp};
        String [] titles = {"My Kido", "Two", "Three", "Forward"};

        for(int i=0; i<50; i++){
            Information info = new Information();
            info.itemId = itemIds[i%itemIds.length];
            info.title = titles[i%titles.length];
            data.add(info);
        }
        return data;
    }

    /**
     *SET UP the NavigationDrawer
     * @param fragmentId
     * @param drawerLayout
     * @param toolbar
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedToPreferences(getActivity(), KEY_USER_LEARNED_DAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset < 0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }


    /**
     *
     * @param context
     * @param prefName
     * @param prefValue
     */
    public static void savedToPreferences(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        //editor.commit();  ==>>>>   synchronous
        editor.apply();   //faster, your not going to notify if successful/unsuccessful, asynchronous
    }


    /**
     *
     * @param context
     * @param prefName
     * @param defaultValue
     * @return
     */
    public static String readFromPreferences(Context context, String prefName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, defaultValue);
    }


    @Override
    public void myItemClicked(View view, int position) {
        startActivity(new Intent(getActivity(), SubActivity.class));
    }

    @Override
    public void myTextViewClicked(View view, int position, TextView textView) {
        CustomTagNToast.setToast(getActivity(),textView.getText()+ " test 1234 position ");
    }
}

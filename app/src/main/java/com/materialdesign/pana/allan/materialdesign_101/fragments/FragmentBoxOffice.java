package com.materialdesign.pana.allan.materialdesign_101.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.materialdesign.pana.allan.materialdesign_101.R;
import com.materialdesign.pana.allan.materialdesign_101.network.VolleySingleton;
import com.materialdesign.pana.allan.materialdesign_101.util.CustomTagNToast;
import com.materialdesign.pana.allan.materialdesign_101.util.MyApplication;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoxOffice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBoxOffice extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URL_RT_BOX_OFFICE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;




    public static FragmentBoxOffice newInstance(String param1, String param2) {
        FragmentBoxOffice fragment = new FragmentBoxOffice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentBoxOffice() {
        // Required empty public constructor
    }

    public static String getRequestUrl(int item){
        /*String url = URL_RT_BOX_OFFICE+"?apikey="+ MyApplication.RT_API_KEY+"&limit="+item;*/
        String url = "http://tpbookserver.herokuapp.com/book/200";
        return url;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            volleySingleton = VolleySingleton.getInstance();
            requestQueue = volleySingleton.getRequestQueue();
            imageLoader = volleySingleton.getImageLoader();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    getRequestUrl(10),
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            CustomTagNToast.setLog(jsonObject.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            CustomTagNToast.setLog(volleyError.toString());
                        }
                    });

            volleySingleton.addToRequestQueue(request);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_box_office, container, false);
    }


}

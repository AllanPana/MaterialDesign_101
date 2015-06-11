package com.materialdesign.pana.allan.materialdesign_101;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.materialdesign.pana.allan.materialdesign_101.network.VolleySingleton;
import com.materialdesign.pana.allan.materialdesign_101.util.CustomTagNToast;

/**
 * Created by allan on 10/06/15.
 */
public class MyFragment extends Fragment {

    private TextView textView;
    public static MyFragment getInstance(int position){
        MyFragment myFragment = new MyFragment();
        Bundle args =  new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container, false);
        textView = (TextView) view.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if(bundle!=null){
            textView.setText("The page selected is"+bundle.getInt("position"));
        }
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://tpbookserver.herokuapp.com/book/300",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        CustomTagNToast.setToast(getActivity(),s.toString());
                        CustomTagNToast.setLog(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        CustomTagNToast.setToast(getActivity(),volleyError.toString());
                    }
                });

        VolleySingleton.getInstance().addToRequestQueue(stringRequest);
        return view;
    }
}
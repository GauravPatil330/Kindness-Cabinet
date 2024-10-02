package com.gauravpatil.kindnesscabinet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gauravpatil.kindnesscabinet.java_classes.AdapterGetAllCategoryDetails;
import com.gauravpatil.kindnesscabinet.java_classes.POJOGetAllCategoryDetails;
import com.gauravpatil.kindnesscabinet.java_classes.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CategoriesFragment extends Fragment
{
    ListView lvShowAllCategory;
    TextView tvNoCategoryAvailable;
    List<POJOGetAllCategoryDetails> pojoGetAllCategoryDetailsList;
    AdapterGetAllCategoryDetails adapterGetAllCategoryDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        pojoGetAllCategoryDetailsList = new ArrayList<>();
        lvShowAllCategory = view.findViewById(R.id.lvCategoryFragmentShowMultipleCategory);
        tvNoCategoryAvailable = view.findViewById(R.id.tvCategoryFragmentNoCategoryAvailable);

        getAllCategory();

        return view;
    }

    private void getAllCategory()
    {
        //Client and Server Communication over network data transfer or manipulate
        AsyncHttpClient client = new AsyncHttpClient(); //Client and Server Communication
        RequestParams params = new RequestParams(); // Put the data

        client.post(Urls.getAllCategoryDetails,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                try
                {
                    JSONArray jsonArray = response.getJSONArray("getAllcategory");

                    if(jsonArray.isNull(0))
                    {
                        tvNoCategoryAvailable.setVisibility(View.VISIBLE);
                    }

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String strid = jsonObject.getString("id");
                        String strCategoryImage = jsonObject.getString("categoryimage");
                        String strCategoryName = jsonObject.getString("categoryname");

                        pojoGetAllCategoryDetailsList.add(new POJOGetAllCategoryDetails(strid,strCategoryImage,strCategoryName));

                    }

                    adapterGetAllCategoryDetails = new AdapterGetAllCategoryDetails(pojoGetAllCategoryDetailsList,getActivity());

                    lvShowAllCategory.setAdapter(adapterGetAllCategoryDetails);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
            {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_LONG).show();
            }
        });

    }
}
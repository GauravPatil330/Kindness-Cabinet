package com.gauravpatil.kindnesscabinet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gauravpatil.kindnesscabinet.java_classes.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment
{
    CircleImageView civProfilePhoto;
    EditText etName,etMobileNo,etEmailid,etAddress,etGender,etAge,etUsername;
    Button btnSaveChanges;
    ProgressDialog progressDialog;
    SharedPreferences preferences; // Store temporary data within app
    String strusername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        civProfilePhoto = view.findViewById(R.id.civProfilePhoto);
        etName = view.findViewById(R.id.etProfileName);
        etMobileNo = view.findViewById(R.id.etProfileMobileNo);
        etEmailid = view.findViewById(R.id.etProfileEmailId);
        etGender = view.findViewById(R.id.etProfileGender);
        etAge = view.findViewById(R.id.etProfileAge);
        etAddress = view.findViewById(R.id.etProfileAddress);
        etUsername = view.findViewById(R.id.etProfileUsername);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        strusername = preferences.getString("username","");

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("My Profile");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        getMydetails();
    }
    private void getMydetails()
    {
        //Client and Server Communication over network data transfer or manipulate
        AsyncHttpClient client = new AsyncHttpClient(); //Client and Server Communication
        RequestParams params = new RequestParams(); //put the data in AsyncHttpClient object

        params.put("username",strusername);

        client.post(Urls.myDetails,params,new JsonHttpResponseHandler()
         {
             @Override
             public void onSuccess(int statusCode, Header[] headers, JSONObject response)
             {
                 super.onSuccess(statusCode, headers, response);
                 try
                 {
                     JSONArray jsonArray = response.getJSONArray("getMyDetails");

                     for(int i=0; i<jsonArray.length(); i++)
                     {
                         JSONObject jsonObject = jsonArray.getJSONObject(i);

                         String strid = jsonObject.getString("id");
                         String strImage = jsonObject.getString("image");
                         String strName = jsonObject.getString("name");
                         String strMobileNo = jsonObject.getString("mobileno");
                         String strEmailID = jsonObject.getString("emailid");
                         String strGender = jsonObject.getString("gender");
                         String strAge = jsonObject.getString("age");
                         String StrAddress = jsonObject.getString("address");
                         String strUsername = jsonObject.getString("username");

                         etName.setText(strName);
                         etMobileNo.setText(strMobileNo);
                         etEmailid.setText(strEmailID);
                         etGender.setText(strGender);
                         etAge.setText(strAge);
                         etAddress.setText(StrAddress);
                         etUsername.setText(strUsername);

                         Glide.with(getActivity())
                                 .load(Urls.image +strImage)
                                 .skipMemoryCache(true)
                                 .error(R.drawable.image_profile)
                                 .into(civProfilePhoto);
                     }

                     progressDialog.dismiss();
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
                 progressDialog.dismiss();
             }
          });
    }
}
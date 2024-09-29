package com.gauravpatil.kindnesscabinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gauravpatil.kindnesscabinet.java_classes.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SetUp_New_Password_Activity extends AppCompatActivity {
    EditText etNewPassword,etConfirmPassword;
    AppCompatButton btnSetPassword;
    String strMobileNo;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_new_password);

        etNewPassword = findViewById(R.id.etSetUpNewPasswordNewPassword);
        etConfirmPassword = findViewById(R.id.etSetUpNewPasswordConfirmPassword);
        btnSetPassword = findViewById(R.id.btnSetUpNewPasswordSetPassword);

        strMobileNo = getIntent().getStringExtra("mobileno");

        btnSetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(etNewPassword.getText().toString().isEmpty() || etConfirmPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(SetUp_New_Password_Activity.this,"Please Enter New or Confirm Password",Toast.LENGTH_SHORT).show();
                }
                else if(!etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
                {
                    Toast.makeText(SetUp_New_Password_Activity.this,"Password did not match",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog = new ProgressDialog(SetUp_New_Password_Activity.this);
                    progressDialog.setTitle("Updating Password...");
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    forgetPassword();
                }
            }
        });
    }

    private void forgetPassword()
    {
        AsyncHttpClient client = new AsyncHttpClient(); // Client server Communication
        RequestParams params = new RequestParams();     // Put Data

        params.put("mobileno",strMobileNo);
        params.put("password",etNewPassword.getText().toString());

        client.post(Urls.forgetpassword,params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                    {
                        super.onSuccess(statusCode, headers, response);
                        try
                        {
                            String status = response.getString("Success");
                            if(status.equals("1"))
                            {
                                Toast.makeText(SetUp_New_Password_Activity.this, "Password was Chnaged", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SetUp_New_Password_Activity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(SetUp_New_Password_Activity.this, "Password not Chnaged", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}
package com.gauravpatil.kindnesscabinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class Forget_Password_Verify_OTP_Activity extends AppCompatActivity
{
    TextView tvMobileNo,tvResentOTP;
    EditText etInputCode1,etInputCode2,etInputCode3,etInputCode4,etInputCode5,etInputCode6;
    AppCompatButton btnVerify;
    ProgressDialog progressDialog;
    private String strVerificationCode,strMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);

        tvMobileNo  = findViewById(R.id.tvVerifyOTPMobileNo);
        tvResentOTP  = findViewById(R.id.tvVerifyOTPResentOTP);
        etInputCode1 = findViewById(R.id.etVerifyOTPInputCode1);
        etInputCode2 = findViewById(R.id.etVerifyOTPInputCode2);
        etInputCode3 = findViewById(R.id.etVerifyOTPInputCode3);
        etInputCode4 = findViewById(R.id.etVerifyOTPInputCode4);
        etInputCode5 = findViewById(R.id.etVerifyOTPInputCode5);
        etInputCode6 = findViewById(R.id.etVerifyOTPInputCode6);
        btnVerify    = findViewById(R.id.btnVerifyOTPVerify);

        strVerificationCode = getIntent().getStringExtra("verificationcode");
        strMobileNo = getIntent().getStringExtra("mobileno");

        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(etInputCode1.getText().toString().trim().isEmpty() ||
                        etInputCode2.getText().toString().trim().isEmpty() ||
                        etInputCode3.getText().toString().trim().isEmpty() ||
                        etInputCode4.getText().toString().trim().isEmpty() ||
                        etInputCode5.getText().toString().trim().isEmpty() ||
                        etInputCode6.getText().toString().trim().isEmpty() )
                {
                    Toast.makeText(Forget_Password_Verify_OTP_Activity.this, "Please Enter Valid OTP", Toast.LENGTH_LONG).show();
                }

                String otpCode = etInputCode1.getText().toString() + etInputCode2.getText().toString() +
                        etInputCode3.getText().toString() + etInputCode4.getText().toString() +
                        etInputCode5.getText().toString() + etInputCode6.getText().toString();

                if(strVerificationCode != null)
                {
                    progressDialog = new ProgressDialog(Forget_Password_Verify_OTP_Activity.this);
                    progressDialog.setTitle("Verifying OTP...");
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(strVerificationCode,otpCode);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(Forget_Password_Verify_OTP_Activity.this,SetUp_New_Password_Activity.class);
                                        intent.putExtra("mobileno",strMobileNo);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(Forget_Password_Verify_OTP_Activity.this, "OTP Verification Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        tvResentOTP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + strMobileNo, 60, TimeUnit.SECONDS, Forget_Password_Verify_OTP_Activity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                    {
                        Toast.makeText(Forget_Password_Verify_OTP_Activity.this, "Verification Completed", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e)
                    {
                        Toast.makeText(Forget_Password_Verify_OTP_Activity.this, "Verification Failed", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        strVerificationCode = s;
                    }
                });
            }
        });

        setUpInputOTP();
    }
    private void setUpInputOTP()
    {
        etInputCode1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.toString().trim().isEmpty())
                {
                    etInputCode2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        etInputCode2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.toString().trim().isEmpty())
                {
                    etInputCode3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        etInputCode3.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.toString().trim().isEmpty())
                {
                    etInputCode4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        etInputCode4.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.toString().trim().isEmpty())
                {
                    etInputCode5.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        etInputCode5.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.toString().trim().isEmpty())
                {
                    etInputCode6.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }
}
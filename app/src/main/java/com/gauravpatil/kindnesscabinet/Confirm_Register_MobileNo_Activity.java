package com.gauravpatil.kindnesscabinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Confirm_Register_MobileNo_Activity extends AppCompatActivity
{
    EditText etMobileNo;
    AppCompatButton btnVerify;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register_mobile_no);

        etMobileNo = findViewById(R.id.etConfirmRegisterMobileNoMobileNo);
        btnVerify = findViewById(R.id.btnConfirmRegisterMobileNoVerify);

        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(etMobileNo.getText().toString().isEmpty())
                {
                etMobileNo.setError("Please Enter Your Mobile Number");
                }
                else if (etMobileNo.getText().toString().length() != 10)
                {
                    etMobileNo.setError("Mobile Number must be 10 Digit");
                }
                else
                {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + etMobileNo.getText().toString(), 60, TimeUnit.SECONDS,
                    Confirm_Register_MobileNo_Activity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                    {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                        {
                            Toast.makeText(Confirm_Register_MobileNo_Activity.this, "Verifying Mobile Number",Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e)
                        {
                            Toast.makeText(Confirm_Register_MobileNo_Activity.this, "Mobile Number Verification Failed", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                        {
                            Intent intent = new Intent(Confirm_Register_MobileNo_Activity.this,Forget_Password_Verify_OTP_Activity.class);
                            intent.putExtra("verificationcode",s); //key => String,Value
                            intent.putExtra("mobileno",etMobileNo.getText().toString());
                            startActivity(intent);
                        }
                    });
                }
            }
        });

    }
}
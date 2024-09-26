package com.gauravpatil.kindnesscabinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity
{
    EditText etName,etEmailid,etMobileNo,etAddress,etGender,etAge,etUsername,etPassword,etConfirmPassword;
    Button btnRegister;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.etRegisterName);
        etEmailid = findViewById(R.id.etRegisterEmailId);
        etMobileNo = findViewById(R.id.etRegisterMobileNo);
        etAddress = findViewById(R.id.etRegisterAddress);
        etGender = findViewById(R.id.etRegisterGender);
        etAge = findViewById(R.id.etRegisterAge);
        etUsername = findViewById(R.id.etRegisterUsername);
        etPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        btnRegister = findViewById(R.id.btnRegisterRegister);

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (etName.getText().toString().isEmpty())
                {
                    etName.setError("Please Enter Your Name");
                }
                else  if(etMobileNo.getText().toString().isEmpty())
                {
                    etMobileNo.setError("Please Enter Your Mobile Number");
                }
                else  if (etEmailid.getText().toString().isEmpty())
                {
                    etEmailid.setError("Please Enter Your Email ID");
                }
                else if (etGender.getText().toString().isEmpty())
                {
                    etGender.setError("Please Enter Your Gender");
                }
                else  if (etAge.getText().toString().isEmpty())
                {
                    etAge.setError("Please Enter Your Age");
                }
                else  if (etAddress.getText().toString().isEmpty())
                {
                    etAddress.setError("Please Enter Your Address");
                }
                else  if (etUsername.getText().toString().isEmpty())
                {
                    etUsername.setError("Please Enter Your Username");
                }
                else if(etPassword.getText().toString().isEmpty())
                {
                    etPassword.setError("Please Enter Your Password");
                }
                else if(etConfirmPassword.getText().toString().isEmpty())
                {
                    etConfirmPassword.setError("Please Enter Your Confirm Password");
                }
                else if (etName.getText().toString().matches(".*[0-9].*"))
                {
                    etName.setError("Name Not allowed Number");
                }
                else if (etName.getText().toString().matches(".*[@#$%&!].*"))
                {
                    etName.setError("Name Not allowed Special symbol");
                }
                else if (etMobileNo.getText().toString().length() != 10)
                {
                    etMobileNo.setError("Mobile Number must be 10 Digit");
                }
                else if (etEmailid.getText().toString().contains(" "))
                {
                    etEmailid.setError("Email ID should not contain spaces");
                }
                else if (!etEmailid.getText().toString().matches(".*[0-9].*"))
                {
                    etEmailid.setError("Please used at least 1 Number");
                }
                else if (!etEmailid.getText().toString().matches(".*[@#$%&!].*"))
                {
                    etEmailid.setError("Please used at  least 1 Special Symbol");
                }
                else if (!etEmailid.getText().toString().contains("@gmail.com"))
                {
                    etEmailid.setError("Please Enter valid email ID");
                }
                else if (etGender.getText().toString().matches(".*[0-9].*"))
                {
                    etGender.setError("Gender Not allowed Number");
                }
                else if (etGender.getText().toString().matches(".*[@#$%&!].*"))
                {
                    etGender.setError("Gender Not allowed Special symbol");
                }
                else if (etAge.getText().toString().length() > 3)
                {
                    etAge.setError("Please Enter valid Age");
                }
                else if (etAddress.getText().toString().matches(".*[0-9].*"))
                {
                    etAddress.setError("Address Not allowed Number");
                }
                else if (etAddress.getText().toString().matches(".*[@#$%&!].*"))
                {
                    etAddress.setError("Address Not allowed Special symbol");
                }
                else if (etUsername.getText().toString().contains(" "))
                {
                    etUsername.setError("Username should not contain spaces");
                }
                else if (!etUsername.getText().toString().matches(".*[0-9].*"))
                {
                    etUsername.setError("Please used at least 1 Number");
                }
                else if (!etUsername.getText().toString().matches(".*[@#$%&!].*"))
                {
                    etUsername.setError("Please used at least 1 Special Symbol");
                }
                else if (etUsername.getText().toString().length() < 8)
                {
                    etUsername.setError("Username must be greater than 8 Character");
                }
                else if (etPassword.getText().toString().contains(" "))
                {
                    etPassword.setError("Password should not contain spaces");
                }
                else if (!etPassword.getText().toString().matches(".*[0-9].*"))
                {
                    etPassword.setError("Please used at least 1 Number");
                }
                else if (!etPassword.getText().toString().matches(".*[@#$%&!].*"))
                {
                    etPassword.setError("Please used at least 1 Special Symbol.");
                }
                else if (etPassword.getText().toString().length() < 8)
                {
                    etPassword.setError("Password must be greater than 8 Character");
                }
                else if (!etConfirmPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
                {
                    etConfirmPassword.setError("Password did not match");
                }
                else
                {
                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Please Wait...");
                    progressDialog.setMessage("Registration is in Progress...");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

//                  Verify Mobile No
//                  arg 1 => Jya mobile no varti OTP receive karaycha aahe
//                  arg 2 => 60
//                  arg 3 => TimeUnit
//                  arg 4 => Current Java Activity
//                  args 5 => call Back

                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + etMobileNo.getText().toString(), 60, TimeUnit.SECONDS, RegistrationActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                    {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Registration Completed", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                        {
                           Intent intent = new Intent(RegistrationActivity.this,VerifyOTPActivity.class);
                           intent.putExtra("verificationcode",s); //key => String,Value
                            intent.putExtra("name",etName.getText().toString());
                            intent.putExtra("mobileno",etMobileNo.getText().toString());
                            intent.putExtra("emailid",etEmailid.getText().toString());
                            intent.putExtra("gender",etGender.getText().toString());
                            intent.putExtra("age",etAge.getText().toString());
                            intent.putExtra("address",etAddress.getText().toString());
                            intent.putExtra("username",etUsername.getText().toString());
                            intent.putExtra("password",etPassword.getText().toString());
                           startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}
package com.gauravpatil.kindnesscabinet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class My_Profile_Activity extends AppCompatActivity
{
    TextView tvName,tvEmailID;
    AppCompatButton btnGoogleSignOut;
    GoogleSignInOptions googleSignInOptions; //Show option of gmail
    GoogleSignInClient googleSignInClient;  //Selected gmail option store

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        tvName = findViewById(R.id.tvMyProfileName);
        tvEmailID = findViewById(R.id.tvMyProfileEmailID);
        btnGoogleSignOut = findViewById(R.id.btnMyProfileGoogleSignOut);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(My_Profile_Activity.this,googleSignInOptions);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(googleSignInAccount != null)
        {
            String name = googleSignInAccount.getDisplayName();
            String emailid = googleSignInAccount.getEmail();

            tvName.setText(name);
            tvEmailID.setText(emailid);

            btnGoogleSignOut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            Intent intent = new Intent(My_Profile_Activity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
}
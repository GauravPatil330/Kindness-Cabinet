package com.gauravpatil.kindnesscabinet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity
{
    ImageView ivLogo;
    TextView tvTitle;
    Animation fadeInAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo = findViewById(R.id.ivSplashLogo);
        tvTitle = findViewById(R.id.tvSplashTitle);

        fadeInAnim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in_anim);

        ivLogo.setAnimation(fadeInAnim);
        tvTitle.setAnimation(fadeInAnim);

        Handler h= new Handler();
        h.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i= new Intent(SplashActivity.this,App_Info_Activity.class);
                startActivity(i);
            }
        }, 2000);
    }
}

//Class
//Object
//Methood
//Keyword
//Anotation,Special Symbol
package com.example.ridowanahmed.childlocator;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.ridowanahmed.childlocator.Registration.ParentDashboard;

public class MainActivity extends AppCompatActivity {

    ImageView imageView_parentLogin, imageView_childLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_parentLogin = (ImageView) findViewById(R.id.imageView_parentLogin);
        imageView_childLogin = (ImageView) findViewById(R.id.imageView_childLogin);


        imageView_parentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), ParentDashboard.class);
            startActivity(intent);

            }
        });

        imageView_childLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageView_childLogin.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}

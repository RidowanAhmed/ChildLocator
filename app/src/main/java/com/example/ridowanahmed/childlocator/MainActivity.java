package com.example.ridowanahmed.childlocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.ridowanahmed.childlocator.Registration.ChildLoginActivity;
import com.example.ridowanahmed.childlocator.Registration.ParentLoginActivity;

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

            Intent intent = new Intent(getApplicationContext(), ParentLoginActivity.class);
            startActivity(intent);

            }
        });

        imageView_childLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageView_childLogin.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);

                SharedPreferences mSharedPreferences;
                mSharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
                String name = mSharedPreferences.getString(getString(R.string.CHILD_NAME), "");
                String mobile = mSharedPreferences.getString(getString(R.string.CHILD_MOBILE), "");

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(mobile)) {
                    Log.e("MainActivity", name + mobile);
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                } else {
                    Log.e("MainActivity", "nothing");
                    startActivity(new Intent(getApplicationContext(), ChildLoginActivity.class));
                }
            }
        });
    }
}

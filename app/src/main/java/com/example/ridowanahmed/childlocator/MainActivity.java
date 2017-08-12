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
    private ImageView imageView_parentLogin, imageView_childLogin;
    private String parentMobile;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_parentLogin = (ImageView) findViewById(R.id.imageView_parentLogin);
        imageView_childLogin = (ImageView) findViewById(R.id.imageView_childLogin);

        mSharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        parentMobile = mSharedPreferences.getString(getString(R.string.MOBILE_NUMBER), "");
        Log.e("MainActivity ", parentMobile);

        imageView_parentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(TextUtils.isEmpty(parentMobile)) {
                startActivity(new Intent(getApplicationContext(), ParentLoginActivity.class));

            } else {
                startActivity(new Intent(getApplicationContext(), ParentMapsActivity.class));
            }
            }
        });

        imageView_childLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageView_childLogin.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);

                String name = mSharedPreferences.getString(getString(R.string.CHILD_NAME), "");
                String mobile = mSharedPreferences.getString(getString(R.string.MOBILE_NUMBER), "");

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(mobile)) {
                    Log.e("MainActivity", name + mobile);
                    startActivity(new Intent(getApplicationContext(), ChildMapsActivity.class));

                } else {
                    Log.e("MainActivity", "nothing");
                    startActivity(new Intent(getApplicationContext(), ChildLoginActivity.class));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", " is destroyed");
    }
}

package com.example.ridowanahmed.childlocator.Registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;

import com.example.ridowanahmed.childlocator.MapsActivity;
import com.example.ridowanahmed.childlocator.R;

public class ChildLoginActivity extends AppCompatActivity {
    EditText editText_childName, editText_childMobile;
    ImageView button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_login);
        
        editText_childName = (EditText) findViewById(R.id.editText_childName);
        editText_childMobile = (EditText) findViewById(R.id.editText_childMobile);
        button_submit = (ImageView) findViewById(R.id.button_submit);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_childName.getText().toString().trim();
                String mobile = editText_childMobile.getText().toString().trim();

                if(TextUtils.isEmpty(name)) {
                    editText_childName.setError("Enter your Name");
                } else if(TextUtils.isEmpty(mobile)) {
                    editText_childMobile.setError("Enter your parent's mobile number");
                }
                SharedPreferences mSharedPreferences = ChildLoginActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putString(getString(R.string.CHILD_NAME), name);
                mEditor.putString(getString(R.string.MOBILE_NUMBER), mobile);
                mEditor.commit();

                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });
    }
}

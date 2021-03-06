package com.example.ridowanahmed.childlocator.Registration;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ridowanahmed.childlocator.Fragment.ParentLoginFragment;
import com.example.ridowanahmed.childlocator.Fragment.ParentRegisterFragment;
import com.example.ridowanahmed.childlocator.HelperClass.ParentInformation;
import com.example.ridowanahmed.childlocator.ParentMapsActivity;
import com.example.ridowanahmed.childlocator.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ridowan Ahmed on 0002, August, 2, 2017.
 */

public class ParentLoginActivity extends AppCompatActivity implements ParentRegisterFragment.OnRegisterListener, ParentLoginFragment.OnLoginListener {
    TextView textView_register;

    ParentLoginFragment parentLoginFragment;
    ParentRegisterFragment parentRegisterFragment;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(ParentLoginActivity.this, ParentMapsActivity.class));
        }

        textView_register = (TextView) findViewById(R.id.textView_register);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        parentLoginFragment = new ParentLoginFragment();
        parentRegisterFragment = new ParentRegisterFragment();

        fragmentTransaction.add(R.id.parent_container, parentLoginFragment);
        fragmentTransaction.commit();



        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
                if(flag) {
                    loginFragmentCall(mFragmentTransaction);

                }  else {
                    registerFragmentCall(mFragmentTransaction);
                }
                mFragmentTransaction.commit();
            }
        });
    }

    private void loginFragmentCall(FragmentTransaction mFragmentTransaction) {
        mFragmentTransaction.replace(R.id.parent_container, parentRegisterFragment);
        textView_register.setText("Already have an account? Log in here");
        flag = false;
    }

    private void registerFragmentCall(FragmentTransaction mFragmentTransaction) {
        mFragmentTransaction.replace(R.id.parent_container, parentLoginFragment);
        textView_register.setText("No account yet? Register here");
        flag = true;
    }

    @Override
    public void registerUser(String userName, String userMobile,String userEmail, String userPassword) {
        Log.e("Registration ", "Name " + userName + " Email " + userEmail + " Mobile " + userMobile + " Password " + userPassword);

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Couldn't Registered. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        progressDialog.dismiss();

        saveParentData(userName, userMobile, userEmail, userPassword);

        finish();
        startActivity(new Intent(ParentLoginActivity.this, ParentMapsActivity.class));
    }

    private void saveParentData(String userName, String userMobile, String userEmail, String userPassword) {
        SharedPreferences mSharedPreferences = ParentLoginActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(getString(R.string.MOBILE_NUMBER), userMobile);
        mEditor.commit();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Parent").child(userMobile);
        ParentInformation mParentInformation = new ParentInformation(userName, userMobile, userEmail, userPassword);
        db.setValue(mParentInformation);
    }

    @Override
    public void loginUser(String userEmail, final String userMobile, String userPassword) {
        Log.e("LoginUser ", "Email " + userEmail + "Phone " + userMobile + " Password " + userPassword);

        final String mobile = userMobile;

        progressDialog.setMessage("Login in.....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()) {
                    SharedPreferences mSharedPreferences = ParentLoginActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    mEditor.putString(getString(R.string.MOBILE_NUMBER), mobile);
                    mEditor.commit();

                    finish();
                    startActivity(new Intent(ParentLoginActivity.this, ParentMapsActivity.class));

                } else {
                    Toast.makeText(getApplicationContext(), "Couldn't Log In. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

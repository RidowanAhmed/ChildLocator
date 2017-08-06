package com.example.ridowanahmed.childlocator.Registration;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ridowanahmed.childlocator.Fragment.ParentLoginFragment;
import com.example.ridowanahmed.childlocator.Fragment.ParentRegisterFragment;
import com.example.ridowanahmed.childlocator.R;

/**
 * Created by Ridowan Ahmed on 0002, August, 2, 2017.
 */

public class ParentDashboard extends AppCompatActivity {
    TextView textView_register;

    ParentLoginFragment parentLoginFragment;
    ParentRegisterFragment parentRegisterFragment;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_dashboard);

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
}

package com.example.ridowanahmed.childlocator.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ridowanahmed.childlocator.R;

/**
 * Created by Ridowan Ahmed on 0002, August, 2, 2017.
 */

public class ParentLoginFragment extends Fragment {
    EditText editText_parentLoginEmail, editText_parentLoginMobile, editText_parentLoginPassword;
    ImageView button_parentLogin;

    OnLoginListener mOnLoginListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view_parentLoginFragment = inflater.inflate(R.layout.fragment_parent_login, container, false);

        editText_parentLoginEmail = (EditText) view_parentLoginFragment.findViewById(R.id.editText_login_email);
        editText_parentLoginMobile = (EditText) view_parentLoginFragment.findViewById(R.id.editText_login_mobile);
        editText_parentLoginPassword = (EditText) view_parentLoginFragment.findViewById(R.id.editText_login_password);
        button_parentLogin = (ImageView) view_parentLoginFragment.findViewById(R.id.button_login);

        button_parentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_parentLoginEmail.getText().toString().trim();
                String mobile = editText_parentLoginMobile.getText().toString().trim();
                String password = editText_parentLoginPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    editText_parentLoginEmail.setError("Enter a valid email");
                } else if(TextUtils.isEmpty(password)) {
                    editText_parentLoginPassword.setError("Enter a valid password");
                } else  {
                    mOnLoginListener.loginUser(email, mobile, password);
                }
            }
        });

        return view_parentLoginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            mOnLoginListener = (OnLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnLoginListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginListener = null;
    }

    public interface OnLoginListener {
        void loginUser(String userEmail, String userMobile, String userPassword);
    }

}

package com.example.ridowanahmed.childlocator.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ridowanahmed.childlocator.R;

/**
 * Created by Ridowan Ahmed on 0002, August, 2, 2017.
 */

public class ParentRegisterFragment extends Fragment {
    EditText editText_registerName, editText_registerEmail, editText_registerMobile, editText_registerPassword;
    ImageView button_register;
    OnRegisterListener mOnRegisterListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view_parentRegisterFragment = inflater.inflate(R.layout.fragment_parent_register, container, false);

        editText_registerName = (EditText) view_parentRegisterFragment.findViewById(R.id.editText_registerName);
        editText_registerEmail = (EditText) view_parentRegisterFragment.findViewById(R.id.editText_registerEmail);
        editText_registerMobile = (EditText) view_parentRegisterFragment.findViewById(R.id.editText_registerMobile);
        editText_registerPassword = (EditText) view_parentRegisterFragment.findViewById(R.id.editText_registerPassword);
        button_register = (ImageView) view_parentRegisterFragment.findViewById(R.id.button_register);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_registerName.getText().toString().trim();
                String email = editText_registerEmail.getText().toString().trim();
                String mobile = editText_registerMobile.getText().toString().trim();
                String password = editText_registerPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name)) {
                    editText_registerName.setError("Enter your name");
                } else if(TextUtils.isEmpty(email)) {
                    editText_registerEmail.setError("Enter a valid email");
                } else if(TextUtils.isEmpty(password)) {
                    editText_registerPassword.setError("Enter a valid password");
                } else {
                    mOnRegisterListener.registerUser(name, mobile, email, password);
                }
            }
        });

        return view_parentRegisterFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            mOnRegisterListener = (OnRegisterListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRegisterListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnRegisterListener = null;
    }

    public interface OnRegisterListener {
        void registerUser(String userName, String userMobile, String userEmail, String userPassword);
    }

}

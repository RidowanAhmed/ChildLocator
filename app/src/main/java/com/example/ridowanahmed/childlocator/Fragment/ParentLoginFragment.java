package com.example.ridowanahmed.childlocator.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ridowanahmed.childlocator.R;

/**
 * Created by Ridowan Ahmed on 0002, August, 2, 2017.
 */

public class ParentLoginFragment extends Fragment {
    EditText editText_parentLoginEmail, editText_parentLoginPassword;
    Button button_parentLogin;

//    OnLoginListener mOnLoginListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view_parentLoginFragment = inflater.inflate(R.layout.fragment_parent_login, container, false);

        editText_parentLoginEmail = (EditText) view_parentLoginFragment.findViewById(R.id.editText_login_email);
        editText_parentLoginPassword = (EditText) view_parentLoginFragment.findViewById(R.id.editText_login_password);
        button_parentLogin = (Button) view_parentLoginFragment.findViewById(R.id.button_login);

        button_parentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_parentLoginEmail.getText().toString().trim();
                String password = editText_parentLoginPassword.getText().toString().trim();
//                mOnLoginListener.loginUser(email, password);
            }
        });

        return view_parentLoginFragment;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        Activity activity = (Activity) context;
//
//        try {
//            mOnLoginListener = (OnLoginListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OnLoginListener");
//        }
//    }
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mOnLoginListener = null;
//    }
//
//    public interface OnLoginListener {
//        void loginUser(String userEmail, String userPassword);
//    }

}

package com.mvvm_logindemo.ui;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mvvm_logindemo.R;
import com.mvvm_logindemo.databinding.ActivityMainBinding;
import com.mvvm_logindemo.model.LoginUser;
import com.mvvm_logindemo.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                if (TextUtils.isEmpty((loginUser).getStrEmail())) {
                    activityMainBinding.txtEmailAddress.setError("Please provide email Id");
                }
                else if (!loginUser.isEmailValid()) {
                    activityMainBinding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                    activityMainBinding.txtEmailAddress.requestFocus();
                }
                else if (TextUtils.isEmpty((loginUser).getStrPassword())) {
                    activityMainBinding.txtPassword.setError("Enter a password");
                    activityMainBinding.txtPassword.requestFocus();
                }
                else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    activityMainBinding.txtPassword.setError("Enter at least 8 character password");
                    activityMainBinding.txtPassword.requestFocus();
                }
                else {
                    activityMainBinding.lblEmailAnswer.setText(loginUser.getStrEmail());
                    activityMainBinding.lblPasswordAnswer.setText(loginUser.getStrPassword());
                }
            }
        });
    }
}

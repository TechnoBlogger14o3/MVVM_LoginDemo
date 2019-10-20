package com.mvvm_logindemo.viewmodel;

import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mvvm_logindemo.model.LoginUser;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<LoginUser> userMutableLiveData;
    public MutableLiveData<LoginUser> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view) {
        LoginUser loginUser = new LoginUser(emailAddress.getValue(), password.getValue());
        userMutableLiveData.setValue(loginUser);
    }
}

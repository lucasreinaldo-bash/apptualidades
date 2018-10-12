//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package vostore.apptualidade;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.CallbackManager.Factory;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.mukeshsolanki.sociallogin.facebook.FacebookListener;

import java.util.Arrays;

public class HelperFacebook {
    private FacebookListener mListener;
    private CallbackManager mCallBackManager;

    public HelperFacebook(@NonNull FacebookListener facebookListener) {
        this.mListener = facebookListener;
        this.mCallBackManager = Factory.create();
        FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                HelperFacebook.this.mListener.onFbSignInSuccess(loginResult.getAccessToken().getToken(), loginResult.getAccessToken().getUserId());
            }

            public void onCancel() {
                HelperFacebook.this.mListener.onFbSignInFail("User cancelled operation");
            }

            public void onError(FacebookException e) {
                HelperFacebook.this.mListener.onFbSignInFail(e.getMessage());
            }
        };
        LoginManager.getInstance().registerCallback(this.mCallBackManager, mCallBack);
    }

    @NonNull
    @CheckResult
    public CallbackManager getCallbackManager() {
        return this.mCallBackManager;
    }

    public void performSignIn(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile",  "email"));
    }

    public void performSignIn(Fragment fragment) {
        LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("public_profile",  "email"));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mCallBackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void performSignOut() {
        LoginManager.getInstance().logOut();
        this.mListener.onFBSignOut();
    }
}

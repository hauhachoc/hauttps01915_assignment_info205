package com.parse.starter;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hau on 29/11/2015.
 */
public class ParseKeyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Application");
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "CJxSHfUZFbLAIREvNektu2utp1d8FWj8vL6PJC1v", "KQ4anrgpVcqV4flj8T9QyFUZtJzI898zC5VYw5vf");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}

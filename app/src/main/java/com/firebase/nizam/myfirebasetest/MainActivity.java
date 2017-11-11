package com.firebase.nizam.myfirebasetest;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView hello;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
//        FacebookSdk.sdkInitialize(getApplicationContext());

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

//        printHashKey();
        hello = (TextView) findViewById(R.id.hello);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



// For E-mail/Password

//        FirebaseUser user = mAuth.getCurrentUser();
//        String uid = null;
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getToken() instead.
//            uid = user.getUid();
//
//            hello.setText(email);
//
//            Log.d(TAG, "onCreate: " + name + " : " + email);
//        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

//        mAuth = FirebaseAuth.getInstance();
//
//
//        myRef.setValue("Hello, World!!!" +  firebaseUser.getDisplayName());

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });




        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference newPostRef = myRef;

//        newPostRef.child(firebaseUser.getUid()).setValue(new ModelForSavingData(firebaseUser.getDisplayName(), "Announcing COBOL, a New Programming Language"));

// We can also chain the two calls together
//        postsRef.push().setValue(new ModelForSavingData("alanisawesome", "The Turing Machine"));


//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("server/saving-data/fireblog/posts");

        Query query = myRef.orderByChild("author").equalTo(firebaseUser.getDisplayName());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "notepad" node with all children with id
                    for (DataSnapshot notepad : dataSnapshot.getChildren()) {
                        // do something with the individual "notepad_data"
                        Log.d(TAG, "onDataChange: " + notepad.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Key Hashes For Facebook Login

//        public void printHashKey() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String hashKey = new String(Base64.encode(md.digest(), 0));
//                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
//            }
//        } catch (NoSuchAlgorithmException e) {
//            Log.e(TAG, "printHashKey()", e);
//        } catch (Exception e) {
//            Log.e(TAG, "printHashKey()", e);
//        }
//    }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        mAuth.addAuthStateListener(mAuthListener);
//        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    @Override
    public void onStop() {
        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
    }


}
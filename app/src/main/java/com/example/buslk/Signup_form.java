package com.example.buslk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_form extends AppCompatActivity {
    EditText txt_firstName;
    EditText txt_lastName;
    EditText txt_emailId;
    EditText txt_password;
    Button  signUpBtn;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Signup Form");

        txt_firstName = findViewById(R.id.firstname);
        txt_lastName = findViewById(R.id.lastname);
        txt_emailId = findViewById(R.id.emailText);
        txt_password = findViewById(R.id.passwordText);
        signUpBtn = findViewById(R.id.signupBtn);
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        firebaseAuth =FirebaseAuth.getInstance();
        

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String firstname = txt_firstName.getText().toString();
                final String lastname = txt_firstName.getText().toString();
                final String emailid = txt_emailId.getText().toString();
                final String password = txt_password.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();


                if(TextUtils.isEmpty(emailid)){

                    Toast.makeText(Signup_form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(password)){

                    Toast.makeText(Signup_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Signup_form.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(firstname)){

                    Toast.makeText(Signup_form.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(lastname)){

                    Toast.makeText(Signup_form.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }



                firebaseAuth.createUserWithEmailAndPassword(emailid, password)
                                     .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {

                                         @Override
                                         public void onComplete(@NonNull Task<AuthResult> task) {
                                             if (task.isSuccessful()) {

                                                 customer information=new customer(
                                                         firstname,
                                                         lastname,
                                                         emailid,
                                                         password

                                                 );

                                                 FirebaseDatabase.getInstance().getReference("Customer")
                                                         .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                         .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                     @Override
                                                     public void onComplete(@NonNull Task<Void> task) {


                                                         Toast.makeText(Signup_form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                                         startActivity(new Intent(getApplicationContext(),HomeActivity.class));



                                                     }
                                                 });


                                             } else {
                                                 Toast.makeText(Signup_form.this, "Registration  Failed ", Toast.LENGTH_SHORT).show();

                                             }

                                             // ...
                                         }
                                     });


                         }
                     });





                 }

}














































































































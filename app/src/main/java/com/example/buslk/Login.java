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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button  loginBtn;
    Button  signUpBtn;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Form");



        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        loginBtn = (Button) findViewById(R.id.loginBtn1);
        signUpBtn = (Button) findViewById(R.id.signupBtn);

        firebaseAuth = FirebaseAuth.getInstance();



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();



                if(TextUtils.isEmpty(emailid)){

                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(password)){

                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(Login.this, "Password too short", Toast.LENGTH_SHORT).show();

                }



                firebaseAuth.signInWithEmailAndPassword(emailid, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                } else {

                                    Toast.makeText(Login.this, "Login Failed ", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });
            }


        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {

                                      public void onClick(View view) {

                                          startActivity(new Intent(getApplicationContext(),Signup_form.class));




                                      }
});

    }
}
























































    /*
    EditText emailId;
    EditText password;
    Button  logInBtn;
    Button  signUpBtn;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        logInBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signupBtn);


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailId.getText().toString();
                String passwordt = password.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if (email.isEmpty()){
                    emailId.setError("Please enter your Email ID");
                    emailId.requestFocus();
                }else if (passwordt.isEmpty()){
                    password.setError("Please enter your Password");
                    password.requestFocus();
                }else if (!email.isEmpty() && !passwordt.isEmpty()){
                    firebaseAuth.createUserWithEmailAndPassword(email,passwordt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(Login.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }else {
                                    startActivity(new Intent(Login.this,HomeActivity.class));
                                }
                        }
                    });

                }

            }
        });




    }

}
*/

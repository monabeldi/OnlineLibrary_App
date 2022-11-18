package tn.esprit.onlinelibrary_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET, passwordET;
    Button loginBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = (EditText) findViewById(R.id.username1);
        passwordET = (EditText) findViewById(R.id.password1);
        loginBtn = (Button) findViewById(R.id.loginBtn1);
        DB = new DBHelper(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = usernameET.getText().toString();
                String pass = passwordET.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    Integer checkrole = DB.checkrole(user);


                    if(checkuserpass==true){
                        if(checkrole == 0){
                            Toast.makeText(LoginActivity.this, "User Sign in successfull", Toast.LENGTH_SHORT).show();
                            Intent intent1  = new Intent(getApplicationContext(), UserHome.class);
                            startActivity(intent1);
                        }else if(checkrole == 1){
                            Toast.makeText(LoginActivity.this, "Admin Sign in successfull", Toast.LENGTH_SHORT).show();
                            Intent intent2  = new Intent(getApplicationContext(), AdminHome.class);
                            startActivity(intent2);
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    }

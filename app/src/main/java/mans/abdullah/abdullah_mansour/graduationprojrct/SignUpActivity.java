package mans.abdullah.abdullah_mansour.graduationprojrct;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity
{
    FirebaseAuth mAuth;

    EditText email,password;
    Button signup;

    String useremail,userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        signup = findViewById(R.id.sign_up_btn2);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                useremail = email.getText().toString();
                userpassword = password.getText().toString();

                if (useremail.length() == 0 || userpassword.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "please enter a valid data", Toast.LENGTH_SHORT).show();
                } else
                    {
                        SignUp(useremail, userpassword);
                    }
            }
        });
    }

    public void SignUp (String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

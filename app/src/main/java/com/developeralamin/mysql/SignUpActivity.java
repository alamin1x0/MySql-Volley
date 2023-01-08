package com.developeralamin.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developeralamin.mysql.databinding.ActivitySignupBinding;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    String fname, lname, emaill;

    String base_rul = "http://192.168.1.110/app/connection.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.regisiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname = binding.inputFirstName.getText().toString().trim();
                lname = binding.inputlastName.getText().toString().trim();
                emaill = binding.inputEmail.getText().toString().trim();


                if (fname.isEmpty()) {
                    binding.inputFirstName.setError("Please Enter your First Name");
                    binding.inputFirstName.requestFocus();
                } else if (lname.isEmpty()) {
                    binding.inputlastName.setError("Please Enter your Last Name");
                    binding.inputlastName.requestFocus();
                } else if (emaill.isEmpty()) {
                    binding.inputEmail.setError("Please Etner your Email");
                    binding.inputEmail.requestFocus();
                } else {

                    StringRequest request = new StringRequest(Request.Method.POST, base_rul, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignUpActivity.this, error.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> user = new HashMap<String, String>();
                            user.put("firstname", fname);
                            user.put("lastname", lname);
                            user.put("email", emaill);
                            return user;

                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(request);


                }

            }
        });

    }
}
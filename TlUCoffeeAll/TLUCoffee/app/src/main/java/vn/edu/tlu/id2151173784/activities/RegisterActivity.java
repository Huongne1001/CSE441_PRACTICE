package vn.edu.tlu.id2151173784.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tlu.id2151173784.R;
import vn.edu.tlu.id2151173784.api.ApiClient;
import vn.edu.tlu.id2151173784.api.ApiService;
import vn.edu.tlu.id2151173784.models.Customer;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText, fullNameEditText, emailEditText;
    private Button registerButton, loginButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);

        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        apiService = ApiClient.getClient().create(ApiService.class);

        registerButton.setOnClickListener(view -> register());

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void register() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String fullname = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        Customer customer = new Customer(username, password, fullname, email);

        apiService.register(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

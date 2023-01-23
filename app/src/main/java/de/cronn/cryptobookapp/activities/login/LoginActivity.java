package de.cronn.cryptobookapp.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigDecimal;
import java.util.Arrays;

import de.cronn.cryptobookapp.activities.dashboard.DashboardActivity;
import de.cronn.cryptobookapp.databinding.ActivityLoginBinding;
import de.cronn.cryptobookapp.db.DatabaseFacade;
import de.cronn.cryptobookapp.db.model.User;
import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.price.Currency;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private final DatabaseFacade databaseFacade = new DatabaseFacade();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Currencies.init();
        databaseFacade.initializeDatabase(getApplicationContext());

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        loginButton.setEnabled(true);
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            long userId = loginOrRegister(username, password);
            if (userId != -1) {
                String welcome = "Welcome: " + username;
                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("USER-ID", userId);
                LoginActivity.this.startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }

    private long loginOrRegister(String username, String password) {
        UserWithWallets user = databaseFacade.findByUsername(username);
        if (user == null) {
            User newUser = new User.Builder()
                    .setName(username)
                    .setPassword(password)
                    .build();

            long userId = databaseFacade.insertUser(newUser);
            Arrays.stream(Currency.values())
                    .map(currency -> new Wallet(userId, currency, new BigDecimal("1000")))
                    .forEach(databaseFacade::insertWallet);
            return userId;
        }
        return password.equals(user.getUser().getPassword()) ?
                user.getUser().getId() : -1;
    }
}
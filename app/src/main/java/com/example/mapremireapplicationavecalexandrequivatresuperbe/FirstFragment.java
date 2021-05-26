package com.example.mapremireapplicationavecalexandrequivatresuperbe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mapremireapplicationavecalexandrequivatresuperbe.api.AuthAPI;
import com.example.mapremireapplicationavecalexandrequivatresuperbe.model.SessionToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText usernameEditText = view.findViewById(R.id.idUsername);
        final EditText passwordEditText = view.findViewById(R.id.idPassword);
        final Button loginButton = view.findViewById(R.id.idSignIn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FRAGMENT 1, username",usernameEditText.getText().toString());
                Log.i("FRAGMENT 1, password",passwordEditText.getText().toString());

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString()))
                        .setLenient()
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://dourlens-monchy.fr:8090/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                AuthAPI authAPI = retrofit.create(AuthAPI.class);
                Map<String, Object> params = new HashMap<>();
                params.put("username", usernameEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
                Call<SessionToken> apiCall = authAPI.loginUser(JsonUtils.mapToJSON(params));
                apiCall.enqueue(new Callback<SessionToken>() {
                    @Override
                    public void onResponse(Call<SessionToken> call, Response<SessionToken> response) {
                        assert response.body() != null;
                        SessionToken sessionToken = new SessionToken(response.body());
                        Log.i("Session Token",sessionToken.toString());
                    }

                    @Override
                    public void onFailure(Call<SessionToken> call, Throwable t) {
                        Log.i("ERROR",t.getMessage());
                    }
                });

                // NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}
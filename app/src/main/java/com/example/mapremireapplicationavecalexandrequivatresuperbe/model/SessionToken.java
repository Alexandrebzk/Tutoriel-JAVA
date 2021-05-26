package com.example.mapremireapplicationavecalexandrequivatresuperbe.model;


import com.google.gson.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionToken {

    private String token;
    private LocalDateTime expirationDate;

    public SessionToken(String json) {
        this(new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (js, typeOfT, context) -> LocalDateTime.parse(js.getAsString()))
                .setLenient()
                .create().fromJson(json, SessionToken.class));
    }

    public SessionToken(SessionToken sessionToken) {
        this.token = sessionToken.token;
        this.expirationDate = sessionToken.expirationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
                "token='" + token + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public String toJSON() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (js, typeOfT, context) -> LocalDateTime.parse(js.getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, srcType, context) -> new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime)))
                .setLenient()
                .create();
        return gson.toJson(this);
    }
}


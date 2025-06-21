package com.filmsverts.api;

import okhttp3.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();
    
    public boolean login(String username, String password, boolean isAdmin) {
        try {
            // For now, we'll do a simple check
            // In a real app, you'd call the actual login API
            if (isAdmin) {
                // Admin login - check if username is a number (admin ID)
                try {
                    Long.parseLong(username);
                    return password.equals("admin123"); // Simple password check
                } catch (NumberFormatException e) {
                    return false;
                }
            } else {
                // User login
                return username.equals("user") && password.equals("user123");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getMovies() {
        try {
            Request request = new Request.Builder()
                .url(BASE_URL + "/api/movies")
                .get()
                .build();
                
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "[]";
    }
    
    public boolean addMovie(String movieData) {
        try {
            RequestBody body = RequestBody.create(
                movieData, 
                MediaType.get("application/json; charset=utf-8")
            );
            
            Request request = new Request.Builder()
                .url(BASE_URL + "/api/movies")
                .post(body)
                .build();
                
            try (Response response = client.newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteMovie(Long movieId) {
        try {
            Request request = new Request.Builder()
                .url(BASE_URL + "/api/movies/" + movieId)
                .delete()
                .build();
                
            try (Response response = client.newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean register(Map<String, Object> payload) {
        try {
            String json = gson.toJson(payload);
            RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                .url(BASE_URL + "/api/register")
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String resp = response.body().string();
                    return resp.contains("success\":true");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} 
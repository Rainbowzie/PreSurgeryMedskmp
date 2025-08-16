package com.example.petmed;

import static spark.Spark.*;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        port(getHerokuAssignedPort()); // Render/Heroku dynamic port fallback

        Gson gson = new Gson();

        // Serve static frontend files from src/main/resources/public
        staticFiles.location("/public");

        // Root route -> loads index.html automatically
        get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });

        // API endpoint
        get("/medications", (req, res) -> {
            res.type("application/json");

            String animalType = req.queryParams("animalType");
            String weightParam = req.queryParams("weight");

            if (animalType == null || weightParam == null) {
                res.status(400);
                return gson.toJson("Missing parameters: animalType and weight are required");
            }

            try {
                int weight = Integer.parseInt(weightParam);
                return gson.toJson(MedicationService.getMedications(animalType, weight));
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Invalid weight value");
            }
        });
    }

    // Utility to support dynamic ports (needed for Render/Heroku)
    static int getHerokuAssignedPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 4567;
    }
}


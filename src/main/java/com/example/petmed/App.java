package com.example.petmed;

import static spark.Spark.*;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        // Serve static files (index.html etc.) from /public
        staticFiles.location("/public");

        MedicationService medService = new MedicationService();
        Gson gson = new Gson();

        // API endpoint
        get("/medications", (req, res) -> {
            res.type("application/json");

            String type = req.queryParams("animalType");
            String weightParam = req.queryParams("weight");

            if (type == null || weightParam == null) {
                res.status(400);
                return gson.toJson("Missing parameters: animalType and weight are required.");
            }

            double weight;
            try {
                weight = Double.parseDouble(weightParam);
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Invalid weight: must be a number.");
            }

            MedicationService.MedResponse response = medService.getMedications(type, weight);
            return gson.toJson(response);
        });
    }

    static int getHerokuAssignedPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 4567; // default for local testing
    }
}

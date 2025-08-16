package com.example.petmed;

import static spark.Spark.*;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        port(getAssignedPort());

        // Serve static files from src/main/resources/public/
        staticFiles.location("/public");

        // Health check
        get("/health", (req, res) -> {
            res.type("application/json");
            return "{\"status\":\"ok\"}";
        });

        // Main endpoint: /meds?type=dog&weight=22.5
        get("/meds", (req, res) -> {
            res.type("application/json");
            String type = req.queryParams("type");
            String w = req.queryParams("weight");

            if (w == null || w.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing 'weight' query param\"}";
            }

            double weight;
            try {
                weight = Double.parseDouble(w);
            } catch (NumberFormatException nfe) {
                res.status(400);
                return "{\"error\":\"'weight' must be a number\"}";
            }

            MedicationService svc = new MedicationService();
            MedicationService.MedResponse response = svc.getMedications(type, weight);
            return new Gson().toJson(response);
        });
    }

    private static int getAssignedPort() {
        String p = System.getenv("PORT");
        if (p != null) return Integer.parseInt(p);
        return 4567; // local dev
    }
}


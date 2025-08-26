package com.example.petmed;

import static spark.Spark.*;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        port(getAssignedPort());
        staticFiles.location("/public"); // serves src/main/resources/public

        // Always return JSON for unknown routes (avoid HTML 404 pages)
        notFound((req, res) -> { res.type("application/json"); return "{\"error\":\"Route not found\"}"; });

        Gson gson = new Gson();

        // Health
        get("/health", (req, res) -> { res.type("application/json"); return "{\"status\":\"ok\"}"; });

        // ---- GET /medications?animal=dog&weight=54.3 ----
        get("/medications", (req, res) -> {
            res.type("application/json");
            String animal = req.queryParams("animal");
            String weightParam = req.queryParams("weight");
            if (animal == null || weightParam == null) {
                res.status(400);
                return gson.toJson("Missing parameters: animal and weight are required");
            }
            double weight;
            try {
                weight = Double.parseDouble(weightParam);
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Invalid weight value");
            }
            return gson.toJson(MedicationService.getMedications(animal, weight));
        });

        // ---- POST /medications (form body: animal=dog&weight=54.3) ----
        post("/medications", (req, res) -> {
            res.type("application/json");
            String animal = req.queryParams("animal");
            String weightParam = req.queryParams("weight");
            if (animal == null || weightParam == null) {
                res.status(400);
                return gson.toJson("Missing parameters: animal and weight are required");
            }
            double weight;
            try {
                weight = Double.parseDouble(weightParam);
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Invalid weight value");
            }
            return gson.toJson(MedicationService.getMedications(animal, weight));
        });
    }

    private static int getAssignedPort() {
        String p = System.getenv("PORT");
        return (p != null) ? Integer.parseInt(p) : 4567;
    }
}


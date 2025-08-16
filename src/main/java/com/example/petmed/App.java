package com.example.petmed;

import static spark.Spark.*;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Render requires Spark to listen on PORT env variable
        port(getHerokuAssignedPort());

        // Basic test route
        get("/", (req, res) -> "🚀 PetMed API is running!");

        // Main meds route
        get("/meds", (req, res) -> {
            String pet = req.queryParams("pet");
            String weightParam = req.queryParams("weight");
            double weight = (weightParam != null) ? Double.parseDouble(weightParam) : 0;

            Map<String, String> meds = new HashMap<>();
            if ("cat".equalsIgnoreCase(pet)) {
                meds.put("Gabapentin", calcDose(weight, "cat"));
            } else if ("dog".equalsIgnoreCase(pet)) {
                meds.put("Gabapentin", calcDose(weight, "dog"));
                meds.put("Cerenia", "Give 2 hours before surgery");
                meds.put("Trazodone", "Give night before surgery");
            } else {
                res.status(400);
                return "Invalid pet type. Use 'dog' or 'cat'.";
            }

            res.type("application/json");
            return new Gson().toJson(meds);
        });
    }

    private static int getHerokuAssignedPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 4567; // default when running locally
    }

    private static String calcDose(double weight, String pet) {
        if ("cat".equalsIgnoreCase(pet)) {
            return weight * 10 + " mg Gabapentin";
        } else {
            return weight * 5 + " mg Gabapentin";
        }
    }
}

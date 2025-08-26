package com.example.petmed;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.function.BiFunction;

public class App {
    public static void main(String[] args) {
        port(getAssignedPort());
        staticFiles.location("/public"); // serves src/main/resources/public

        Gson gson = new Gson();

        // Health check
        get("/health", (req, res) -> { res.type("application/json"); return "{\"status\":\"ok\"}"; });

        // One handler we can reuse for GET and POST (and with/without trailing slash)
        BiFunction<String,String,String> handle = (animal, weightParam) -> {
            try {
                if (animal == null || weightParam == null) {
                    return gson.toJson(error("Missing parameters: animal and weight are required"));
                }
                double weight = Double.parseDouble(weightParam);
                return gson.toJson(MedicationService.getMedications(animal, weight));
           } catch (NumberFormatException e) {
    res.status(400);
    res.type("application/json");
    return new com.google.gson.Gson().toJson("Invalid weight value");
}

return new com.google.gson.Gson().toJson(MedicationService.getMedications(animal, weight));
        };

        // GET routes
        get("/medications", (req, res) -> { res.type("application/json");
            return handle.apply(req.queryParams("animal"), req.queryParams("weight"));
        });
        get("/medications/", (req, res) -> { res.type("application/json");
            return handle.apply(req.queryParams("animal"), req.queryParams("weight"));
        });

        // POST routes (form-encoded body or querystring)
        post("/medications", (req, res) -> { res.type("application/json");
            String animal = firstNonNull(req.queryParams("animal"), req.body() != null ? req.queryParams("animal") : null);
            String weight = firstNonNull(req.queryParams("weight"), req.body() != null ? req.queryParams("weight") : null);
            return handle.apply(animal, weight);
        });
        post("/medications/", (req, res) -> { res.type("application/json");
            String animal = firstNonNull(req.queryParams("animal"), req.body() != null ? req.queryParams("animal") : null);
            String weight = firstNonNull(req.queryParams("weight"), req.body() != null ? req.queryParams("weight") : null);
            return handle.apply(animal, weight);
        });
    }

    private static int getAssignedPort() {
        String p = System.getenv("PORT");
        return (p != null) ? Integer.parseInt(p) : 4567;
    }

    private static java.util.Map<String,String> error(String msg) {
        java.util.Map<String,String> m = new java.util.HashMap<>();
        m.put("error", msg);
        return m;
    }

    private static String firstNonNull(String a, String b) { return a != null ? a : b; }
}

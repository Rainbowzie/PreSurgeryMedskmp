package com.example.petmed;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        // Tell Spark where to find static files (index.html, CSS, JS)
        staticFiles.location("/public");

        // Optionally keep your test route for debugging
        get("/hello", (req, res) -> "🚀 PetMed API is running!");

        // Your existing /meds API route stays the same
        get("/meds", (req, res) -> {
            String type = req.queryParams("type");
            String weightStr = req.queryParams("weight");
            int weight = Integer.parseInt(weightStr);

            if (type.equalsIgnoreCase("cat")) {
                return "Give Gabapentin (" + weight + "mg)";
            } else if (type.equalsIgnoreCase("dog")) {
                return "Give Gabapentin, Cerenia, Trazodone (" + weight + "mg each)";
            } else {
                return "Unknown pet type.";
            }
        });
    }
}


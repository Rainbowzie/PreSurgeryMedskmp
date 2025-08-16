package com.example.petmed;

import java.util.HashMap;
import java.util.Map;

public class MedicationService {

    // Static method (so you can call MedicationService.getMedications() directly)
    public static Map<String, Object> getMedications(String animalType, int weight) {
        Map<String, Object> result = new HashMap<>();

        if (animalType == null || animalType.isEmpty()) {
            result.put("error", "Animal type is required");
            return result;
        }

        animalType = animalType.toLowerCase();

        if (animalType.equals("cat")) {
            // Cats only get Gabapentin
            result.put("animal", "cat");
            result.put("weight", weight);
            result.put("medications", new String[]{"Gabapentin"});
        } else if (animalType.equals("dog")) {
            // Dogs get Gabapentin, Cerenia, and Trazodone
            result.put("animal", "dog");
            result.put("weight", weight);
            result.put("medications", new String[]{"Gabapentin", "Cerenia", "Trazodone"});
        } else {
            result.put("error", "Unknown animal type: " + animalType);
        }

        return result;
    }
}

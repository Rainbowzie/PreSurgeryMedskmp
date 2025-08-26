package com.example.petmed;

import java.util.*;

public class MedicationService {

    // Main entry: returns JSON-friendly map
    public static Map<String, Object> getMedications(String animalType, int weightLbs) {
        Map<String, Object> out = new LinkedHashMap<>();
        if (animalType == null) {
            out.put("error", "Animal type required");
            return out;
        }
        String type = animalType.trim().toLowerCase(Locale.ROOT);
        out.put("animal", type);
        out.put("weight", weightLbs);

        List<String> recs = new ArrayList<>();

        if (type.equals("cat")) {
            recs.addAll(gabapentinForCat(weightLbs));
        } else if (type.equals("dog")) {
            recs.addAll(gabapentinForDog(weightLbs));
            recs.addAll(cereniaForDog(weightLbs));
            recs.addAll(trazodoneForDog(weightLbs));
        } else {
            out.put("error", "Unknown animal type: " + animalType);
            out.put("medications", Collections.emptyList());
            return out;
        }

        out.put("medications", recs);
        return out;
    }

    // ===== Cats =====
    private static List<String> gabapentinForCat(double w) {
        List<String> a = new ArrayList<>();
        a.add("--- Gabapentin for Cat ---");
        if (w >= 4 && w <= 6.99) {
            a.add("Gabapentin 50mg/ml #2. Give 1ml night before and morning of surgery.");
        } else if (w >= 7 && w <= 9.99) {
            a.add("Gabapentin 50mg/ml #3. Give 1.5ml night before and morning of surgery.");
        } else if (w >= 10 && w <= 16.99) {
            a.add("Gabapentin 100mg #2. Give 1 by mouth night before and morning of surgery.");
        } else if (w >= 17 && w <= 27.99) {
            a.add("Gabapentin 100mg #4. Give 2 by mouth night before and morning of surgery.");
        } else {
            a.add("No gabapentin dosage recommendation for this weight.");
        }
        return a;
    }

    // ===== Dogs =====
    private static List<String> gabapentinForDog(double w) {
        List<String> a = new ArrayList<>();
        a.add("--- Gabapentin for Dog ---");
        if (w >= 2 && w <= 3.99) {
            a.add("Gabapentin 50mg/ml #1. Give 0.5ml night before and morning of surgery.");
        } else if (w >= 4 && w <= 6.99) {
            a.add("Gabapentin 50mg/ml #2. Give 1ml night before and morning of surgery.");
        } else if (w >= 7 && w <= 9.99) {
            a.add("Gabapentin 50mg/ml #3. Give 1.5ml night before and morning of surgery.");
        } else if (w >= 10 && w <= 16.99) {
            a.add("Gabapentin 100mg #2. Give 1 by mouth night before and morning of surgery.");
        } else if (w >= 17 && w <= 27.99) {
            a.add("Gabapentin 100mg #4. Give 2 by mouth night before and morning of surgery.");
        } else if (w >= 28 && w <= 38.99) {
            a.add("Gabapentin 600mg #1. Give 1/2 by mouth night before and morning of surgery.");
        } else if (w >= 39 && w <= 49.99) {
            a.add("Gabapentin 800mg #1. Give 1/2 by mouth night before and morning of surgery.");
        } else if (w >= 50 && w <= 71.99) {
            a.add("Gabapentin 600mg #2. Give 1 by mouth night before and morning of surgery.");
        } else if (w >= 72 && w <= 93.99) {
            a.add("Gabapentin 800mg #2. Give 1 by mouth night before and morning of surgery.");
        } else if (w >= 94 && w <= 104.99) {
            a.add("Gabapentin 600mg #3. Give 1.5 by mouth night before and morning of surgery.");
        } else if (w >= 105 && w <= 137.99) {
            a.add("Gabapentin 600mg #4. Give 2 by mouth night before and morning of surgery.");
        } else if (w >= 138 && w <= 155.99) {
            a.add("Gabapentin 800mg #4. Give 2 by mouth night before and morning of surgery.");
        } else {
            a.add("No gabapentin dosage recommendation for this weight.");
        }
        return a;
    }

    private static List<String> cereniaForDog(double w) {
        List<String> a = new ArrayList<>();
        a.add("--- Cerenia for Dog ---");
        if (w < 4) {
            a.add("Do not dispense.");
        } else if (w <= 6.99) {
            a.add("Cerenia 16mg #1. Give 1/4 by mouth night before surgery.");
        } else if (w <= 11.99) {
            a.add("Cerenia 16mg #1. Give 1/2 by mouth night before surgery.");
        } else if (w <= 16.99) {
            a.add("Cerenia 24mg #1. Give 1/2 by mouth night before surgery.");
        } else if (w <= 22.99) {
            a.add("Cerenia 16mg #1. Give 1 by mouth night before surgery.");
        } else if (w <= 29.99) {
            a.add("Cerenia 24mg #1. Give 1 by mouth night before surgery.");
        } else if (w <= 34.99) {
            a.add("Cerenia 60mg #1. Give 1/2 by mouth night before surgery.");
        } else if (w <= 38.99) {
            a.add("Cerenia 16mg #2. Give 2 by mouth night before surgery.");
        } else if (w <= 43.99) {
            a.add("Cerenia 24mg #2. Give 1.5 by mouth night before surgery.");
        } else if (w <= 57.99) {
            a.add("Cerenia 24mg #2. Give 2 by mouth night before surgery.");
        } else if (w <= 72.99) {
            a.add("Cerenia 60mg #1. Give 1 by mouth night before surgery.");
        } else if (w <= 98.99) {
            a.add("Cerenia 160mg #1. Give 1/2 by mouth night before surgery.");
        } else if (w <= 113.99) {
            a.add("Cerenia 60mg #2. Give 1.5 by mouth night before surgery.");
        } else if (w <= 148.99) {
            a.add("Cerenia 60mg #2. Give 2 by mouth night before surgery.");
        } else if (w <= 155.99) {
            a.add("Cerenia 160mg #2. Give 1 by mouth night before surgery.");
        } else {
            a.add("No Cerenia dosage recommendation for this weight.");
        }
        return a;
    }

    private static List<String> trazodoneForDog(double w) {
        List<String> a = new ArrayList<>();
        a.add("--- Trazodone for Dog ---");
        if (w < 4) {
            a.add("Do not dispense.");
        } else if (w <= 6.99) {
            a.add("Trazodone 50mg #1. Give 1/4 by mouth night before and morning of surgery.");
        } else if (w <= 11.99) {
            a.add("Trazodone 50mg #1. Give 1/2 by mouth night before and morning of surgery.");
        } else if (w <= 13.99) {
            a.add("Trazodone 50mg #2. Give 3/4 by mouth night before and morning of surgery.");
        } else if (w <= 22.99) {
            a.add("Trazodone 50mg #2. Give 1 by mouth night before and morning of surgery.");
        } else if (w <= 27.99) {
            a.add("Trazodone 50mg #3. Give 1.5 by mouth night before and morning of surgery.");
        } else if (w <= 44.99) {
            a.add("Trazodone 100mg #2. Give 1 by mouth night before and morning of surgery.");
        } else if (w <= 66.99) {
            a.add("Trazodone 100mg #3. Give 1.5 by mouth night before and morning of surgery.");
        } else if (w <= 88.99) {
            a.add("Trazodone 100mg #4. Give 2 by mouth night before and morning of surgery.");
        } else if (w <= 110.99) {
            a.add("Trazodone 100mg #5. Give 2.5 by mouth night before and morning of surgery.");
        } else if (w <= 132.99) {
            a.add("Trazodone 100mg #6. Give 3 by mouth night before and morning of surgery.");
        } else if (w <= 155.99) {
            a.add("Trazodone 100mg #8. Give 4 by mouth night before and morning of surgery.");
        } else {
            a.add("No Trazodone dosage recommendation for this weight.");
        }
        return a;
    }
}

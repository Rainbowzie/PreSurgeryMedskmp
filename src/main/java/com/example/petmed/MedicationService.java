package com.example.petmed;

public class MedicationService {

    public MedResponse getMedications(String type, double weight) {
        MedResponse resp = new MedResponse();

        if (type.equalsIgnoreCase("cat")) {
            resp.gabapentin = calculateGabapentinForCat(weight);
        } else if (type.equalsIgnoreCase("dog")) {
            resp.gabapentin = calculateGabapentinForDog(weight);
            resp.cerenia = calculateCereniaForDog(weight);
            resp.trazodone = calculateTrazodoneForDog(weight);
        } else {
            resp.gabapentin = "Unknown animal type";
        }

        return resp;
    }

    private String calculateGabapentinForCat(double weight) {
        return (5 * weight) + " mg Gabapentin";
    }

    private String calculateGabapentinForDog(double weight) {
        return (5 * weight) + " mg Gabapentin";
    }

    private String calculateCereniaForDog(double weight) {
        return (2 * weight) + " mg Cerenia";
    }

    private String calculateTrazodoneForDog(double weight) {
        return (1 * weight) + " mg Trazodone";
    }

    // Nested class used for JSON response
    public static class MedResponse {
        public String gabapentin;
        public String cerenia;
        public String trazodone;
    }
}


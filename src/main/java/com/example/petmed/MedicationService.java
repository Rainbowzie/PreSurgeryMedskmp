package com.example.petmed;

public class MedicationService {

    public static class MedResponse {
        public String gabapentin;
        public String cerenia;
        public String trazodone;
    }

    public MedResponse getMedications(String type, double weight) {
        MedResponse resp = new MedResponse();

        if ("cat".equalsIgnoreCase(type)) {
            resp.gabapentin = calculateGabapentinForCat(weight);
            resp.cerenia = null;
            resp.trazodone = null;
        } else if ("dog".equalsIgnoreCase(type)) {
            resp.gabapentin = calculateGabapentinForDog(weight);
            resp.cerenia = calculateCereniaForDog(weight);
            resp.trazodone = calculateTrazodoneForDog(weight);
        } else {
            resp.gabapentin = null;
            resp.cerenia = null;
            resp.trazodone = null;
        }
        return resp;
    }

    private String calculateGabapentinForCat(double weight) {
    // Example: 5 mg per pound
    return (5 * weight) + " mg";
}

private String calculateGabapentinForDog(double weight) {
    return (5 * weight) + " mg";
}

private String calculateCereniaForDog(double weight) {
    return (2 * weight) + " mg";
}

private String calculateTrazodoneForDog(double weight) {
    return (1 * weight) + " mg";
}
}

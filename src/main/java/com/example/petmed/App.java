import static spark.Spark.*;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        port(getHerokuAssignedPort()); // Render uses this pattern too

        // Allow CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/health", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Status("ok"));
        });

        get("/meds", (req, res) -> {
            String type = req.queryParams("type");
            String weightStr = req.queryParams("weight");
            res.type("application/json");

            if (type == null || weightStr == null) {
                res.status(400);
                return new Gson().toJson(new ErrorMessage("Missing parameters 'type' and 'weight'"));
            }

            double weight;
            try {
                weight = Double.parseDouble(weightStr);
            } catch (NumberFormatException e) {
                res.status(400);
                return new Gson().toJson(new ErrorMessage("Invalid weight format"));
            }

            String result = MedicationService.getMedications(type, weight);
            return new Gson().toJson(new MedResult(result));
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // default
    }

    record Status(String status) {}
    record ErrorMessage(String error) {}
    record MedResult(String medications) {}
}


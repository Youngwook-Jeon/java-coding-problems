package com.project.young.chap10.P220_IntroStructuredTaskScope;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.logging.Logger;

public class Solution {
    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        buildTestingTeam();
    }

    public static TestingTeam buildTestingTeam() throws InterruptedException {
        try (StructuredTaskScope<String> scope = new StructuredTaskScope<>()) {
            Subtask<String> subtask = scope.fork(() -> fetchTester(1));
            logger.info(() -> "Waiting for " + subtask.toString() + " to finish ...\n");
            scope.join();
            String result = subtask.get();
            logger.info(result);
            return new TestingTeam(result);
        }
    }

    public static String fetchTester(int id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestGet = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://reqres.in/api/users/" + id))
                .build();

        HttpResponse<String> responseGet = client.send(
                requestGet, HttpResponse.BodyHandlers.ofString());

        if (responseGet.statusCode() == 200) {
            return responseGet.body();
        }

        throw new RuntimeException("Code: " + responseGet.statusCode());
    }
}

package com.project.young.secondedition.chap10.P221_IntroStructuredTaskScopeOnSuccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.ShutdownOnSuccess;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Solution {
    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        System.out.println(buildTestingTeam());
    }

    public static TestingTeam buildTestingTeam() throws InterruptedException, ExecutionException {
        try (ShutdownOnSuccess<String> scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {

            scope.fork(() -> fetchTester(1));
            scope.fork(() -> fetchTester(2));
            scope.fork(() -> fetchTester(3));

            scope.join();

            return new TestingTeam(scope.result());
        }
    }

    public static String fetchTester(int id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // intentionally added a delay of 1-5 seconds
        Thread.sleep(Duration.ofMillis(ThreadLocalRandom.current().nextLong(5000)));

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

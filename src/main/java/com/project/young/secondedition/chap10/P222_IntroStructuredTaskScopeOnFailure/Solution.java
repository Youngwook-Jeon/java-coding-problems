package com.project.young.secondedition.chap10.P222_IntroStructuredTaskScopeOnFailure;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.ShutdownOnFailure;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.logging.Logger;

public class Solution {
    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        buildTestingTeam();
    }

    public static TestingTeam buildTestingTeam() throws InterruptedException, ExecutionException {
        try (ShutdownOnFailure scope = new StructuredTaskScope.ShutdownOnFailure()) {

            Subtask<String> subtask1 = scope.fork(() -> fetchTester(1));
            Subtask<String> subtask2 = scope.fork(() -> fetchTester(2));
            Subtask<String> subtask3 = scope.fork(() -> fetchTester(Integer.MAX_VALUE)); // this will cause the exception

            scope.join();
            scope.throwIfFailed();

            // because we have an exception the following code will not be executed
            return new TestingTeam(subtask1.get(), subtask2.get(), subtask3.get());
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

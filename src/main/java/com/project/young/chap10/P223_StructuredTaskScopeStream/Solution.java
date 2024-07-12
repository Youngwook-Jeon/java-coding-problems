package com.project.young.chap10.P223_StructuredTaskScopeStream;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.ShutdownOnSuccess;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Solution {
    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        buildTestingTeam();
    }

    public static TestingTeam buildTestingTeam() throws InterruptedException, ExecutionException {
        try (ShutdownOnSuccess<String> scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {

            List<Subtask<String>> subtasks = Stream.of(Integer.MAX_VALUE, 2, 3)
                    .<Callable<String>>map(id -> () -> fetchTester(id))
                    .map(scope::fork)
                    .toList();

            scope.join();

            List<Throwable> failed = subtasks.stream()
                    .filter(f -> f.state() == Subtask.State.FAILED)
                    .map(Subtask::exception)
                    .toList();

            logger.info(failed.toString());

            TestingTeam result = subtasks.stream()
                    .filter(f -> f.state() == Subtask.State.SUCCESS)
                    .map(Subtask::get)
                    .collect(collectingAndThen(toList(),
                            list -> new TestingTeam(list.toArray(String[]::new))));

            logger.info(result.toString());

            return result;
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

package chap6.P131_JoinPaths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Solver {

    public static void main(String[] args) {

        // fix path
        Path base1 = Paths.get("/home/lucas/사진");

        Path path1 = base1.resolve("cat1.jpg");
        System.out.println(path1);

        Path path2 = base1.resolve("cat2.jpg");
        System.out.println(path2 + "\n");

        Path basePath = Paths.get("/home/lucas/사진");
        String[] photos = {"dog1.jpg", "dog2.jpg", "dog3.jpg"};
        for (String photo : photos) {
            Path next = basePath.resolve(photo);
            System.out.println(next);
        }

        // fix path
        Path base2 = Paths.get("/home/lucas/사진/private");

        // resolveSibling()은 주어진 경로의 부모 경로를 기준으로 경로 구함
        Path path3 = base2.resolveSibling("cat1.jpg");
        System.out.println("\n" + path3);

        Path path4 = base2.getParent().resolveSibling("비디오").resolve("video-sample1.mp4");
        System.out.println("\n" + path4);
    }

}

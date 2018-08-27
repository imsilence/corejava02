package com.silence.ch01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class StreamTest {
    public static void main( String[] args ) throws IOException, URISyntaxException {
        String content = new String(Files.readAllBytes(Paths.get(StreamTest.class.getResource("words.txt").toURI())), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(content.split(" "));
        long count = words.stream().filter(word -> word.length() >= 5).count();
        System.out.println(count);


        long count2 = words.parallelStream().filter(word -> word.length() >= 5).count();
        System.out.println(count2);
    }
}

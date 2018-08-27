package com.silence.ch01;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URISyntaxException;

public class StreamCreated {

    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> list = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.println(title + ": ");
        for(int i = 0; i < list.size(); ++i) {
            if(i > 0) {
                System.out.print(", ");
            }
            if(i < SIZE) {
                System.out.print(list.get(i));
            } else {
                System.out.print("...");
            }
        }
        System.out.println();

    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Stream<String> words = Stream.of("aa", "bbb", "cccc", "m", "nnnnnnn", "xx", "y", "zzz");
        show("words", words);

        String[] texts = new String[] {"aa", "bbb", "cccc", "m", "nnnnnnn", "xx", "y", "zzz"};
        Stream<String> subtexts = Arrays.stream(texts, 2, 5);
        show("subtexts", subtexts);

        Stream<String> empty = Stream.empty();
        show("empty", empty);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        try(Stream<String> lines = Files.lines(Paths.get(StreamTest.class.getResource("words.txt").toURI()), StandardCharsets.UTF_8)) {
            show("lines", lines);
        }
    }
}
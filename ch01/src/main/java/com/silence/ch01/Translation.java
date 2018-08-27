package com.silence.ch01;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Translation {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("abc", "xyz", "abcdef");
        System.out.println(words.filter(x -> x.startsWith("abc")).collect(Collectors.toList()));
        
        words = Stream.of("abc", "xyz", "abcdef");
        System.out.println(words.map(x -> x.toUpperCase()).collect(Collectors.toList()));
        
        words = Stream.of("abc", "xyz", "abcdef");
        System.out.println(words.flatMap(x -> Stream.of(x.split(""))).collect(Collectors.toList()));
    }
}
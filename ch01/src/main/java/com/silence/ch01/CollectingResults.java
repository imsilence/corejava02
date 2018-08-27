package com.silence.ch01;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    public static void main(String[] args) {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        
        Object[] numbers = Stream.iterate(0,  n -> n + 1).limit(10).toArray();
        System.out.println(Arrays.toString(numbers));
        
        Integer[] numbers2 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println(Arrays.toString(numbers2));
        
        String[] words = new String[] {"aaa", "bbb", "ccc", "xxxx", "yyy", "xxx", "zzz", "aaa", "ccc"};
        Stream<String> wordStream = Stream.of(words);
        Set<String> wordSet = wordStream.collect(Collectors.toSet());
        System.out.println(wordSet);
        
        TreeSet<String> wordTreeSet = Stream.of(words).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(wordTreeSet);
        
        System.out.println(Stream.of(words).collect(Collectors.joining()));
        System.out.println(Stream.of(words).collect(Collectors.joining(", ")));

        IntSummaryStatistics summary = Stream.of(words).collect(Collectors.summarizingInt(String::length));
        
        System.out.println(summary.getAverage());
        System.out.println(summary.getMax());
        
        Stream.of(words).forEach(System.out::println);
        
    }
}

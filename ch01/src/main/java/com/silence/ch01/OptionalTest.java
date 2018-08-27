package com.silence.ch01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OptionalTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String content = new String(Files.readAllBytes(Paths.get(OptionalTest.class.getResource("words.txt").toURI())), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(content.split("\\s"));
        
        Optional<String> first = words.stream().findFirst();
        System.out.println(first.orElse("N/A"));
        
        Optional<String> kk = words.stream().filter(x -> x.contains("kk")).findFirst();
        System.out.println(kk.orElse("N/A"));
        
        first.ifPresent(x -> System.out.println("not empty"));
        kk.ifPresent(x -> System.out.println("contains kk"));
        
        if(first.isPresent()) {
            System.out.println(first.get());
        }
        if(kk.isPresent()) {
            System.out.println(kk.get());
        }
        
        Set<String> results = new HashSet<String>();
        first.ifPresent(results::add);
        Optional<Boolean> added = kk.map(results::add);
        
        System.out.println(added);
        System.out.println(results);
        
        
        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));
        
        System.out.println(Optional.of(4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot));
        
    
    }
    
    public static Optional<Double> inverse(Double x) {
        return Math.abs(x) < 0.0005 ? Optional.empty() : Optional.of(1 / x);
    }
    
    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}

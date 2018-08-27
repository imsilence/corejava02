package com.silence.ch01;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingGroupByResults {

    private static class Person {
        private String name = "";
        private int age = 0;
        
        public Person() {}
        
        public Person(String aName, int aAge) {
            name = aName;
            age = aAge;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return getClass().getName() + "["
                    + "name=" + getName()
                    + ", age=" + getAge()
                    + "]";
        }
    }
    
    public static void main(String[] args) {

        Stream<Person> persons =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        Map<String, List<Person>> map =  persons.collect(Collectors.groupingBy(Person::getName));
        map.forEach((k, v) -> System.out.println(k + ":" + v));
        
        Stream<Person> persons2 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        
        Map<Boolean, List<Person>> map2 = persons2.collect(Collectors.partitioningBy(x -> x.getName() == "kk"));
        map2.forEach((k, v) -> System.out.println(k + ":" + v));
        
        Stream<Person> persons3 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        persons3.collect(Collectors.groupingBy(Person::getName, Collectors.counting())).forEach((k, v) -> System.out.println(k + ": " + v));

        Stream<Person> persons4 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        persons4.collect(Collectors.groupingBy(Person::getName, Collectors.toSet())).forEach((k, v) -> System.out.println(k + ": " + v));
        
        Stream<Person> persons5 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        persons5.collect(Collectors.groupingBy(Person::getName, Collectors.summarizingInt(Person::getAge))).forEach((k, v) -> System.out.println(k + ": " + v));
        
        Stream<Person> persons6 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        persons6.collect(Collectors.groupingBy(Person::getName, Collectors.maxBy(Comparator.comparing(Person::getAge)))).forEach((k, v) -> System.out.println(k + ": " + v));
        
        
        Stream<Person> persons7 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        persons7.collect(Collectors.groupingBy(Person::getName, Collectors.mapping(Person::getAge, Collectors.minBy(Comparator.comparing(Integer::intValue))))).forEach((k, v) -> System.out.println(k + ": " + v));
        
    }
}

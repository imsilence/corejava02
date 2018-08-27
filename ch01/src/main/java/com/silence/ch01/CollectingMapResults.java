package com.silence.ch01;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingMapResults {

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
        Stream<Person> persons = Stream.of(new Person("kk", 30), new Person("silence", 30),new Person("null", 30));
        Map<String, Person> map =  persons.collect(Collectors.toMap(person -> person.getName(), person-> person));
        
        map.forEach((k, v) -> System.out.println(k + ":" + v));
        
        Stream<Person> persons2 = Stream.of(new Person("kk", 30), new Person("silence", 30),new Person("null", 30));
        Map<String, Person> map2 =  persons2.collect(Collectors.toMap(Person::getName, Function.identity()));
        map2.forEach((k, v) -> System.out.println(k + ":" + v));
        
        

        Stream<Person> persons3 = Stream.of(new Person("kk", 30), new Person("kk", 33),  new Person("silence", 30),new Person("null", 30));
        Map<String, Person> map3 =  persons3.collect(Collectors.toMap(Person::getName, Function.identity(), (ev, nv) -> ev));
        map3.forEach((k, v) -> System.out.println(k + ":" + v));
        
        Stream<Person> persons4 = Stream.of(new Person("kk", 30), new Person("kk", 33),  new Person("silence", 30),new Person("null", 30));
        Map<String, Person> map4 =  persons4.collect(Collectors.toMap(Person::getName, Function.identity(), (ev, nv) -> ev, TreeMap::new));
        map4.forEach((k, v) -> System.out.println(k + ":" + v));
        

        Stream<Person> persons5 =Stream.of(new Person("kk", 30), new Person("kk", 33), new Person("silence", 30),new Person("null", 30));
        Map<String, Set<Person>> map5 =  persons5.collect(Collectors.toMap(Person::getName, person -> Collections.singleton(person), (ev, nv) -> {
            Set<Person> set = new HashSet<Person>(ev);
            set.addAll(nv);
            return set;
        }, TreeMap::new));
        map5.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}

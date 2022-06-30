package ru.Homeworks.A_Batsanov.PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class PopulationCensusMain {
    static void delimeter() {
        System.out.println("------------------------------------------------------");
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();
        System.out.println(count);
        delimeter();
        List<String> conscripts = persons.stream()
                .filter(Person -> Person.getSex().equals(Sex.MAN) && Person.getAge() >= 18 && Person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        conscripts.forEach(System.out::println);
        delimeter();
        List<Person> workable = persons.stream()
                .filter(Person -> Person.getEducation().equals(Education.HIGHER))
                .filter(Person -> Person.getAge() >= 18 && Person.getAge() <= 65)
                .filter(Person -> Person.getSex().equals(Sex.MAN) ||
                        Person.getSex().equals(Sex.WOMAN) && Person.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        workable.forEach(System.out::println);
        delimeter();
    }
}
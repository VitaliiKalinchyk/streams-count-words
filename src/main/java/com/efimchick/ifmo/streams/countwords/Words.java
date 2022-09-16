package com.efimchick.ifmo.streams.countwords;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        return  lines.stream().flatMap(line -> Arrays.stream(
                                                line.replaceAll("[^a-zA-Zа-яА-Я]", " ")
                                                     .split("\\s+")))
                .map(String::toLowerCase)
                .collect(Collectors.toMap(Function.identity(), e -> 1, Integer::sum))
                .entrySet()
                .stream()
                .filter(((Predicate<Map.Entry<String, Integer>>) entry -> entry.getKey().length() > 3)
                                                                           .and(entry -> entry.getValue() > 9))
                .sorted(Comparator.comparingInt((Map.Entry<String, Integer> o) -> o.getValue()).reversed()
                                  .thenComparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}

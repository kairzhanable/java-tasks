package ru.mail.polis.homework.collections.streams;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Написать программу, которая из текста (стрим строк), возвращает 10 самых популярных слов (В порядке убывания частоты).
 * Словом считается последовательность символов из букв и цифр от пробела до пробела или знака препинания (.,!:-?;).
 * (Посмотрите статические методы в классе Character)
 * <p>
 * В исходном стриме строка - это строка из книги, которая может содержать в себе много слов.
 * <p>
 * Если слов в стриме меньше 10, то вывести все слова. Если слова имеют одинаковое количество упоминаний, то выводить
 * в лексикографическом порядеке.
 * Слова надо сравнивать без учета регистра.
 * 3 балла
 */
public class WordFrequency {

    /**
     * Задачу можно решить без единого условного оператора, только с помощью стримов.
     */

    public static List<String> wordFrequency(Stream<String> lines) {
        return lines
                .map(String::toLowerCase)
                .map(X -> X.replaceAll("\\p{Punct}", " ")) // убираем мусор
                .map(X -> X.replaceAll("[\\s]{2,}", " ")) // убираем мусор
                .flatMap((string) -> Arrays.stream(string.split(" "))) // делим на слова по пробелу
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // запихиваем в мапу (key - слово, value - количество повторов)
                .entrySet() // получаем массив пар (ключ, значение)
                .stream() // создаем стрим пар
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed() // сортируем их по значению от большего к меньшему
                        .thenComparing(Map.Entry.comparingByKey())) // если равны, то сортируем по ключу
                .map(Map.Entry::getKey) //получаем ключи, т.е. слова
                .limit(10) // не более 10 штук
                .collect(Collectors.toList()); // запихиваем в лист, который возвращаем
    }
}

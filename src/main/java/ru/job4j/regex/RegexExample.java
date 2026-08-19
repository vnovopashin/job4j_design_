package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String textOne = "Я учусь на Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "Я учусь на курсе Job4j";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);

        /*
        Без учета регистра букв
         */
        Pattern patternCaseIns = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        String textThree = "joB4J";
        Matcher matcherThree = patternCaseIns.matcher(textThree);
        boolean isPresentThree = matcherThree.matches();
        System.out.println(isPresentThree);

        /*
        Проверяем шаблон на присутствие в тексте
         */
        Pattern patternFind = Pattern.compile("Job4j");
        String textFour = "Я учусь на курсе Job4j1 Job4j2 Job4j3";
        Matcher matcherFour = patternFind.matcher(textFour);
        boolean isPresentFour = matcherFour.find();
        System.out.println(isPresentFour);
        while (matcherFour.find()) {
            System.out.printf("Совпадение найдено: %s\n", matcherFour.group());
        }

        /*
            Получаем начальный и конечный индекс найденного совпадения
         */
        Pattern patternFive = Pattern.compile("Job4j");
        String textFive = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher = patternFive.matcher(textFive);
        while (matcher.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher.start()
                    + " iEnd: " + matcher.end());
        }

        /*
        Замена найденных совпадений
         */
        Pattern patternSix = Pattern.compile("123");
        String textSix = "1231 и 1232 и 1233";
        Matcher matcherSix = patternSix.matcher(textSix);
        String result = matcherSix.replaceAll("Job4j");
        System.out.println(result);
    }
}

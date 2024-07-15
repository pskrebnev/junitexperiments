package com.healthycodder.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BMICalculatorTest {

  String phrase = "The inspector opens a browser window and highlights elements"
      + " as the test is being executed. The toolbar provides options to play the test, step"
      + " through each action using Step over, or resume the script. You have access to"
      + " Actionability Logs that provide some useful info about actions being performed."
      + " You can also examine the page by using the Explore option. Last but not least, you"
      + " can use the browser developer tools. Page Object pattern with Playwright\n"
      + "Once the first test is passing, we can move on to the next one. This time will be"
      + " creating tests for TodoMVC Vanilla.js-based application available"
      + " here: http://todomvc.com/examples/vanillajs. The application is a Single"
      + " Page Application (SPA) and uses local storage as a task repository. The"
      + " possible scenarios to be implemented include adding and editing todo, removing todo"
      + ", marking single or multiple todos as done. The implementation will be done using Page"
      + " Object patternakaPOP`.\n"
      + "\n"
      + "The goal of POP is to abstract the application pages and functionality from the"
      + " actual tests. POP improves re-usability of the code across tests and fixtures"
      + " but also makes the code easier to maintain.\n"
      + "\n"
      + "Let's create an interface with the methods that represent scenarios that"
      + " we will be automating:";

  @ParameterizedTest
  @ValueSource(doubles = {89.0, 95.0, 110.0})
  public void testShouldTrueInCaseDietRecommended(Double coderWeight) {
    // given
    double weight = coderWeight;
    double height = 1.72;

    // when
    boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

    // then
    Assertions.assertTrue(isRecommended);
  }

  @ParameterizedTest(name = "weight={0}, height={1}")
  @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
  public void testShouldTrueInCaseDietRecommendedParameterized(Double coderWeight
  , Double coderHeight) {
    // given
    double weight = coderWeight;
    double height = coderHeight;

    // when
    boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

    // then
    Assertions.assertTrue(isRecommended);
  }

  @ParameterizedTest(name = "weight={0}, height={1}")
  @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
  public void testShouldTrueParameterizedByFile(Double coderWeight
      , Double coderHeight) {
    // given
    double weight = coderWeight;
    double height = coderHeight;

    // when
    boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

    // then
    Assertions.assertTrue(isRecommended);
  }

  @Test
  public void testShouldFalseInCaseDietNotRecommended() {
    // given
    double weight = 50.0;
    double height = 1.92;

    // when
    boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

    // then
    Assertions.assertFalse(isRecommended);
  }

  @Test
  public void testShouldExceptionInCaseHeightZero() {
    // given
    double weight = 50.0;
    double height = 0.0;

    // when
    Runnable executable = () -> BMICalculator.isDietRecommended(weight, height);

    // then
    Assertions.assertThrows(ArithmeticException.class, executable::run);
  }

  @Test
  public void shouldReturnCoderWithWorstBMIWhenCoderListNotEmpty() {
    // given
    List<Coder> coders = new ArrayList<>();
    coders.add(new Coder(1.80, 60.0));
    coders.add(new Coder(1.82, 98.0));
    coders.add(new Coder(1.82, 64.7));

    // when
    Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

    // then
    Assertions.assertAll(
        () -> Assertions.assertEquals(1.82, coderWorstBMI.getHeight()),
        () -> Assertions.assertEquals(98.0, coderWorstBMI.getWeight())
    );
  }

  @Test
  public void shouldReturnCorrectBMIScoreWhenCodersListNotEmpty() {
    // given
    List<Coder> coders = new ArrayList<>();
    coders.add(new Coder(1.80, 60.0));
    coders.add(new Coder(1.82, 98.0));
    coders.add(new Coder(1.82, 64.7));
    double[] expected = {18.52, 29.59, 19.53};

    // when
    double[] bmiScores = BMICalculator.getBMIScores(coders);

    // then
    Assertions.assertArrayEquals(expected, bmiScores);
  }

  private String cleanText(String str) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(str)
        .replaceAll(" ")
        .toLowerCase()
        .replaceAll(" +", " ");
  }

  @Test
  public void countChars() {
    Predicate<String> notSpace = s -> !s.equals(" ");

    cleanText(phrase).chars()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .filter(notSpace)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(item -> System.out.println(item.getKey() + "=" + item.getValue()));
  }
}

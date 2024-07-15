package com.healthycodder.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BMICalculatorTest {

  @Test
  public void testShouldTrueInCaseDietRecommended() {
    // given
    double weight = 89.0;
    double height = 1.72;

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
}

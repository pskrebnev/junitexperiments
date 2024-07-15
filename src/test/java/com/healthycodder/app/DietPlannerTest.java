package com.healthycodder.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DietPlannerTest {

  private DietPlanner dietPlanner;

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Before all unit tests");
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("After all unit tests");
  }

  @BeforeEach
  public void setUp() {
    this.dietPlanner = new DietPlanner(20, 30, 50);
  }

  @AfterEach
  public void afterTest() {
    System.out.println("Unit test finished");
  }

  @Test
  public void shouldReturnCorrectDietPlanWhenCorrectOrder() {
    // given
    Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
    DietPlan expected = new DietPlan(2202, 110, 73, 275);

    // when
    DietPlan actual = dietPlanner.calculateDiet(coder);

    // then
    Assertions.assertAll(
        () -> Assertions.assertEquals(expected.getCalories(), actual.getCalories()),
        () -> Assertions.assertEquals(expected.getProtein(), actual.getProtein()),
        () -> Assertions.assertEquals(expected.getFat(), actual.getFat()),
        () -> Assertions.assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
    );
  }
}

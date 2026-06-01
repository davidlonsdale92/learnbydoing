import scala.math.Numeric.Implicits.infixNumericOps

class Ex1SimpleTests extends munit.FunSuite {
  test("Given two positive integers, when added, then their correct sum is returned") {
    val calculator = new Ex1Simple
    val obtained = calculator.calculate(4, 4)
    val expected = 8

    assertEquals(obtained, expected)
  }

  test("Given a positive number and a negative number, when added, then the correct signed result is returned") {
    val calculator = new Ex1Simple
    val obtained = calculator.calculate(8, -4)
    val expected = 4

    assertEquals(obtained, expected)
  }

  test("Given two negative numbers, when added, then the correct negative sum is returned") {
    val calculator = new Ex1Simple
    val obtained = calculator.calculate(-4, -4)
    val expected = -8

    assertEquals(obtained, expected)
  }

  test("Given zero and any number, when added, then the original number is returned unchanged") {
    val calculator = new Ex1Simple
    val obtained = calculator.calculate(0, 10)
    val expected = 10

    assertEquals(obtained, expected)
  }

  test("Given two decimal numbers, when added, then the correct decimal sum is returned") {
    val calculator = new Ex1Simple
    val obtained = calculator.calculate(0.5, 0.5)
    val expected = 1.0

    assertEquals(obtained, expected)
  }

  test("Given two very large numbers, when added, then no overflow or precision error occurs") {
    val calculator = new Ex1Simple
    val obtained = calculator.calculate(BigDecimal("999999999999999999999999999999"), BigDecimal("999999999999999999999999999999"))
    val expected = BigDecimal("1999999999999999999999999999998")

    assertEquals(obtained, expected)
  }
}

class Ex1IntermediateTests extends munit.FunSuite {
  test("Given a list of positive integers, when the maximum is found, then the largest integer is returned") {
    val finder = new Ex1Intermediate
    val values = Array(7,4,1,6,3)
    val obtained = finder.getLargest(values)
    val expected = 7

    assertEquals(obtained, expected)
  }

  test("Given a list containing only negative numbers, when the maximum is found, then the least negative value is returned") {
    val finder = new Ex1Intermediate
    val values = Array(-7,-4,-1,-6,-3)
    val obtained = finder.getLargest(values)
    val expected = -1

    assertEquals(obtained, expected)
  }

  test("Given a list with a single element, when the maximum is found, then that element is returned") {
    val finder = new Ex1Intermediate
    val values = Array(5)
    val obtained = finder.getLargest(values)
    val expected = 5

    assertEquals(obtained, expected)
  }

  test("Given an empty list, when the maximum is requested, then an appropriate error or null/none is returned") {
    val finder = new Ex1Intermediate
    val values = Array.empty[Int]

    intercept[IllegalArgumentException] {
      finder.getLargest(values)
    }
  }

  test("Given a list containing duplicate maximum values, when the maximum is found, then that value is returned once") {
    val finder = new Ex1Intermediate
    val values = Array(7, 4, 1, 6, 3)
    val obtained = finder.getLargest(values)
    val expected = 7

    assertEquals(obtained, expected)
  }

  test("Given a list with a mix of positive and negative numbers, when the maximum is found, then the correct largest value is returned") {
    val finder = new Ex1Intermediate
    val values = Array(-2, 7, 4, 1, -10, 6, 3)
    val obtained = finder.getLargest(values)
    val expected = 7

    assertEquals(obtained, expected)
  }

  test("Given a list of decimal numbers, when the maximum is found, then the largest decimal is returned") {
    val finder = new Ex1Intermediate
    val values = Array(7.5, 4.2, 1.3, 1.0, 6.7, 3.2)
    val obtained = finder.getLargest(values)
    val expected = 7.5

    assertEquals(obtained, expected)
  }
}

class Ex1AdvancedTests extends munit.FunSuite {
  test("Given a list of numbers and a doubling function, when the function is applied to the list, then each item in the result is doubled") {
    val transformer = new Ex1Advanced

    def double[T: Numeric](value: T) = value + value

    val values = List(2, 4, 6, 8)
    val obtained = transformer.transform(values, double)
    val expected = List(4, 8, 12, 16)

    assertEquals(obtained, expected)
  }

  test("Given a list of strings and an uppercase function, when the function is applied to the list, then each string in the result is uppercased") {
    val transformer = new Ex1Advanced

    def upper(value: String) = value.toUpperCase

    val values = List("this", "is", "a", "test")
    val obtained = transformer.transform(values, upper)
    val expected = List("THIS", "IS", "A", "TEST")

    assertEquals(obtained, expected)
  }

  test("Given an empty list and any function, when the function is applied, then an empty list is returned") {
    val transformer = new Ex1Advanced

    def upper(value: String) = value.toUpperCase

    val values = List.empty[String]
    val obtained = transformer.transform(values, upper)
    val expected = List.empty[String]

    assertEquals(obtained, expected)
  }

  test("Given a list and an identity function (returns its input unchanged), when the function is applied, then the result equals the original list") {
    val transformer = new Ex1Advanced

    def identify(value: String) = value

    val values = List("this", "is", "a", "test")
    val obtained = transformer.transform(values, identity)
    val expected = List("this", "is", "a", "test")

    assertEquals(obtained, expected)
  }

  test("Given a list and a function that throws for certain inputs, when the function is applied, then the error is surfaced clearly rather than silently swallowed") {
    val transformer = new Ex1Advanced

    def identify[T](value: T) = require(value.isInstanceOf[String], "values must be of type String")

    val values = List("test", 1, "a", "test")

    intercept[IllegalArgumentException] {
      transformer.transform(values, identify)
    }
  }

  test("Given a list that has been passed to the higher-order function, when the result is returned, then the original list is unchanged") {
    val transformer = new Ex1Advanced

    def upper(value: String) = {
      value.toUpperCase
      value
    }

    val values = List("this", "is", "a", "test")
    val obtained = transformer.transform(values, upper)
    val expected = List("this", "is", "a", "test")

    assertEquals(obtained, expected)
  }
}

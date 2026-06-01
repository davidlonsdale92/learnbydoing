class Ex3SimpleTests extends munit.FunSuite {
  test("Given a positive integer (e.g. 5), when checked, then 'positive' is returned") {
    val check = new Ex3Simple
    val obtained = check.valueCheck(5)
    val expected = "positive"

    assertEquals(obtained, expected)
  }

  test("Given a negative integer (e.g. -3), when checked, then 'negative' is returned") {
    val check = new Ex3Simple
    val obtained = check.valueCheck(-3)
    val expected = "negative"

    assertEquals(obtained, expected)
  }

  test("Given zero, when checked, then 'zero' is returned") {
    val check = new Ex3Simple
    val obtained = check.valueCheck(0)
    val expected = "zero"

    assertEquals(obtained, expected)
  }

  test("Given a positive decimal (e.g. 0.1), when checked, then 'positive' is returned") {
    val check = new Ex3Simple
    val obtained = check.valueCheck(0.1)
    val expected = "positive"

    assertEquals(obtained, expected)
  }

  test("Given a negative decimal (e.g. -0.001), when checked, then 'negative' is returned") {
    val check = new Ex3Simple
    val obtained = check.valueCheck(-0.001)
    val expected = "negative"

    assertEquals(obtained, expected)
  }

  test("Given a very large positive number, when checked, then 'positive' is returned without overflow") {
    val check = new Ex3Simple
    val obtained = check.valueCheck(BigDecimal("999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"))
    val expected = "positive"

    assertEquals(obtained, expected)
  }
}

class Ex3IntermediateTests extends munit.FunSuite {
  test("Given a score of 100, when graded, then 'A' is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(100)
    val expected = "A"

    assertEquals(obtained, expected)
  }

  test("Given a score in the A range (90–100), when graded, then 'A' is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(100)
    val expected = "A"

    assertEquals(obtained, expected)
  }

  test("Given a score in the B range (80–89), when graded, then 'B' is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(89)
    val expected = "B"

    assertEquals(obtained, expected)
  }

  test("Given a score in the C range (70–79), when graded, then 'C' is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(70)
    val expected = "C"

    assertEquals(obtained, expected)
  }

  test("Given a score in the D range (60–69), when graded, then 'D' is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(60)
    val expected = "D"

    assertEquals(obtained, expected)
  }

  test("Given a score below 60, when graded, then 'F' is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(1)
    val expected = "F"

    assertEquals(obtained, expected)
  }

  test("Given a boundary score (e.g. exactly 80), when graded, then the correct grade for that boundary is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(80)
    val expected = "B"

    assertEquals(obtained, expected)
  }

  test("Given a score below 0 or above 100, when graded, then an invalid input error is returned") {
    val grader = new Ex3Intermediate
    val obtained = grader.getGrade(-5)
    val expected = "Invalid Input"

    assertEquals(obtained, expected)
  }
}

// class Ex3AdvancedTests extends munit.FunSuite {
//   test("Given all fields containing valid values, when validated, then no errors are returned") {
//     val validator = new Ex3Advanced

//     val fields = Map(
//       ("name", "David Lonsdale"),
//       ("email", "david.lonsdale@opencastsoftware.com"),
//       ("password", "-Test1234"),
//       ("age", 24),
//     )
//     val obtained = validator.validate(fields)
//     val expected = 0

//     assertEquals(obtained, expected)
//   }

  // test("description") {
  //   val validator = new Ex3Advanced
  //   val fields = Map(
  //     ("name", "David Lonsdale"),
  //     ("email", "david.lonsdale@opencastsoftware.com"),
  //     ("password", "-Test1234"),
  //     ("age", 24),
  //   )
  //   val obtained = validator.validate(fields)

  //   assertEquals(obtained, expected)
  // }
// }

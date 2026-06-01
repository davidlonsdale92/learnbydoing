class Ex4SimpleTests extends munit.FunSuite {
  test("Given the function is called, when the list is returned, then all 100 numbers are present in ascending order") {
    val incrementor = new Ex4Simple
    val obtained = incrementor.increment()
    val expected = (1 to 100).toList

    assertEquals(obtained, expected)
  }

  test("Given the function is called, when the list is returned, then the first value is 1") {
    val incrementor = new Ex4Simple
    val numbers = incrementor.increment()
    val obtained = numbers(0)
    val expected = 1

    assertEquals(obtained, expected)
  }

  test("Given the function is called, when the list is returned, then the last value is 100") {
    val incrementor = new Ex4Simple
    val numbers = incrementor.increment()
    val obtained = numbers(numbers.length-1)
    val expected = numbers.length

    assertEquals(obtained, expected)
  }

  test("Given the function is called, when the list is returned, then exactly 100 values are present with no off-by-one errors") {
    val incrementor = new Ex4Simple
    val numbers = incrementor.increment()
    val obtained = numbers.length
    val expected = 100

    assertEquals(obtained, expected)
  }
}

class Ex4IntermediateTests extends munit.FunSuite {
  test("Given a list of [2, 3, 4, 5], when filtered for evens, then [2, 4] is returned") {
    val filter = new Ex4Intermediate
    val numbers = List(2, 3, 4, 5)
    val result = filter.getEvens(numbers)
    val obtained =  result._1
    val expected = List(2, 4)

    assertEquals(obtained, expected)
  }

  test("Given a list containing only odd numbers, when filtered for evens, then an empty list is returned") {
    val filter = new Ex4Intermediate
    val numbers = List(1, 3, 5, 7)
    val result = filter.getEvens(numbers)
    val obtained =  result._1
    val expected = List.empty[Int]

    assertEquals(obtained, expected)
  }

  test("Given a list containing only even numbers, when filtered for evens, then all items are returned") {
    val filter = new Ex4Intermediate
    val numbers = List(2, 4, 6, 8)
    val result = filter.getEvens(numbers)
    val obtained =  result._1
    val expected = List(2, 4, 6, 8)

    assertEquals(obtained, expected)
  }

  test("Given an empty list, when filtered for evens, then an empty list is returned") {
    val filter = new Ex4Intermediate
    val numbers = List.empty[Int]
    val result = filter.getEvens(numbers)
    val obtained =  result._1
    val expected = List.empty[Int]

    assertEquals(obtained, expected)
  }

  test("Given a list containing zero, when filtered for evens, then zero is included in the result (zero is even)") {
    val filter = new Ex4Intermediate
    val numbers = List(0, 2, 4, 6, 8)
    val result = filter.getEvens(numbers)
    val obtained =  result._1
    val expected = List(0, 2, 4, 6, 8)

    assertEquals(obtained, expected)
  }

  test("Given a list containing negative numbers, when filtered for evens, then negative even numbers are included in the result") {
    val filter = new Ex4Intermediate
    val numbers = List(-2, -4, -6, -8)
    val result = filter.getEvens(numbers)
    val obtained =  result._1
    val expected = List(-2, -4, -6, -8)

    assertEquals(obtained, expected)
  }

  test("Given the original list, when filtered, then the original list is not mutated") {
    val filter = new Ex4Intermediate
    val numbers = List(2, 3, 4, 5)
    val result = filter.getEvens(numbers)
    val obtained =  result._2
    val expected = List(2, 3, 4, 5)

    assertEquals(obtained, expected)
  }
}

class Ex4AdvancedTests extends munit.FunSuite {
  test("Given [1, [2, 3], 4] and depth 1, when flattened, then [1, 2, 3, 4] is returned") {
    val transform = new Ex4Advanced
    val list = List(1, List(2, 3), 4)
    val obtained = transform.flatten(1, list)
    val expected = List(1, 2, 3, 4)

    assertEquals(obtained, expected)
  }

  test("Given [1, [2, [3, 4]]] and depth 1, when flattened, then [1, 2, [3, 4]] is returned") {
    val transform = new Ex4Advanced
    val list = List(1, List(2, List(3, 4)))
    val obtained = transform.flatten(1, list)
    val expected = List(1, 2, List(3, 4))

    assertEquals(obtained, expected)
  }

  test("Given [1, [2, [3, 4]]] and depth 2, when flattened, then [1, 2, 3, 4] is returned") {
    val transform = new Ex4Advanced
    val list = List(1, List(2, List(3, 4)))
    val obtained = transform.flatten(2, list)
    val expected = List(1, 2, 3, 4)

    assertEquals(obtained, expected)
  }

  test("Given any list and depth 0, when flattened, then the original list is returned unchanged") {
    val transform = new Ex4Advanced
    val list = List(1, List(2, List(3, 4)))
    val obtained = transform.flatten(0, list)
    val expected = List(1, List(2, List(3, 4)))

    assertEquals(obtained, expected)
  }

  test("Given an already flat list and any depth, when flattened, then the list is returned unchanged") {
    val transform = new Ex4Advanced
    val list = List(1, 2, 3, 4)
    val obtained = transform.flatten(1, list)
    val expected = List(1, 2, 3, 4)

    assertEquals(obtained, expected)
  }

  test("Given an empty list and any depth, when flattened, then an empty list is returned") {
    val transform = new Ex4Advanced
    val list = List.empty[Int]
    val obtained = transform.flatten(1, list)
    val expected = List.empty[Int]

    assertEquals(obtained, expected)
  }

  test("Given a list and a depth greater than its nesting depth, when flattened, then the list is fully flattened without error") {
    val transform = new Ex4Advanced
    val list = List(1, 2, 3, 4)
    val obtained = transform.flatten(10, list)
    val expected = List(1, 2, 3, 4)

    assertEquals(obtained, expected)
  }
}

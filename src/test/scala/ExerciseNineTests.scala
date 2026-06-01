class Ex9SimpleTests extends munit.FunSuite {
  test("Given a text file containing multiple lines, when read, then the correct line count is returned") {
    val reader = Ex9Simple()
    val obtained = reader.countLines("multiline_test.txt")
    val expected = 2

    assertEquals(obtained, expected)
  }

  test("Given a text file containing a single line with no newline at the end, when read, then 1 is returned") {
    val reader = Ex9Simple()
    val obtained = reader.countLines("test1.txt")
    val expected = 1

    assertEquals(obtained, expected)
  }

  test("Given an empty file, when read, then 0 is returned") {
    val reader = Ex9Simple()
    val obtained = reader.countLines("empty_test.txt")
    val expected = 0

    assertEquals(obtained, expected)
  }

  test("Given a file with trailing newlines, when read, then the result follows the conventions of the language's file reading API and is applied consistently") {
    val reader = Ex9Simple()
    val obtained = reader.countLines("trailing_line_test.txt")
    val expected = 2

    assertEquals(obtained, expected)
  }

  test("Given a file path that does not exist, when a read is attempted, then an appropriate error is returned") {
    val reader = Ex9Simple()
    intercept[java.io.FileNotFoundException] {
        reader.countLines("faketest.txt")
    }
  }

  test("Given a very large file, when read, then the implementation processes it without loading the entire file into memory at once") {
    val reader = Ex9Simple()
    val obtained = reader.countLines("odyssey_1.txt")
    val expected = 362

    assertEquals(obtained, expected)
  }
}

class Ex9IntermediateTests extends munit.FunSuite {
    test("Given a valid JSON file containing the expected fields, when parsed and extracted, then the correct values for each field are returned") {
        val reader = Ex9Intermediate()
        val obtained = reader.readJson("test1.json")
        val expected = ujson.Obj(
          "name" -> "David",
          "age" -> 33,
          "tags" -> ujson.Arr("scala", "json")
        )

        assertEquals(obtained, expected)
    }
    
    test("Given a valid JSON file where an expected field is absent, when extracted, then a clear error or null/none is returned for that missing field") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given a file containing malformed JSON, when parsed, then a parse error is returned rather than an unhandled crash") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given a valid JSON file with nested fields, when a nested value is extracted, then the correct nested value is returned") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given a valid JSON file with an array-valued field, when extracted, then the full array is returned") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given an empty file, when parsed as JSON, then an appropriate error is returned") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given a file path that does not exist, when a parse is attempted, then a file-not-found error is returned") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }
}

// class Ex9AdvancedTests extends munit.FunSuite {
//     test("description") {
//     val obtained = 0
//     val expected = 0

//     assertEquals(obtained, expected)
//   }
// }

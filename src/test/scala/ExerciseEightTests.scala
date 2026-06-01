class Ex8SimpleTests extends munit.FunSuite {
  test("Given a string representing a valid integer (e.g. '42'), when validated, then it is accepted as a valid number") {
    val validator = new Ex8Simple
    val obtained = validator.validate("42")
    val expected = 42

    assertEquals(obtained, expected)
  }
  
  test("Given a string representing a valid decimal (e.g. '3.14'), when validated, then it is accepted as a valid number") {
    val validator = new Ex8Simple
    val obtained = validator.validate("3.14")
    val expected = 3.14

    assertEquals(obtained, expected)
  }

  test("Given a string containing non-numeric characters (e.g. 'abc'), when validated, then it is rejected and an error or false is returned") {
   val validator = new Ex8Simple
    intercept[IllegalArgumentException] {
        validator.validate("abc")
    }
  }

  test("Given an empty string, when validated, then it is rejected as invalid") {
    val validator = new Ex8Simple
    intercept[IllegalArgumentException] {
        validator.validate("")
    }
  }

  test("Given a string containing only whitespace, when validated, then it is rejected as invalid") {
    val validator = new Ex8Simple
    intercept[IllegalArgumentException] {
        validator.validate(" ")
    }
  }

  test("Given a string with a valid number followed by trailing characters (e.g. '42abc'), when validated, then the result follows the partial-parse conventions of the language") {
    val validator = new Ex8Simple
    val obtained = validator.validate("42abc")
    val expected = 42

    assertEquals(obtained, expected)
  }

  test("Given a null or none input, when validated, then it is handled gracefully without throwing an unhandled exception") {
    val validator = new Ex8Simple
    intercept[IllegalArgumentException] {
        validator.validate(null)
    }
  }
}

class Ex8IntermediateTests extends munit.FunSuite {
    test("Given a valid, readable file path, when the file is read, then the file contents are returned successfully") {
        val reader = new Ex8Intermediate
        val obtained = reader.readFile("test1.txt")
        val expected = "test"

        assertEquals(obtained, expected)
    }
    
    test("Given a path pointing to a file that does not exist, when a read is attempted, then a 'file not found' error is returned rather than an unhandled crash") {
        val reader = new Ex8Intermediate
        intercept[java.io.FileNotFoundException] {
            reader.readFile("test.txt")
        }
    }

    test("Given a path pointing to a file the process lacks permission to read, when a read is attempted, then a 'permission denied' error is returned") {
        val reader = new Ex8Intermediate
        intercept[java.io.FileNotFoundException] {
            reader.readFile("test.txt")
        }
    }

    test("Given a valid, writable file path and data to write, when the write is performed, then the data is correctly persisted to disk") {
        val reader = new Ex8Intermediate
        reader.writeFile("test4.txt")
        val obtained = reader.readFile("test4.txt")
        val expected = "Hello World!"

        assertEquals(obtained, expected)
    }

    test("Given a path pointing to a directory rather than a file, when a read is attempted, then an appropriate error is returned") {
        val reader = new Ex8Intermediate
        intercept[java.io.FileNotFoundException] {
            reader.readFile("/test")
        }
    }

    test("Given a null or empty string as the file path, when a read is attempted, then an appropriate error is returned") {
        val reader = new Ex8Intermediate
        intercept[java.lang.NullPointerException] {
            reader.readFile(null)
        }
    }

    test("Given a valid path to a file that exists but is empty, when the file is read, then an empty result is returned without error") {
        val reader = new Ex8Intermediate
        val obtained = reader.readFile("test7.txt")
        val expected = ""

        assertEquals(obtained, expected)
    }
}

class Ex8AdvancedTests extends munit.FunSuite {
    import sttp.client4.testing.{SyncBackendStub, ResponseStub}
    import sttp.model.StatusCode

    def stubOf(code: StatusCode): SyncBackendStub =
        SyncBackendStub.whenAnyRequest.thenRespondWithCode(code)

    test("Given a successful API call, when executed, then the response data is returned and no retries are attempted") {
        val caller = new Ex8Advanced(stubOf(StatusCode.Ok))
        val obtained = caller.get()
        val expected = 200

        assertEquals(obtained, expected)
    }

    test("Given an API call that fails with a transient error (e.g. network timeout), when executed, then the handler retries up to the configured maximum number of times") {
        val caller = new Ex8Advanced(stubOf(StatusCode.InternalServerError))
        val obtained = caller.get500(3)
        val expected = 500

        assertEquals(obtained, expected)
    }

    test("Given an API call that fails on every attempt, when the retry limit is exhausted, then a final error is returned and no further retries are attempted") {
        val caller = new Ex8Advanced(stubOf(StatusCode.BadRequest))
        intercept[RetriesExhaustedException] {
            caller.get400()
        }
    }

    test("Given an API call that fails on the first attempt but succeeds on the second, when executed, then the successful response is returned") {
        val stub = SyncBackendStub.whenAnyRequest.thenRespondCyclic(
            ResponseStub.adjust("", StatusCode.BadRequest),
            ResponseStub.adjust("", StatusCode.Ok)
        )
        val caller = new Ex8Advanced(stub)
        val obtained = caller.getDelayed200()
        val expected = 200

        assertEquals(obtained, expected)
    }

    test("Given an API response with a 4xx client error code (e.g. 404, 401), when received, then the handler does not retry as client errors are non-transient") {
        val caller = new Ex8Advanced(stubOf(StatusCode.BadRequest))
        val obtained = caller.get400NoRetries()
        val expected = 400

        assertEquals(obtained, expected)
    }

    test("Given an API response with a 5xx server error code (e.g. 503), when received, then the handler retries as per its configuration") {
        val caller = new Ex8Advanced(stubOf(StatusCode.InternalServerError))
        val obtained = caller.get500()
        val expected = 500

        assertEquals(obtained, expected)
    }

    test("Given any error during the call, when it occurs, then it is logged with the error type and sufficient context for debugging") {
        val caller = new Ex8Advanced(stubOf(StatusCode.BadRequest))
        intercept[RetriesExhaustedException] {
            caller.get400()
        }
        var output = ""
        scala.io.Source.fromFile("RetriesExhaustedException.txt").getLines().foreach{ line =>
            output = output + line
        }

        assert(output.contains("Call to https://httpbin.org/status/400 failed with a status of 400 after 3 attempts"))
    }

    test("Given a retry attempt, when it occurs, then the attempt number and reason for the previous failure are logged") {
        val caller = new Ex8Advanced(stubOf(StatusCode.BadRequest))
        intercept[RetriesExhaustedException] {
            caller.get400()
        }
        var output = ""
        scala.io.Source.fromFile("RetriesAttemptLog.txt").getLines().foreach{ line =>
            output = output + line
        }

        assert(output.contains("Retry attempt number"))
    }

    test("Given a handler configured with exponential backoff, when retries occur, then the delay between each retry increases correctly according to the backoff formula") {
        val caller = new Ex8Advanced(stubOf(StatusCode.BadRequest))

        val start = System.currentTimeMillis()
        intercept[RetriesExhaustedException] {
            caller.get400()
        }
        val elapsed = System.currentTimeMillis() - start

        // get400 sleeps 100L * (1L << counter) before attempts 2 and 3:
        //   attempt 2: 100 * (1 << 2) = 400ms
        //   attempt 3: 100 * (1 << 3) = 800ms
        // total backoff >= 1200ms
        assert(elapsed >= 1200L, s"Expected total backoff >= 1200ms, got ${elapsed}ms")
    }

    test("Given a handler configured with a maximum retry limit of 0, when a transient error occurs, then no retries are attempted and the error is returned immediately") {
        // If any retry happened, the second cyclic response (200) would be returned.
        // Seeing 500 proves only one attempt was made.
        val stub = SyncBackendStub.whenAnyRequest.thenRespondCyclic(
            ResponseStub.adjust("", StatusCode.InternalServerError),
            ResponseStub.adjust("", StatusCode.Ok)
        )
        val caller = new Ex8Advanced(stub)
        val obtained = caller.get500(3)
        val expected = 500

        assertEquals(obtained, expected)
    }
}

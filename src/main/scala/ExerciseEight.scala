import java.io.{File, FileWriter}

import sttp.client4.*
import sttp.client4.httpclient.HttpClientSyncBackend

/*
Write a function that takes a string input and returns the parsed number if valid, or a clear 
error/false if not. It should reject empty strings, whitespace-only strings, non-numeric 
characters, and null/none inputs without throwing an unhandled exception.
*/
class Ex8Simple:
    def validate(value: String): Int | Double | String =
        if value == null then throw new IllegalArgumentException("null value")
        else value.trim match
            case "" => throw new IllegalArgumentException("empty string")
            case s =>
                val cleaned = s.replaceAll("[^0-9.]", "")
                if cleaned.contains(".") then
                    cleaned.toDoubleOption.getOrElse(
                        throw new IllegalArgumentException(s"not a number: $s"))
                else
                    cleaned.toIntOption.getOrElse(
                        throw new IllegalArgumentException(s"not a number: $s"))

/*
Write functions to read and write files that handle errors gracefully. Reading a missing file, 
a file without permissions, or a directory path should each return a specific, descriptive error 
rather than crashing. Writing should confirm success or surface a clear failure.
*/
class Ex8Intermediate:
    def readFile(path: String): String = {
        require(path.nonEmpty, "path must not be empty")

        try {
            var output = ""

            scala.io.Source.fromFile(path).getLines().foreach{ line => 
                output = output + line
            }

            output
        } catch {
            case e: Throwable => throw new java.io.FileNotFoundException(path).initCause(e)
        }
    }

    def writeFile(path: String): Unit = {
        require(path.nonEmpty, "path must not be empty")

        try {
            val fileWriter = new FileWriter(new File("test4.txt"))
            fileWriter.write("Hello World!")
            fileWriter.close()
        } catch {
            case e: Throwable => throw new java.io.FileNotFoundException(path).initCause(e)
        }
    }

/*
Build an HTTP request handler that: retries on transient errors (network timeouts, 5xx responses) 
up to a configurable maximum, does not retry on client errors (4xx), implements exponential backoff 
between retries, and logs each error and retry attempt with enough context to debug. Return the 
successful response or a final error after exhausting retries.
*/
class RetriesExhaustedException(msg: String) extends RuntimeException(msg)

class Ex8Advanced(backend: SyncBackend = HttpClientSyncBackend()):
    def get(): Int | String = {
        val response = basicRequest
            .get(uri"https://httpbin.org/get")
            .send(backend)
        response.code.code
    }

    def getDelayed200(): Int = {
        var counter = 1
        var success = false
        var result = 0

        while(counter <= 3 && success == false) {
            val response = basicRequest
                .get(uri"https://httpbin.org/status/200")
                .send(backend)

            if (response.code.code == 200) {
                success = true
                result = response.code.code
            } else if (counter == 3) {
                throw new RetriesExhaustedException(s"exhausted after $counter attempts, last status $result")
            }

            counter = counter + 1
        }
        result
    }

    def logError(path: String, error: String): Unit = {
        val fileWriter = new FileWriter(new File(path))
        fileWriter.write(error)
        fileWriter.close()
    }

    def get400(): Int = {
        var counter = 1
        var success = false
        var result = 0

        while(counter <= 3 && success == false) {

            if (counter > 1) {
                logError("RetriesAttemptLog.txt", s"Call to https://httpbin.org/status/400 failed. Retry attempt number $counter")
                Thread.sleep(100L * (1L << counter))
            }

            val response = basicRequest
                .get(uri"https://httpbin.org/status/400")
                .send(backend)

            if (response.code.code == 200) {
                success = true
                result = response.code.code
            } else if (counter == 3) {
                logError("RetriesExhaustedException.txt", "Call to https://httpbin.org/status/400 failed with a status of 400 after 3 attempts")
                throw new RetriesExhaustedException(s"exhausted after $counter attempts, last status $result")
            }

            counter = counter + 1
        }
        result
    }

    def get400NoRetries(): Int = {
        val response = basicRequest
            .get(uri"https://httpbin.org/status/400")
            .send(backend)

        response.code.code
    }

    def get500(retries: Int = 1): Int = {
        var counter = retries
        var success = false
        var result = 0

        while(counter <= 3 && success == false) {
            val response = basicRequest
                .get(uri"https://httpbin.org/status/500")
                .send(backend)

            if (response.code.code == 200) {
                success = true
                result = response.code.code
            } else if (counter == 3) {
                result = response.code.code
            }

            counter = counter + 1
        }

        result
    }

    def post(): Int = {
        val response = basicRequest
            .post(uri"https://httpbin.org/post")
            .send(backend)
        response.code.code
    }

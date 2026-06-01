class Ex7SimpleTests extends munit.FunSuite {
    test("Given the string 'the quick brown fox jumped over the lazy dog', when converted, then 'The Quick Brown Fox Jumped Over The Lazy Dog' is returned") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("the quick brown fox jumped over the lazy dog")
        val expected = "The Quick Brown Fox Jumped Over The Lazy Dog"

        assertEquals(obtained, expected)
    }
    
    test("Given the string 'THE QUICK BROWN FOX JUMPED OVER THE LAZY DOG', when converted, then 'The Quick Brown Fox Jumped Over The Lazy Dog' is returned") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOG")
        val expected = "The Quick Brown Fox Jumped Over The Lazy Dog"

        assertEquals(obtained, expected)
    }

    test("Given the string 'The Quick Brown Fox Jumped Over The Lazy Dog', when converted, then the string is returned unchanged") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("The Quick Brown Fox Jumped Over The Lazy Dog")
        val expected = "The Quick Brown Fox Jumped Over The Lazy Dog"

        assertEquals(obtained, expected)
    }

    test("Given the single word 'fox', when converted, then 'Fox' is returned") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("fox")
        val expected = "Fox"

        assertEquals(obtained, expected)
    }

    test("Given an empty string, when converted, then an empty string is returned") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("")
        val expected = ""

        assertEquals(obtained, expected)
    }

    test("Given the string 'the quick brown fox jumped over 3 lazy dogs', when converted, then '3' is preserved unchanged in the result") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("the quick brown fox jumped over 3 lazy dogs")
        val expected = "The Quick Brown Fox Jumped Over 3 Lazy Dogs"

        assertEquals(obtained, expected)
    }

    test("Given the string 'the quick brown fox jumped over the lazy dog' (two spaces between fox and jumped), when converted, then the extra space is preserved in the result") {
        val transformer = new Ex7Simple
        val obtained = transformer.capitalise("the quick brown fox  jumped over the lazy dog")
        val expected = "The Quick Brown Fox  Jumped Over The Lazy Dog"

        assertEquals(obtained, expected)
    }
}

class Ex7IntermediateTests extends munit.FunSuite {
    test("Given a sentence containing a target word multiple times, when counted, then the correct count is returned") {
        val seeker = new Ex7Intermediate
        val sentence = "the quick brown fox then jumped over the lazy dog"
        val word = "the"
        val obtained = seeker.findWord(sentence, word)
        val expected = 3

        assertEquals(obtained, expected)
    }
    
    test("Given a sentence containing a target word exactly once, when counted, then 1 is returned") {
        val seeker = new Ex7Intermediate
        val sentence = "the quick brown fox"
        val word = "the"
        val obtained = seeker.findWord(sentence, word)
        val expected = 1

        assertEquals(obtained, expected)
    }
    
    test("Given a sentence that does not contain the target word, when counted, then 0 is returned") {
        val seeker = new Ex7Intermediate
        val sentence = "the quick brown fox"
        val word = "cucumber"
        val obtained = seeker.findWord(sentence, word)
        val expected = 0

        assertEquals(obtained, expected)
    }
    
    test("Given an empty sentence, when counted for any word, then 0 is returned") {
        val seeker = new Ex7Intermediate
        val sentence = ""
        val word = "cucumber"
        val obtained = seeker.findWord(sentence, word)
        val expected = 0

        assertEquals(obtained, expected)
    }
    
    test("Given a sentence and an empty string as the search word, when counted, then an error is returned") {
        val seeker = new Ex7Intermediate
        val sentence = ""
        val word = ""
        intercept[IllegalArgumentException] {
            seeker.findWord(sentence, word)
        }
    }
    
    test("Given a sentence with mixed-case occurrences of a word and a case-insensitive search, when counted, then all case variants are included in the count") {
        val seeker = new Ex7Intermediate
        val sentence = "THE quick brown fox then jumped over tHe lazy dog"
        val word = "the"
        val obtained = seeker.findWord(sentence, word)
        val expected = 3

        assertEquals(obtained, expected)
    }
    
    test("Given a sentence where the target word appears as a substring of a longer word (e.g. 'cat' in 'caterpillar'), when counted, then the implementation clearly documents whether partial matches are included") {
        val seeker = new Ex7Intermediate
        val sentence = "caterpillar"
        val word = "cat"
        val obtained = seeker.findWord(sentence, word)
        val expected = 1

        assertEquals(obtained, expected)
    }
}

class Ex7AdvancedTests extends munit.FunSuite {
    test("Given a simple CSV line with no quoted fields, when parsed, then each comma-separated value is returned as a separate field") {
        val parser = new Ex7Advanced
        val obtained = parser.parse("test1.csv")
        val expected = List(List("This", "is", "a", "test"))

        assertEquals(obtained, expected)
    }
    
    // test("Given a CSV line containing a quoted field with a comma inside it, when p arsed, then the comma inside the quotes is not treated as a field delimiter") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }

    // test("Given a CSV line with a quoted field containing an escaped quote, when parsed, then the escaped quote appears as a single literal quote in the returned value") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }

    // test("Given a CSV line with consecutive commas representing empty fields, when parsed, then empty strings are returned for those fields") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }

    // test("Given a CSV line where a field is fully surrounded by quotes, when parsed, then the surrounding quotes are stripped from the returned value") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }

    // test("Given a CSV line with whitespace inside a quoted field, when parsed, then the whitespace is preserved in the returned value") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }

    // test("Given a CSV line with whitespace outside a quoted field, when parsed, then the whitespace is handled per the implementation's documented contract") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }

    // test("Given a malformed CSV line such as one with an unclosed quote, when parsed, then an appropriate error is returned") {
    //     val parser = new Ex7Advanced
    //     val obtained = parser.parse()
    //     val expected = 0

    //     assertEquals(obtained, expected)
    // }
}

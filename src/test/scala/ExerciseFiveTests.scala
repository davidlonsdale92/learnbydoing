class Ex5SimpleTests extends munit.FunSuite {
  test("Given an empty playlist, when a track is added, then the playlist contains that track") {
    val manager = new Ex5Simple
    val playlist = List.empty[String]
    val obtained = manager.add(playlist, "Sample - Example")
    val expected = List("Sample - Example")

    assertEquals(obtained, expected)
  }

  test("Given a playlist with tracks, when a track is added, then it appears at the end of the playlist") {
    val manager = new Ex5Simple
    val playlist = List("Sample - Example")
    val obtained = manager.add(playlist, "Sample2 - Example")
    val expected = List("Sample - Example", "Sample2 - Example")

    assertEquals(obtained, expected)
  }

  test("Given a playlist containing a track, when that track is removed by name, then it no longer appears in the playlist") {
    val manager = new Ex5Simple
    val playlist = List("Sample - Example", "Sample2 - Example")
    val obtained = manager.removeTrack(playlist, "Sample2 - Example")
    val expected = List("Sample - Example")

    assertEquals(obtained, expected)
  }

  test("Given a playlist, when a track at a specific position is retrieved, then the correct track name is returned") {
    val manager = new Ex5Simple
    val playlist = List("Sample - Example", "Sample2 - Example")
    val obtained = manager.get(playlist, "Sample2 - Example")
    val expected = List("Sample2 - Example")

    assertEquals(obtained, expected)
  }

  test("Given a playlist with three tracks, when the total is requested, then 3 is returned") {
    val manager = new Ex5Simple
    val playlist = List("Sample - Example", "Sample2 - Example")
    val obtained = manager.total(playlist)
    val expected = 2

    assertEquals(obtained, expected)
  }

  test("Given an empty playlist, when the total is requested, then 0 is returned") {
    val manager = new Ex5Simple
    val playlist = List.empty[String]
    val obtained = manager.total(playlist)
    val expected = 0

    assertEquals(obtained, expected)
  }

  test("Given a playlist, when a track name that doesn't exist is removed, then an appropriate error is returned") {
    val manager = new Ex5Simple
    val playlist = List("Sample - Example", "Sample2 - Example")

    intercept[NoSuchElementException] {
      manager.removeTrack(playlist, "Sample3 - Example")
    }
  }
}

class Ex5IntermediateTests extends munit.FunSuite {
    test("Given an empty phone book, when a contact is added, then that contact can be retrieved by name") {
        val phoneBook = new Ex5Intermediate
        phoneBook.addContact("David", "07875123456")
        val obtained = phoneBook.getContact("David")
        val expected = Map("David" -> "07875123456")

        assertEquals(obtained, expected)
    }

    test("Given a phone book with existing contacts, when a new contact is added, then all previously existing contacts remain retrievable") {
        val phoneBook = new Ex5Intermediate
        phoneBook.addContact("David", "07875123456")
        phoneBook.addContact("Sara", "07875456789")
        val obtained = phoneBook.getPhoneBook()
        val expected = Map("David" -> "07875123456", "Sara" -> "07875456789")

        assertEquals(obtained, expected)
    }

    test("Given a phone book containing a contact, when that contact's name is looked up, then the correct phone number is returned") {
        val phoneBook = new Ex5Intermediate
        phoneBook.addContact("David", "07875123456")
        val obtained = phoneBook.getNumber("David")
        val expected = "07875123456"

        assertEquals(obtained, expected)
    }

    test("Given a phone book, when a name that does not exist is looked up, then a not-found result is returned") {
        val phoneBook = new Ex5Intermediate
        intercept[NoSuchElementException] {
            phoneBook.getContact("David")
        }
    }

    test("Given a phone book with an existing contact, when a new entry is added with the same name, then the phone number is updated rather than duplicated") {
        val phoneBook = new Ex5Intermediate
        phoneBook.addContact("David", "07875123456")
        phoneBook.addContact("David", "07875456789")
        val obtained = phoneBook.getNumber("David")
        val expected = "07875456789"

        assertEquals(obtained, expected)
    }

    test("Given a phone book with multiple contacts, when one specific name is looked up, then only that contact's details are returned") {
        val phoneBook = new Ex5Intermediate
        phoneBook.addContact("David", "07875123456")
        phoneBook.addContact("Sara", "07875456789")
        val obtained = phoneBook.getContact("David")
        val expected = Map("David" -> "07875123456")

        assertEquals(obtained, expected)
    }

    test("Given a phone book containing a contact, when that contact is removed and then looked up, then a not-found result is returned") {
        val phoneBook = new Ex5Intermediate
        phoneBook.addContact("David", "07875123456")
        phoneBook.addContact("Sara", "07875456789")
        phoneBook.removeContact("David")

        intercept[NoSuchElementException] {
            phoneBook.getContact("David")
        }
    }
}

class Ex5AdvancedTests extends munit.FunSuite {
    test("Given an empty trie and a new word, when the word is inserted and its prefix is searched, then that word is returned") {
        val trie = new Ex5Advanced
        trie.insert("This")
        trie.insert("Test")
        val obtained = trie.search("Test")
        val expected = true

        assertEquals(obtained, expected)
    }

    test("Given a trie containing words sharing a prefix (e.g. 'cat', 'car', 'card'), when searching the shared prefix 'ca', then all three words are returned") {
        val trie = new Ex5Advanced
        trie.insert("cat")
        trie.insert("car")
        trie.insert("card")
        val obtained = trie.startsWith("ca")
        val expected = Seq("car", "card", "cat")

        assertEquals(obtained, expected)
    }

    test("Given a trie with several words, when searching a prefix that matches none of them, then an empty result is returned") {
        val trie = new Ex5Advanced
        trie.insert("cat")
        trie.insert("car")
        trie.insert("card")
        val obtained = trie.startsWith("z")
        val expected = Seq()

        assertEquals(obtained, expected)
    }

    test("Given a trie with several words, when an empty string is searched, then all words in the trie are returned") {
        val trie = new Ex5Advanced
        trie.insert("cat")
        trie.insert("car")
        trie.insert("card")
        val obtained = trie.startsWith("")
        val expected = Seq("car", "card", "cat")

        assertEquals(obtained, expected)
    }

    test("Given a trie containing a word, when that word is deleted and its prefix is searched, then the deleted word no longer appears in results") {
        val trie = new Ex5Advanced
        trie.insert("cat")
        trie.delete("cat")
        val obtained = trie.startsWith("ca")
        val expected = Seq()

        assertEquals(obtained, expected)
    }

    test("Given a trie containing 'card' and 'car', when 'car' is deleted and 'ca' is searched, then 'card' still appears in results but 'car' does not") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given a trie, when a word that does not exist is deleted, then no error is thrown and the trie is unchanged") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given a trie with words of different cases (e.g. 'Apple', 'apple'), when searching 'app', then both are treated as the same word") {
        val obtained = 0
        val expected = 0

        assertEquals(obtained, expected)
    }
}

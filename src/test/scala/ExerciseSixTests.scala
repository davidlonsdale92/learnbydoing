class Ex6SimpleTests extends munit.FunSuite {
  test("Given a list with several items and a search term that exists in the list, when searched, then true is returned") {
    val linearSearch = new Ex6Simple
    val list = List("This", "is", "a", "test")
    val obtained = linearSearch.search(list, "test")
    val expected = true

    assertEquals(obtained, expected)
  }
  
  test("Given a list with several items and a search term that does not exist in the list, when searched, then false is returned") {
    val linearSearch = new Ex6Simple
    val list = List("This", "is", "a", "test")
    val obtained = linearSearch.search(list, "good")
    val expected = false

    assertEquals(obtained, expected)
  }

  test("Given an empty list and any search term, when searched, then false is returned") {
    val linearSearch = new Ex6Simple
    val list = List.empty[String]
    val obtained = linearSearch.search(list, "good")
    val expected = false

    assertEquals(obtained, expected)
  }

  test("Given a list containing null/none values and a search for null/none, when searched, then true is returned") {
    val linearSearch = new Ex6Simple
    val list = List(null)
    val obtained = linearSearch.search(list, null)
    val expected = true

    assertEquals(obtained, expected)
  }

  test("Given a list with duplicate occurrences of an item and a search for that item, when searched, then true is returned") {
    val linearSearch = new Ex6Simple
    val list = List("test", "test", "test", "test")
    val obtained = linearSearch.search(list, "test")
    val expected = true

    assertEquals(obtained, expected)
  }

  test("Given a list containing integer 1 and a search for string '1', when searched, then false is returned in strictly-typed " +
       "languages, and the result is consistent with the language's equality semantics for dynamically-typed languages where " +
       "type coercion may occur") {
    val linearSearch = new Ex6Simple
    val list = List(1)
    val obtained = linearSearch.search(list, "1")
    val expected = false

    assertEquals(obtained, expected)
  }
}

class Ex6IntermediateTests extends munit.FunSuite {
    test("Given an unsorted list of 15 names including mixed-case entries " +
         "(['Zara', 'alice', 'MIKE', 'bob', 'Charlie', 'anna', 'Aaron', 'zoe', 'Beth', 'DAVID', 'eve', 'Frank', 'grace', 'HENRY', 'isla']), " +
         "when sorted case-insensitively, then the names appear in A–Z order") {
        val sorter = new Ex6Intermediate
        val names = List("Zara", "alice", "MIKE", "bob", "Charlie", "anna", "Aaron", "zoe", "Beth", "DAVID", "eve", "Frank", "grace", "HENRY", "isla")
        val result = sorter.sortNames(names)
        val obtained =  result._2
        val expected = List("Aaron", "alice", "anna", "Beth", "bob", "Charlie", "DAVID", "eve", "Frank", "grace", "HENRY", "isla", "MIKE", "Zara", "zoe")

        assertEquals(obtained, expected)
    }
    
    test("Given an already-sorted list of names, when sorted, then the order is unchanged") {
        val sorter = new Ex6Intermediate
        val names = List("Aaron", "alice", "anna", "Beth", "bob", "Charlie", "DAVID", "eve", "Frank", "grace", "HENRY", "isla", "MIKE", "Zara", "zoe")
        val result = sorter.sortNames(names)
        val obtained =  result._2
        val expected = List("Aaron", "alice", "anna", "Beth", "bob", "Charlie", "DAVID", "eve", "Frank", "grace", "HENRY", "isla", "MIKE", "Zara", "zoe")

        assertEquals(obtained, expected)
    }

    test("Given a list containing a single name, when sorted, then that name is returned") {
        val sorter = new Ex6Intermediate
        val names = List("Aaron")
        val result = sorter.sortNames(names)
        val obtained =  result._2
        val expected = List("Aaron")

        assertEquals(obtained, expected)
    }

    test("Given an empty list, when sorted, then an empty list is returned") {
        val sorter = new Ex6Intermediate
        val names = List.empty[String]
        val result = sorter.sortNames(names)
        val obtained =  result._2
        val expected = List.empty[String]

        assertEquals(obtained, expected)
    }

    test("Given a list containing duplicate names, when sorted, then the duplicates appear adjacent to each other") {
        val sorter = new Ex6Intermediate
        val names = List("Zara", "alice", "MIKE", "bob", "Charlie", "anna", "grace", "Aaron", "zoe", "alice", "Beth", "DAVID", "eve", "Frank", "grace", "HENRY", "isla")
        val result = sorter.sortNames(names)
        val obtained =  result._2
        val expected = List("Aaron", "alice", "alice", "anna", "Beth", "bob", "Charlie", "DAVID", "eve", "Frank", "grace", "grace", "HENRY", "isla", "MIKE", "Zara", "zoe")


        assertEquals(obtained, expected)
    }

    test("Given a list of mixed-case names, when sorted case-insensitively, then the names are ordered correctly regardless of case") {
        val sorter = new Ex6Intermediate
        val names = List("Zara", "alice", "MIKE", "bob", "Charlie", "anna", "Aaron", "zoe", "Beth", "DAVID", "eve", "Frank", "grace", "HENRY", "isla")
        val result = sorter.sortNames(names)
        val obtained =  result._2
        val expected = List("Aaron", "alice", "anna", "Beth", "bob", "Charlie", "DAVID", "eve", "Frank", "grace", "HENRY", "isla", "MIKE", "Zara", "zoe")

        assertEquals(obtained, expected)
    }

    test("Given the original list, when a non-destructive sort is performed, then the original list is not mutated") {
        val sorter = new Ex6Intermediate
        val names = List("Zara", "alice", "MIKE", "bob", "Charlie", "anna", "Aaron", "zoe", "Beth", "DAVID", "eve", "Frank", "grace", "HENRY", "isla")
        val result = sorter.sortNames(names)
        val obtained =  result._1
        val expected = names

        assertEquals(obtained, expected)
    }
}

class Ex6AdvancedTests extends munit.FunSuite {
    test("Given the sorted list [1, 3, 5, 7, 7, 9, 11, 13, 15, 19] and a target value that exists once (e.g. 9), " +
         "when searched, then the correct index is returned (5 in this case)") {
        val search = new Ex6Advanced
        val obtained = search.binarySearch(IndexedSeq(1, 3, 5, 7, 7, 9, 11, 13, 15, 19), 9)
        val expected = 5

        assertEquals(obtained, expected)
    }

    test("Given the sorted list [1, 3, 5, 7, 7, 9, 11, 13, 15, 19] and a target value that does not exist (e.g. 6)," +
         "when searched, then -1 (or the implementation's not-found signal) is returned") {
        val search = new Ex6Advanced
        val obtained = search.binarySearch(IndexedSeq(1, 3, 5, 7, 7, 9, 11, 13, 15, 19), 6)
        val expected = -1

        assertEquals(obtained, expected)
    }

    test("Given the sorted list [1, 3, 5, 7, 7, 9, 11, 13, 15, 19] and a target that is the first element (1), when " +
         "searched, then index 0 is returned") {
        val search = new Ex6Advanced
        val obtained = search.binarySearch(IndexedSeq(1, 3, 5, 7, 7, 9, 11, 13, 15, 19), 1)
        val expected = 0

        assertEquals(obtained, expected)
    }

    test("Given the sorted list [1, 3, 5, 7, 7, 9, 11, 13, 15, 19] and a target that is the last element (19), when " +
         "searched, then index 9 is returned") {
        val search = new Ex6Advanced
        val obtained = search.binarySearch(IndexedSeq(1, 3, 5, 7, 7, 9, 11, 13, 15, 19), 19)
        val expected = 9

        assertEquals(obtained, expected)
    }

    test("Given the sorted list [1, 3, 5, 7, 7, 9, 11, 13, 15, 19] and a target with duplicate values (7), when searched, " +
         "then a valid index of either occurrence (4 or 5) is returned") {
        val search = new Ex6Advanced
        val obtained = search.binarySearch(IndexedSeq(1, 3, 5, 7, 7, 9, 11, 13, 15, 19), 7)
        val expected = 4

        assertEquals(obtained, expected)
    }

    test("Given an empty list and any target value, when searched, then the not-found signal is returned") {
        val search = new Ex6Advanced
        val obtained = search.binarySearch(IndexedSeq(1, 3, 5, 7, 7, 9, 11, 13, 15, 19), 11)
        val expected = 6

        assertEquals(obtained, expected)
    }

    test("Given the unsorted list [3, 1, 4, 1, 5, 9, 2, 6], when binary search is applied, then an error is returned") {
        val search = new Ex6Advanced
        intercept[IllegalArgumentException] {
            search.binarySearch(IndexedSeq(3, 1, 4, 1, 5, 9, 2, 6), 11)
        }
    }
}

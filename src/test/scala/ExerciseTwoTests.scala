class Ex2SimpleTests extends munit.FunSuite {
  test("Given an initial total of zero, when a positive number is added, then the total equals that number") {
    var tracker = new Ex2Simple

    tracker.add(4)

    val obtained = tracker.get()
    val expected = 4

    assertEquals(obtained, expected)
  }

  test("Given a running total with an existing value, when multiple numbers are added sequentially, " +
        "then the total equals the sum of all added values") {
    var tracker = new Ex2Simple

    tracker.add(4)
    tracker.add(6)
    tracker.add(8)

    val obtained = tracker.get()
    val expected = 18

    assertEquals(obtained, expected)
  }

  test("Given a running total, when zero is added, then the total remains unchanged") {
    var tracker = new Ex2Simple
    
    tracker.add(4)
    tracker.add(6)
    tracker.add(8)
    tracker.add(0)

    val obtained = tracker.get()
    val expected = 18

    assertEquals(obtained, expected)
  }

  test("Given a running total, when a negative number is added, then the total decreases by that amount") {
    var tracker = new Ex2Simple

    tracker.add(4)
    tracker.add(6)
    tracker.add(-8)

    val obtained = tracker.get()
    val expected = 2
    assertEquals(obtained, expected)
  }

  test("Given a running total with a non-zero value, when the total is reset, then it returns to zero") {
    var tracker = new Ex2Simple

    tracker.add(4)
    tracker.add(6)
    tracker.add(-8)
    tracker.reset()

    val obtained = tracker.get()
    val expected = 0
    assertEquals(obtained, expected)
  }
}

class Ex2IntermediateTests extends munit.FunSuite {
  test("Given a new account, when the balance is checked, then it is zero") {
    var account = new Ex2Intermediate
    val obtained = account.getBalance()
    val expected = 0

    assertEquals(obtained, expected)
  }

  test("Given an account, when a positive amount is deposited, then the balance increases by that amount") {
    var account = new Ex2Intermediate
    account.deposit(10)
    val obtained = account.getBalance()
    val expected = 10

    assertEquals(obtained, expected)
  }

  test("Given an account with a balance, when a valid amount is withdrawn, then the balance decreases by that amount") {
    var account = new Ex2Intermediate
    account.deposit(5)
    account.withdrawal(5)
    val obtained = account.getBalance()
    val expected = 0

    assertEquals(obtained, expected)
  }

  test("Given an account with insufficient funds, when a withdrawal is attempted, then it is rejected and " +
       "the balance is unchanged") {
    var account = new Ex2Intermediate
    account.withdrawal(5)
    val obtained = account.getBalance()
    val expected = 0

    assertEquals(obtained, expected)
  }

  test("Given an account, when a zero amount is deposited, then it is rejected with an error") {
    val account = new Ex2Intermediate
    intercept[IllegalArgumentException] {
      account.deposit(0)
    }
  }

  test("Given an account, when a negative amount is deposited, then it is rejected with an error") {
    val account = new Ex2Intermediate
    intercept[IllegalArgumentException] {
      account.deposit(-10)
    }
  }

  test("Given an account, when a zero amount is withdrawn, then it is rejected with an error") {
    val account = new Ex2Intermediate
    intercept[IllegalArgumentException] {
      account.withdrawal(0)
    }
  }

  test("Given an account, when a negative amount is withdrawn, then it is rejected with an error") {
    val account = new Ex2Intermediate
    intercept[IllegalArgumentException] {
      account.withdrawal(-10)
    }
  }

  test("Given an account with a balance, when multiple deposits and withdrawals are made in sequence, " +
       "then the final balance reflects all valid transactions correctly") {
    var account = new Ex2Intermediate
    account.deposit(10)
    account.deposit(10)
    account.withdrawal(10)
    val obtained = account.getBalance()
    val expected = 10

    assertEquals(obtained, expected)
  }
}

class Ex2AdvancedTests extends munit.FunSuite {
  // test("Given a state container with an initial value, when the state is read, then the initial value is returned") {
  //   var container = new Ex2Advanced
  //   val obtained = container.readState()
  //   val expected = ""

  //   assertEquals(obtained, expected)
  // }

  // test("Given a state container, when the state is updated, then reading the state returns the new value") {
  //   var container = new Ex2Advanced
  //   container.setState(true)
  //   val obtained = container.readState()
  //   val expected = true

  //   assertEquals(obtained, expected)
  // }

  // test("Given a subscriber registered with the container, when the state is updated, then the subscriber callback is called with the new state") {
  // var container = new Ex2Advanced
  //   val obtained = container.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }

  // test("Given a subscriber registered with the container, when the state is updated multiple times, then the subscriber is called once for each update") {
  // var container = new Ex2Advanced
  //   val obtained = account.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }

  // test("Given multiple subscribers registered with the container, when the state is updated, then all subscribers are notified") {
  // var container = new Ex2Advanced
  //   val obtained = account.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }

  // test("Given a subscriber that has been unsubscribed, when the state is updated, then that subscriber is no longer called") {
  // var container = new Ex2Advanced
  //   val obtained = account.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }

  // test("Given two subscribers where one unsubscribes, when the state is updated, then only the remaining subscriber is called") {
  // var container = new Ex2Advanced
  //   val obtained = account.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }

  // test("Given a subscriber that throws an error, when the state is updated, then the error does not prevent other subscribers from being notified") {
  // var container = new Ex2Advanced
  //   val obtained = account.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }

  // test("Given a state container, when setState is called with the same value as the current state, then the implementation documents whether subscribers are notified or not") {
  // var container = new Ex2Advanced
  //   val obtained = account.getBalance()
  //   val expected = 0

  //   assertEquals(obtained, expected)
  // }
}

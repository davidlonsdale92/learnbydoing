import scala.annotation.tailrec
import scala.io.StdIn.*

/*
Build a running total tracker that starts at zero. It should expose an "add" operation 
that accepts any number (positive, negative, or zero) and updates the total accordingly. 
It should also expose a "reset" operation that returns the total to zero. The total 
should be readable at any point.
*/
class Ex2Simple:
    var tracker = 0

    def add(value: Int): Int = {
        this.tracker += value
        value
    }

    def get(): Int = this.tracker

    def reset(): Int = {
        this.tracker = 0
        this.tracker
    }

/*
Create a bank account that starts with a zero balance. It should expose a deposit operation 
that adds a positive amount to the balance, a withdrawal operation that deducts an amount 
but rejects it if funds are insufficient, and a balance enquiry that returns the current 
balance. Both deposit and withdrawal should reject negative or zero amounts.
*/
class Ex2Intermediate:
    var balance = 0

    def deposit(value: Int): Unit =
        if value <= 0 then throw new IllegalArgumentException("amount must be positive")
        balance += value

    def withdrawal(value: Int): Unit =
        if value <= 0 then throw new IllegalArgumentException("amount must be positive")
        if balance < value then return   // or throw, depending on spec
        balance -= value

    def getBalance(): Int = this.balance


/*
Create a value store that holds a current value and supports five operations: 
    set (replace the current value with a new one), 
    get (return the current value), 
    undo (revert to the previous value), 
    redo (reapply a value that was undone), 
    history (return all previous values in order, oldest first). 

The store should support a configurable maximum history size -- when the limit is reached, 
the oldest history entry is discarded. Performing a new set after an undo should clear the 
redo stack.
*/
class Ex2Advanced:
    var state: Any = ""

    def setState(newState: Any): Any = {
        state = newState
    }

    def readState(): Any = state

    def subscribe(): Any = {

    }

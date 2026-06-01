import scala.annotation.tailrec
import scala.math.BigDecimal
import scala.io.StdIn.*


/*
Write a function that returns a list of every integer from 1 to 100 inclusive, in ascending order.
*/
class Ex4Simple:
    def increment(): List[Int] = {
        (for n <- 1 to 100 yield n).toList
    }

/*
Write a function that takes a list of integers and returns a new list containing only the even 
numbers, in their original order. The original list must not be modified.
*/
class Ex4Intermediate:
    def getEvens(numbers: List[Int]): (List[Int], List[Int]) = {

        var evens = List.empty[Int]

        for(n <- numbers) {
            if(n % 2 == 0) evens = evens :+ n
        }

        val result = (evens, numbers)
        result
    }

/*
Write a function that takes a nested list and a depth parameter, and flattens the list up to 
that number of levels. A depth of 1 should flatten only the outermost nesting, a depth of 2 
should flatten two levels, and so on. A depth of 0 should return the list unchanged.
*/
class Ex4Advanced:
    def flatten(depth: Int, numbers: List[Any]): List[Any] =
        if depth == 0 then numbers
        else numbers.flatMap {
            case nested: List[?] => flatten(depth - 1, nested)
            case leaf            => List(leaf)
        }

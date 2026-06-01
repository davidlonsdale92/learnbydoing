import scala.annotation.tailrec
import scala.io.StdIn.*
import scala.math.Numeric.Implicits.infixNumericOps
import scala.math.Ordered.orderingToOrdered

/*
Write one or more functions that take two numbers as arguments and return their sum. 
It should handle integers, decimals, negatives, and zero correctly.
*/
class Ex1Simple:
    def calculate[T: Numeric](first: T, second: T): T = {
        first + second
    }

/*
Write a function that takes a list of numbers and returns the largest value in the 
list. It should handle negative numbers and decimals, and return an appropriate 
error or null when given an empty list.
*/
class Ex1Intermediate:
    // This could be done with values.max but chose to implement bubble sort for
    // the challene
    def getLargest[T: Numeric](values: Array[T]): T =
        require(values.nonEmpty, "values must not be empty")

        for (i <- values.indices) {
            for (j <- 0 until values.length - i - 1) {
                if (values(j) > values(j + 1)) {
                    val tmp = values(j)
                    values(j) = values(j + 1)
                    values(j + 1) = tmp
                }
            }
        }
        values.last

/*
Write a higher-order function that takes a list and another function as arguments,
applies that function to every item in the list, and returns the results as a new
list. The original list must not be mutated.
*/
class Ex1Advanced:
    def transform[T, R](values: List[T], func: T => R): List[T | R] = {
        
        var transformed = List.empty[R]

        for(value <- values) {
            transformed = transformed :+ (func(value))
        }

        transformed
    }


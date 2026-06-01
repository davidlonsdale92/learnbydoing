import scala.annotation.tailrec
import scala.math.BigDecimal
import scala.io.StdIn.*

/*
Write a function that takes a single number and returns the string 
"positive", "negative", or "zero" depending on its value.
*/
class Ex3Simple:
    def valueCheck(value: BigDecimal): String = {
        value match {
            case value if (value > 0)  => "positive"
            case value if (value < 0)  => "negative"
            case value if (value == 0) => "zero"
            case _                     => "Unknown"
        }
    }

/*
Write a function that takes a numerical score from 0–100 and returns 
the corresponding letter grade: 
    A (90–100), 
    B (80–89), 
    C (70–79), 
    D (60–69), 
    F (below 60). 
Scores outside the 0–100 range should return an error.
*/
class Ex3Intermediate:
    def getGrade(score: Int): String = {
        score match {
            case score if (score >=90 & score <= 100) => "A"
            case score if (score >=80 & score <= 89)  => "B"
            case score if (score >=70 & score <= 79)  => "C"
            case score if (score >=60 & score <= 69)  => "D"
            case score if (score >=0 & score <= 59)   => "F"
            case _                                    => "Invalid Input"
        }
    }

/*
Build a form validator that takes an object of field values and returns a list of specific errors for every invalid field — not just the first one. Fields to validate include: a required name, an email address validated against a regex pattern (must have a valid local part, @ symbol, domain, and domain extension such as .com), a password of minimum 8 characters, and a numeric age field. The validator must use a regex for email validation — a simple @ check is not sufficient.
*/
// class Ex3Advanced:
//     def validate[T](fields:Map[String, T]): Unit = {


//         fields match {
//             case fields.name if (fields.name.isInstanceOf[String]) => {
//                 println("String")
//             }
//             case fields.email if (fields.email.isInstanceOf[String]) => {
//                 println("String")
//             }
//             case fields.password if (fields.password.isInstanceOf[String]) => {
//                 println("String")
//             }
//             case fields.age if (fields.age.isInstanceOf[Int]) => {
//                 println("Int")
//             }
//             case _ => throw new Error
//         }
//     }

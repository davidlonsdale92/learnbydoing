import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.Using

/*
Write a function that takes a file path, reads the file, and returns the number of lines 
it contains. It should handle empty files, files without a trailing newline, and missing 
files gracefully. For large files, avoid loading the entire contents into memory at once.
*/
class Ex9Simple:
    def countLines(path: String): Int = {
        require(path.nonEmpty, "path must not be empty")

        try {
            val count: Int = Using(Source.fromFile(path)) { source =>
              source.getLines().foldLeft(0)((c, _) => c + 1)
            }.get

            count
        } catch {
            case e: Throwable => throw new java.io.FileNotFoundException(path).initCause(e)
        }
    }

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
Write a function that reads a JSON file from disk, parses it, and extracts a set of specified 
fields. It should return clear errors for: a missing file, malformed JSON, and missing fields 
within otherwise valid JSON. Nested field access should also be supported.
*/
class Ex9Intermediate:
    def readJson(path: String): ujson.Value = {
        try {

        val text = Using.resource(Source.fromFile(path))(_.mkString)
        ujson.read(text)
        } catch {
            case e: Throwable => throw new java.io.FileNotFoundException(path).initCause(e)
        }
    }

/*

*/
// class Ex9Advanced:
//     def validate[T](fields:Map[String, T]): Unit = {

//     }

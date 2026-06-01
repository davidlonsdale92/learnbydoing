import com.github.tototoshi.csv._

/*
Write a function that takes a string and returns a new string with the first letter 
of each word capitalised and all other letters lowercased. Words are separated by spaces. 
Numbers and symbols should be preserved unchanged.
*/
class Ex7Simple:
    def capitalise(string: String): String = {
        var counter = 0
        var capitalised = ""

        for(i <- 0 until string.length) {
            val char = string(i)
            val previous = if(i == 0) ' ' else string(i - 1)

            if(counter == 0 || previous.isWhitespace) {
                capitalised = capitalised + char.toUpper
            } else {
                capitalised = capitalised + char.toLower
            }
            counter = counter + 1
        }
        capitalised
    }

/*
Write a function that takes a sentence and a search word, and returns the number of times 
that word appears. Document clearly whether the search is case-sensitive and whether partial
matches (e.g. "cat" inside "caterpillar") are counted.
*/
class Ex7Intermediate:
    def findWord(sentence: String, needle: String): Int = {
        require(needle.nonEmpty, "Search term must not be empty")

        var counter = 0

        for (word <- sentence.split(" ")) {
            var normalisedWord = word.toLowerCase
            if normalisedWord.contains(needle) then counter = counter + 1
        }

        counter
    }

/*
Write a CSV line parser that correctly splits a line into fields, handling: commas inside
quoted fields (which should not be treated as delimiters), escaped quotes inside a quoted
field, empty fields, and stripping surrounding quotes from field values. Return an error
for malformed input like unclosed quotes.
*/
class Ex7Advanced:
    def parse(file: String): List[List[String]] = {

        var parsed = List.empty[String]

        val reader = CSVReader.open(file)
        val all: List[List[String]] = reader.all()

        reader.foreach(fields => println(fields))
        println(parsed)
        reader.close()  
        all
    }

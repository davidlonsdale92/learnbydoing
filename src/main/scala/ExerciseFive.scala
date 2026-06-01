import scala.collection.mutable
import scala.util.boundary, boundary.break

/*
Write a playlist manager that stores a collection of track names and supports four 
operations: adding a track to the end of the playlist, removing a track by name, 
retrieving a track by its position, and returning the total number of tracks.
*/
class Ex5Simple:
    def add(playlist: List[String], track: String): List[String] = {
        playlist :+ track
    }

    def removeTrack(playlist: List[String], track: String): List[String] = {
        if (!playlist.contains(track))
            throw new NoSuchElementException(s"Track not found: $track")

        var filteredList = List.empty[String]
        for (t <- playlist) {
            if (t != track) filteredList = filteredList :+ t
        }
        filteredList
    }

    def get(playlist: List[String], track: String): List[String] = {

        var newPlaylist = List.empty[String]

        for(t <- playlist) {
            if(t == track) newPlaylist = newPlaylist :+ t 
        }

        newPlaylist
    }

    def total(playlist: List[String]): Int = playlist.length

/*
Build a phone book data structure that supports: adding a contact (name + phone number), 
looking up a number by name, updating an existing contact's number, and removing a contact. 
Looking up a name that doesn't exist should return a clear not-found result.
*/
class Ex5Intermediate:
    type PhoneBook = Map[String, String]
    var phoneBook: PhoneBook = Map()

    def addContact(name: String, number: String): PhoneBook = {
        phoneBook = phoneBook.updated(name, number)
        phoneBook
    }

    def removeContact(name: String): PhoneBook = {
        phoneBook = phoneBook.removed(name)
        phoneBook
    }

    def getContact(name: String): PhoneBook = {
        if (!phoneBook.contains(name))
            throw new NoSuchElementException(s"$name not found")
        phoneBook.filter{ case (k, _) => k == name }
    }

    def getNumber(name: String): String = {
        var number = phoneBook.getOrElse(name, throw new NoSuchElementException(s"$name not found"))
        number
    }

    def getPhoneBook(): PhoneBook = {
        phoneBook
    }

/*
Build a trie (prefix tree) from scratch that supports inserting words, deleting words, and returning 
all words that match a given prefix. Searching an empty prefix should return all words in the trie. 
The trie should be case-insensitive -- treat all input as lowercase.
*/
class Ex5Advanced:
    case class TrieNode(
        var children: mutable.Map[Char, TrieNode] = mutable.Map.empty,
        var isWord: Boolean = false
    )

    private val root = TrieNode()

    def insert(word: String): Unit = {
        var current = root

        // For each character in the word you traverse the trie to check if the current character 
        // is the value of the node. If it is then you set the curr value as the next node. 
        // If it's not then you create a new node with the current character. This either sets 
        // a new word entirely or extends from an existing word to form a new one.
        for(i <- 0 until word.length) {
            val char = word(i)
            print(char)

            if (!current.children.contains(char)) {
                current.children(char) = TrieNode()
            }
            current = current.children(char) 
        }
        // isWord signifies the end of the word 
        current.isWord = true
    }
    
    // Checks if a given word is in the trie and returns a bool indicating it's existance
    // boundary allows for break statements
    def search(word: String): Boolean = boundary {
        var current = root 

        for(i <- 0 until word.length) {
            val char = word(i)
            
            if(!word.contains(char)) {
                break(false)
            }

            current = current.children(char)
        }

        true
    }
    
    // Checks if a word contains a given prefix is in the trie then returns a list of those words
    def startsWith(partial: String): Seq[String] = boundary {
        var node = root
        for (ch <- partial) {
            node.children.get(ch) match {
                case Some(next) => node = next
                case None       => break(Seq.empty)
            }
        }

        val results = mutable.ListBuffer.empty[String]
        traverseTrie(node, partial, results)
        results.toSeq
    }

    // Recursively walks the trie for all nodes of words containing the prefix
    private def traverseTrie(node: TrieNode, prefix: String, results: mutable.ListBuffer[String]): Unit = {
        // base case
        if (node.isWord) results += prefix

        for ((ch, child) <- node.children) {
            traverseTrie(child, prefix + ch, results)
        }
    }

    def delete(item: String): Boolean = {
        deleteFrom(root, item, 0)
    }

    // Recursively walks the trie for all nodes of words containing the prefix and deletes the nodes
    private def deleteFrom(node: TrieNode, item: String, idx: Int): Boolean =
        // base case
        if (idx == item.length) {
            if (!node.isWord) false
            else { 
                node.isWord = false
                node.children.isEmpty
            }
        } else {
            val ch = item(idx)
            node.children.get(ch).exists { child =>
                if (deleteFrom(child, item, idx + 1)) {
                    node.children.remove(ch)
                    !node.isWord && node.children.isEmpty
                } else false
            }
        }



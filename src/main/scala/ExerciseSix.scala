

/*
Write a function that takes a list and a search term, and returns true if the item exists 
in the list, false if not. It should handle empty lists and null/none values correctly.
*/
class Ex6Simple:
    def search[T](haystack: List[T], needle: String ): Boolean = {
        var found = false 

        // linear search algorithm
        for (i <- haystack.indices) do {
            if(haystack(i) == needle) {
                found = true
            }
        }
        found
    }

/*
Write a function that takes a list of name strings and returns a new list sorted in A–Z 
alphabetical order, case-insensitively. The original list must not be modified.
*/
class Ex6Intermediate:
    def sortNames(names: List[String]): (List[String], List[String]) = {
        val arr = names.toArray   // copy into a mutable Array
        
        // Bubble Sort
        for i <- 0 until arr.length do {
            for j <- 0 until arr.length - 1 - i do {
                if (arr(j).toLowerCase > arr(j + 1).toLowerCase) {
                    val tmp = arr(j)
                    arr(j) = arr(j + 1)
                    arr(j + 1) = tmp
                }
            }
        }

        var result = (names, arr.toList)
        result
    }

/*
Implement binary search from scratch — do not use a built-in search method. The function 
takes a sorted list and a target value, and returns the index of the target if found, or -1 
(or your language's equivalent) if not. Return an error if the list is unsorted.
*/
class Ex6Advanced:
    def binarySearch(haystack: IndexedSeq[Int], needle: Int): Int = {
        val isSorted = haystack.lazyZip(haystack.drop(1)).forall(_ <= _)
  
        if (!isSorted) {
            throw new IllegalArgumentException("The input list must be sorted.")
        }

        var low = 0
        var high = haystack.length

        var found = -1

        while {
            var midpoint = (low + (high - low) / 2)
            var pivot = haystack(midpoint)

            if(pivot == needle) {
                return midpoint
            } else if (pivot > needle) {
                high = midpoint
            } else {
                low = midpoint + 1
            }

            low < high
        }
        do ()

        found
    }

/project folder:
kennwu17-project07/

 Brief description of submitted files:


lib/json-simple-1.1.1.jar
    - Java toolkit for JSON (JavaScript Object Notation) used to encode or decode

 resources/findAllGenres.txt
 -Test file to find each genre in existing tableOfGenres

 resources/findBoundaryIDs.txt
 -Test file to find Boundary IDs of 1 and 59600

 resources/findGenres.txt
 -Example test file for hashing based on genres names


 resources/findIDs.txt
 - Example test file for hashing based on IDs.


 resources/findNegativeIDs.txt
 -Test file to see what happens if negatives IDs are inputted

 resources/music_genre_subset.json
 -list songs and their elements/attributes

 resources/RUN.txt
 -Sample outputs of MyTunes


 src/cs1c/SongEntry.java
 -One object of class SongEntry stores a simplified version of the genre data set from the Million Song Dataset.


 src/cs1c/TimeConverter.java
 -Converts duration into a string representation.


 src/cs1c/MillionSongDataSubset.java
 -One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

 src/hashTables/FHhashQP.java
 -stores data in hashes using quadratic probing

 src/hashTables/FHhashQPwFind.java
 -like its superclass FHhashQP added keys and find capabilities


 src/hashTables/HashEntry.java
 -generic entry for hashes

 src/hashTables/MyTunes.java
 -contains main
 -stores,manages and find songs by hash

 src/hashTables/SongCompInt.java
 -wrapper class for a SongEntry object
 -compares objects based on the songs int id field


 src/hashTables/SongsCompGenre.java
 -wrapper class for a SongEntry object
 -compares objects based on the songs String genre field


 src/hashTables/TableGenerator.java
 -create and populate two hash tables of type FHhashQPwFind class, one for each wrapper class
/project folder:
kennwu17-project04/

 Brief description of submitted files:

resources/tunes.txt
   -Test input file to check if program can enqueue and dequeue properly.

resources/tunes_boundary.txt
   -Test input file to see what happens if we dequeue all songs in the playlist

resources/tunes_boundary.txt
   -Test input file to see what happens if don't dequeue any songs

resources/tunes_truncated.txt
   -Test input file to what happens if we dequeue an empty playlist


src/queues/Jukebox.java
    -A helper class that used for organizing the queue.
    -One object of Jukebox consist of favorite, lounge and roadtrip playlist.


src/queues/MyTunes.java
    -Creates an object of type MyTunes which simulates a playlist queue.
    -Enqueues and dequeues SongEntry objects from each playlist.

src/queues/Queue.java
    -queue class for singly linked list
    -contain inner class node


src/cs1c/MillionSongDataSubset.java
    -One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.


src/cs1c/SongEntry.java
    - One object of class SongEntry stores a simplified version of the genre data set from the Million Song Dataset.


src/cs1c/TimeConverter.java
    - Converts duration into a string representation.

lib/json-simple-1.1.1.jar
    - Java toolkit for JSON (JavaScript Object Notation) used to encode or decode JSON text.
    - https://code.google.com/p/json-simple/downloads/list

 resources/RUN.txt
      - console output of MyTunes.java with different budgets

  README.txt
      - description of submitted files
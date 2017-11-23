/project folder:
kennwu17-project05/

 Brief description of submitted files:

resources/inventory_invalid_removal.txt
    -Input test file to show what happens if user request to buy item 2x

resources/inventory_log.txt
    -Input test file to show the difference between the ouput when traversing the hard tree vs the soft tree

resources/inventory_long_minmax.txt
    -Input test file to show what findMin and findMax return correct items for small file


resources/inventory_short_minmax.txt
    -Input test file to show what findMin and findMax correct items for large file
    -Input test file to show that what happens to first and last items after repeated buys.

resources/inventory_short.txt
    -Input test file to show what happens if user requests to buy few items

src/lazyTrees/item.java
    -One object of Item class represents one item in the inventory, with two class members.

src/lazyTrees/LazySearchTree.java
    -Class that implements Lazy Deletion

src/lazyTrees/PrintObject.java
    -Class used to print the tree

src/lazyTrees/SuperMarket.java
    -Simulates the market scenario of buying and adding items to a store's inventory.
    -contains main

src/lazyTrees/Traverser.java
    -class used for visiting every nodes
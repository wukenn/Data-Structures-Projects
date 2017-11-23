/project folder:
kennwu17-project05/

 Brief description of submitted files:

resources/inventory_invalid_removal.txt
    -Input test file to show what happens if user request to buy item 2x

resources/inventory_log.txt
    -Input test file to show the difference between the ouput when traversing the hard tree vs the soft tree
    -Input test file to show garbage collection and hard removal


resources/inventory_short.txt
    -Input test file to show what happens if user requests to buy few items

resources/inventory_threshold_five.txt
    -Input test file to see what happens if threshold change to 2

resources/inventory_threshold_two.txt
    -Input test file to see what happens if threshold change to 5

resources/inventory_garbage_collector.txt
    -Input test file to show multiples of calling collectGarbage



src/lazyTrees/item.java
    -One object of Item class represents one item in the inventory, with two class members.

src/lazyTrees/LazySearchTree.java
    -Class that implements Lazy Deletion
    -Class also implements garbage collections

src/lazyTrees/PrintObject.java
    -Class used to print the tree

src/lazyTrees/SuperMarket.java
    -Simulates the market scenario of buying and adding items to a store's inventory.
    -contains main
    -Now test for garbage collection

src/lazyTrees/Traverser.java
    -class used for visiting every nodes
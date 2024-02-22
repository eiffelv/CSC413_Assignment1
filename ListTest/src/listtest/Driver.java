package listtest;

import java.util.*;
/** 
   A driver that demonstrates the revised class ArrayListWithIterator.
 */
public class Driver  {
    
    public static void main (String args[]) {
            
        System.out.println("Create a list: ");

        ListWithIteratorInterface<String> myList = new ListWithIterator<>();

        System.out.println("Testing add A, B, C, D");
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");

        System.out.println("\nBegin iteration:");
        Iterator<String> it = myList.getIterator();
        System.out.println("next() returns " + it.next() + " (should be A)");

        System.out.println("List state after all adds and removes");
        displayList(myList);
        
    } // end main

    public static void displayList(ListWithIteratorInterface<String> aList) {

        System.out.println("The list contains " + aList.getLength() +
                            " string(s) running through iterator, as follows:");		

        Iterator<String> it = aList.getIterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }  // end displayList

}  // end Driver
/*************************************************
File: MyLList.java
By: Eiffel Valentino with code from Karun Mehta
Date: 2/21/2024
Description: Takes input from the user and
evaluates the infix and postfix expressions
*************************************************/
package listtest;

public class MyLList<E> implements ListInterface<E> {
 
    private boolean integrityOK;
    private ListNode firstNode; // reference to first node of the list
    private ListNode lastNode; // reference to last node of the list
    private int numberOfEntries; // number of entries in list
    
    // constructor
    public MyLList() {
        clear ();
    } // end default constructor

    // clear list. Set firstNode to NULL
    public final void clear () {
        
        integrityOK = false;
        
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
        
        integrityOK = true;
    } // end clear    

    private void checkIntegrity() {
        
        if(!integrityOK)
            throw new SecurityException("Corrupt Linked List.. cannot continue..");
        
    }// end checkIntegorty
        
    public boolean isEmpty() {

        return (firstNode == null);

    } // end isEmpty
    
    public int getLength() {
        return numberOfEntries;
    }
    
    public boolean contains(Comparable<E> anEntry) {

        checkIntegrity();
        ListNode fn = firstNode;
        
        if(fn == null) return false;
        else if(fn.getData().equals(anEntry)) return true;
        else {
            do {
                fn = fn.next;
                if(anEntry.equals(fn.getData())) return true;
            } while (fn.next != null);
        }
        return false;
    }
    
    public void printAll() {
        
        Comparable<E>[] arr = this.toArray();
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    
    public Comparable<E>[] toArray() {
        
        if(firstNode == null) return null;

        @SuppressWarnings("unchecked")
        Comparable<E>[] arr = (Comparable<E>[]) new Comparable[numberOfEntries];

        int count = 0;
        ListNode currentNode = firstNode;
        
        do {
            arr[count] = currentNode.getData();
            count++;
            if(currentNode.next == null) {
                break;
            }
            currentNode = currentNode.next;
        } while (!(currentNode.equals(lastNode)));
        
        arr[count] = lastNode.getData();
        return arr;
        
    }// end toArray
    
    
    public ListNode getNodeAt(int givenPosition) {
        
        checkIntegrity();
        // use temporary node currentNode to traverse
        // list starting at firstNode
        ListNode currentNode = firstNode;
        // traverse the list to locate the desired node
        for (int counter = 1 ; counter < givenPosition ; counter++) {
            currentNode = currentNode.next;
        }
        
        // currentList now refers to node at givenPosition
        return currentNode;
    } // end getNodeAt

    public Comparable<E> getEntry(int i) {
        
        Comparable<E>[] arr = this.toArray();
        
        Comparable<E> element = null;
        if((i > 0) && (i < arr.length)) {
            element = (Comparable<E>) arr[i - 1];
        }

        return element;

    } // end getEntry    
 
    public Comparable<E> replace(int position, Comparable<E> anEntry) {

        checkIntegrity();
        if (!((position >= 1) && (position <= numberOfEntries))) {
            return null;
        } else {
        
            ListNode theNode = this.getNodeAt(position - 1);
            theNode.setData(anEntry);
        }
        
        return anEntry;

    } // end replace    

    
    public Comparable<E> remove(int position) {

        checkIntegrity();
        
        Comparable<E> result = null; // return value
        
        if ((position >= 1) && (position <= numberOfEntries)) {
            if (position == 1) { // case 1: first node, remove first entry
                result = firstNode.data; // save entry to be removed
                firstNode = firstNode.next;
            } else { // case 2: not first node
                ListNode nodeBefore = getNodeAt(position - 1);
                ListNode nodeToRemove = nodeBefore.next;
                ListNode nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter; // disconnect the node to be removed
                result = nodeToRemove.getData(); // save entry to be removed
            } // end if
            
            numberOfEntries --;

        } else {
         
            System.out.println(position + ": is out of range of the list with size: " + numberOfEntries);
            
        }// end if
        
        return result; // return removed entry, or

    } // end remove(int i)  

    
    @SuppressWarnings("unchecked")
    public ListInterface<E> getAllLessThan(Comparable<E> elementToCompare)  {
        
        ListInterface<E> resultList = new MyLList<>();
        ListNode currentNode = firstNode;
        boolean done = false;
        
        do { 
            Comparable<E> currentElement = currentNode.getData();        
            if (elementToCompare.compareTo((E) currentElement) > 0) 
                resultList.add(currentElement);
            if(currentNode.getNextNode() == null)
                done = true;
            currentNode = currentNode.getNextNode();
        } while (!done);
        
        return resultList;
    
    } // end getAllLessThan     
    
    
    /** add an existing entry of this list.
       The list size is increased by 1.
       @param anEntry  The object to be added as a new entry. */      
    public void add(Comparable<E> anEntry) {

        checkIntegrity();
        
        // get new node to hold newEntry
        ListNode newNode = new ListNode (anEntry);

        // adding to empty list
        if (isEmpty ()) {
            firstNode = newNode;
        }
        
        //add to non-empty list
        else {
            ListNode lastNode = getNodeAt (numberOfEntries);
            lastNode.next = newNode; // make last node reference new node
        } // end if
        lastNode = newNode;
        numberOfEntries ++;        
    }
    
    public boolean add(int insertPosition, Comparable<E> anEntry) {
        
        ListNode nodeBefore, nodeAfter;
        
        checkIntegrity();
        
        boolean isSuccessful = true;
        if ((insertPosition >= 1) && (insertPosition <= numberOfEntries + 1)){
            // get new node to hold newEntry
            ListNode newNode = new ListNode (anEntry);
            if (isEmpty () || (insertPosition == 1)) { // empty or 1st position
                newNode.next = firstNode;
                firstNode = newNode;
                lastNode = newNode;
            } else {// not empty and newPosition > 1
                nodeBefore = getNodeAt (insertPosition - 1);
                
                //if insert point was after last node
                if(nodeBefore.next == null) {
                    nodeBefore.next = newNode;
                    newNode.next = null;
                    lastNode = newNode;
                // if insert point was somewhere in the middle of the list//
                } else {
                    nodeAfter = nodeBefore.next;
                    newNode.next = nodeAfter;
                    nodeBefore.next = newNode;
                    lastNode = nodeAfter;
                }
            } // end if

            numberOfEntries ++;
        } else isSuccessful = false;
            
        return isSuccessful;
    }
    
     public void printLinkedList() {

        int nodeCount = 1;
        ListNode currentNode = firstNode;
        Comparable<E> data = (Comparable<E>)currentNode.getData();   

         System.out.println("Node[1]: " + data);
        
        while( (currentNode.getNextNode() != null) ) {

            currentNode = currentNode.getNextNode();
            data = (Comparable<E>)currentNode.getData();
            System.out.println("Node[" + (++nodeCount) + "]: " + data);

        } 
     }
    

    
/** 
 *
 * @author kmehta
 */    
    public class ListNode {
        
        public Comparable<E> data; // entry in queue
        public ListNode next; // link to next node

        public ListNode(Comparable<E> anEntry) {
            this(anEntry, null);
        }
        
        public ListNode(Comparable<E> anEntry, ListNode n){
            this.data = anEntry;
            this.next = n;        
        }
        
        public Comparable<E> getData() {
            return data;
        }
        
        public ListNode getNextNode() {
            return this.next;
        }
        
        public void setNextNode(ListNode anEntry) {
            this.next = anEntry;
        }
        
        public void setData(Comparable<E> anEntry) {
            this.data = anEntry;
        }
  
    } // end QueueNode    
    
}// end MyLList

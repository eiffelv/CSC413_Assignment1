/*************************************************
File: ListWithIterator.java
By: Eiffel Valentino
Date: 2/21/2024
Description: A class that extends MyLList and implements ListWithIteratorInterface
*************************************************/

package listtest;

import java.util.Iterator;

public class ListWithIterator<T> extends MyLList<T> implements ListWithIteratorInterface<T>{

    public ListWithIterator() {
        super();
    }

    @Override
    public Iterator<T> iterator() {
        return getIterator();
    }

    @Override
    public Iterator<T> getIterator() {
        return new IteratorForList();
    }

    private class IteratorForList implements Iterator<T> {
        private ListNode nextNode;

        private IteratorForList() {
            nextNode = getNodeAt(0);
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            T result = null;
            if (hasNext()) {
                result = (T) nextNode.getData();
                nextNode = nextNode.getNextNode();
            } else {
                throw new UnsupportedOperationException("No next element");
            }
            return result;
        }
    }
    
}

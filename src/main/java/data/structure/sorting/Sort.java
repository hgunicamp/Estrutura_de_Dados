package data.structure.sorting;

public class Sort {

    private static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    /**
     * Bubblesort.
     * 
     * @return void
     */
    public static void bubble(Comparable[] array, boolean reverse) {
        int size = array.length - 1;
        for (int i = size; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (!reverse && array[j].compareTo(array[j+1]) > 0) {
                    swap(array, j, j+1);
                }
                if (reverse && array[j].compareTo(array[j+1]) < 0) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    /**
     * Bubblesort - ascending order.
     * 
     * @return void
     */
    public static void bubble(Comparable[] array) {
        bubble(array, false);
    }

    /**
     * SelectionSort
     * 
     * @return void
     */
     public static void selection(Comparable[] array, boolean reverse) {
         int size = array.length;
         for (int i = 0; i < size; i++) {
             for (int j = i + 1; j < size; j++) {
                 if (!reverse && array[i].compareTo(array[j]) > 0) {
                     swap(array, i, j);
                 }
                 if (reverse && array[i].compareTo(array[j]) < 0) {
                     swap(array, i, j);
                 }
             }
         }
      }

    /**
     * SelectionSort - ascending order.
     * 
     * @return void
     */
    public static void selection(Comparable[] array) {
        selection(array, false);
    }

    // Functions to support InsertionSort algorithm
    private final static int NOT_FOUND = -1;

    private static void swiftRightAndInsert(Comparable[] array, int i , int j) {
        Comparable temp = array[i];
        for (int k = i; k > j; k--) {
            array[k] = array[k-1];
        }
        array[j] = temp;
    }

    private static int backwardComparison(Comparable[] array, boolean reverse, int startPos) {
        int finalPos = NOT_FOUND;
        for (int searchPos = startPos - 1; searchPos >=0; searchPos--) {
            if (!reverse && array[startPos].compareTo(array[searchPos]) < 0 ||
                 reverse && array[startPos].compareTo(array[searchPos]) > 0) {
                finalPos = searchPos;
            } else {
                break;
            }
        }
        return finalPos;
    }

    /**
     * InsertionSort
     * 
     * @return void
     */
     public static void insertion(Comparable[] array, boolean reverse) {
         int size = array.length;
         for (int i = 1; i < size; i++) {
             int newPos = backwardComparison(array, reverse, i);
             if (newPos != NOT_FOUND) {
                 swiftRightAndInsert(array, i, newPos);
             }
         }
     }

    /**
     * InsertionSort - ascending order.
     * 
     * @return void
     */
    public static void insertion(Comparable[] array) {
        insertion(array, false);
    }

    private Sort() {}
}
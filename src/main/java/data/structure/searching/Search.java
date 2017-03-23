package data.structure.searching;

public class Search {
    private final static int NOT_FOUND = -1;

    /**
     * private Constructor
     */
    private Search() {}

    /**
     * Sequential search algorithm.
     * 
     * @return int
     */
    public static int sequential(Comparable searched, Comparable[] array) {
        int i = 0, position = NOT_FOUND;
        for (Comparable element: array) {
            if (searched.compareTo(element) == 0) {
                position = i;
                break;
            }
            i++;
        }
        return position;
    }

    private static int binarySearch(int init, int end, Comparable searched, Comparable[] array) {
        int pivot = (init + end) / 2;
        int compResult = searched.compareTo(array[pivot]);
        if (init == end && compResult != 0) return NOT_FOUND;
        switch (compResult) {
            case  1: // Greater
                pivot = binarySearch(pivot+1, end, searched, array);
                break;
            case -1: // Lesser
                pivot = binarySearch(init, pivot, searched, array);
                break;
            default:
                break;
        }
        return pivot;
    }

    /**
     * Binary Search algorithm.
     * 
     * @return int
     */
    public static int binary(Comparable searched, Comparable[] array) {
        int init = 0, end = array.length - 1;
        return binarySearch(init, end, searched, array);
    }
}
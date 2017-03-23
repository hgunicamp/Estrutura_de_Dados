package data.structure.searching.test;

import data.structure.searching.Search;

public class SequentialSearchGlobalTest {
    public static void main(String[] args) {
        Integer[] numbers = new Integer[20];
        int size = numbers.length;
        for (int i = 0; i < size; i++) {
            numbers[i] = new Integer(i);
        }

        int position = Search.sequential(new Integer(8), numbers);
        System.out.println("numbers[" + position + "] = " + numbers[position]);

        position = Search.sequential(new Integer(15), numbers);
        System.out.println("numbers[" + position + "] = " + numbers[position]);
    }
}
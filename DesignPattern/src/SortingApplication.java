import java.util.Arrays;

public class SortingApplication {
    public static void main(String[] args) {
        int[] arrayToSort = {5, 2, 8, 7, 1, 3};

        SortingStrategy bubbleSort = new BubbleSort();
        Sorter bubbleSorter = new Sorter(bubbleSort);
        int[] bubbleSortedArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
        bubbleSorter.sortArray(bubbleSortedArray);
        System.out.println("Sorted array using Bubble Sort: " + Arrays.toString(bubbleSortedArray));

        SortingStrategy selectionSort = new SelectionSort();
        Sorter selectionSorter = new Sorter(selectionSort);
        int[] selectionSortedArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
        selectionSorter.sortArray(selectionSortedArray);
        System.out.println("Sorted array using Selection Sort: " + Arrays.toString(selectionSortedArray));
    }
}
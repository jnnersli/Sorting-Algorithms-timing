package cpsc200_jli3_4;

import java.util.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jenny
 */
public class SortingAndTiming2 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        /*....................................................................*/
        //testing for correctness:
        
        final int length = 40;
        final int bound = 100;
        Integer[] array;
        Integer[] array2 = new Integer[length];
        
        //testing heapsort
        System.out.println("Comparable-based heapsort:");
        array = getArray(length, bound);
        System.arraycopy(array, 0, array2, 0, length);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        heapSort(array);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        System.out.println("Comparator-based heapsort:");
        array = getArray(length, bound);
        System.arraycopy(array, 0, array2, 0, length);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        heapSort(array, Comparator.naturalOrder());
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        //testing quicksort
        System.out.println("Comparable-based quicksort:");
        array = getArray(length, bound);
        System.arraycopy(array, 0, array2, 0, length);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        quickSort(array);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        System.out.println("Comparator-based quicksort:");
        array = getArray(length, bound);
        System.arraycopy(array, 0, array2, 0, length);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        quickSort(array, Comparator.naturalOrder());
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        //testing robust sort
        System.out.println("Comparable-based robust sort:");
        array = getArray(length, bound);
        System.arraycopy(array, 0, array2, 0, length);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        robustHeap(array, 0, array.length-1);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        System.out.println("Comparator-based robust sort:");
        array = getArray(length, bound);
        System.arraycopy(array, 0, array2, 0, length);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        robustHeap(array, 0, array.length-1, Comparator.naturalOrder());
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2));
        
        /*....................................................................*/
        //timing
//        PrintWriter writer = new PrintWriter("SortingAndTiming2.txt");
//        StopWatch stopwatch = new StopWatch();
//        final int iterations = 10;
//        final int upperBound = 100000;
//        final int lowerBound = 1000;
//        final int increment = 5000;
//        double[] times = new double[iterations];
//        double average = 0;
//        
//        writer.println("Heapsort Times:");
//        for(int i = lowerBound; i < upperBound; i += increment) {
//            for(int j = 0; j < iterations; j++) {
//                stopwatch.reset().start();
//                heapSort(getArray(i, bound));
//                stopwatch.stop();
//                times[j] = stopwatch.elapsed();
//            }
//            for(double t : times)
//                average += t;
//            average /= iterations;
//            writer.println(String.format("%.4f", average));
//        } stopwatch.reset();
//        
//        writer.println("Quicksort Times:");
//        for(int i = lowerBound; i < upperBound; i += increment) {
//            for(int j = 0; j < iterations; j++) {
//                stopwatch.reset().start();
//                quickSort(getArray(i, bound));
//                stopwatch.stop();
//                times[j] = stopwatch.elapsed();
//            }
//            for(double t : times)
//                average += t;
//            average /= iterations;
//            writer.println(String.format("%.4f", average));
//        } stopwatch.reset();
//        
//        writer.println("Robustsort Times:");
//        for(int i = lowerBound; i < upperBound; i += increment) {
//            for(int j = 0; j < iterations; j++) {
//                stopwatch.reset().start();
//                robustSort(getArray(i, bound));
//                stopwatch.stop();
//                times[j] = stopwatch.elapsed();
//            }
//            for(double t : times)
//                average += t;
//            average /= iterations;
//            writer.println(String.format("%.4f", average));
//        } stopwatch.reset();
//        
//        writer.flush();
//        writer.close();
    }
    
    
    /*........................................................................*/
    //testing and general stuff
    
    //prints given array
    private static <E> void printArray(E[] array) {
        for(E e : array)
            System.out.print(e.toString() + " ");
        System.out.println();
    }
    
    //checks if array is sorted in increasing order
    private static <E extends Comparable<E>> boolean isSorted(E[] array) {
        for(int i = 0; i < array.length-1; i++) {
            if(array[i].compareTo(array[i+1]) > 0)
                return false;
        }
        return true;
    }

    //checks if one array is a permutation of the other
    private static <E extends Comparable<E>> boolean isPermutation(E [] a1, E[] a2) {
        if(a1.length != a2.length)
            return false;
        else {
            insertionSort(a1, 0, a1.length-1, Comparator.naturalOrder());
            insertionSort(a2, 0, a2.length-1, Comparator.naturalOrder());
            return Arrays.equals(a1, a2);
        }
    }
    
    //returns array of random integers
    private static Integer[] getArray(int size, int bound) {
	Random random = new Random();
	Integer[] array = new Integer[size];
	for(int i = 0; i < array.length; i++) {
	    array[i] = random.nextInt(bound);
	}
	return array;
    }
    
    //swaps 2 elements at given indexes
    private static <E> void swap(E[] data, int index1, int index2) {
        E temp;
        temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
    
    
    /*........................................................................*/
    //heap sort stuff
    
    //returns the index of the left child of a given element
    private static int leftChild(int i) { return 2 * i + 1; }
    
    //comparable-based percolate down
    private static <E extends Comparable> void percDown(E[] data, int i, int n) {
        int child;
        E temp;
        
        for(temp = data[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if(child != n-1 && data[child].compareTo(data[child+1]) < 0)
                child++;
            if(temp.compareTo(data[child]) < 0)
                data[i] = data[child];
            else
                break;
        }
        data[i] = temp;
    }
    
    //comparator-based percolate down
    private static <E> void percDown(E[] data, int i, int n, Comparator<E> c) {
        int child;
        E temp;
        
        for(temp = data[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if(child != n-1 && c.compare(data[child], data[child+1]) < 0)
                child++;
            if(c.compare(temp, data[child]) < 0)
                data[i] = data[child];
            else
                break;
        }
        data[i] = temp;
    }
    
    //comparable-based heapsort
    public static <E extends Comparable<E>> void heapSort(E[] data) {
        for(int i = data.length/2 - 1; i >= 0; i--) {
            percDown(data, i, data.length);
        }
        for(int i = data.length-1; i > 0; i--) {
            swap(data, 0, i);
            percDown(data, 0, i);
        }
    }
    
    //comparator-based heapsort
    public static <E> void heapSort(E[] data, Comparator<E> c) {
        for(int i = data.length/2 - 1; i >= 0; i--) {
            percDown(data, i, data.length, c);
        }
        for(int i = data.length-1; i > 0; i--) {
            swap(data, 0, i);
            percDown(data, 0, i, c);
        }
    }
    
    
    /*........................................................................*/
    //quick sort stuff
    
    //method chooses a pivot from (start..end inclusive)
    private static <E extends Comparable<E>> E pivot(E[] data, int start, int end) {
        E first, mid, last;
        int i = start;
        int j = (start+end)/2;
        int k = end;
        first = data[i];
        mid = data[j];
        last = data[k];
        
        if(mid.compareTo(first) < 0)
            swap(data, i, j);
        if(last.compareTo(first) < 0)
            swap(data, i, k);
        if(last.compareTo(mid) < 0)
            swap(data, j, k);
        
        //pivot is now in the middle
        swap(data, j, k-1);
        return data[k-1];
    }
    
    private static <E> E pivot(E[] data, int start, int end, Comparator<E> c) {
        E first, mid, last;
        int i = start;
        int j = (start+end)/2;
        int k = end;
        first = data[i];
        mid = data[j];
        last = data[k];
        
        if(c.compare(mid, first) < 0)
            swap(data, i, j);
        if(c.compare(last, first) < 0)
            swap(data, i, k);
        if(c.compare(last, mid) < 0)
            swap(data, j, k);
        
        //pivot is now in the middle
        swap(data, j, k-1);
        return data[k-1];
    }
    
    //comparable-based quicksort that sorts over (start..end inclusive)
    private static <E extends Comparable<E>> void quickSort(E[] data, int start, int end) {
        //base case
        //if array is less than cutoff, insertion sort
        final int cutoff = 10;
        if(start + cutoff <= end){
            E pivot = pivot(data, start, end);
            
            //partition
            int i, j;
            i = start;
            j = end-1;
        
            while (true) {
                while(data[++i].compareTo(pivot) < 0){}
                while(data[--j].compareTo(pivot) > 0){}
                if(i < j) {
                    swap(data, i, j);
                }
                else break;
            }
            
            //put the pivot back in place
            swap(data, i, end-1);
        
            quickSort(data, start, i-1);
            quickSort(data, i+1, end);
        }
        else insertionSort(data, start, end);
    }
    
    
    //comparator-based quicksort over (start..end inclusive)
    private static <E> void quickSort(E[] data, int start, int end, Comparator<E> c) {
        //base case
        //if array is less than cutoff, insertion sort
        final int cutoff = 10;
        if(start + cutoff <= end){
            E pivot = pivot(data, start, end, c);
            
            //partition
            int i, j;
            i = start;
            j = end-1;
        
            while (true) {
                while(c.compare(data[++i], pivot) < 0){}
                while(c.compare(data[--j], pivot) > 0){}
                if(i < j) {
                    swap(data, i, j);
                }
                else break;
            }
            
            //put the pivot back in place
            swap(data, i, end-1);
        
            quickSort(data, start, i-1, c);
            quickSort(data, i+1, end, c);
        }
        else insertionSort(data, start, end, c);
    }
    
    //simpler public method calls
    public static <E extends Comparable<E>> void quickSort(E[] data) { quickSort(data, 0, data.length-1); }
    public static <E> void quickSort(E[] data, Comparator<E> c) { quickSort(data, 0, data.length-1, c); }
    
    
    /*........................................................................*/
    //robust sort stuff
    
    public static <E extends Comparable<E>> void robustSort(E[] data) {
        int h = (int) (10 + (Math.log(data.length) / Math.log(2.0)));
       // System.out.println("h: "+h);
        robustQuick(data, 0, data.length-1, h);
    }
    
    public static <E> void robustSort(E[] data, Comparator<E> c) {
        int h = (int) (10 + 3*(Math.log(data.length) / Math.log(2.0)));
        robustQuick(data, 0, data.length-1, h, c);
    }
    
    private static <E extends Comparable<E>> void robustQuick(E[] data, int start, int end, int h) {
        //base case
        if(end - start < 2) return;
        
        //if array is less than 10, insertion sort
        final int cutoff = 10;
        if(end - start < cutoff) insertionSort(data, start, end);
        
        //call heapsort if h=0
        if(h == 0) {
            robustHeap(data, start, end);
        }
        
        //choose pivot and put it out of the way
        E pivot = pivot(data, start, end);
            
        //partition
        int i, j;
        i = start;
        j = end-1;
        
        while (true) {
            while(data[++i].compareTo(pivot) < 0){}
            while(data[--j].compareTo(pivot) > 0){}
            if(i < j) {
                swap(data, i, j);
            }
            else break;
        }
            
        //put the pivot back in place
        swap(data, i, end-1);
        
        robustQuick(data, start, i-1, h-1);
        robustQuick(data, i+1, end, h-1);
    }
    
    //wuicksort that takes h as param
    private static <E> void robustQuick(E[] data, int start, int end, int h, Comparator<E> c) {
        //base case
        if(end - start < 2) return;
        
        //if array is less than 10, insertion sort
        final int cutoff = 10;
        if(end - start < cutoff) insertionSort(data, start, end, c);
        
        //call heapsort if h=0
        if(h == 0) {
            robustHeap(data, start, end, c);
        }
        
        //choose pivot and move it out of the way
        E pivot = pivot(data, start, end, c);
            
        //partition
        int i, j;
        i = start;
        j = end-1;
        
        while (true) {
            while(c.compare(data[++i], pivot)< 0){}
            while(c.compare(data[--j], pivot)> 0){}
            if(i < j) {
                swap(data, i, j);
            }
            else break;
        }
            
        //put the pivot back in place
        swap(data, i, end-1);
        
        robustQuick(data, start, i-1, h-1, c);
        robustQuick(data, i+1, end, h-1, c);
    }
    
    //quicksort for robust sort
    private static <E extends Comparable<E>> void robustHeap(E[] data, int start, int end) {
        for(int i = (end+1)/2 - 1; i >= start; i--) {
            percDown(data, i, end+1);
        }
        for(int i = end; i > start; i--) {
            swap(data, start, i);
            percDown(data, start, i);
        }
    }
    
    //heapsort over specified region
    private static <E> void robustHeap(E[] data, int start, int end, Comparator<E> c) {
        for(int i = (end+1)/2 - 1; i >= start; i--) {
            percDown(data, i, end+1, c);
        }
        for(int i = end; i > start; i--) {
            swap(data, start, i);
            percDown(data, start, i, c);
        }
    }
    
    /*........................................................................*/
    //insertion sort stuff
    
    //public method calls only take an array (and a comparator)
    public static <E extends Comparable<E>> void insertionSort(E[] data) {SortingAndTiming2.insertionSort(data, 0, data.length-1);}
    public static <E> void insertionSort(E[] data, Comparator<E> c) {SortingAndTiming2.insertionSort(data, 0, data.length-1, c);}
        
    //insertion sort over a specified region
    private static <E extends Comparable<E>> void insertionSort(E [] data, int start, int end) {
        E temp;
        int j;
        for(int i = start ; i <= end; i++) {
            temp = data[i];
            for(j = i; j > 0 && temp.compareTo(data[j-1]) < 0; j--) {
                data[j] = data[j-1];
            }
            data[j] = temp;
        }
    }
    
    //sentinel insertion sort over a specified region
    private static <E> void insertionSort(E [] data, int start, int end, Comparator<E> c) {
	E temp;
        int j;
        for(int i = start ; i <= end; i++) {
            temp = data[i];
            for(j = i; j > 0 && c.compare(temp, data[j-1]) < 0; j--) {
                data[j] = data[j-1];
            }
            data[j] = temp;
        }
    }
}

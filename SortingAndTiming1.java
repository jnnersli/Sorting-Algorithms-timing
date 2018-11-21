/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc200_jli3_3;

/**
 *
 * @author jenny
 */
import java.io.*;
import java.util.*;
public class SortingAndTiming1 {
    public static void main(String[] args) throws FileNotFoundException {
        
        /*............... testing .................*/
        final int length = 10;
        final int rndBound = 50;
        Integer[] array = getArray(length, rndBound);
        Integer[] array2 = new Integer[length];
        copyArray(array, array2);
        
        System.out.println("Testing Comparable Insertion Sort");
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        insertionSort1(array);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        array = getArray(length, rndBound);
        copyArray(array, array2);
        System.out.println("Testing Comparator Insertion Sort");
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        insertionSort2(array, Comparator.naturalOrder());
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        array = getArray(length, rndBound);
        copyArray(array, array2);
        System.out.println("Testing Shell Shellsort");
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        shellSort1(array, 1);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        array = getArray(length, rndBound);
        copyArray(array, array2);
        System.out.println("Testing Hibbard Shellsort");
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        shellSort2(array, Comparator.naturalOrder(), 2);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        
        array = getArray(length, rndBound);
        copyArray(array, array2);
        System.out.println("Testing Gonnet Shellsort");
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        shellSort1(array, 3);
        printArray(array);
        System.out.println("is sorted: " + isSorted(array));
        System.out.println("is permutation: " + isPermutation(array, array2) + "\n");
        /*....................................................................*/
        
        
        /*............... stability ..................*/
        //sorting based on names
        Student s1 = new Student("jenny", 800);
        Student s2 = new Student("jenny", 90);
        Student s3 = new Student("jenny", 8);
        Student s4 = new Student("jenny", 1);
        Student[] studentArray = {s1, s2, s3, s4};
        printArray(studentArray);
        insertionSort1(studentArray);
        printArray(studentArray);
        //sorting based on number
        Person p1 = new Person("yangus", 8);
        Person p2 = new Person("hill", 8);
        Person p3 = new Person("freddy", 8);
        Person p4 = new Person("ann", 8);
        Person[] personArray = {p1, p2, p3, p4};
        printArray(personArray);
        insertionSort2(personArray, Comparator.naturalOrder());
        printArray(personArray);
        /*....................................................................*/
        
        
        /*............... timing ................*/
        PrintWriter writer = new PrintWriter("SortingTimes.txt");
        StopWatch stopwatch = new StopWatch();
        final int iterations = 1;
        final int upperBound = 100000;
        final int lowerBound = 10000;
        final int increment = 10000;
        double[] times = new double[iterations];
        double average = 0;
        
        writer.println("Comparable-based insertion sort:");
        for(int i = lowerBound; i < upperBound; i += increment) {
            for(int j = 0; j < iterations; j++) {
                stopwatch.reset().start();
                insertionSort1(getArray(i, 100));
                stopwatch.stop();
                times[j] = stopwatch.elapsed();
            }
            for(double t : times)
                average += t;
            average /= iterations;
            writer.println(String.format("%.4f %s", average, "seconds"));
        }
        
        writer.println();
        writer.println("Shellsort with Shell increments:");
        for(int i = lowerBound; i < upperBound; i+=increment) {
            for(int j = 0; j < iterations; j++) {
                stopwatch.reset().start();
                shellSort1(getArray(i, 100), 1);
                stopwatch.stop();
                times[j] = stopwatch.elapsed();
            }
            for(double t : times)
                average += t;
            average /= iterations;
            writer.println(String.format("%.4f %s", average, "seconds"));
        }
        
        writer.println();
        writer.println("Shellsort with Hibbard increments:");
        for(int i = lowerBound; i < upperBound; i+=increment) {
            for(int j = 0; j < iterations; j++) {
                stopwatch.reset().start();
                shellSort2(getArray(i, 100), Comparator.naturalOrder(), 2);
                stopwatch.stop();
                times[j] = stopwatch.elapsed();
            }
            for(double t : times)
                average += t;
            average /= iterations;
            writer.println(String.format("%.4f %s", average, "seconds"));
        }
        
        writer.println();
        writer.println("Shellsort with Gonnet increments:");
        for(int i = lowerBound; i < upperBound; i+=increment) {
            for(int j = 0; j < iterations; j++) {
                stopwatch.reset().start();
                shellSort1(getArray(i, 100), 3);
                stopwatch.stop();
                times[j] = stopwatch.elapsed();
            }
            for(double t : times)
                average += t;
            average /= iterations;
            writer.println(String.format("%.4f %s", average, "seconds"));
        }

        writer.flush();
    }
    
    /*........................................................................*/
    //testing stuff
    
    private static <E> void copyArray(E[] array, E[] copy) {
        if(array.length != copy.length)
            throw(new ArrayIndexOutOfBoundsException());
        else {
            for(int i = 0; i < array.length; i++)
                copy[i] = array[i];
        }
    }
    
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
            insertionSort2(a1, Comparator.naturalOrder());
            insertionSort2(a2, Comparator.naturalOrder());
            return a1.equals(a2);
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
    
    /*........................................................................*/
    //sorting stuff
    
    //method finds the index with minimum element in array for insertionsort1
    private static <E extends Comparable<E>> int minIndex1(E[] array) {
	E min = array[0];
        int minIndex = 0;

	if(array.length > 2) {
	    if(array[0].compareTo(array[1]) > 0) {
		min = array[1];
                minIndex = 1;
            }
	    for(int i = 0; i < array.length; i++) {
		if(array[i].compareTo(min) < 0) {
		    min = array[i];
                    minIndex = i;
                }
	    }
	}
	else if(array.length == 2) {
	    if(array[0].compareTo(array[1]) > 0)
		minIndex = 1;
	}
	return minIndex;
    }
        
    //insertion sort algorithm with sentinels that uses Comparable<E>
    public static <E extends Comparable<E>> void insertionSort1(E [] data) {
	E temp;
        
        //putting sentinel in place
        int sentinel = minIndex1(data);
        if (sentinel != 0) {
            temp = data[0];
            data[0] = data[sentinel];
            data[sentinel] = temp;
        }
        
        //sorting
        int j;
	for(int i = 1; i < data.length; i++) {
	    temp = data[i];
	    for (j = i; data[j-1].compareTo(temp)>0; j--) {
		data[j] = data[j-1];
	    }
	    data[j] = temp;
	}
    }
    
    //finds min index for insertionsort 2
    private static <E> int minIndex2(E[] array, Comparator<E> c) {
	E min = array[0];
        int minIndex = 0;

	if(array.length > 2) {
	    if(c.compare(array[0], array[1]) > 0) {
		min = array[1];
                minIndex = 1;
            }
	    for(int i = 0; i < array.length; i++) {
		if(c.compare(array[i], min) < 0) {
		    min = array[i];
                    minIndex = i;
                }
	    }
	}
	else if(array.length == 2) {
	    if(c.compare(array[0], array[1]) > 0)
		minIndex = 1;
	}
	return minIndex;
    }
    
    //insertion sort with sentinels that uses Comparator<E>
    public static <E> void insertionSort2(E [] data, Comparator<E> c) {
	E temp;
        
        //putting sentinel in place
        int sentinel = minIndex2(data, c);
        if (sentinel != 0) {
            temp = data[0];
            data[0] = data[sentinel];
            data[sentinel] = temp;
        }
        
        //sorting
        int j;
	for(int i = 1; i < data.length; i++) {
	    temp = data[i];
	    for (j = i; c.compare(data[j-1],temp)>0; j--) {
		data[j] = data[j-1];
	    }
	    data[j] = temp;
	}
    }

    //shell sort that uses Comparable
    //increment = 1 for shell, 2 for hibbard, 3 for gonnet
    public static <E extends Comparable<E>> void shellSort1(E[] data, int increment) {
	E temp;
        int n = data.length/2;
        int gap = 1;
        
        int j;
        while(gap > 0) {
            for(int i = gap; i < data.length; i++) {
                j = i;
                temp = data[i];
                while(j >= gap && data[j-gap].compareTo(temp) > 0) {
                    data[j] = data[j-gap];
                    j = j-gap;
                }
                data[j] = temp;
            }
            if(gap == 2)
                gap = 1;
            else {
                //increment lets you choose between 3 increment sequences
                switch(increment) {
                    case 1: gap = shell(n--);
                        break;
                    case 2: gap = hibbard(n--);
                        break;
                    case 3: gap = gonnet(gap);
                        break;
                }
            }
        }
    }
    
    //returns next increment in each sequence
    private static int shell(int n) { return (int) Math.pow(2,n);  }
    private static int hibbard(int n) { return (int) Math.pow(2,n) - 1; }
    private static int gonnet(int k) { return (int) (k/2.2); }

    //shell sort that uses Comparator
    //increment = 1 for shell, 2 for hibbard, 3 for gonnet
    public static <E> void shellSort2(E [] data, Comparator<E> c, int increment) {
	E temp;
	int n = 1;
        int gap = 1;
        
        int j;
        while(gap > 0) {
            for(int i = gap; i < data.length; i++) {
                j = i;
                temp = data[i];
                while(j >= gap && c.compare(data[j-gap], temp) > 0) {
                    data[j] = data[j-gap];
                    j = j-gap;
                }
                data[j] = temp;
            }
            if(gap == 2)
                gap = 1;
            else {
                //increment lets you choose between 3 increment sequences
                switch(increment) {
                    case 1: gap = shell(n--);
                        break;
                    case 2: gap = hibbard(n--);
                        break;
                    case 3: gap = gonnet(gap);
                        break;
                }
            }
        }
    }
}

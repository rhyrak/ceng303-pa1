/*
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    // buffers
    static int[] ints1mil, ints50mil;

    public static void main(String[] args) {
        init(args); // read args and fill buffers

        // init data structures for 1M test
        Benchmarkable array = new StaticArray(1_000_000);
        Benchmarkable dynamicArray = new DynamicArray();
        Benchmarkable linkedList = new SLL();

        // perform 1M test
        System.out.println("1M integer benchmark:");
        benchmark(array, "array", 1, ints1mil, 0, 1_000_000, 900_000, 9);
        benchmark(linkedList, "linked list", 2, ints1mil, 0, 900_000, 900_000, 9);
        benchmark(dynamicArray, "dynamic array", 3, ints1mil, 0, 1_000_000, 900_000, 9);

        // init data structures for 50M test
        array = new StaticArray(50_000_000);
        dynamicArray = new DynamicArray();
        linkedList = new SLL();

        // perform 50M test
        System.out.println();   // padding
        System.out.println("50M integer benchmark:");
        benchmark(array, "array", 1, ints50mil, 0, 50_000_000, 45_000_000, 9);
        benchmark(linkedList, "linked list", 2, ints50mil, 0, 45_000_000, 45_000_000, 9);
        benchmark(dynamicArray, "dynamic array", 3, ints50mil, 0, 50_000_000, 45_000_000, 9);
    }

    /* perform tests and measure their time */
    private static void benchmark(Benchmarkable benchmarkable, String type, int order, int[] source,
                                  int insertIndex1, int insertIndex2,
                                  int readIndex1, int readIndex2) {
        /* variables */
        long start, end;
        double elapsed;
        // build and measure build time
        start = System.nanoTime();
        benchmarkable.build(source);
        end = System.nanoTime();
        elapsed = (end - start) / 1_000_000.0;
        System.out.println(order + "a) The integer " + type + " structure is built in "
                + String.format("%.4f", elapsed) + " milliseconds");

        int random = (int) (end - start);   // random integer to insert
        // insert into the index "insertIndex1" and measure insertion time
        start = System.nanoTime();
        benchmarkable.insert(insertIndex1, random);
        end = System.nanoTime();
        elapsed = (end - start) / 1_000_000.0;

        /* logic to format strings according to their data structure and inserted index */
        if (insertIndex1 == 0) {
            System.out.println(order + "b) An integer is inserted into the first index of the " + type + " in "
                    + String.format("%.4f", elapsed) + " milliseconds");
        } else {
            System.out.println(order + "b) An integer is inserted into the " + insertIndex1 + "-th index of the "
                    + type + " in " + String.format("%.4f", elapsed) + " milliseconds");
        }


        random = (int) (end - start);   // random integer to insert
        // insert into the index "insertIndex2" and measure insertion time
        start = System.nanoTime();
        benchmarkable.insert(insertIndex2, random);
        end = System.nanoTime();
        elapsed = (end - start) / 1_000_000.0;

        /* logic to format strings according to their data structure and inserted index */
        if (insertIndex2 == source.length) {
            System.out.println(order + "c) An integer is inserted into the last index of the " + type + " in "
                    + String.format("%.4f", elapsed) + " milliseconds");
        } else {
            System.out.println(order + "c) An integer is inserted into the " + insertIndex2 + "-th index of the "
                    + type + " in " + String.format("%.4f", elapsed) + " milliseconds");
        }

        int read;
        // read item at index "readIndex1" and measure read time
        start = System.nanoTime();
        read = benchmarkable.read(readIndex1);
        end = System.nanoTime();
        elapsed = (end - start) / 1_000_000.0;
        System.out.println(order + "d) An integer, which is " + read + ", is read from the index " + readIndex1 + " of the "
                + type + " in " + String.format("%.4f", elapsed) + " milliseconds.");

        // read item at index "readIndex2" and measure read time
        start = System.nanoTime();
        read = benchmarkable.read(readIndex2);
        end = System.nanoTime();
        elapsed = (end - start) / 1_000_000.0;
        System.out.println(order + "e) An integer, which is " + read + ", is read from the index " + readIndex2 + " of the "
                + type + " in " + String.format("%.4f", elapsed) + " milliseconds.");
    }

    /* initializer method to populate buffers with data from files and check for errors */
    private static void init(String[] args) {
        // check arg count
        if (args.length < 2) {
            System.out.println("Invalid number of arguments: expected 2, got " + args.length);
            System.out.println("Usage: java -jar pa1.jar 1Mint.txt 50Mint.txt");
            System.exit(1);
        }
        File file1mil = new File(args[0]);
        File file50mil = new File(args[1]);
        Scanner scanner = null;

        // open 1Mint.txt file
        try {
            scanner = new Scanner(file1mil);
        } catch (FileNotFoundException e) {
            System.out.println("File: " + args[0] + " does not exist");
            System.exit(1);
        }
        // fill 1M integer buffer
        ints1mil = new int[1_000_000];
        int count = 0;
        while (scanner.hasNextInt()) {
            ints1mil[count++] = scanner.nextInt();
        }
        scanner.close();
        // check if 1Mint.txt had 1M integers
        if (count < 1_000_000) {
            System.out.println("File: " + file1mil + " does not have 1M integers.");
            System.out.println("Count: " + count);
            System.exit(1);
        }

        // open 50Mint.txt file
        try {
            scanner = new Scanner(file50mil);
        } catch (FileNotFoundException e) {
            System.out.println("File: " + args[1] + " does not exist");
            System.exit(1);
        }

        // fill 50M integer buffer
        ints50mil = new int[50_000_000];
        count = 0;
        while (scanner.hasNextInt()) {
            ints50mil[count++] = scanner.nextInt();
        }
        scanner.close();

        // check if 50Mint.txt had 50M integers
        if (count < 50_000_000) {
            System.out.println("File: " + file50mil + " does not have 50M integers.");
            System.out.println("Count: " + count);
            System.exit(1);
        }
    }
}

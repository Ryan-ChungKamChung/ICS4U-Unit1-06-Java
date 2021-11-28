
/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Ryan Chung
* @version 1.0
* @since   2021-10-26
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param intArray the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] intArray) {

        double sum = 0;

        for (int number : intArray) {
            sum += number;
        }

        return sum / intArray.length;
    }

    /**
    * The median() function.
    *
    * @param intArray the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] intArray) {

        final double median;
        final int arrayLength = intArray.length;

        Arrays.sort(intArray);

        if (intArray.length % 2 == 0) {
            median = ((double) intArray[arrayLength / 2]
                    + (double) intArray[arrayLength / 2 - 1]) / 2;
        } else {
            median = (double) intArray[arrayLength / 2];
        }

        return median;
    }

    /**
    * The mode() function.
    *
    * @param intArray the collection of integers
    * @return the mode of the integers
    */
    public static Set<Integer> mode(final Integer[] intArray) {

        final Set<Integer> modes = new HashSet<Integer>();
        int maxCount = 0;

        for (int number1 : intArray) {

            int count = 0;

            for (int number2 : intArray) {
                if (number2 == number1) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                modes.clear();
                modes.add(number1);
            }

        }

        return modes;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {

        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("../", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        final double mean;
        final double median;
        final List<Integer> mode;
        final Integer[] arrayOfNumbers;

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        mean = mean(arrayOfNumbers);
        median = median(arrayOfNumbers);
        mode = new ArrayList<Integer>(mode(arrayOfNumbers));

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode(s) is/are: "
                + Arrays.toString(mode.toArray()));

        System.out.println("\nDone.");
    }
}


// Import libraries
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** .
* This program checks for prime numbers.
* It uses bubble sorting, files, binary search, arrays, and lists.
*
* @author  Zak Goneau
* @version 1.0
* @since   2025-04-07
*/

// Creating class
public final class PrimeNumbers {

    /** .
     * MAX is the largest number to be generated.
     */
    private static final int MAX = 100;

    /** .
     * MIN is the smallest number to be generated.
     */
    private static final int MIN = 0;

    /**
     * This is a private constructor used to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private PrimeNumbers() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {

        // Create instance of random class
        Random rand = new Random();

        // Initialize output string
        String outputStr = "";

        // Initialize array list
        ArrayList<String> numList = new ArrayList<String>();

        // Introduce program to user
        System.out.println("This program finds prime numbers in a file");

        // Open writer to write to file
        FileWriter myWriter = new FileWriter("numbers.txt");

        // Loop through 5 lines of file
        for (int lines = 0; lines < 5; lines++) {

            // Loop through 10 numbers per line
            for (int numbers = 0; numbers < 10; numbers++) {

                // Generate random number 0-100
                int randNum = rand.nextInt(MAX - MIN + 1) + MIN;

                // Write random number to file
                myWriter.write(randNum + " ");
            }
            // Add new line to file
            myWriter.write("\n");
        }
        // Close writer
        myWriter.close();

        // Create scanner and get number file
        File file = new File("./numbers.txt");
        Scanner scanner = new Scanner(file);

        // Loop through lines of file
        while (scanner.hasNextLine()) {
            // Assign current line to variable
            String line = scanner.nextLine();

            // Split line into array of strings
            String[] numbers = line.split(" ");

            // Loop through array of strings
            for (String number : numbers) {

                // Add each number to array list
                numList.add(number);
            }

            // Create array of integers
            int[] intArray = new int[numList.size()];

            // Populate array with list contents
            for (int counter = 0; counter < numList.size(); counter++) {

                // Convert string to integer
                intArray[counter] = Integer.parseInt(numList.get(counter));
            }

            // Create pass variable to bubble sort array
            for (int pass = 0; pass < intArray.length - 1; pass++) {

                // Loop through array
                for (int counter = 0; counter < intArray.length
                     - 1 - pass; counter++) {

                    // Check if number is larger than other
                    if (intArray[counter] > intArray[counter + 1]) {

                        // Assign larger number to temp variable
                        int temp = intArray[counter];

                        // Swap numbers
                        intArray[counter] = intArray[counter + 1];
                        intArray[counter + 1] = temp;
                    }
                }
            }

            // Print sorted array
            for (int counter = 0; counter < intArray.length; counter++) {
                System.out.print(intArray[counter] + " ");
            }
            System.out.println();

            // Write sorted array to output string
            for (int counter = 0; counter < intArray.length; counter++) {
                outputStr += intArray[counter] + " ";
            }
            outputStr += "\n";

            // Create list to hold indexes of prime numbers
            ArrayList<Integer> resultList = new ArrayList<Integer>();

            // Call function to check for prime numbers
            resultList = checkPrime(intArray);

            // Write prime numbers to console
            System.out.print("Prime Numbers: ");
            for (int counter = 0; counter < resultList.size(); counter++) {
                // Print prime number
                System.out.print(intArray[resultList.get(counter)] + " ");
            }
            System.out.println();

            // Write prime numbers to output file
            outputStr += "Prime Numbers: ";
            for (int counter = 0; counter < resultList.size(); counter++) {
                // Add prime number to output string
                outputStr += intArray[resultList.get(counter)] + " ";
            }
            outputStr += "\n";

            // Write output string to file
            FileWriter outputWriter = new FileWriter("output.txt");
            outputWriter.write(outputStr);
            outputWriter.close();

            // Clear array list for next line
            numList.clear();
        }

        // Initialize string for user input
        String userNum = "";

        // Create scanner to read input and file
        Scanner inputScanner = new Scanner(System.in);
        Scanner scannerTwo = new Scanner(file);

        // Get user input for the number they're looking for
        System.out.println("Enter a number to search for: ");
        userNum = inputScanner.nextLine();

        // Declare variable to hold user input
        int userNumInt = 0;

        // Try converting user input to integer
        try {
            userNumInt = Integer.parseInt(userNum);

            // Initialize integer array
            int[] numArray = new int[10];

            // Initialize line counter
            int lineCounter = 1;

            // Execute binary search on file to look for user input
            while (scannerTwo.hasNextLine()) {

                // Assign current line to variable
                String line = scannerTwo.nextLine();

                // Split line into array of strings
                String[] numbers = line.split(" ");

                // Convert string array to integers
                for (int counter = 0; counter < numbers.length; counter++) {
                    // Convert string to integers
                    numArray[counter] = Integer.parseInt(numbers[counter]);
                }

                // Sort num array
                Arrays.sort(numArray);

                // Initialize variables for binary search
                int low = 0;
                int high = numbers.length - 1;
                int mid = 0;
                int indexValue = -1;

                // Binary search array
                do {
                    // Get mid value
                    mid = (low + high) / 2;

                    // Check if input is the same as mid number
                    if (userNumInt == numArray[mid]) {
                        indexValue = mid;
                        break;

                        // Check if user input is less than mid number
                    } else if (userNumInt < numArray[mid]) {
                        // Set high to mid - 1
                        high = mid - 1;

                        // Otherwise number is greater than mid number
                    } else if (userNumInt > numArray[mid]) {
                        // Set low to mid + 1
                        low = mid + 1;

                        // Low is greater than high
                    } else {
                        // Set index value to -1
                        indexValue = -1;
                        break;
                    }

                    // Loop while theres still numbers to search
                } while (low <= high);

                // Write results to output file
                if (indexValue == -1) {
                    // Write to output string number not found
                    outputStr += "\n Number not found at row: " + lineCounter;
                    outputStr += "\n";

                    // Write output string to file
                    FileWriter outputWriter = new FileWriter("output.txt");
                    outputWriter.write(outputStr);
                    outputWriter.close();
                } else {
                    // Write to output string of number found
                    outputStr += "\n number first found at index: " + mid
                            + " in row: " + lineCounter + "\n";
                }
                // Increment line counter
                lineCounter++;
            }

        // Catch invalid inputs
        } catch (NumberFormatException error) {
            System.out.println("This is not a number "
                + error.getMessage());
        }

        // Close scanner
        inputScanner.close();
        scanner.close();
        scannerTwo.close();

    }

    /**
    * This is the method for checking for prime numbers.
    *
    * @param array
    * @return primeIndexes
    */

    // Declare function to check for prime numbers
    public static ArrayList<Integer> checkPrime(final int[] array) {

        // Initialize array list to hold prime numbers
        ArrayList<Integer> primeIndexes = new ArrayList<Integer>();

        // Loop through array
        for (int counter = 0; counter < array.length; counter++) {

            // Initialize flag to check if number is prime
            boolean flag = false;

            // 0 and 1 are not prime or composite
            if (array[counter] == 0 || array[counter] == 1) {

                // Flag number as non-prime
                flag = true;

                // Break out of loop
                break;
            }

            // Loop to check if number is prime
            // https://www.programiz.com/java-programming/examples/prime-number
            for (int counterTwo = 2; counterTwo
                <= array[counter] / 2; counterTwo++) {

                // Condition for non-prime number
                if (array[counter] % counterTwo == 0) {
                    // Flag number as non-prime
                    flag = true;
                }
            }

            if (!flag) {
                // Add index of prime number to array list
                primeIndexes.add(counter);
            }

        }

        // Return list of prime numbers
        return primeIndexes;
    }

}

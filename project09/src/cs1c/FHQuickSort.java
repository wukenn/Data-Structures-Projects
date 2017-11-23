package cs1c;

        import java.io.File;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;

/**
 * The main program that does recursion
 * @author Kenneth Wu
 */
public class FHQuickSort
{
    /**
     * used for populating the array and looking for estimated time
     * @param testSize
     * size of the array
     * @return
     * the estimated time it takes to populate the array
     */
    public static long sortAlgos(int testSize)
    {
        int k, randomInt;
        long startTime, estimatedTime;

        Integer[] arrayOfInts = new Integer[testSize];

        //populate arrays
        for (k = 0; k < testSize; k++)
        {
            randomInt = (int) (Math.random() * testSize);
            arrayOfInts[k] = randomInt;
        }
        startTime = System.nanoTime();  // ------------------ start 

        FHsort.quickSort(arrayOfInts);
        estimatedTime = System.nanoTime() - startTime;    // ---------------------- stop
        System.out.println("Quick sort Elapsed Time: "
                + " \nsize: " + testSize + ", "
                + TimeConverter.convertTimeToString(estimatedTime)
                + " = " + estimatedTime + "ns");

        // Note: un-comment  to verify sort
        //displayArray(arrayOfInts1, "Heap");
        //displayArray(arrayOfInts2, "Quick");

        return estimatedTime;
    }

    /**
     * Display the value of the array corresponding with its size
     * @param theArray
     * the array that wants to be displayed
     * @param message
     * string message
     */
    public static void displayArray(Integer [] theArray, String message)
    {
        for (int k = 0; k < theArray.length; k+= theArray.length/10)
        {
            System.out.println( message + " #" + k + ": " + theArray[k] + "");
        }
    }


    // -------  main --------------
    public static void main(String[] args) throws Exception
    {

        final int [] ARRAY_SIZES = {20000, 70000, 170000, 370000, 770000,
                1170000, 1570000};


        final String filePath = "resources/";
        DateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String timeStamp = format.format(new Date());
        String outputFileName = "result_of_quick_sort_" + timeStamp + ".csv";
        File destination = new File(filePath + outputFileName);
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new File
                    (filePath + outputFileName));
        }
        catch (IOException e1){e1.printStackTrace();}
        writer.print("recursion limit");
        for(int names = 0; names < ARRAY_SIZES.length; names++)
            writer.print("," + ARRAY_SIZES[names]);

        int recSet = 2;

        while(recSet < 302)
        {
            writer.print("\n" + recSet + ",");

            for (int test = 0; test < ARRAY_SIZES.length; test++)
            {
                long consoleRun = 0;
                int currentSize = ARRAY_SIZES[test];
                for(int multRun = 0; multRun < 3; multRun++)
                {

                    FHsort.setRecursionLimit(recSet);
                    long runTime = sortAlgos(currentSize);
                    consoleRun += runTime;
                }

                System.out.println("recursion limit = " + recSet);
                System.out.printf("For data size %d runTime = %d%n \n\n"
                        , currentSize, consoleRun / 3);
                writer.print(consoleRun/3 + ",");

            }
            recSet += 2;
        }

        writer.close();
    }
}
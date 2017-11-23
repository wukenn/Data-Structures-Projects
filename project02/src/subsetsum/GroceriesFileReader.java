package subsetsum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




public class GroceriesFileReader
{
    /**
     *
     * @param path
     * The input file to parse.
     *
     * @return
     * return list of items' prices
     */
    public static ArrayList<Double> readFile(String path)
    {
        ArrayList<Double> doubleList = new ArrayList<Double>();

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String price = in.readLine();
            String str;

            while((str = in.readLine()) != null)
                price += "," + str;

            String[] priceList = price.split(",");

            for(int k = 1; k < priceList.length; k += 2)
                doubleList.add(Double.parseDouble(priceList[k]));

            in.close();
        }

        catch(IOException e){}

        return doubleList;
    }
}
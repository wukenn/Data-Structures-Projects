package subsetsum;

import java.util.ArrayList;

class Sublist implements Cloneable
{
    private double totalObjects = 0.0;
    private double sum = 0.0;
    protected ArrayList<Double> originalObjects;
    protected ArrayList<Integer> indices;

    // constructor creates an empty Sublist (no indices)
    /**
     * Sublist's Constructor
     * @param orig
     */
    public Sublist(ArrayList<Double> orig)
    {
        sum = 0;
        originalObjects = orig;
        calcSumOfTotalObjects();
        indices = new ArrayList<Integer>();
    }

    /**
     *
     * @return
     * sum of originalObject's elements
     */
    double getTotalObjects()
    {
        return totalObjects;
    }

    /**
     * helper for summing up originalObject's elements
     */
    private void calcSumOfTotalObjects()
    {
        for(int k = 0; k < originalObjects.size(); k++)
            totalObjects += originalObjects.get(k);
    }

    /**
     *
     * @return
     * return sum
     */
    double getSum()
    { return sum; }

    // I have done the clone() for you, since you will need clone()
    // inside addItem().

    public Object clone() throws CloneNotSupportedException
    {
        // shallow copy
        Sublist newObject = (Sublist)super.clone();
        // deep copy
        newObject.indices = (ArrayList<Integer>)indices.clone();

        return newObject;
    }

    /**
     *
     * @param waitingList
     * list of items that is waiting to be added to the sublist
     * @return
     * returns a list with items added
     */
    public Sublist addItem( int waitingList)
    {
        Sublist cloneObj = null;

        try
        {
            cloneObj = (Sublist)this.clone();
        }

        catch(CloneNotSupportedException e){}

        cloneObj.indices.add(waitingList);
        cloneObj.sum += cloneObj.originalObjects.get(waitingList);

        return cloneObj;
    }

    /**
     * displays sublist
     */
    void showSublist()
    {
        System.out.println("[");
        for(int k = 0; k < indices.size(); k++)
        {
            System.out.println(originalObjects.get(indices.get(k)));
            if(k != (indices.size() - 1))
                System.out.println(", ");
        }
        System.out.println("]");
    }
}
public class SubsetSum
{
    /**
     * Help finding which sublist closest to budget
     * @param doubleList
     * @param budget
     * @return
     */

    public static ArrayList<Double> findSubset(ArrayList<Double> doubleList, double budget)
    {
        Sublist upperList = new Sublist(doubleList);

        if(upperList.getTotalObjects() < budget)
            return upperList.originalObjects;

        buildingSubsetHelper(upperList, budget);
        return findSubsetHelper(upperList, buildingSubsetHelper(upperList, budget));
    }


    /**
     *
     * @param upperList
     * @param list
     * @return
     */

    private static ArrayList<Double> findSubsetHelper(Sublist upperList, ArrayList<Sublist> list)
    {
        int closestIndex = 0;

        for(int j = 1; j < list.size(); j++)
            if(list.get(closestIndex).getSum() <
                    list.get(j).getSum())
                closestIndex = j;

        ArrayList<Double> finalList = new ArrayList<Double>();

        for(int k = 0; k < list.get(closestIndex).indices.size(); k++)
            finalList.add(upperList.originalObjects.
                    get(list.get(closestIndex).indices.get(k)));
        return finalList;
    }

    /**
     *
     * @param upperList
     * @param budget
     * @return
     */
    private static ArrayList<Sublist> buildingSubsetHelper(Sublist upperList, double budget)
    {
        ArrayList<Sublist> list = new ArrayList<Sublist>();
        list.add(upperList);
        boolean test = false;

        while ( !test )
        {
            for(int k = 0; k < upperList.originalObjects.size(); k++)
            {
                for(int i = 0, value = list.size(); i < value; i++)
                {
                    double priceSum = list.get(i).getSum() + upperList.originalObjects.get(k);

                    if(priceSum <= budget)
                        list.add(list.get(i).addItem(k));
                    else if(priceSum > budget)
                        test = true;
                }
            }
            break;
        }
        return list;
    }
}

package stacks;

/**
 * navigator to BrowserNavigation.java
 * @author Kenneth Wu
 *
 */
class Navigator
{
    private String currentLink;
    private StackList<String> backLinks;
    private StackList<String> forwardLinks;

    /**
     * constructor for Navigator class object that is empty
     * and has 2 StackList named Back and Forward
     */
    Navigator()
    {
        currentLink = "";
        backLinks = new StackList <String> ("Back");
        forwardLinks = new StackList <String> ("Forward");
    }


    /**
     * @param currentLink
     * Link that will become the currentLink
     * @return
     * return boolean value depending on condition
     */
    public boolean setCurrentLink (String currentLink)
    {
        if (currentLink == "" || currentLink == null)
            return false;

        if(this.currentLink != null)
            backLinks.push(this.currentLink);
        this.currentLink = currentLink;

        while(forwardLinks.pop() != null);
        return true;
    }


    /**
     * Checking for any backLinks left on the top of the stack and warn user
     */
    public void goBack()
    {
        if(backLinks.top == null)
            System.out.println("\nWARNING! No back links left.");
        else
        {
            forwardLinks.push(currentLink);
            currentLink = backLinks.pop();
        }
    }


    /**
     * Checking for any forwardLinks left on top of the stack and warn user
     */
    public void goForward()
    {
        if(forwardLinks.top == null)
            System.out.println("\nWARNING! No forward links left.");
        else
        {
            backLinks.push(currentLink);
            currentLink = forwardLinks.pop();
        }
    }


    /**
     * accessor for CurrentLink
     * @return
     * return currentLink
     */
    public String getCurrentLink()
    {
        return currentLink;
    }


    /**
     * accessor for BackLink
     * @return
     * return BackLink
     */
    public StackList<String> getBackLinks()
    {
        return backLinks;
    }


    /**
     * accessor for ForwardLink
     * @return
     * return ForwardLink
     */
    public StackList<String> getForwardLinks()
    {
        return forwardLinks;
    }
}
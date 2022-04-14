import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class HashtableMapTests
{

  public static void main(String[] args)
  {
    testSize();
    testGet();
    testGet2();
    testContains();
    testRemove();
    testClear();
   
  }
  public static boolean testSize() // Tests that the size method returns the correct size
  {
    HashtableMap table = new HashtableMap();
    table.put(1, 1);
    table.put(2, 2);
    table.put("big", "beans");
    table.put("dog", 4);
    
    if(table.size() == 4)
    {
      System.out.println("testSize returned the correct value");
      return true;
    }
    System.out.println("testSize returned the incorrect value");
    return false;
  }
  
  public static boolean testGet() // Tests the get method returns the correct value
  {
    HashtableMap table = new HashtableMap(2);
    table.put(1, "dog");
    table.put(2, "cat"); 
    if(table.get(2).equals("cat"))
    {
      System.out.println("testGet returned the correct value");
      return true;
    }
    System.out.println("testGet returned the incorrect value");
    System.out.println(table.get(2));
   
    return false;
  }
  
  //Tests that the get method throws an exception when the key is not in the table
  public static boolean testGet2() 
  {
    HashtableMap table = new HashtableMap(2);
    table.put(1, 1);
    table.put(2, 2);
    
    try
    {
      table.get(3);
      return false;
    }
    catch(NoSuchElementException e)
    {
      System.out.println("testGet correctly threw an exception when the key was not in the table");
    }
    return true;
  }
  
  // Tests that the contains method returns the correct value 
  // for when a key is not in the table and when the key is in the table
  public static boolean testContains() 
  {
    HashtableMap table = new HashtableMap(3);
    table.put(1, "dog");
    table.put(2, "cat");
    table.put(3, "bird");
    
    if(table.containsKey(4) == false)
    {
      System.out.println("testContains returned the correct value"
          + " when the key was not in the table");
    }
    if(table.containsKey(1) == true)
    {
      System.out.println("testContains returned the correct value when the key was in the table");
      return true;
    }     
    
    System.out.println("testContains returned the incorrect value");
    return false;
  }
  
  
  // Tests that the remove method removes the correct k,v pair.
  // Also checks that the size is correctly updated.
  public static boolean testRemove()
  {

    HashtableMap table = new HashtableMap(3);
    table.put(1, "dog");
    table.put(2, "cat");
    table.put(3, "bird");
    
    if(table.remove(1).equals(1))
    {
      System.out.println("testRemove removed the correct k, v pair");
    }
    if(table.size() == 2)
    {
      System.out.println("testRemove correctly updated the size");
      return true;
    }
    System.out.println("testRemove removed the incorrect k, v pair. Or the size was not updated");
    return false;
  }
  
  public static boolean testClear()
  {
    HashtableMap table = new HashtableMap(20);
    table.put(1, "dog");
    table.put(2, "cat");
    table.put(3, "bird");
    table.put(4, "cow");
    table.clear();
    if(table.size() == 0)
    {
      System.out.println("testClear returned the correct size");
      return true;
    }
    System.out.println("testClear returned the incorrect size");
    System.out.println(table.size());
    return false;
  }
}

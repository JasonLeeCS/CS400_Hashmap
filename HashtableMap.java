// --== CS400 Project One File Header ==--
// Name: Jason Lee
// Email: jlee967@wisc.edu
// Team: blue
// Group: CI
// TA: Harper
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
@SuppressWarnings("rawtypes")
public class HashtableMap <KeyType, ValueType> implements MapADT
{
  static private class ListNode
  {
    Object key;
    Object value;
    ListNode pointer; // pointer to next node in list
  }
  
  private ListNode[] table; // hash table
  private int capacity; // number of k,v pairs in hash table
  
  public HashtableMap(int capacity) // with inputed capcity
  {
    table = new ListNode[capacity];
  }
  
  public HashtableMap() // with default capacity = 20
  {
    table = new ListNode[20];
  }

  @Override
  public boolean put(Object key, Object value) 
  {
    int bucket = hash(key); // Location of key
    ListNode list = table[bucket]; // Used to traverse table
    
    while(list != null) // Search through list and check if the key already there.
    {
      if(list.key.equals(key))
      {
        return false;
      }
      list = list.pointer;
    }
    
    if(list != null) // The key has been found
    {
      return false;
    }
    
    else // Else, list is null and the key is not in the list
    {
      
      ListNode newNode = new ListNode();
      newNode.key = key;
      newNode.value = value;
      newNode.pointer = table[bucket];
      table[bucket] = newNode;
      capacity++;
      if(capacity >= 0.8 * table.length) // Checks if the LF is over 80%. Resizes if it is
      {
        resize();
      }
      return true;
      
    } 
  }
  
  @Override
  // If the key is in the table, get the value associated with it. If key is not in table,
  // throw NoSuchElementException with the error message "Element not in table"
  public Object get(Object key) throws NoSuchElementException 
  {
    int bucket = hash(key); // Location of key
    ListNode list = table[bucket];
    
    while(list != null) // Checks if key is in node that table points to. If yes, returns the vlaue
    {
      if(list.key.equals(key))
      {
        return list.value;
      }
      list = list.pointer; // Move to next node
    }
    // After looking at every node in table.
    // The key has not been found, throw a NoSuchElementException
    throw new NoSuchElementException("Element not in table"); 
  }

  @Override
  public int size() // Returns the number of k,v pairs in the table
  {
    return capacity;
  }

  @Override
  public boolean containsKey(Object key)  // Checks whether the given key is in the table or not.
  {
    int bucket = hash(key);
    ListNode list = table[bucket];
    while(list != null) // If key is found, return true.
    {
      if(list.key.equals(key))
      {
        return true;
      }
      list = list.pointer;
    }
    
    return false; // No key was found in the table, return false.
  }

  @Override
  // If the given key is in the table, remove the key and it's value pair. 
  // If the key is not in the table, return null
  public Object remove(Object key) 
  {
    int bucket = hash(key); // Location of key
    
    if(table[bucket] == null) // If there is no key at the location, return null.
    {
      return null;
    }
    
    // Checks if the key is the first node. If it is, remove from the table. Update capacity
    if(table[bucket].key.equals(key)) 
    {
      table[bucket] = table[bucket].pointer;
      capacity--;
      return key;
    }
    
    ListNode previous = table[bucket]; // The previous node.
    ListNode current = previous.pointer; // The current node, used to traverse table.
    
    while(current != null && !current.key.equals(key))
    {
      current = current.pointer;
      previous = current;
    }
    
    if(current != null) // If current.key is equal to the key, remove current from the table.
    {
      previous.pointer = current.pointer;
      capacity--; 
    }
    
    return null; // If current is null, no key was found. Return null
  }

  @Override
  public void clear() // Removes all k,v pairs in table
  {
    for(int i = 0; i < table.length; i++)
    {
      if(table[i] != null)
      {
        table[i].equals(null);
        capacity--;
      }
    }
  }
  
  private int hash(Object key) // Computes hash code for a given key. 
  {
    return (Math.abs(key.hashCode())) % table.length;
  }
  
  // Double size of table when you reach 80% of the table and rehash.
  private void resize()
  {
    ListNode [] newTable = new ListNode[table.length * 2];
    for(int i = 0; i < table.length; i++) // Move
    {
      ListNode list = table[i];
      
      while (list != null)
      {
        ListNode next = list.pointer;
        
        int hash = (Math.abs(list.key.hashCode())) % newTable.length;
        
        list.pointer = newTable[hash];
        newTable[hash] = list;
        list = next;
      }
    }
    table = newTable;
  }
}

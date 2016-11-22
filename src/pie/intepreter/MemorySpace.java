package pie.intepreter;

import java.util.HashMap;
import java.util.Map;

/** A scope of variable:value pairs */
public class MemorySpace {

  String name; // mainly for debugging purposes
  Map<String, Object> members = new HashMap<String, Object>();
  boolean debug = false;

  public MemorySpace(String name) {
    this.name = name;
  }

  public Object get(String id) {
    return members.get(id);
  }

  public void put(String id, Object value) {
    members.put(id, value);
    if (debug)
      System.out.println("Memspace '" + this.name + "' added " + id + "-->" + value.toString());
  }

  public String toString() {
    return name + ":" + members;
  }
}

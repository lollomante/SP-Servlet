import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServlet;

public class ServletHashTable {

	// hash table
	static Hashtable<String, HttpServlet> ht;

	// constructor
	ServletHashTable() {
		ht = new Hashtable<String, HttpServlet>();
	}

	// method to add servlet
	static void put (String s, HttpServlet h){
		ht.put(s,  h);
	}

	// method to check if a servlet is loaded
	static boolean contains (String s){
		return ht.containsKey(s);
	}

	// method to remove a servlet
	static HttpServlet get(String s) {
		return ht.get(s);
	}

	// method to retrieve a servlet
	static void remove(String s) {
		ht.remove(s);
	}

	// method to get all servlet names
	static Vector<String> getServletNames() {
		return new Vector<>(ht.keySet());
	}
}

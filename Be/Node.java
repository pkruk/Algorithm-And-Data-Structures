import java.util.*;
import java.io.*;

public class Node {

	public void printKeys() {
		System.out.print(getKeys());
	}

	public String getKeys() {
		String wyjscie = "[ ";
		for (int i = 0; i < n; i++)
			wyjscie += key.get(i) + ",";
		return wyjscie += " ]";
	}

	public Node() {
		leaf = true;
		n = 0;
	}

	public boolean leaf;
	public int n;
	public ArrayList<Integer> key = new ArrayList<Integer>();
	public List<Node> c = new LinkedList<Node>();
}

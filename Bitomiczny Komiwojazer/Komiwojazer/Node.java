import java.util.*;
import java.io.*;

public class Node{

	public String toString(){
		return "" +ID + ": " + "(" + x + "," + y + ")";
	}

	public Node(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Node(int x, int y, char ID){
		this.x = x;
		this.y = y;
		this.ID = ID;
	}

	public Node(){
		x = y = -1;
		ID = ' ';
	}
	public boolean visited = false;
	public char color = 'W';
	public int waga = 0;
	public int x;
	public int y;
	public char ID;
}
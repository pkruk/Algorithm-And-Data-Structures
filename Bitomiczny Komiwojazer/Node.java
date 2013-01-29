import java.util.*;
import java.io.*;

public class Node{

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
		ID = "";
	}

	public int x;
	public int y;
	public char ID;
}
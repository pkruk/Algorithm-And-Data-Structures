import java.util.*;
import java.io.*;
public class Node{
   	public void print(){
        System.out.print(" Key : " + key + " C: " + color);
    }

    public void printAll(){
        System.out.print(" Key: " + key + " C " + color + " pi " + pi + " d " + d);
    }
    public Node(int key){
        this.key = key;
        index = key;
        color = 'W';
        d = Integer.MAX_VALUE;
        pi = null;
    }

    public Node(){
        key = 0;
        index = 0;
        color = 'W';
        d = Integer.MAX_VALUE;
        pi = null;
    }
	public ArrayList<Node> lista = new ArrayList<Node>();
    public int f = 0;
    public int d;
    public Node pi;
    public char color;
    public int index; // na wszelki wypadek
    public int key;
}

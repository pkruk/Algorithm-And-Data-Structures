import java.util.*;
import java.io.*;

public class Node { 
	public String print(){
		return "[" + (index+1) +"," + getColor() + " d: " + d + " f: " + f + getP() + "]";
	}
	public String getP(){
		if (pi == null)
			return "n";
		else
			return " P: " + (pi.index + 1);
	}
	public String getColor(){
		if (color == 'G') // G = Gray
			return "\u001B[36m" + 'G' + ANSI_RESET;
		else if (color == 'B')
			return ANSI_BLACK + 'B' + ANSI_RESET;
		else 
			return ANSI_WHITE + 'W' + ANSI_RESET;
	}

	public boolean isBlack(){
		return color == 'B';
	}

	public boolean isWhite(){
		return color == 'W';
	}

	public boolean isGrey(){
		return color == 'G';
	}
	public void znaczenie(){
		System.out.println( ".d == krok obliczen : kiedy zostal odwiedzony po raz pierwszy");
		System.out.println( ".f = krok obliczen : kiedy zostal odwiedzony po raz ostatni");
		System.out.println(" color = kolor\nindex = klucz\npi = poprzednik");
	}

	public Node (int i){
		d = 0; f = 0;
		color = 'W';
		index = i;
		pi = null;
	}

	public Node(){
		d = 0; f = 0;
		color = 'W';
		index = 0;
		pi = null;
	}

	public int d; //krok obliczen kiedy byl odwiedzany po raz pierwszy
	public int f; //krok obliczen kiedy byl odwiedzany po raz ostatni
	public LinkedList <Node> sasiedzi = new LinkedList <Node> ();
	public char color;
	public int index; //klucz
	public Node pi; //poprzednik

	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK = "\u001B[30m";
}

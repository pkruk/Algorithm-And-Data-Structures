import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.crypto.interfaces.PBEKey;
import javax.management.Query;

public class Main {

	public static void main(String[] args) {
		BTree a = new BTree(2);
		a.testDelete();
		BTree b = new BTree(2);
		b.test_Insert(14);
	}

}

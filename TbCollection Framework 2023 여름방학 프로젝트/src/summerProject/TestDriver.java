package summerProject;
import java.util.*;

public class TestDriver {

	public static void main(String[] args) {
		TbStack stack = new TbStack();
		
		for(int i=0; i<10; i++) {
			stack.push(i);
		}
		
		System.out.println(stack.search(9));
		
	}
	
	public String toString() {
		return "fun";
	}
	
}

class Fun{
	public String toString() {
		return "Fun";
	}
}

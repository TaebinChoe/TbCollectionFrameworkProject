package summerProject;
/*
 * Last In First Out의 Stack 자료구조
 * TbArrayList를 상속하여 기능을 이용한다.
 */
public class TbStack extends TbArrayList{
	/*
	 * 비어있는지 알려준다.
	 */
	public boolean empty() {
		return isEmpty();
	}
	
	/*
	 * 맨 위의 요소를 삭제하지 않고 반환한다.
	 */
	public Object peek() {
		return get(size()-1);
	}
	
	/*
	 * 맨 위의 요소를 삭제하고 반환한다.
	 */
	public Object pop() {
		return remove(size()-1);
	}
	
	/*
	 * 맨위의 객체를 저장한다.
	 * 넘겨받은 객체를 그대로 반환하다.
	 */
	public Object push(Object item) {
		add(item);
		return item;
	}
	
	/*
	 * 넘겨받은 객체를 찾아서 위치를 반환한다.
	 * 위치는 맨위가 1이다.
	 * 같은 값을 가지는 요소가 많을때는 그중 가장위에있는 인덱스를 반환한다.
	 * 못찾으면 -1을 반환한다.
	 */
	public int search(Object o) {
		int position = size() - lastIndexOf(o);
		
		if(position<=size()) {
			return position;
		}
		return -1;
	}
}

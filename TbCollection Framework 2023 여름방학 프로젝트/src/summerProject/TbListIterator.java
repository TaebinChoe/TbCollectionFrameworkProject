package summerProject;

/*
 * List형 자료구조에서 Collection의 각 요소에 접근하는 기능들을 제공해준다.
 */
public interface TbListIterator extends TbIterator{
	/*
	 * 읽어올 요소가 있는지 검사하는 메소드
	 */
	boolean hasPrevious();
	
	/*
	 * 앞의 요소를 읽어온다.
	 */
	Object previous();
	
	/*
	 * 방금 읽어온 요소를 삭제한다.
	 * next(), previous()가 호출되지 않았다면 IllegalStateException발생
	 */
	void remove();
	
	/*
	 * 방금 읽어온 요소를 넘겨받은 요소로 대체한다.
	 * next(), previous()가 호출되지 않았다면 IllegalStateException발생
	 */
	void set(Object o);
}

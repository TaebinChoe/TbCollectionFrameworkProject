package summerProject;

/*
 * 양방향으로 넣고 뺄수 있는 자료구조
 * dequeue는 queue의 기능을 포함하기때문에 일단 dequeue만을 구현함
 * 추후 priorityQueue추가를 위해 TbQueue인터페이스를 만들 계획임
 * 
 */

public interface TbDeque extends TbCollection{
	/*
	 * add함수들
	 */
	void addFirst(Object o);
	void addLast(Object o);
	
	/*
	 * peek함수들 : dequeue에서 요소를 삭제하지 않고 값만 반환 하는 함수
	 */
	Object peekFirst();
	Object peekLast();
	
	/*
	 * poll함수들: dequeue에서 요소를 삭제하고 삭제한 값을 반환하는 함수
	 */
	Object pollFirst();
	Object pollLast();
	
}

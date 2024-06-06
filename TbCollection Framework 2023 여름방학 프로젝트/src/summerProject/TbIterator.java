package summerProject;

/*
 * 일단 껍데기일 뿐이다.
 */
public interface TbIterator {
	/*
	 * 읽어올 다음 요소가 있는지 검사하는 메소드
	 */
	boolean hasNext();
	
	/*
	 * 다음요소를 읽어온다.
	 */
	Object next();
	
	/*
	 * 방금 읽어온 요소를 삭제한다.
	 * next()가 호출되지 않았다면 IllegalStateException발생
	 */
	void remove();
}

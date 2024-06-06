package summerProject;

/*
 * TbList와 TbSet의 공통 조상 interface이다.
 * 이 둘이 모두 갖추어야할 기본 기능에 해당하는 메소드들을 정의한다.
 */

public interface TbCollection {
	/*
	 * o에 저장된 객체 또는 c에 저장된 객체 전부를 Collection에 추가
	 */
	boolean add(Object o);
	boolean addAll(TbCollection c);
	
	/*
	 * Collection을 비움 (모든 데이터 삭제)
	 */
	void clear();
	
	/*
	 * o에 저장된 객체가 또는 c에 저장된 객체 전부가 Collection에 포함되어 있는지를 검사
	 */
	boolean contains(Object o);
	boolean contains(TbCollection c);
	
	/*
	 * 비어있는지 확인
	 */
	boolean isEmpty();
	
	/*
	 * 저장된 객체 수를 반환
	 */
	int size();
	
	/*
	 * Collection에 저장된 객체를 Object배열로 만들어 반환 한다.
	 */
	Object[] toArray();
	
	/*
	 * Collection의 TbIterator를 반환한다.
	 */
	TbIterator iterator();
}

package summerProject;

/*
 * 중복을 허용, 저장순서 유지하는 자료구조 
 */

public interface TbList extends TbCollection{
	/*
	 * 넘겨받은 index에 넘겨받은 객체를 저장 또는 Collection에 포함된 모든 객체를 index부터 저장함
	 * index뒤에 원래있던 요소들은 뒤로 밀림
	 */
	void add(int index, Object element);
	boolean addAll(int index, TbCollection c);
	
	/*
	 * 넘겨받은 index에 저장된 값을 반환한다.
	 */
	Object get(int index);
	
	/*
	 * 값이 o와 같은 요소중 가장 뒤에있는 값의 index를 반환한다.
	 */
	int lastIndexOf(Object o);
	
	/*
	 * listIterator를 반환한다.
	 * index를 넘겨주면 그 지점 부터 시작한다.
	 */
	TbListIterator listIterator();
	TbListIterator listIterator(int index);
	
	/*
	 * index에 저장된 객체를 삭제하고 반환한다.
	 */
	Object remove(int index);
	
	/*
	 * index 위치에 넘겨받은 객체를 저장한다.
	 */
	Object set(int index, Object element);
	
	//void sort(Comparator c);
	
	/*
	 * fromIndex부터 toIndex-1까지의 list를 만들어서 반환하다.
	 */
	TbList subList(int fromIndex, int toIndex);	
}

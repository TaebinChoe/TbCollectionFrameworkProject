package summerProject;

import java.util.NoSuchElementException;

/*
 * 일단 구현해 보고 linkedList와 구현이 겹치는 부분이 있으면 TbAbstractLsit로 빼자
 */
public class TbArrayList implements TbList{
	static private final int DEFAULT_CAPACITY = 10; //기본 크기
	private Object[] arr;
	private int capacity;
	private int topIdx = -1; //데이터가 저장된 마지막 index
	
	//constructor
	public TbArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	//크기를 넘겨 받는 경우
	public TbArrayList(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
	}
	
	//저장할 collection을 넘겨 받은 경우
	public TbArrayList(TbCollection c) {
		this(c.size() + DEFAULT_CAPACITY); //collection의 크기 + 기본크기로 리스트 생성
		addAll(c);
	}
	
	public boolean add(Object o){
		if(capacity-1 == topIdx) {//배열이 가득 찾다면
			capacityUp(2*capacity);
		}
		
		arr[++topIdx] = o;
		return true; //언제 false?
	}
	
	//index의 값을 반환 index가 범위를 밖이라면 예외 발생
	public Object get(int index){
		return arr[index];
	}
	
	//넘겨받은 컬렉션의 모든 값을 arr에 저장한다.
	public boolean addAll(TbCollection c){
		TbIterator i = c.iterator();
		while(i.hasNext()) {
			add(i.next());
		}
		return true; //언제 false?
	}
	
	
	/*
	 * iterator 인스턴스를 반환하는 함수들
	 * 해당 프로젝트에서는 리스트형에서 Iterator와 ListIterator를 모두 구현하지 않고 
	 * ListIterator만 구현하는 형태로 단순화 했다. 
	 * 하지만 Collection 인터페이스에서 정의한 메소드를 구현 해야하기 때문에 iterator() 메소드를 구현함
	 */
	public TbIterator iterator() {
		return new ListItr();
	}
	
	public TbListIterator listIterator(){
		return new ListItr();
	}
	
	//넘겨 받을 index를 현재 위치로하는 iterator를 반환
	public TbListIterator listIterator(int index){
		return new ListItr(index);
	}
	
	/*
	 * index에 있는 요소를 삭제함 
	 */
	public Object remove(int index){
		rangeCheck(index);
		
		Object rData = arr[index]; //백업해 두기
		
		//앞으로 당겨오며 덮어쓰기
		for(int i=index; i<topIdx; i++) {
			arr[i] = arr[i+1];
		}
		topIdx--; //topIdx갱신
		
		return rData;
	}
	
	/*
	 * index에 있는 요소를 넘겨받은 객체로 대체하고 이전에 있던 객체를 반환함
	 */
	public Object set(int index, Object element){
		rangeCheck(index);
		
		Object rData = arr[index]; //백업해 두기
		
		arr[index] = element; //덮어쓰기
				
		return rData;
	}
	
	/*
	 * list를 비우는 메소드
	 */
	public void clear() {
		arr = null;
		topIdx = -1;
		capacity = 0;
	}
	
	/*
	 * 넘겨받은 객체가 있는지 검사하는 함수
	 */
	public boolean contains(Object o) {
		TbListIterator it = new ListItr();
		while(it.hasNext()) {
			if(it.next().equals(o)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * c에 포함된 모든 객체가 리스트에 표함되어 있는지 검사하는 함수
	 */
	public boolean contains(TbCollection c){
		TbIterator it = c.iterator(); 	
		while(it.hasNext()) {
			if(!contains(it.next())) {
				return false;
			}
		}	
		return true;
	}
	
	/*
	 * 비어 있는지 검사
	 */
	public boolean isEmpty(){
		return topIdx<0;
	}
	
	/*
	 * 크기를 반환하는 함수
	 */
	public int size() {
		return topIdx+1;
	}
	
	/*
	 * Object배열로 만들어서 반환
	 */
	public Object[] toArray() {
		return TbArrays.copyOf(arr, topIdx+1);
	}
	
	/*
	 * 넘겨받은 자리에 요소를 삽입 한다. 뒷 부분은 한칸씩 뒤로 이동한다.
	 */
	public void add(int index, Object element) {
		if(index<0 || topIdx+1<index) {
			throw new IndexOutOfBoundsException();
		}
		
		if(capacity-1 == topIdx) {//배열이 가득 찾다면
			capacityUp(2*capacity);
		}
		
		move(index,1);//index부터 한칸씩 뒤로 민다. topIdx와 capacity는 알아서 조정해준다.
		
		arr[index] = element;
		
		//topIdx++;//topIdx 갱신
	}
	
	/*
	 * c의 모든 원소를 넘겨받은 위치부터 list에 삽입한다.
	 */
	public boolean addAll(int index, TbCollection c){
		move(index,c.size());//index부터 size만큼 이동
		
		int idx = index;
		TbIterator it = c.iterator();
		while(it.hasNext()) {
			arr[idx++] = it.next();
		}
		
		return true;//언제 false?
	}
	
	/*
	 * 값이 o와 같은 요소중 인덱스가 가장 큰 인덱스를 반환
	 */
	public int lastIndexOf(Object o){
		TbListIterator it = new ListItr(topIdx+1);
		
		int idx = topIdx;
		while(it.hasPrevious()) {
			if(it.previous().equals(o)) {
				return idx;
			}
			idx--;
		}
		return -1;
	}
	
	/*
	 * fromIndex부터 toIndex-1까지 subList를 만들어서 반환한다.
	 */
	public TbList subList(int fromIndex, int toIndex){
		int size = toIndex-fromIndex;;
		
		if(size<0) {
			throw new IllegalStateException();
		}
		
		rangeCheck(toIndex);
		
		TbList rList = new TbArrayList(size+10); //10만큼 여유있게 크기를 잡음
		
		TbIterator it = new ListItr(fromIndex);
		for(int idx=0; idx<size; idx++) {
			rList.add(it.next());
		}
		
		return rList;
	
	}
	
	/*
	 * 구현에 사용한 private메소드들
	 */
	
	//주어진 크기로 용량을 증가시킴
	private void capacityUp(int newCapacity) {
		arr = TbArrays.copyOf(arr, newCapacity);
		capacity = newCapacity;
	}
	
	//범위를 체크 하고 범위를 나갔다면 예외를 발생 시킴
	private void rangeCheck(int index) {
		if(index<0 || topIdx < index) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	//startIdx 부터 끝까지 한칸씩 뒤로 이동
	private void move(int startIdx, int step) {
		int endIdx = topIdx +step;//끝점
		
		if(capacity <= endIdx) {
			capacityUp(2*(endIdx-1)); //필요한 것의 두배크기로 확장
		}
		
		for(int i=endIdx; i>=startIdx; i--) {
			arr[i+step] = arr[i];
		}
		
		topIdx = endIdx;
	}
	
	private class ListItr implements TbListIterator{
		int cursor = 0; //현재 위치를 표시함
		int justReadIndex = -1; //방금 읽은 index remove, set 함수가 호출 되면 -1로 바뀜
		
		//constructor
		ListItr(){}
		
		ListItr(int index){
			cursor = index; //cursor를 설정함
		}
		
		/*
		 *읽어올 값이 있는지 확인 해주는 함수
		 */	
		public boolean hasNext() {
			return cursor <= topIdx;
		}
		
		public boolean hasPrevious() {
			return 0 < cursor;
		}
		
		/*
		 *list에 저장된 값을 반환 하는 함수
		 *앞으로 갈때와 뒤로 갈때 cursor의 사용이 다르다
		 *뒤로 갈때: cursor의 값을 반환
		 *앞으로 갈때: cursor 앞에것을 반환
		 *읽어올 값이 없을때 NoSuchElementException 을 throw함
		 */
		public Object next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			//cursor의 요소를 반환
			justReadIndex = cursor; 
			cursor++;
			return arr[justReadIndex];
		}
		
		public Object previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			//cursor 앞의 요소를 반환
			cursor--;
			justReadIndex = cursor;
			return arr[justReadIndex];
		}
		
		/*
		 * 가장 최근에 읽어 들인 요소를 삭제하는 함수
		 * 최근에 읽어온 값이 없다면(이전에 next()나 previous()가 호출되지 않았다면) IllegalStateException을 throw 
		 */
		public void remove() {
			if(justReadIndex<0) {
				throw new IllegalStateException();
			}
			TbArrayList.this.remove(justReadIndex);
			cursor = justReadIndex; //cursor갱신
			justReadIndex = -1;
		}
		
		/*
		 * 가장 최근에 읽어 들인 요소를 넘겨받은 객체로 교체하는 함수
		 * 최근에 읽어온 값이 없다면(이전에 next()나 previous()가 호출되지 않았다면) IllegalStateException을 throw 
		 */
		public void set(Object o) {
			if(justReadIndex<0) {
				throw new IllegalStateException();
			}
			TbArrayList.this.set(justReadIndex,o);
			justReadIndex = -1;
		}
		
	}
}

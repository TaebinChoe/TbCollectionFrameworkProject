package summerProject;
import java.util.NoSuchElementException;

//LinkedList

/*
 * linkedList의 경우 java의 api를 많이 참고하면서 공부하면서 진행함
 */

public class TbLinkedList implements TbList, TbDeque{
	private Node first = null; //맨 앞부분
	private Node last = null; //맨 끝부분
	private int size = 0; //현재 크기 (topIdx = size-1)

	/*
	 * constructor
	 */
	public TbLinkedList(){
		//empty
	}

	public TbLinkedList(TbCollection c) {
		addAll(c);
	}

	/*
	 * 기본 add함수, 맨뒤에 값이 data인 노드를 만들어서 위치시킨다.
	 */
	public boolean add(Object data){
		linkLast(data);
		return true;//언제 false?
	}

	/*
	 * c에 포함된 모든 요소를 리스트에 추가시킴
	 */
	public boolean addAll(TbCollection c){
		TbIterator it = c.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
		return true;//언제 false?
	}

	/*
	 * 값이 element인 노드를 만들어 index위체에 삽입한다.
	 */
	public void add(int index, Object element) {
		if(index<0 || size<index) {
			throw new IndexOutOfBoundsException();
		}else if(size == index) {
			add(element);
		}else {
			linkBefore(element,node(index));
		}
	}

	/*
	 * c의 모든 요소를 index부터 채워 넣는다.
	 */
	public boolean addAll(int index, TbCollection c){
		if(index<0 || size<index) {
			throw new IndexOutOfBoundsException();
		}else if(index==size){//맨 뒤부터 이어 붙이는 경우
			TbIterator it = c.iterator();

			while(it.hasNext()) {
				add(it.next());
			}
		}else {
			TbIterator it = c.iterator();

			if(it.hasNext()) { 
				Node cur = node(index); //null이 아님
				//맨 처음 하나는 앞에 삽입
				cur = linkBefore(it.next(),cur);
				while(it.hasNext()) {
					cur = linkAfter(it.next(),cur); //이후는 뒤에 삽입
				}
			}
		}

		return true;
	}

	/*
	 * 기본 get함수, index를 넘겨받아 해당 index의 노드의 값을 반환하다.
	 */
	public Object get(int index){
		rangeCheck(index);

		return node(index).item;
	}

	/*
	 * 기본 remove함수, index를 넘겨받아 해당 index의 노드를 삭제한다.
	 */
	public Object remove(int index){
		rangeCheck(index);

		if(index == 0) { //맨 앞 삭제이면
			return unlinkFirst();
		}else if(size-1 == index) {
			return unlinkLast();
		}else {
			return unlink(node(index));
		}
	}

	/*
	 * 리스트를 비움
	 */
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	/*
	 * 리스트가 비어있는지 알려줌
	 */
	public boolean isEmpty(){
		return size <= 0;
	}

	/*
	 * 크기를 반환
	 */
	public int size() {
		return size;
	}

	/*
	 * contains 함수들
	 */
	public boolean contains(Object o) {
		TbIterator it = iterator();

		while(it.hasNext()) {
			if(it.next().equals(o)) return true;
		}
		return false;
	}

	public boolean contains(TbCollection c){
		TbIterator it = c.iterator();

		while(it.hasNext()) {
			if(!contains(it.next())) return false;
		}
		return true;
	}

	/*
	 * list를 object배열로 만들어서 반환한다.
	 */
	public Object[] toArray() {
		Object[] arr = new Object[size];

		int index = 0;
		TbIterator it = iterator();

		while(it.hasNext()) {
			arr[index++] = it.next();
		}

		return arr;
	}

	/*
	 * 값이 o이 노드중 가장 인덱스가 큰 노드의 인덱스를 반환한다.
	 */
	public int lastIndexOf(Object o){
		if(get(size-1).equals(o)) return size-1; //맨 뒤는 따로 확인

		int index = size-2;
		TbListIterator it = listIterator(size-1);

		while(it.hasPrevious()) {
			if(it.previous().equals(o)) {
				return index;
			}
			index--;
		}

		return -1; //못찾으면 -1 반환
	}


	/*
	 * iterator 객체를 반환 하는 함수들
	 */
	public TbListIterator listIterator(){
		return new ListItr();
	}

	public TbListIterator listIterator(int index){
		return new ListItr(index);
	}

	public TbIterator iterator() {
		return listIterator();
	}

	/*
	 * index에 위치해 있던 노드의 갚을 element로 덮어쓰고 원래있던 값을 반환한다.
	 */
	public Object set(int index, Object element){
		rangeCheck(index);

		Node temp = node(index);

		Object rData = temp.item;//백업

		temp.item = element;//덮어쓰기

		return rData;
	}

	/*
	 * fromIndex부터 toIndex-1까지 subList를 만들어서 반환한다.
	 */
	public TbList subList(int fromIndex, int toIndex){
		int size = toIndex-fromIndex;

		if(size<0) {
			throw new IllegalStateException();
		}

		rangeCheck(toIndex);

		TbList rList = new TbLinkedList();

		TbIterator it = new ListItr(fromIndex);
		for(int idx=0; idx<size; idx++) {
			rList.add(it.next());
		}

		return rList;
	}

	/*
	 * TbDeque를 구현하기 위한 함수들
	 */

	/*
	 * 넘겨받은 값을 가지는 노드를 만들어 맨앞에 추가한다.
	 */
	public void addFirst(Object o) {
		linkFirst(o);
	}

	/*
	 * add(Object o)와 기능이 같다. 
	 */
	public void addLast(Object o) {
		add(o);
	}

	/*
	 * peek함수들이다. 각각 맨앞, 맨뒤의 노드를 삭제하지않고 들여다 보기만 하는 것이다.
	 */
	public Object peekFirst(){
		if(!isEmpty()) {
			return node(0).item;
		}
		return null;
	}

	public Object peekLast(){
		if(!isEmpty()) {
			return node(size-1).item;
		}
		return null;
	}

	/*
	 * poll함수들이다. 각각 맨앞, 맨뒤의 노드를 삭제하고 그 값을 반환하다.
	 */
	public Object pollFirst(){
		if(!isEmpty()) {
			return remove(0);
		}
		return null;
	}

	public Object pollLast(){
		if(!isEmpty()) {
			return remove(size-1);
		}
		return null;
	}

	/*
	 * 구현에 사용된 private 함수들
	 */

	//범위를 체크 하고 범위를 나갔다면 예외를 발생 시킴
	private void rangeCheck(int index) {
		if(index<0 || size <= index) {
			throw new IndexOutOfBoundsException();
		}
	}

	/*
	 * 해당 인덱스의 Node를 반환한다. 
	 * 앞에서 가는 것이 빠르면 앞에서 부터 뒤에서 가는 것이 빠르면 뒤에서 부터 접근한다.
	 * 인덱스 검사를 하지 않음
	 * 음수가 들어 오면 first반환
	 * size-1이상의 index가 들어오면 last반환
	 */
	private Node node(int index) {
		Node rNode; //반환할 노드

		if(index < size/2) {//앞에서 접근함
			rNode = first;
			for(int i=0; i<index; i++) {
				rNode = rNode.next;
			}
		}else {//뒤에서 접근함
			rNode = last;
			for(int i=0; i<size-1-index; i++) {
				rNode = rNode.prev;
			}
		}

		return rNode;
	}

	/*
	 * data를 값으로 갖는 노드를 만들어 first에 위치 시킴
	 */
	private Node linkFirst(Object data) {
		Node nNode = new Node(null,data,first);

		if(first==null) {//리스트가 비어있을때
			last = nNode; //first 가 null이면 last도 null이다.
		}else {//리스트가 비지 않았을때
			first.prev = nNode;
		}

		first = nNode;
		size++;

		return nNode;
	}

	/*
	 * data를 값으로 갖는 노드를 만들어 tail에 위치 시킴
	 */
	private Node linkLast(Object data) {
		Node nNode = new Node(last,data,null);

		if(last==null) {//리스트가 비어있을때
			first = nNode;
		}else {//리스트가 비지 않았을때
			last.next = nNode;
		}
		last = nNode;
		size++;

		return nNode;
	}

	/*
	 * 값이 data인 노드를 만들어서 succ앞에 삽입한다.
	 * succ는 null이 아니어야한다.
	 */
	private Node linkBefore(Object data, Node succ) {
		Node nNode = new Node(succ.prev,data,succ);

		if(succ.prev == null) {//맨 앞 삽입인 경우
			first = nNode;
		}else {
			succ.prev.next = nNode;
		}
		succ.prev = nNode;

		size++;

		return nNode;
	}

	/*
	 * 값이 data인 노드를 만들어서 pred뒤에 삽입한다.
	 * pred는 null이 아니어야한다.
	 */
	private Node linkAfter(Object data, Node pred) {
		Node nNode = new Node(pred,data,pred.next);
		if(pred.next == null) {
			last = nNode;
		}else {
			pred.next.prev = nNode;
		}
		pred.next = nNode;

		size++;

		return nNode;

	}
	/*
	 * first를 삭제하고 저장된 값을 반환
	 * first != null이어야 함
	 */
	private Object unlinkFirst() {
		Node rNode = first;

		if(first == last) { //요소가 하나인 경우
			last = null;
		}else {
			first.next.prev = null;
		}
		first = first.next;

		size--;
		return rNode.item;
	}

	/*
	 * last를 삭제하고 저장된 값을 반환
	 * last != null이어야 함
	 */
	private Object unlinkLast() {
		Node rNode = last;

		if(last == first) { //요소가 하나인 경우
			first = null;
		}else {
			last.prev.next = null;
		}
		last = last.prev;

		size--;
		return rNode.item;
	}

	/*
	 * null이 아닌 rNode를 리스트에서 삭제하고 그값을 반환한다.
	 */
	Object unlink(Node rNode) {

		//앞 연결
		if(rNode.prev == null) {//rNode == first
			first = first.next;
		}else {
			rNode.prev.next = rNode.next;
		}

		//뒤 연결
		if(rNode.next == null) {//rNode == last
			last = last.prev;
		}else {
			rNode.next.prev = rNode.prev;
		}

		size--;
		return rNode.item;
	}

	/*
	 * 기본 저장 단위인 노드 클래스
	 */
	private static class Node{
		Object item;//내용물
		Node next;//다음 노드
		Node prev;//앞 노드

		Node(Node prev, Object element, Node next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	private class ListItr implements TbListIterator{
		Node cursor = first; // 현재 노드, default값: first
		Node justReadNode = null; //방금 읽은 node remove, set 함수가 호출 되면 null로 바뀜

		/*
		 * constructor
		 */
		public ListItr() {
			this(0);
		}

		public ListItr(int index){
			TbLinkedList.this.rangeCheck(index);
			cursor = node(index);
		}

		/*
		 * 다음 읽어올 값이 있는지 확인하는 함수
		 */
		public boolean hasNext() {
			return (cursor != null);
		}

		public boolean hasPrevious() {
			return (cursor.prev != null);
		}


		/*
		 *list에 저장된 값을 반환 하는 함수
		 *앞으로 갈때와 뒤로 갈때 cursor의 사용이 다르다
		 *뒤로 갈때: cursor의 값을 반환
		 *앞으로 갈때: cursor 앞에것을 반환
		 *읽어올 값이 없을때 NoSuchElementException 을 throw함
		 */
		public Object next() {
			if(cursor == null) {
				throw new NoSuchElementException();
			}

			justReadNode = cursor;
			cursor = cursor.next;

			return justReadNode.item;
		}

		public Object previous() {
			cursor = cursor.prev;

			if(cursor == null) {
				throw new NoSuchElementException();
			}

			justReadNode = cursor;

			return justReadNode.item;
		}

		/*
		 * 가장 최근에 읽어 들인 요소를 삭제하는 함수
		 * 최근에 읽어온 값이 없다면(이전에 next()나 previous()가 호출되지 않았다면) IllegalStateException을 throw 
		 */
		public void remove() {
			if(justReadNode == null) {
				throw new IllegalStateException();
			}
			TbLinkedList.this.unlink(justReadNode);
			justReadNode = null;
		}

		/*
		 * 가장 최근에 읽어 들인 요소를 넘겨받은 객체로 교체하는 함수
		 * 최근에 읽어온 값이 없다면(이전에 next()나 previous()가 호출되지 않았다면) IllegalStateException을 throw 
		 */
		public void set(Object o) {
			if(justReadNode == null) {
				throw new IllegalStateException();
			}
			justReadNode.item = o;
			justReadNode = null;
		}
	}
}

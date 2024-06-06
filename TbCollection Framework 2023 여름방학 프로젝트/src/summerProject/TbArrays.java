package summerProject;

/*
 * 기능: 배열을 다루는데 필요한 기능들을 제공하는 클래스
 * 특징: 모든 메소드가 static 메소드이다.
 * 
 */
public class TbArrays {
	/*
	 * 배열을 문자열로 만들어서 반환 
	 * Object클래스의 +toString() : String을 overriding한게 아님
	 * int형과 Object형만 구현함
	 */
	public static String toString(int[] arr) {
		if(arr == null) return "null"; //arr이 null이면 "null"을 return
		
		StringBuffer rStr = new StringBuffer("[");
		
		for(int i=0; i<arr.length; i++) {
			rStr.append(Integer.toString(arr[i]));
		
			if(i<arr.length-1) {
				rStr.append(", ");
			}
		}
		rStr.append("]");
		
		return rStr.toString();
	}
	
	public static String toString(Object[] arr) {
		if(arr == null) return "null"; //arr이 null이면 "null"을 return
		
		StringBuffer rStr = new StringBuffer("[");
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == null) {
				rStr.append("null");
			}else {
				rStr.append(arr[i].toString());
			}
			
			if(i<arr.length-1) {
				rStr.append(", ");
			}
		}
		rStr.append("]");
		
		return rStr.toString();
	}
	
	/*
	 * 두 배열이 같은지 확인해 주는 메소드
	 * 같으면 -> ture, 다르면 ->false 반환
	 * int형과 Object형만 구현함
	 */
	public static boolean equals(int[] arr1, int[] arr2) {
		if(arr1 == arr2) return true; //같은 배열이거나 둘다 null 이라면 true
		
		if(arr1 == null || arr2 == null) return false; //하나라도 null이 들어오면 false
		
		if(arr1.length != arr2.length) return false; //길이가 다르면 바로 false
		
		for(int i=0; i<arr1.length; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
	
	public static boolean equals(Object[] arr1, Object[] arr2) {
		if(arr1 == arr2) return true; //같은 배열이거나 둘다 null 이라면 true
		
		if(arr1 == null || arr2 == null) return false; //하나만 null이면 false
		
		if(arr1.length != arr2.length) return false; //길이가 다르면 false
		
		for(int i=0; i<arr1.length; i++) {
			if(arr1[i]==null && arr2[i] != null) {
				return false;
			}else if(!arr1[i].equals(arr2[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	/* 원본배열과 새로운 배열의 길이를 넘겨받아 원본의 값을 복사한 새로운 배열을 만들어서 반환한다.
	 * 원본의 길이보다 새로운 배열의 길이가 더 크다면 남은 뒷부분은 디폴트값으로 채워진다.
	 * int형과 Object형만 구현함
	 * Object형의 경우 Shallow Copy만 이루어짐에 주의
	 * throws: 원본배열이 null -> NullPointerException, 요구된 배열크기가 음수 -> NegativeArraySizeException
	 */
	public static int[] copyOf(int[] orgArr, int requiredSize) {
		if(orgArr == null) {//orgArr이 null이면 throw
			throw new NullPointerException();
		}
		
		if(requiredSize < 0) {//requiredSize가 음수이면 throw
			throw new NegativeArraySizeException();
		}
		
		int[] rArr = null; //반환할 배열
		rArr = new int[requiredSize];
	
		for(int idx=0; idx<requiredSize && idx<orgArr.length; idx++) {
			rArr[idx] = orgArr[idx];
		}
		return rArr;
	}
	
	
	public static Object[] copyOf(Object[] orgArr, int requiredSize) {
		if(orgArr == null) {//orgArr이 null이면 throw
			throw new NullPointerException();
		}
		
		if(requiredSize < 0) {//requiredSize가 음수이면 throw
			throw new NegativeArraySizeException();
		}
		
		Object[] rArr = null; //반환할 배열
		rArr = new Object[requiredSize];
		
		for(int idx =0; idx<requiredSize && idx<orgArr.length; idx++) {
			rArr[idx] = orgArr[idx];
		}
		return rArr;
	}
	
	/* 원본배열과 새로운 배열로 만들기 원하는 범위를 넘겨받아 원본의 값을 복사한 새로운 배열을 만들어서 반환한다.
	 * endBound < startIdx이면 예외 발생, 요구된 인덱스가 원본의 마지막 인덱스보다 더 크다면 남은 뒷부분은 디폴트값으로 채워진다.
	 * endBound 인덱스에 저장된 값은 미포함함에 주의
	 * int형과 Object형만 구현함
	 * Object형의 경우 Shallow Copy만 이루어짐에 주의
	 * throws: 원본배열이 null -> NullPointerException, endBound < startIdx -> IllegalArgumentException
	 */
	public static int[] copyOfRange(int[] orgArr, int startIdx, int endBound) {
		if(orgArr == null) {//orgArr이 null이면 throw
			throw new NullPointerException();
		}
		
		int length = endBound - startIdx;
		
		if(length < 0) {//endBound<startIdx면 throw
			throw new IllegalArgumentException();
		}
		
		int[] rArr = null; //반환할 배열
		rArr = new int[length];
		
		for(int copyIdx=0, orgIdx = startIdx; copyIdx<length && orgIdx<orgArr.length; copyIdx++) {
			rArr[copyIdx] = orgArr[orgIdx++];
		}
		
		return rArr;
	}
	
	public static Object[] copyOfRange(Object[] orgArr, int startIdx, int endBound) {
		if(orgArr == null) {//orgArr이 null이면 throw
			throw new NullPointerException();
		}
		
		int length = endBound - startIdx;
		
		if(length < 0) {//endBound<startIdx면 throw
			throw new IllegalArgumentException();
		}
		
		Object[] rArr = null; //반환할 배열
		rArr = new Object[length];
		
		for(int copyIdx=0, orgIdx = startIdx; copyIdx<length && orgIdx<orgArr.length; copyIdx++) {
			rArr[copyIdx] = orgArr[orgIdx++];
		}
		
		return rArr;
	}
	
	/*
	 * 넘겨받은 배열을 정렬한다.
	 * int형과 object형만 구현함
	 * 퀵소트 알고리즘을 이용함
	 * object형의 경우 comparable을 구현하지 않았을 경우 예외를 발생 시킨다.
	 * throws : 배열이 null -> NullPointerException, object가 comparable을 구현X -> NotComparableException
	 */
	public static void sort(int[] arr) {
		if(arr == null) {//arr이 null이면 예외 발생
			throw new NullPointerException();
		}
		
		QuickSort.quickSort(arr,0,arr.length-1);
	}
	
	public static void sort(Object[] arr) {
		if(arr == null) {//arr이 null이면 예외 발생
			throw new NullPointerException();
		}
		
		if(!(arr instanceof Comparable[])) {//Comparable을 구현하지 않았다면 예외 발생
			throw new NotComparableException(arr);
		}
		
		QuickSort.quickSort((Comparable[])arr,0,arr.length-1);
	}
	
	/*
	 * 이진 탐색을 이용해서 넘겨받은 배열에서 넘겨받은 값을 가지는 요소의 인덱스를 반환 한다.
	 * 배열이 오름차순으로 정렬된 상태일때 올바른 결과를 얻을 수 있다.
	 * 넘겨받은 값을 가지는 요소가 여러개 있다면 그 중 어떤 인덱스를 반환 할지 알 수 없다.
	 * 값이 일치하는 요소가 없다면 -1을 반환한다.
	 * int형과 object형만 구현함
	 * object형의 경우 comparable을 구현하지 않았을 경우 예외를 발생 시킨다.
	 * throws : 배열이 null -> NullPointerException, object가 comparable을 구현X -> NotComparableException
	 */
	public static int binarySearch(int[] arr, int keyValue) {
		if(arr == null) {//배열이 null이면 throw
			throw new NullPointerException();
		}
		
		int lowIdx = 0;
		int highIdx = arr.length-1;
		
		while(lowIdx<=highIdx) {
			int midIdx = (lowIdx+highIdx)/2;
			
			if(arr[midIdx]<keyValue) {
				lowIdx = midIdx+1; //midIdx이하는 버림
			}else if(keyValue<arr[midIdx]) {
				highIdx = midIdx-1; //highIdx이상은 버림
			}else {//keyValue == arr[midIdx]이면
				return midIdx;
			}
		}
		return -1;
	}
	
	public static int binarySearch(Object[] arr, Object keyValue) {
		if(arr == null) {//배열이 null이면 throw
			throw new NullPointerException();
		}
		
		if(!(arr instanceof Comparable[])) {//Comparable을 구현하지 않았다면 예외 발생
			throw new NotComparableException(arr);
		}
		
		int lowIdx = 0;
		int highIdx = arr.length-1;
		
		while(lowIdx<=highIdx) {
			int midIdx = (lowIdx+highIdx)/2;
			
			if(((Comparable)arr[midIdx]).compareTo(keyValue) < 0) {//arr[midIdx]가 더 우선순위가 높으면
				lowIdx = midIdx+1; //midIdx이하는 버림
			}else if(((Comparable)keyValue).compareTo(arr[midIdx]) < 0) {//keyValue가 더 우선순위가 높으면
				highIdx = midIdx-1; //highIdx이상은 버림
			}else {//keyValue == arr[midIdx]이면
				return midIdx;
			}
		}
		return -1;
	}
}

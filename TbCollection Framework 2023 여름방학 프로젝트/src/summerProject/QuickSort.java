package summerProject;
/*
 * QuickSort 기능을 제공해 주는 클래스
 * int형과 Comparable 배열을 정렬 할 수 있도록 구현함
 * 오름차순으로 정렬만 구현함
 */

final public class QuickSort {
	
	/*
	 * quick sort를 수행하는 함수 divide and conquer 기법을 이용한다.
	 */
	public static void quickSort(int[] arr, int leftIdx, int rightIdx) {
		if(leftIdx >= rightIdx) {//종결 조건
			return;
		}
		
		int pivotIdx = partition(arr, leftIdx, rightIdx);
		
		quickSort(arr, leftIdx, pivotIdx-1); //왼쪽 정렬
		quickSort(arr, pivotIdx+1, rightIdx); //오른쪽 정렬
	}
	
	public static void quickSort(Comparable[] arr, int leftIdx, int rightIdx) {
		if(leftIdx >= rightIdx) {//종결 조건
			return;
		}
		
		int pivotIdx = partition(arr, leftIdx, rightIdx);
		
		quickSort(arr, leftIdx, pivotIdx-1); //왼쪽 정렬
		quickSort(arr, pivotIdx+1, rightIdx); //오른쪽 정렬
	}
	
	
	/*
	 * pivot값 보다 작거나 같은 값은 왼쪽 구역 .... 1 구역
	 * pivot값 보다 큰값은 오른쪽 구역 .... 2 구역
	 * 에 배치하는 함수 분류 과정이 끝나고 새로 pivot이 자리잡은 인덱스를 반환
	 */
	private static int partition(int[] arr, int leftIdx, int rightIdx) {
		
		if(4<=rightIdx-leftIdx) {//배열의 크기가 5이상이면
			selectPivot(arr, leftIdx, rightIdx); //적절한 pivot을 뽑아 맨 왼쪽으로 배치함
		}
		
		int pivotValue = arr[rightIdx]; //pivot 값
		int smallerIdx = leftIdx -1; //1구역의 끝 인덱스
		
		for(int curIdx = leftIdx; curIdx<rightIdx; curIdx++) {
			if(arr[curIdx] <= pivotValue) { //pivot값 보다 작거나 같은 값을 찾으면
				/*1구역을 오른쪽으로 한칸 확장시키고 curIdx에 저장된 값과 그 자리에 값을 swap함
				 * 목적: 발견한 pivotValue보다 작거나 같은 값(arr[curIdx])을 1구역으로 보내고
				 * 그자리에 있던 arr[smallerIdx+1]값은 2구역내에서 이동(자리를 내어주기 위함)
				 */
				swap(arr,++smallerIdx,curIdx);
			}
		}
		swap(arr,++smallerIdx,rightIdx); //2구역의 첫번째 요소가 pivotValue의 자리이므로
		
		return smallerIdx;
	}
	
	private static int partition(Comparable[] arr, int leftIdx, int rightIdx) {
		
		if(4<=rightIdx-leftIdx) {//배열의 크기가 5이상이면
			selectPivot(arr, leftIdx, rightIdx); //적절한 pivot을 뽑아 맨 왼쪽으로 배치함
		}
		
		Comparable pivotValue = arr[rightIdx]; //pivot 값
		int smallerIdx = leftIdx -1; //1구역의 끝 인덱스
		
		for(int curIdx = leftIdx; curIdx<rightIdx; curIdx++) {
			if(arr[curIdx].compareTo(pivotValue) <= 0 ) { //pivot값 보다 우선순위가 높거나 같은 값을 찾으면
				/*1구역을 오른쪽으로 한칸 확장시키고 curIdx에 저장된 값과 그 자리에 값을 swap함
				 * 목적: 발견한 pivotValue보다 작거나 같은 값(arr[curIdx])을 1구역으로 보내고
				 * 그자리에 있던 arr[smallerIdx+1]값은 2구역내에서 이동(자리를 내어주기 위함)
				 */
				swap(arr,++smallerIdx,curIdx);
			}
		}
		swap(arr,++smallerIdx,rightIdx); //2구역의 첫번째 요소가 pivotValue의 자리이므로
		
		return smallerIdx;
	}
	
	
	/*
	 * 구간의 처음 중간 끝 인덱스에 저장된 값중 우선순위가 중간인 값을 뽑아서 맨 오른쪽으로 보내는 함수
	 * 최대한 중간값의 pivot을 선정해서 평균적인 계산수를 줄이기 위함
	 */
	private static void selectPivot(int[] arr, int leftIdx, int rightIdx) {
		int idxArr[] = {leftIdx,(leftIdx+rightIdx)/2,rightIdx};
		
		//bubbleSort이용
		if(arr[idxArr[1]] < arr[idxArr[0]]) swap(idxArr,0,1);
		if(arr[idxArr[2]] < arr[idxArr[1]]) swap(idxArr,1,2);
		if(arr[idxArr[1]] < arr[idxArr[0]]) swap(idxArr,0,1);
		
		swap(arr, idxArr[1], rightIdx);//운선 순위가 중간인 값을 맨 오른쪽에 배치
	}
	
	private static void selectPivot(Comparable[] arr, int leftIdx, int rightIdx) {
		int idxArr[] = {leftIdx,(leftIdx+rightIdx)/2,rightIdx};
		
		//bubbleSort이용
		if(0 < arr[idxArr[0]].compareTo(arr[idxArr[1]])) swap(idxArr,0,1);
		if(0 < arr[idxArr[1]].compareTo(arr[idxArr[2]])) swap(idxArr,1,2);
		if(0 < arr[idxArr[0]].compareTo(arr[idxArr[1]])) swap(idxArr,0,1);
		
		swap(arr, idxArr[1], rightIdx);//운선 순위가 중간인 값을 맨 오른쪽에 배치
	}
	
	
	/*
	 * partition 구현에 사용한 swap 함수
	 */
	private static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	private static void swap(Comparable[] arr, int idx1, int idx2) {
		Comparable temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
}

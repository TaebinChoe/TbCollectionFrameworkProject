package summerProject;

/*
 * comparable을 구현하지 않은 클래스의 객체를 비교하려고 할때 발생하는 예외이다.
 * RuntimeException을 상속하여 반드시 처리해주지 않아도 된다.
 */

public class NotComparableException extends RuntimeException{
	NotComparableException(){
		super();
	}
	
	NotComparableException(Object[] o){
		super(o.getClass().getName()+"는 Comparable을 구현하지 않은 클래스 입니다.");
	}
}

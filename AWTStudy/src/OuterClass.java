
public class OuterClass {
	// 인스턴스 객체를 생성안하고 쓸 수 있는 내용

	enum Direction {
		A, B, C
	}

	class InnerClass {
		public void foo() {
			System.out.println("foo 호출됨...");
		}
	}

	// 클래스이름으로 바로 접근가능
	static class SInnerClass {
		public void bar() {
			System.out.println("bar 호출됨...");
		}
	}
}

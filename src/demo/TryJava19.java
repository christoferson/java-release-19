package demo;

import java.math.BigDecimal;

import demo.models.Line;
import demo.models.Point;

public class TryJava19 {
	public static void main(String[] args) {
		
		demoRecordPatterns();
		
		demoRecordPatternsNested();
		
		//demoSwitchNull(null);
		
		demoVirtualThread();
		
	}
	
	public static void demoRecordPatterns() {
		var p = new Point(7, 3);
		if (p instanceof Point(var x, var y)) {
			System.out.printf("x=%s y=%s %n", x, y);
		}
	}
	
	public static void demoRecordPatternsNested() {
		var l = new Line(new Point(5, 2), new Point(3, 1));
		if (l instanceof Line(Point(int x, int y), Point(int x2, int y2))) {
			System.out.printf("x=%s y=%s x=%s y=%s %n", x, y, x2, y2);
		}
	}
	
	// switch will throw NPE if no matching case null
	// java.lang.NullPointerException: Cannot invoke "String.hashCode()" because "value" is null
	public static void demoSwitchNull(String value) {
		String converted = switch (value) {
			case null -> "Null";
			case "1" -> "One";
			default -> "Default";
		};
		System.out.println(converted);
	}
	
	public static void demoVirtualThread() {
		Thread t = Thread.ofPlatform().unstarted(() -> System.out.println("platform..."));
		System.out.printf("Thread=%s State=%s %n", t, t.getState());
		t.start();
		
		t = Thread.ofVirtual().unstarted(() -> System.out.println("virtual..."));
		System.out.printf("Thread=%s State=%s %n", t, t.getState());
		t.start();
	}
}

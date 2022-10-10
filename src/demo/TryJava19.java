package demo;

import demo.models.Point;

public class TryJava19 {
	public static void main(String[] args) {
		demoRecordPatterns();
	}
	
	public static void demoRecordPatterns() {
		var p = new Point(7, 3);
		if (p instanceof Point(var x, var y)) {
			System.out.printf("x=%s y=%s %n", x, y);
		}
	}
}

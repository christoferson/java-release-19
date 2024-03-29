package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import demo.java11.DemoHttpClient;
import demo.models.Box;
import demo.models.Line;
import demo.models.Point;

public class TryJava19 {
	public static void main(String[] args) throws Exception {
		
		demoRecordPatterns();
		
		demoRecordPatternsDeconstruct();
		
		demoRecordPatternsNested();
		
		demoRecordPatternsGeneric();
		
		demoSwitch1("");
		
		demoSwitch2("2");
		//demoSwitchNull(null);
		
		demoTypeSwitch(7);
		
		demoTypeSwitchWithWhen("oLP");
		demoTypeSwitchWithWhen("At");
		
		demoTypeSwitchSealedClass(new IncrementExpr(2));
		
		// Virtual Threads
		
		demoVirtualThread();
		
		demoVirtualThreadTaskExecutor();
		
		//
		
		DemoApi.demoDecimal();
		
		DemoApi.demoSleep();
		
		DemoApi.demoLocale();
		
		DemoApi.demoCollectionStaticNewXXX();
		
		DemoApi.demoMath();
		
		
		demoStructuredConcurrency();
		
		DemoHttpClient.demo();
		
	}

	private static void demoStructuredConcurrency() {
		StructuredConcurency sc = new StructuredConcurency();
		try {
			System.out.println(sc.handle());
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void demoRecordPatterns() {
		
		var o = new Point(7, 3);

		if (o instanceof Point p) {
			System.out.printf("x=%s y=%s %n", p.x(), p.y());
		}
		
	}
	
	public static void demoRecordPatternsDeconstruct() {
		
		var p = new Point(7, 3);

		if (p instanceof Point(var x, var y)) {
			System.out.printf("x=%s y=%s %n", x, y);
		}
		
		if (p instanceof Point(int x, int y)) {
			System.out.printf("x=%s y=%s x+y=%s %n", x, y, x + y);
		}
		
	}
	
	public static void demoRecordPatternsNested() {
		
		var l = new Line(new Point(5, 2), new Point(3, 1));
		
		if (l instanceof Line(Point(int x, int y), Point(int x2, int y2))) {
			System.out.printf("x=%s y=%s x=%s y=%s %n", x, y, x2, y2);
		}
		
	}
	
	public static void demoRecordPatternsGeneric() {
		
		var o = new Box<Integer>(27);

		if (o instanceof Box b) {
			System.out.printf("b=%s %n", b.t());
		}

		if (o instanceof Box<Integer> b) {
			System.out.printf("b=%s %n", b.t() + 1);
		}
		
		//if (o instanceof Box(var t)) { //var is not allowed here
			//System.out.printf("t=%s %n", t);
		//}
		
		//if (o instanceof Box<Integer>(var t)) { // 'var' is not allowed here
		//	System.out.printf("t=%s %n", t);
		//}
		
		//if (o instanceof Box<Integer>(Integer t)) { // Only record types are permitted in a record pattern
			//System.out.printf("t=%s %n", t + 1);
		//}
		
	}
	
	
	public static void demoSwitch1(String value) {
		String converted = switch (value) {
			case null, "" -> "NullOrEmpty";
			case "1" -> "One";
			default -> "Default";
		};
		System.out.println(converted);
	} 
	
	public static void demoSwitch2(String value) {
		String converted = switch (value) {
			case "" -> "Empty";
			case "1" -> "One";
			case null, default -> "%s -> Default".formatted(value);
		};
		System.out.println(converted);
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
	
	public static void demoTypeSwitch(Object o) {
		String converted = switch (o) {
			case String s -> "(String) %s".formatted(s);
			case Integer i -> "(Integer) %02d".formatted(i);
			default -> o.toString();
		};
		System.out.println(converted);
	}
	
	public static void demoTypeSwitchSealedClass(Expression o) {
		int converted = switch(o) {
		    case IncrementExpr(int n) -> n + 1;
		    case NegateExpr(Expression n) -> -1 * n.evaluate();
		    case AddExpr(Expression left, Expression right) -> left.evaluate() + right.evaluate();
		    case MultiplyExpr(Expression left, Expression right) -> left.evaluate() * right.evaluate();
		    default -> throw new IllegalArgumentException("");
		};
		System.out.println("DemoTypeSwitchSealedClass: " + converted);
	}
	
	public static void demoTypeSwitchWithWhen(Object o) {
		String converted = switch (o) {
			case String s when s.length() >= 3 -> "(String) %s".formatted(s.toUpperCase());
			case String s -> "(String) %s".formatted(s.toLowerCase());
			case Integer i -> "(Integer) %02d".formatted(i);
			default -> o.toString();
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
	
	public static void demoVirtualThreadTaskExecutor() {
		System.out.printf("demoVirtualThreadTaskExecutor Start %n");
		List<Future<Integer>> tasks = new ArrayList<>();
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
		    IntStream.range(0, 5).forEach(i -> {
		        var task = executor.submit(() -> {
		            Thread.sleep(Duration.ofSeconds(2));
		            return i;
		        });
		        tasks.add(task);
		    });
		    for (Future<Integer> t : tasks) {
		    	System.out.printf("t=%s %n", t.get());
		    }
		} catch (ExecutionException | InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		System.out.printf("demoVirtualThreadTaskExecutor End %n");
	}
}

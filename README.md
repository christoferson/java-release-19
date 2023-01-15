# java-release-19
Java SDK 19

## JEP 405: Record Patterns (Preview) | [link](https://openjdk.org/jeps/405)

Enhance the Java programming language with record patterns to deconstruct record values. Record patterns and type patterns can be nested to enable a powerful, declarative, and composable form of data navigation and processing. 

A record pattern can use var to match against a record component without stating the type of the component. In that case the compiler infers the type of the pattern variable introduced by the var pattern. For example, the pattern Point(var a, var b) is shorthand for the pattern Point(int a, int b)

```java

public record Point(int x, int y) { }

public record Line(Point start, Point end) { }

public record Box<T>(T t) { }

```

```java
var o = new Point(7, 3);

if (o instanceof Point p) {
	System.out.printf("x=%s y=%s %n", p.x(), p.y());
}
```

```java
var p = new Point(7, 3);

if (p instanceof Point(var x, var y)) {
	System.out.printf("x=%s y=%s %n", x, y);
}

if (p instanceof Point(int x, int y)) {
	System.out.printf("x=%s y=%s x+y=%s %n", x, y, x + y);
}
```


```java
var l = new Line(new Point(5, 2), new Point(3, 1));

if (l instanceof Line(Point(int x, int y), Point(int x2, int y2))) {
	System.out.printf("x=%s y=%s x=%s y=%s %n", x, y, x2, y2);
}
```
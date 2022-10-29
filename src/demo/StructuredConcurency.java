package demo;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import jdk.incubator.concurrent.*;

public class StructuredConcurency {

	Response handle() throws ExecutionException, InterruptedException {
	    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
	        Future<String> f1 = scope.fork(() -> {
	        	Random rnd = new Random();
	        	int value = rnd.nextInt(5);
				System.out.println("Thread 1 " + value);
	        	Thread.sleep(Duration.ofMillis(1000 * value));
	        	return String.valueOf(rnd.nextInt(100));
	        });
	        Future<String> f2 = scope.fork(() -> {
	        	Random rnd = new Random();
	        	int value = rnd.nextInt(5);
	        	System.out.println("Thread 2 " + value);
	        	Thread.sleep(Duration.ofMillis(1000 * value));
	        	return String.valueOf(rnd.nextInt(100));
	        });

	        scope.join();           // Join both forks
	        scope.throwIfFailed();  // Propagate errors

	        // Here, both forks have succeeded, so compose their results
	        return new Response(f1.resultNow(), f2.resultNow());
	    }
	}
	
	private static record Response(String value1, String value2) {
		
	}
	
}

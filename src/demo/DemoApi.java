package demo;

import java.math.BigDecimal;
import java.time.Duration;

public class DemoApi {
	
	public static void demoDecimal() {
		System.out.println(BigDecimal.TWO);
	}
	
	public static void demoSleep() {
		try {
			Thread.sleep(500);
			Thread.sleep(Duration.ofSeconds(1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

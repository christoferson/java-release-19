package demo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Locale;

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

	public static void demoLocale() {
		System.out.println(Locale.of("ja"));
		System.out.println(Locale.of("ja","jp"));
		//System.out.println(new Locale()); // Deprecated
	}
}

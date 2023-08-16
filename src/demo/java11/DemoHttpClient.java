package demo.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DemoHttpClient {
	
	public static void demo() throws IOException, InterruptedException {
		
		System.out.println("------- DemoHttpClient ------");
		
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://google.com"))
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + httpResponse.statusCode());

        System.out.println("Response body: " + httpResponse.body());
	}

}

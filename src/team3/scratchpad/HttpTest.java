package team3.scratchpad;

import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpRequest.Builder;

import javax.net.ssl.HttpsURLConnection;

@SuppressWarnings("unused")
public class HttpTest {
	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public static void main(String[] args) {
		HttpTest httpTest = new HttpTest();

		try {
			httpTest.sendGetRequest("content-type", "application/json", "f", "fff", "f", "aaa");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void sendGetRequest(String... headers) throws Exception {
		final String source = "weatherapi.com";
		final String apiKey = "719433f15a3249d2b79154934202403";

		String urlString = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + "Riga";

		Builder builder = HttpRequest.newBuilder().GET();
		builder.uri(URI.create(urlString));

		if ((headers.length != 0) && (0 == headers.length % 2)) {
			builder.headers(headers);
		}

		// builder.header("content-type", "application/json");
		HttpRequest request = builder.build();
		System.out.println(request.toString());
		System.out.println(request.headers().toString());

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// print status code
		System.out.println("response status: " + response.statusCode());

		// print response body
		System.out.println(response.body());
	}
	
	private void sendHttpsGetRequest() {
		final String apiKey = "719433f15a3249d2b79154934202403";
		String httpsUrl = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + "Riga";

		try {
			HttpsURLConnection httpsConnection = (HttpsURLConnection) new URL(httpsUrl).openConnection();

			if (httpsConnection != null) {
				//httpsConnection.
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package controller;

import enums.RequestMethod;
import exception.InvalidArgumentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import com.sun.xml.internal.ws.policy.AssertionSet;

public class RequestController {

	/**
	 * Gets the response of an HttpRequest.
	 * @param String endpoint The requested URL
	 * @param String request params
	 * @param method request Method.
	 * @return String responseRequest - Response of the request or null if request status it's not OK.
	 * @throws InvalidArgumentException if has an incorrect argument value
	 */
	
	public static String getResponseRequest(String endpoint, String params, RequestMethod method)
			throws IOException, InvalidArgumentException {

		//checking for correct method arguments
		if (method != RequestMethod.POST && method != RequestMethod.GET && method != RequestMethod.PUT
				&& method != RequestMethod.PATCH) {
			throw new InvalidArgumentException("Request method is not valid");
		}

		if (endpoint == null || endpoint.equals("")) {
			throw new InvalidArgumentException("Invalid endpoint request. Must be a valid endpoint request URL");
		}
		
		//Initializing a new URL object
		URL url = new URL(endpoint);

		//checking for a valid URL
		try {
			if (!url.toURI().isAbsolute()) {
				throw new InvalidArgumentException("Endpoint url must be an absolute path");
			}
		} catch (URISyntaxException ex) {
			throw new InvalidArgumentException("URL incorrectly format");
		}
		
		//creating an HttpUrlConnection and setting Request method. Method must be an existing method.
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod(method.toString());

		httpURLConnection.setDoOutput(true);

		//writing request...
		try (OutputStream os = httpURLConnection.getOutputStream()) {
			os.write(params.getBytes());
			os.flush();
		}

		//getting response code form request
		int responseCode = httpURLConnection.getResponseCode();

		//System.out.println("Response code: " + responseCode); for testing...

		//checking if responde code it's OK
		if (responseCode == HttpURLConnection.HTTP_OK) {
			StringBuffer response;
			//saving reponse request
			try ( // success
					BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}

			String strResponse = response.toString();

			return strResponse;
		}

		return null;
	}

	/**
	 * Send a POST Request
	 */
	public static String postRequest(String endpoint, String postParams) throws IOException, InvalidArgumentException {

		String response = getResponseRequest(endpoint, postParams, RequestMethod.POST);

		return response;
	}

	/**
	 * Send a GET request
	 */
	public static String getRequest(String endpoint, String getParams) throws IOException, InvalidArgumentException {

		String response = getResponseRequest(endpoint, getParams, RequestMethod.GET);

		return response;
	}

}

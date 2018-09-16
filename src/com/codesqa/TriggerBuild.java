package com.codesqa;

import java.io.IOException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TriggerBuild {

	private static String JenkinsAuthenticationUserName = "admin";
	private static String JenkinsAuthenticationPassword = "admin";

	public static void JenkinsRemoteAPIBuild(String param1, String param2) throws AuthenticationException, ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(
				"http://localhost:8080/job/Parameterized_Job/buildWithParameters?token=e82c6c0878521d55f899f897f4ad07d8&Environment="
						+ param1 + "&Architecture=" + param2);

		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(JenkinsAuthenticationUserName,
				JenkinsAuthenticationPassword);
		httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
		httpPost.addHeader("Content-type", "application/json");
		httpPost.addHeader("Accept", "application/json");
		System.out.println("posting: " + httpPost);
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println((response.getStatusLine().getStatusCode()));
		System.out.println("Response: " + response);
		client.close();
	}

	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException {
		JenkinsRemoteAPIBuild("Alpha", "ARM");
	}
	
}
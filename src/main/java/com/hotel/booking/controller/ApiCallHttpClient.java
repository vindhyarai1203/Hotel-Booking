package com.hotel.booking.controller;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.net.http.HttpRequest.newBuilder;

public class ApiCallHttpClient {
    static Logger logger = LoggerFactory.getLogger(ApiCallHttpClient.class);

    private static void GetPincpdeApi() {
        try {
            String name = "";
            String url = "https://api.postalpincode.in/pincode/201306";
            HttpGet httpGet = new HttpGet(url);


            httpGet.addHeader(name, "vindhya rai");
        }
        catch (Exception exception)
        {
            logger.error(exception.getMessage());
        }


    }

    private static void practice() throws IOException {
        String url = "http://localhost:8080/api/hotel";
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
        nameValuePairs.add(new BasicNameValuePair("hotelName",
                "Practice1"));
        nameValuePairs.add(new BasicNameValuePair("city",
                "abcd"));
        nameValuePairs.add(new BasicNameValuePair("rooms",
                "9"));
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }


    }

    public static void POSTAPICALL() throws IOException, InterruptedException {
        try {
            String url = "http://localhost:8080/api/hotel";
            String FILE_JSON = "/Users/vindhyarai/Downloads/generated.json";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of(FILE_JSON)))
                    .timeout(Duration.ofSeconds(10))
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.debug(String.valueOf(httpResponse.statusCode()));
            if (httpResponse.statusCode() == 200 && httpResponse != null) {
                logger.info(httpResponse.body());
            } else {
                logger.error("An exception occurred");
            }
        }
        catch (Exception exception)
        {
            logger.error(exception.getMessage());
        }

    }

    public static void GETAPICALL() throws IOException, InterruptedException {
        String url = "http://localhost:8080/api/hotel?city=Silchar&check_in=2022-03-25&check_out=2022-03-27";
        HttpRequest httpRequest = newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info(String.valueOf(httpResponse.statusCode()));
            if (httpResponse.statusCode() == 200 && httpResponse != null) {
                logger.info(httpResponse.body());
            } else {
                throw new RuntimeException("Something went wrong");
            }
        }
        catch (Exception exception)
        {
            logger.error(exception.getMessage());
        }


    }

    public static void POSTAPICALL2() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/hotel");
        String json = "{\"hotelName\":\"Vinay11231\",\"city\":\"Silchar\",\"rooms\":\"9\"}";
        StringEntity stringEntity = new StringEntity(json);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
        }
        client.close();

    }

    public static void POSTAPICALL3() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/hotel/call");
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("user", "vindhya"));
        param.add(new BasicNameValuePair("pass", "1234"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(param));
            CloseableHttpResponse response = client.execute(httpPost);
            logger.info(String.valueOf(response.getEntity()));
        }
        catch (Exception exception)
        {
            logger.error(exception.getMessage());
        }
        client.close();

    }

    public static void DELETEAPICALL() throws IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpDelete httpGet = new HttpDelete("http://localhost:8080/api/hotel/201306");
            CloseableHttpResponse response = client.execute(httpGet);
            logger.info(String.valueOf(response.getStatusLine()));
            logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DELETEAPICALL();


    }

}








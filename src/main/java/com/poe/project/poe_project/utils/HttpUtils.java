package com.poe.project.poe_project.utils;/**
 * @Author: C2020118
 * @Date：2020/8/25
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author jiaqiaodan
 * @DATE 2020/8/25
 */
@Slf4j
public class HttpUtils {

    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> headers) {
        CloseableHttpClient httpClient = null;
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            httpClient = getHttpClient();
            URIBuilder builder = new URIBuilder(url);
            if (param != null && !param.isEmpty()) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    httpGet.setHeader(header.getKey(), header.getValue());
                }
            }
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error("doGet fail, errorMsg={}", ExceptionUtils.getStackTrace(e));
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("doGet close fail, errorMsg={}", ExceptionUtils.getStackTrace(e));
            }
        }
        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            if (param != null && !param.isEmpty()) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("url:{}, response={}", url, responseStr);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = responseStr;
            }
        } catch (Exception e) {
            log.error("doPost fail, errorMsg={}", ExceptionUtils.getStackTrace(e));
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("doPost close fail, errorMsg={}", ExceptionUtils.getStackTrace(e));
            }
        }
        return resultString;
    }

    public static String doPostJson(String url, String json) {
        return doPostJson(url, json, null);
    }

    public static String doPostJson(String url, String json, Map<String, String> headers) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(3000).build();
            httpPost.setConfig(requestConfig);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    httpPost.setHeader(header.getKey(), header.getValue());
                }
            }
            if (json != null && json.trim().length() > 0) {
                StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("url:{}, response={}", url, responseStr);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = responseStr;
            }
        } catch (Exception e) {
            log.error("doPostJson fail, errorMsg={}", ExceptionUtils.getStackTrace(e));
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("doPostJson close fail, errorMsg={}", ExceptionUtils.getStackTrace(e));
            }
        }
        return resultString;
    }

    private static CloseableHttpClient getHttpClient() {
        try {
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE)
                    .build();

            ConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainsf)
                    .register("https", sslsf)
                    .build();

            HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);

            return HttpClients.custom()
                    .setConnectionManager(cm)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

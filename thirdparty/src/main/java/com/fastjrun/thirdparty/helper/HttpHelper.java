package com.fastjrun.thirdparty.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fastjrun.thirdparty.common.NullHostNameVerifier;

public class HttpHelper {

    public static String requestJsonPost(String reqJson, String urlReq, Map<String, String> headerMap) {
        String line = "";
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            URL request = new URL(urlReq);
            HttpURLConnection connection = (HttpURLConnection) request.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            Iterator<String> itera = headerMap.keySet().iterator();
            if (itera != null) {
                while (itera.hasNext()) {
                    String key = itera.next();
                    String value = headerMap.get(key);
                    connection.setRequestProperty(key, value);
                }
            }
            connection.connect();
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(reqJson);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                result += line;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String request(String reqStr, String urlReq, boolean isPost) {
        String line = "";
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL request = new URL(urlReq);
            HttpURLConnection connection = (HttpURLConnection) request.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            if (isPost) {
                connection.setRequestMethod("POST");
            }
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(reqStr.length()));
            connection.connect();
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(reqStr);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                result += line;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (out != null) {
                out.close();
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    public static String sendGet(String url, Map<String, String> headerMap) {
        String result = "";
        BufferedReader in = null;

        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            URL request = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) request.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            Iterator<String> itera = headerMap.keySet().iterator();
            if (itera != null) {
                while (itera.hasNext()) {
                    String key = itera.next();
                    String value = headerMap.get(key);
                    connection.setRequestProperty(key, value);
                }
            }
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}

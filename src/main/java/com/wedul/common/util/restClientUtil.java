package com.wedul.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * HTTPS 통신을위한 요청
 * 
 * @author wedul
 *
 */
public final class restClientUtil {
	
	/**
	 * Method type
	 * 
	 * @author wedul
	 *
	 */
	private enum METHOD_TYPE {
		GET,
		POST
	}
	
	/**
	 * Content Type
	 * 
	 * @author wedul
	 *
	 */
	private enum CONTENT_TYPE {
		PARAM("application/x-www-form-urlencoded"),
		JSON("application/json");
		
		private String contentType; 
		
		private CONTENT_TYPE(String contentType) {
			this.contentType = contentType;
		}
		
		/**
		 * Content Type 반환
		 * 
		 * @return
		 */
		public String getContentType() {
			return this.contentType;
		}
	}
	
	private static String ENCODING = "UTF-8";
	
	private restClientUtil() {}
	
	/**
	 * Get Json 요청
	 * 
	 * @param rul
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doGetJsonRequest(String url, byte... params) throws Exception {
		return doRequest(url, METHOD_TYPE.GET, CONTENT_TYPE.JSON, params);
	}
	
	/**
	 * Get 요청
	 * 
	 * @param rul
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doGetParamRequest(String url, byte... params) throws Exception {
		return doRequest(url, METHOD_TYPE.GET, CONTENT_TYPE.PARAM, params);
	}
	
	/**
	 * Post 요청
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doJsonPost(String url, byte... params) throws Exception {
		return doRequest(url, METHOD_TYPE.POST, CONTENT_TYPE.JSON, params);
	}
	
	/**
	 * Post 요청
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doParamPost(String url, byte... params) throws Exception {
		return doRequest(url, METHOD_TYPE.POST, CONTENT_TYPE.PARAM, params);
	}
	
	/**
	 * request 전송 및 response 받기
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private static String doRequest(String url, METHOD_TYPE method, CONTENT_TYPE content, byte... params) throws Exception {
		boolean isTLS = url.toLowerCase().startsWith("https://");
		HttpURLConnection conn = null;
		OutputStream out = null;
		String responseContent = null;
		
		try {
			if (isTLS) {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager() }, new SecureRandom());
				SSLContext.setDefault(ctx);
			}

			URL connUrl = new URL(url);
			conn = (HttpURLConnection) connUrl.openConnection();
			conn.setRequestMethod(method.toString());
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", content.getContentType());

			if (isTLS && (conn instanceof HttpsURLConnection)) {
				((HttpsURLConnection) conn).setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			}

			conn.connect();
			if (params != null && params.length > 0) {
				out = conn.getOutputStream();
				out.write(params);
				out.flush();
			}

			responseContent = getResponseAsString(conn);
		} finally {
			if ( null != conn ) {
				conn.disconnect();
			}
			if ( null != out ) {
				out.close();
			}
		}
		
		return responseContent;
	}
	
	/**
	 * Response 결과값 가져오기
	 * 
	 * @param conn
	 * @return
	 * @throws IOException
	 */
	private static String getResponseAsString(HttpURLConnection conn) throws IOException {
		InputStream errorStream = conn.getErrorStream();
		if (errorStream == null) {
			return getStreamAsString(conn.getInputStream(), ENCODING);
		} else {
			String msg = "ERR:" + conn.getResponseCode() + ":" + conn.getResponseMessage();
			return msg;
		}
	}
	
	/**
	 * return Stream에서 데이터 추출
	 * 
	 * @param stream
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset)); 
				StringWriter writer = new StringWriter();) {
			char[] chars = new char[1024];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}
			return writer.toString();
		} finally {
			stream.close();
		}
	}
	
	/**
	 * 인증서 Default trust manager
	 * 
	 * @author wedul
	 *
	 */
	private static class DefaultTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
		}
		
		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
		}
		
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
}
package rml.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.zip.GZIPInputStream;

@SuppressWarnings("deprecation")
public class HttpUtil {
	private Set<String> cookies = new HashSet<String>();
	private HttpClient httpClient;
	private static final int TIME_OUT = 60 * 1000;
	private static HttpUtil instance = null;

	public static HttpUtil getInstance() {
		if (instance == null) {
			synchronized (HttpUtil.class) {
				instance = new HttpUtil();
			}
		}
		return instance;
	}

	public HttpUtil() {
		this.initHttpClient();
	}

	public void setCookie(HttpUriRequest request) {
		/*
		 * synchronized (this) { for (String v : this.cookies) {
		 * System.err.println("Set Cookie: " + v); request.addHeader("Cookie",
		 * v); } }
		 */

	}

	public void getCookie(HttpResponse response) {
		/*
		 * 
		 * synchronized (this) { for (Header h :
		 * response.getHeaders("Set-Cookie")) { String value =
		 * h.getValue().split(";", 2)[0]; if (value.startsWith("JSESSIONID")) {
		 * this.cookies.clear(); } System.err.println(h.getValue().split(";",
		 * 2)[0]); this.cookies.add(h.getValue().split(";", 2)[0]); } }
		 * 
		 */
	}

	public String getHost() {
		return "";
	}

	public String httpExecute(HttpUriRequest request) throws ClientProtocolException, IOException {
		this.setCookie(request);
		HttpResponse response = this.httpClient.execute(request);
		/*
		 * this.getCookie(response); while
		 * (response.getStatusLine().getStatusCode() == 401) {
		 * this.getCookie(response); response =
		 * this.httpClient.execute(request); } this.getCookie(response);
		 */

		HttpEntity entity = response.getEntity();
		if (entity == null)
			return null;
		return EntityUtils.toString(entity, "utf-8");
	}

	private String getURL(String url) {
		if (!url.startsWith("http://")) {
			url = this.getHost() + url;
		}
		return url;
	}

	public String httpGet(String url) throws ClientProtocolException, IOException {
		return this.httpExecute(new HttpGet(this.getURL(url)));
	}

	public String httpGet(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		if (url.startsWith("http://"))
			return this.httpGet(QueryString.build(url, params));
		else
			return this.httpGet(QueryString.build(this.getURL(url), params));
	}

	public byte[] httpGetByte(String url) throws ClientProtocolException, IOException {
		HttpGet get;
		if (!url.startsWith("http://"))
			get = new HttpGet(this.getURL(url));
		else
			get = new HttpGet(url);

		HttpContext context = new BasicHttpContext();
		HttpResponse response = this.httpClient.execute(get, context);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toByteArray(entity);
	}

	public String httpPostJson(String url, String json) throws ClientProtocolException, IOException {
		String hosturl = this.getURL(url);
		HttpPost post = new HttpPost(hosturl);
		StringEntity entity = new StringEntity(json, "UTF-8");
		post.setEntity(entity);
		post.addHeader("content-type", "application/json");
		return this.httpExecute(post);

	}

	public String httpPost(String url, Map<String, ?> map) throws ParseException, IOException {
		HttpPost post = new HttpPost(this.getURL(url));
		post.addHeader("content-type", "application/x-www-form-urlencoded");
		post.setHeader("Connection", "close");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (null != map) {
			for (String key : map.keySet()) {
				Object p = map.get(key);
				if (p != null) {
					if (p instanceof String) {
						nvps.add(new BasicNameValuePair(key, p.toString()));
					} else {
						@SuppressWarnings("unchecked")
						List<String> l = (List<String>) p;
						for (String v : l) {
							nvps.add(new BasicNameValuePair(key, v));
						}
					}
				}
			}
		}
		post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		return this.httpExecute(post);
	}

	public String httpPost(String url, Map<String, ?> headParams, Map<String, ?> bodyParams)
			throws ParseException, IOException {
		HttpPost post = new HttpPost(this.getURL(url));
		post.addHeader("content-type", "application/x-www-form-urlencoded");
		post.setHeader("Connection", "close");

		// 请求头参数
		if (null != headParams) {
			for (String key : headParams.keySet()) {
				Object p = headParams.get(key);
				if (null != p) {
					if (p instanceof String) {
						post.addHeader(key, p.toString());
					} else {
						@SuppressWarnings("unchecked")
						List<String> l = (List<String>) p;
						for (String v : l) {
							post.addHeader(key, v);
						}
					}
				}
			}
		}

		// 请求体参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (null != bodyParams) {
			for (String key : bodyParams.keySet()) {
				Object p = bodyParams.get(key);
				if (p != null) {
					if (p instanceof String) {
						nvps.add(new BasicNameValuePair(key, p.toString()));
					} else {
						@SuppressWarnings("unchecked")
						List<String> l = (List<String>) p;
						for (String v : l) {
							nvps.add(new BasicNameValuePair(key, v));
						}
					}
				}
			}
		}

		post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

		return this.httpExecute(post);
	}

	public void initHttpClient() {
		HttpParams params = new BasicHttpParams();
		ConnManagerParams.setMaxTotalConnections(params, 15);
		ConnManagerParams.setTimeout(params, 10000);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		List<BasicHeader> headers = new ArrayList<BasicHeader>();
		headers.add(
				new BasicHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322)"));
		headers.add(new BasicHeader("Accept", "*/*"));
		headers.add(new BasicHeader("Accept-Encoding", "gzip"));

		// headers.add(new
		// BasicHeader("Content-type","application/x-www-form-urlencoded"));
		params.setParameter(ClientPNames.DEFAULT_HEADERS, headers);

		HttpConnectionParams.setSocketBufferSize(params, 8 * 1024);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT);
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
		HttpConnectionParams.setStaleCheckingEnabled(params, true);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		this.httpClient = new DefaultHttpClient(cm, params);
		((AbstractHttpClient) this.httpClient).addResponseInterceptor(new HttpResponseInterceptor() {
			public void process(final HttpResponse response, final HttpContext context)
					throws HttpException, IOException {
				HttpEntity entity = response.getEntity();
				Header encheader = entity.getContentEncoding();
				if (encheader != null) {
					HeaderElement[] codecs = encheader.getElements();
					for (int i = 0; i < codecs.length; i++) {
						if (codecs[i].getName().equalsIgnoreCase("gzip")) {
							response.setEntity(new GzipDecompressingEntity(entity));
							return;
						}
					}
				}
			}
		});

	}

	static class GzipDecompressingEntity extends HttpEntityWrapper {
		public GzipDecompressingEntity(final HttpEntity entity) {
			super(entity);
		}

		@Override
		public InputStream getContent() throws IOException, IllegalStateException {
			// the wrapped entity's getContent() decides about repeatability
			InputStream wrappedin = wrappedEntity.getContent();
			return new GZIPInputStream(wrappedin);
		}

		@Override
		public long getContentLength() {
			// length of ungzipped content is not known
			return -1;
		}
	}

	public String httpUpload(String u, Map<String, String> params, String filePath) {
		FileInputStream fis = null;
		DataOutputStream dos = null;
		ByteArrayOutputStream rd = null;
		try {
			String boundary = "*****************************************";
			String newLine = "\r\n";
			int bytesAvailable;
			int bufferSize;
			int maxBufferSize = 4096;
			int bytesRead;
			u = this.getHost() + u;
			URL url = new URL(u);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			for (String v : this.cookies) {
				con.addRequestProperty("Cookie", "rtime=0; ltime=" + System.currentTimeMillis() + "; " + v);
			}

			dos = new DataOutputStream(con.getOutputStream());
			// System.out.println("---filePath--httphelper---"+filePath);
			File f = new File(filePath);
			// System.out.println("----isFileNull----"+f);
			fis = new FileInputStream(f);
			dos.writeBytes("--" + boundary + newLine);
			dos.writeBytes("Content-Disposition: form-data; " + "name=\"" + "image" + "\";filename=\"" + f.getName()
					+ ".jpg\"" + newLine + newLine);
			bytesAvailable = fis.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			bytesRead = fis.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fis.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fis.read(buffer, 0, bufferSize);
			}
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);

			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append("--");
				sb.append(boundary);
				sb.append(newLine);
				sb.append("Content-Disposition: form-data; name=\"");
				sb.append(entry.getKey());
				sb.append("\"");
				sb.append(newLine);
				sb.append("Content-Type: text/plain; charset=");
				sb.append("UTF-8");
				sb.append(newLine);
				sb.append("Content-Transfer-Encoding: 8bit");
				sb.append(newLine);
				sb.append(newLine);
				sb.append(entry.getValue());
				sb.append(newLine);
				sb.append("--");
				sb.append(boundary);
				sb.append("--");
				sb.append(newLine);
			}
			dos.write(sb.toString().getBytes("UTF-8"));
			dos.flush();
			rd = new ByteArrayOutputStream();
			byte[] buffer2 = new byte[1024];
			for (int i = con.getInputStream().read(buffer2); i > -1; i = con.getInputStream().read(buffer2)) {
				rd.write(buffer2, 0, i);
				rd.flush();
			}
			return rd.toString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static final class QueryString {

		private QueryString() {

		}

		/**
		 * 重写方法
		 * 
		 * @param params
		 * @return
		 */
		public static String build(Map<String, String> params) {
			StringBuffer buffer = new StringBuffer();
			if (params != null) {
				for (Map.Entry<String, String> e : params.entrySet()) {
					buffer.append(e.getKey());
					buffer.append("=");
					if (e.getValue() != null) {
						buffer.append(e.getValue());
					}
					buffer.append("&");
				}
				buffer.setLength(buffer.length() - 1);
			}
			return buffer.toString();
		}

		public static String build(String url, Map<String, String> params) throws UnsupportedEncodingException {
			StringBuffer buffer = new StringBuffer();
			buffer.append(url);
			if (params != null) {
				buffer.append("?");
				for (Map.Entry<String, String> e : params.entrySet()) {
					buffer.append(e.getKey());
					buffer.append("=");
					if (e.getValue() != null) {
						buffer.append(java.net.URLEncoder.encode(e.getValue(), "utf-8"));
					}
					buffer.append("&");
				}
				buffer.setLength(buffer.length() - 1);
			}
			return buffer.toString();
		}

		public static Map<String, String> buildMap(NameValuePair[] pairs) {
			if (null != pairs) {
				Map<String, String> map = new HashMap<String, String>();
				for (NameValuePair nameValuePair : pairs) {
					map.put(nameValuePair.getName(), nameValuePair.getValue());
				}

				return map;
			}
			return null;
		}

	}
}

package rml.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpPostUtils {

    /** 应答内容 */
    private String resContent;
    private static HttpPostUtils instance = null;

    /**
     * @param
     */
  /*  public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "http://10.200.48.135:80/paycenter/queryOrder.do?order_no=1364953038575&amp;partner=130&amp;sign=4ecb9b1225b2599e270c937071bd482a";
        HttpPostUtil postutil = new HttpPostUtil();
        if(postutil.postRequest(url)){
            System.out.print(postutil.getResContent());
        }

    }*/

    public static HttpPostUtils getInstance() {
        if (instance == null) {
            synchronized (HttpPostUtils.class) {
                instance = new HttpPostUtils();
            }
        }
        return instance;
    }
    /**
     * 发送post请求
     * @param strUrl
     * @return
     */
    public boolean postRequest(String strUrl){
        //请求url
        String url = this.getURL(strUrl);
        //请求参数
        String queryString = this.getQueryString(strUrl);
        try{
            if (url.indexOf("http://") != -1) {
                this.resContent = this.postHttp(url, queryString);
                return true;
            } else if (url.indexOf("https://") != -1) {
              //  this.resContent = this.postHttps(url, queryString,null,null,null);
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String postHttp(String urlStr, String params) {
        System.out.println("http方式提交的URL: "+urlStr + "?" + params);
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(urlStr);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
//			System.out.println("remote host respone : "+result);
            System.out.println("远程主机返回文本结果："+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result.toString();
    }

    /**
     * 以SSL方式提交请求
     * @param urlString  提交的url地址
     * @param
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public String postHttps(String urlString, Map<String ,String> params) throws NoSuchAlgorithmException, KeyManagementException, IOException
    {


        // 请求体参数
        HttpPost post = new HttpPost(this.getURL(urlString));
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (null != params) {
            for (String key : params.keySet()) {
                Object p = params.get(key);
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
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            new UrlEncodedFormEntity(nvps, "UTF-8");
        }

        System.out.println("https方式提交的URL:"+urlString);
        System.out.println("提交的参数："+params);
        OutputStreamWriter os = null;

        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        System.out.println("连接类型："+ con.getClass());
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();

        if (con instanceof javax.net.ssl.HttpsURLConnection) {
            System.out.println("*** openConnection returns an instanceof javax.net.ssl.HttpsURLConnection");

            //信任所有证书 开始
            javax.net.ssl.SSLContext sc = null;
            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[] { new javax.net.ssl.X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            } };

            // Install the all-trusting trust manager
            sc = javax.net.ssl.SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            //信任所有证书 结束

            javax.net.ssl.HostnameVerifier hv = new javax.net.ssl.HostnameVerifier() {
                public boolean verify(String urlHostName, javax.net.ssl.SSLSession session) {
                    return urlHostName.equals(session.getPeerHost());
                }
            };
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(hv);
            javax.net.ssl.HttpsURLConnection conn = null;
            conn = (javax.net.ssl.HttpsURLConnection) con;
            // conn.setRequestProperty("Content-Type", "text/xml");
            conn.setDoOutput(true);
            conn.setFollowRedirects(true);
            // conn.setReadTimeout(30000);
            os = new OutputStreamWriter(conn.getOutputStream());
            os.write("https://api.weixin.qq.com/sns/jscode2session?js_code=071nG3yN0lJzb623qdyN0mtHxN0nG3yP&grant_type=authorization_code&secret=96b0d8c25e65644476f1657991429bbb&appid=wxa97f30ee6cd9ffc0");
            os.flush();
            if (conn.getResponseCode() == 302 || conn.getResponseCode() == 200) {
                System.out.println("https请求发送成功。");
                System.out.println("返回码：" + conn.getResponseCode());
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        else if (con instanceof com.sun.net.ssl.HttpsURLConnection) {
            System.out.println("***openConnection returns an instanceof com.sun.net.ssl.HttpsURLConnection");
            //信任所有证书 开始
            com.sun.net.ssl.SSLContext sc = null;
            com.sun.net.ssl.TrustManager[] trustAllCerts = new com.sun.net.ssl.TrustManager[] { new com.sun.net.ssl.X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override
                public boolean isClientTrusted(X509Certificate[] arg0) {
                    // TODO Auto-generated method stub
                    return true;
                }
                @Override
                public boolean isServerTrusted(X509Certificate[] arg0) {
                    // TODO Auto-generated method stub
                    return true;
                }
            } };
            // Install the all-trusting trust manager
            sc = com.sun.net.ssl.SSLContext.getInstance("TLSv1");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            com.sun.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            //信任所有证书 结束

            com.sun.net.ssl.HttpsURLConnection conn = null;
            conn = (com.sun.net.ssl.HttpsURLConnection) con;
            com.sun.net.ssl.HostnameVerifier hv = new com.sun.net.ssl.HostnameVerifier() {
                @Override
                public boolean verify(String arg0, String arg1) {
                    return true;
                }
            };
            com.sun.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(hv);
            conn.setAllowUserInteraction(true);
            conn.setDoOutput(true);

            os = new OutputStreamWriter(conn.getOutputStream());
            //os.write(data);
            os.write("https://api.weixin.qq.com/sns/jscode2session?js_code=071nG3yN0lJzb623qdyN0mtHxN0nG3yP&grant_type=authorization_code&secret=96b0d8c25e65644476f1657991429bbb&appid=wxa97f30ee6cd9ffc0");
            os.flush();

            if (conn.getResponseCode() == 302 || conn.getResponseCode() == 200) {
                System.out.println("https请求发送成功。");
                System.out.println("返回码：" + conn.getResponseCode());
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        System.out.println("远程主机返回的文本结果："+result);

        return result.toString();
    }

    /**
     * 获取不带查询串的url
     * @param strUrl
     * @return String
     */
    private String getURL(String strUrl) {

        if(null != strUrl) {
            int indexOf = strUrl.indexOf("?");
            if(-1 != indexOf) {
                return strUrl.substring(0, indexOf);
            }

            return strUrl;
        }

        return strUrl;

    }

    /**
     * 获取查询串
     * @param strUrl
     * @return String
     */
    private String getQueryString(String strUrl) {

        if(null != strUrl) {
            int indexOf = strUrl.indexOf("?");
            if(-1 != indexOf) {
                return strUrl.substring(indexOf+1, strUrl.length());
            }

            return "";
        }

        return strUrl;
    }

    public String getResContent() {
        return resContent;
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

}
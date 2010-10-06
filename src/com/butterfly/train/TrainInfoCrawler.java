package com.butterfly.train;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TrainInfoCrawler {

	public static List<TrainInfo> decodeHtml(String html) {
		List<TrainInfo> info = new ArrayList<TrainInfo>();
		Pattern p = Pattern.compile("parent.mygrid.addRow.+?\"(.+?)\"");
		Matcher matcher = p.matcher(html);

		while (matcher.find()) {
			String m = matcher.group(1);
			System.out.println(m);
			TrainInfo i = TrainInfo.valueOf(m);
			if (i != null) {
				info.add(i);
			}
		}
		return info;

	}

	public static String fetchHtml(String month, String day, String from,
			String to) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"http://dynamic.12306.cn/TrainQuery/iframeLeftTicketByStation.jsp");

		post.addHeader(
				"Accept",
				"image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
		post.addHeader("Referer",
				"http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp");
		post.addHeader("Accept-Language", "en-US");
		post.addHeader(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)");
		post.addHeader("Host", "dynamic.12306.cn");

		List<NameValuePair> values = new ArrayList<NameValuePair>();
		// 查询 00； 学生团体 0x; 农民工团体：0M
		values.add(new BasicNameValuePair("lx", "00"));
		values.add(new BasicNameValuePair("nmonth3", month));
		values.add(new BasicNameValuePair("nmonth3_new_value", "true"));
		values.add(new BasicNameValuePair("nday3", day));
		values.add(new BasicNameValuePair("nday3_new_value", "false"));
		values.add(new BasicNameValuePair("startStation_ticketLeft", from));
		values.add(new BasicNameValuePair("startStation_ticketLeft_new_value",
				"true"));
		values.add(new BasicNameValuePair("arriveStation_ticketLeft", to));
		values.add(new BasicNameValuePair("arriveStation_ticketLeft_new_value",
				"true"));
		values.add(new BasicNameValuePair("trainCode", ""));
		values.add(new BasicNameValuePair("trainCode_new_value", "true"));
		values.add(new BasicNameValuePair("rFlag", "1"));
		values.add(new BasicNameValuePair("name_ckball", "value_ckball"));
		values.add(new BasicNameValuePair("tFlagDC", "DC"));
		values.add(new BasicNameValuePair("tFlagZ", "Z"));
		values.add(new BasicNameValuePair("tFlagT", "T"));
		values.add(new BasicNameValuePair("tFlagK", "K"));
		values.add(new BasicNameValuePair("tFlagPK", "PK"));
		values.add(new BasicNameValuePair("tFlagPKE", "PKE"));
		values.add(new BasicNameValuePair("tFlagLK", "LK"));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values, "utf8");

		post.setEntity(entity);

		String response = EntityUtils
				.toString(client.execute(post).getEntity());
		return response;
	}

	public static List<TrainInfo> getInfo(String month, String day,
			String from, String to) throws IOException {
		return decodeHtml(fetchHtml(month, day, from, to));
	}

	public static void main(String[] args) throws IOException {

		for(;;){}
//		List<TrainInfo> infos = test();//getInfo("10", "06", "广州", "重庆");
//		for (TrainInfo info : infos) {
//			System.out.println(info);
//		}
		// System.out.println(info);
		// test();
	}

	private static List<TrainInfo> test() {
		List<TrainInfo> list = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("readme")));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}

			String html = sb.toString();
			list = decodeHtml(html);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

}

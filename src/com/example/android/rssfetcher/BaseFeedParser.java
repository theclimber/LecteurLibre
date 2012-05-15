package com.example.android.rssfetcher;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseFeedParser implements FeedParser {

	// names of the XML tags
	static final String CHANNEL = "channel";
	static final String PUB_DATE = "pubDate";
	static final String DESCRIPTION = "description";
	static final String CONTENT = "content:encoded";
	static final String LINK = "link";
	static final String TITLE = "title";
	static final String ITEM = "item";

	private final URL feedUrl;

	protected BaseFeedParser(String feedUrl) {
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) feedUrl
					.openConnection();
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			return in;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// try {
		// HttpURLConnection urlConnection = (HttpURLConnection)
		// feedUrl.openConnection();
		// return connection.getInputStream();
		// //return feedUrl.openConnection().getInputStream();
		// } catch (IOException e) {
		// throw new RuntimeException(e);
		// }
	}
}
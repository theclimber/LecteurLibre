/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.newsreader;

import android.os.AsyncTask;

import com.example.android.rssfetcher.AndroidSaxFeedParser;
import com.example.android.rssfetcher.Message;
import com.example.android.rssfetcher.RssHandler;



/**
 * A news category (collection of articles).
 */
public class NewsCategory extends AsyncTask<String, Void, RssHandler>{
    // how many articles?
    final int ARTICLES_PER_CATEGORY = 20;

    // array of our articles
    NewsArticle[] mArticles;
    AndroidSaxFeedParser parser;
    boolean ready = false;
    String name = "noname";
    String feed;

    /**
     * Create a news category.
     *
     * The articles are dynamically generated with fun and random nonsense.
     */
    public NewsCategory(String name, String feed) {
    	this.name = name;
    	this.feed = feed;
   		parser = new AndroidSaxFeedParser(feed);
   
        mArticles = new NewsArticle[ARTICLES_PER_CATEGORY];
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getFeed() {
    	return feed;
    }
    
    public void setFeed(String feed) {
    	this.feed = feed;
    }

    /** Returns how many articles exist in this category. */
    public int getArticleCount() {
        return mArticles.length;
    }

    /** Gets a particular article by index. */
    public NewsArticle getArticle(int index) {
    	while (this.getStatus().equals(AsyncTask.Status.RUNNING) && !ready) {
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        return mArticles[index];
    }

	@Override
	protected RssHandler doInBackground(String... arg0) {
		int i = 0;
		for (Message message : parser.parse()) {
			if (i < ARTICLES_PER_CATEGORY) {
				mArticles[i] = new NewsArticle(message);
			}
			i++;
		}
		ready = true;
		return null;
	}
}

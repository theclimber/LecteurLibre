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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Source of strange and wonderful news.
 *
 * This singleton functions as the repository for the news we display.
 */
public class NewsSource {
    // the instance
    static NewsSource instance = null;

    // the category names
    private HashMap<String,String> categories;

    // category objects, representing each category
    List<NewsCategory> mCategory;

    /** Returns the singleton instance of this class. */
    public static NewsSource getInstance() {
        if (instance == null) {
            instance = new NewsSource();
        }
        return instance;
    }

    public NewsSource() {
    	categories = new HashMap<String, String>();
        categories.put("Popular", "http://www.planet-libre.org/feed.php?type=rss&tribe_id=popular");
        categories.put("All", "http://www.planet-libre.org/feed.php?type=rss");
        mCategory = new ArrayList<NewsCategory>();
        for (String key : categories.keySet()) {
        	NewsCategory cat = new NewsCategory(key, categories.get(key));
        	cat.execute();
            mCategory.add(cat);
        }
    }

    /** Returns the list of news categories. */
    public List<String> getCategories() {
        return (List<String>) categories.keySet();
    }

    /** Returns a category by index. */
    public NewsCategory getCategory(int categ) {
    	return mCategory.get(categ);
    }
}

package io.bloc.android.blocly.api;

import java.util.ArrayList;
import java.util.List;

import io.bloc.android.blocly.BloclyApplication;
import io.bloc.android.blocly.R;
import io.bloc.android.blocly.api.model.RssFeed;
import io.bloc.android.blocly.api.model.RssItem;
import io.bloc.android.blocly.api.network.GetFeedNetworkRequest;

/**
 * Created by Daksh on 1/6/16.
 */
public class DataSource {

    private List<RssFeed> feeds;
    private List<RssItem> items;

    public DataSource() {
        feeds = new ArrayList<RssFeed>();
        items = new ArrayList<RssItem>();
        createFakeData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new GetFeedNetworkRequest("http://feeds.feedburner.com/androidcentral?format=xml").performRequest();
            }
        }).start();
    }

    public List<RssFeed> getFeeds() {
        return feeds;
    }

    public List<RssItem> getItems() {
        return items;
    }

    void createFakeData() {
        feeds.add(new RssFeed("My Favorite Feed",
                              "This feed is just incredible, I can't even begin to tell you...",
                              "http://favoritefeed.net",
                              "https://feeds.feedburner.com/favorite_feed?format=xml"));
        for (int i = 0; i < 10; i++) {
            items.add(new RssItem(String.valueOf(i),
                                  BloclyApplication.getSharedInstance().getString(R.string.placeholder_headline) + " "+ i,
                                  BloclyApplication.getSharedInstance().getString(R.string.placeholder_content),
                                  "http://favoritefeed.net?story_id=an-incredible-news-story",
                                  "http://lorempixel.com/400/200/sports/1/",
                                  0, System.currentTimeMillis(), false, false));
        }
    }
}

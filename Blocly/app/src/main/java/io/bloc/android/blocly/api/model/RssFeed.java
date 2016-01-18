package io.bloc.android.blocly.api.model;

/**
 * Created by Daksh on 1/6/16.
 */
public class RssFeed extends Model{
    private String title;
    private String description;
    private String siteUrl;
    private String feedUrl;


    public RssFeed (long rowId, String fTitle, String fDescription, String fSiteUrl, String fFeedUrl) {
        super(rowId);
        this.title = fTitle;
        this.description = fDescription;
        this.siteUrl = fSiteUrl;
        this.feedUrl = fFeedUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getFeedUrl() {
        return feedUrl;
    }
}

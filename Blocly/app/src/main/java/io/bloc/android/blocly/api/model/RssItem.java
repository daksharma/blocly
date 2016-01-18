package io.bloc.android.blocly.api.model;

/**
 * Created by Daksh on 1/6/16.
 */
public class RssItem extends Model {
    private String guId;
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private long rssFeedId;
    private long datePublished;
    private boolean favorite;
    private boolean archived;


    public RssItem (long rowId, String riGuId, String riTitle, String riDescription, String riUrl, String riImageUrl, long riRssFeedId, long riDatePublished, boolean riFavorite, boolean riArchived) {
        super(rowId);
        this.guId = riGuId;
        this.title = riTitle;
        this.description = riDescription;
        this.url = riUrl;
        this.imageUrl = riImageUrl;
        this.rssFeedId = riRssFeedId;
        this.datePublished = riDatePublished;
        this.favorite = riFavorite;
        this.archived = riArchived;
    }

    public String getGuId() {
        return guId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getRssFeedId() {
        return rssFeedId;
    }

    public long getDatePublished() {
        return datePublished;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isArchived() {
        return archived;
    }
}

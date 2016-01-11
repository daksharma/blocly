package io.bloc.android.blocly.ui.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import io.bloc.android.blocly.BloclyApplication;
import io.bloc.android.blocly.R;
import io.bloc.android.blocly.api.DataSource;
import io.bloc.android.blocly.api.model.RssFeed;
import io.bloc.android.blocly.api.model.RssItem;

/**
 * Created by Daksh on 1/6/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder> {

    private static String TAG = ItemAdapter.class.getSimpleName();

    @Override
    public ItemAdapterViewHolder onCreateViewHolder (ViewGroup viewGroup, int index) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                                     .inflate(R.layout.rss_item, viewGroup, false);
        return new ItemAdapterViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder (ItemAdapterViewHolder itemAdapterViewHolder, int index) {
        DataSource sharedDataSource = BloclyApplication.getSharedDataSource();
        itemAdapterViewHolder.update(sharedDataSource.getFeeds().get(0),
                                     sharedDataSource.getItems().get(index));
    }


    @Override
    public int getItemCount () {
        return BloclyApplication.getSharedDataSource()
                                .getItems()
                                .size();
    }


    class ItemAdapterViewHolder extends RecyclerView.ViewHolder implements ImageLoadingListener,
                                                                           View.OnClickListener,
                                                                           CompoundButton.OnCheckedChangeListener {

        TextView  title;
        TextView  feed;
        TextView  content;
        View      headerWrapper;
        ImageView headerImage;
        String    imageURL;
        RssItem   rssItem;

        CheckBox archiveCheckBox;
        CheckBox favoriteCheckBox;

        boolean contentExpand;
        View expandedContentWrapper;
        TextView expandedContent;
        Button visitSiteBtn;


        public ItemAdapterViewHolder (View itemView) {
            super(itemView);

            title = ( TextView ) itemView.findViewById(R.id.tv_rss_item_title);
            feed = ( TextView ) itemView.findViewById(R.id.tv_rss_item_feed_title);
            content = ( TextView ) itemView.findViewById(R.id.tv_rss_item_content);

            headerWrapper = itemView.findViewById(R.id.fl_rss_item_image_header);
            headerImage = ( ImageView ) headerWrapper.findViewById(R.id.iv_rss_item_image);

            archiveCheckBox = ( CheckBox ) itemView.findViewById(R.id.cb_rss_item_check_mark);
            favoriteCheckBox = (CheckBox)itemView.findViewById(R.id.cb_rss_item_favorite_star);

            expandedContentWrapper = itemView.findViewById(R.id.ll_rss_item_expanded_content_wrapper);
            expandedContent = (TextView) expandedContentWrapper.findViewById(R.id.tv_rss_item_content_full);
            visitSiteBtn = (Button) expandedContentWrapper.findViewById(R.id.visit_site_btn);

            itemView.setOnClickListener(this);
            archiveCheckBox.setOnCheckedChangeListener(this);
            favoriteCheckBox.setOnCheckedChangeListener(this);

            visitSiteBtn.setOnClickListener(this);
        }

        void update (RssFeed rssFeed, RssItem rssItem) {
            feed.setText(rssFeed.getTitle());
            title.setText(rssItem.getTitle());
            content.setText(rssItem.getDescription());
            expandedContent.setText(rssItem.getDescription());

            imageURL = rssItem.getImageUrl();

            if ( rssItem.getImageUrl() != null ) {
                headerWrapper.setVisibility(View.VISIBLE);
                headerImage.setVisibility(View.INVISIBLE);
                ImageLoader.getInstance().loadImage(imageURL, this);
            } else {
                headerWrapper.setVisibility(View.GONE);
            }
        }

        /*
         * ImageLoadingListener
         */

        @Override
        public void onLoadingStarted (String imageUri, View view) {
        }

        @Override
        public void onLoadingFailed (String imageUri, View view, FailReason failReason) {
            Log.e(TAG, "onLoadingFailed: " + failReason.toString() + " for URL: " + imageUri);
        }

        @Override
        public void onLoadingComplete (String imageUri, View view, Bitmap loadedImage) {
            // #10
            if ( imageUri.equals(rssItem.getImageUrl()) ) {
                headerImage.setImageBitmap(loadedImage);
                headerImage.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onLoadingCancelled (String imageUri, View view) {
            // Attempt a retry
            ImageLoader.getInstance().loadImage(imageUri, this);
        }

        @Override
        public void onClick (View view) {
            if (view == itemView) {
                contentExpand = !contentExpand;
                expandedContentWrapper.setVisibility(contentExpand ? View.VISIBLE : View.GONE);
                content.setVisibility(contentExpand ? View.GONE : View.VISIBLE);
            } else {
                Toast.makeText(view.getContext(), "Visit " + rssItem.getUrl(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCheckedChanged (CompoundButton compoundButton, boolean isChecked) {
            Log.v(TAG, "Checked changed to: " + isChecked);
            if (compoundButton.getId() == archiveCheckBox.getId()) {
                Log.v(TAG, "Checkbox: Archive was Clicked");
            } else {
                Log.v(TAG, "Checkbox: Favorite was Clicked");
            }
        }
    }

}
package com.grabop.novoicom.grabop_an.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.grabop.novoicom.grabop_an.R;
import com.grabop.novoicom.grabop_an.Utils;


/**
 * Created by froger_mcs on 15.12.14.
 */
public class FeedContextMenu extends LinearLayout {
    private static final int CONTEXT_MENU_WIDTH = Utils.dpToPx(240);

    private int feedItem = -1;

    private OnFeedContextMenuItemClickListener onItemClickListener;

    public FeedContextMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_context_menu, this, true);
        findViewById(R.id.btnReport).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onReportClick(feedItem);
                }

            }
        });

        findViewById(R.id.btnSharePhoto).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onSharePhotoClick(feedItem);
                }

            }
        });

        findViewById(R.id.btnCopyShareUrl).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onCopyShareUrlClick(feedItem);
                }

            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onCancelClick(feedItem);
                }

            }
        });

        setBackgroundResource(R.drawable.bg_container_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void bindToItem(int feedItem) {
        this.feedItem = feedItem;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void dismiss() {
        ((ViewGroup) getParent()).removeView(FeedContextMenu.this);
    }

//    @OnClick(R.id.btnReport)
//    public void onReportClick() {
//        if (onItemClickListener != null) {
//            onItemClickListener.onReportClick(feedItem);
//        }
//    }
//
//    @OnClick(R.id.btnSharePhoto)
//    public void onSharePhotoClick() {
//        if (onItemClickListener != null) {
//            onItemClickListener.onSharePhotoClick(feedItem);
//        }
//    }
//
//    @OnClick(R.id.btnCopyShareUrl)
//    public void onCopyShareUrlClick() {
//        if (onItemClickListener != null) {
//            onItemClickListener.onCopyShareUrlClick(feedItem);
//        }
//    }
//
//    @OnClick(R.id.btnCancel)
//    public void onCancelClick() {
//        if (onItemClickListener != null) {
//            onItemClickListener.onCancelClick(feedItem);
//        }
//    }

    public void setOnFeedMenuItemClickListener(OnFeedContextMenuItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnFeedContextMenuItemClickListener {
        public void onReportClick(int feedItem);

        public void onSharePhotoClick(int feedItem);

        public void onCopyShareUrlClick(int feedItem);

        public void onCancelClick(int feedItem);
    }
}
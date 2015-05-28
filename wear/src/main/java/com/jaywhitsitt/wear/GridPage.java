package com.jaywhitsitt.wear;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.ArrayList;

/**
 * Created by jay on 5/28/15.
 */
public class GridPage  {
    private String mTitle;
    private String mText;
    private int mIcon;
    private int mBackground;
    /**
     * Constructor for the GridPage
     * @param mTitle
     *          Title for the card
     * @param mText
     *          Text for the card
     * @param mIcon
     *          Icon that will be on the right of the title
     * @param mBackground
     *          The Background image to be used by the fragment. The card will overlay on top of the background.
     */
    public GridPage(String mTitle, String mText, int mIcon, int mBackground) {
        this.mTitle = mTitle;
        this.mText = mText;
        this.mIcon = mIcon;
        this.mBackground = mBackground;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getText() {
        return mText;
    }
    public int getIcon() {
        return mIcon;
    }
    public int getBackground() {
        return mBackground;
    }


}

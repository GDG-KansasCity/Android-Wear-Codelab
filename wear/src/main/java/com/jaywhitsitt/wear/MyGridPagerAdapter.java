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
public class MyGridPagerAdapter extends FragmentGridPagerAdapter {

    private Context mContext;
    private ArrayList<GridRow> mRows;

    public MyGridPagerAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
        initAdapter();
    }
    /**
     * This method is used for demonstration only. In a real app the data and the adapters would
     * probably come from somewhere else.
     */
    private void initAdapter() {
        mRows = new ArrayList<GridRow>();
        GridRow row1 = new GridRow();
        row1.addPage(new GridPage("Pink Flower", "Wow! Such flower! Much smells!", R.drawable.ic_info, R.drawable.flower1));
        row1.addPage(new GridPage("Flower?", "Much Pretty! Such nature!", R.drawable.ic_info, R.drawable.flower2));
        row1.addPage(new GridPage("Red Flower!", "Yes! I know nothing about flowers!", R.drawable.ic_info, R.drawable.flower3));
        GridRow row2 = new GridRow();
        row2.addPage(new GridPage("Flowers!", "This is row 2!!!", R.drawable.ic_info, R.drawable.flower4));
        row2.addPage(new GridPage("Row 2 Col 2..", "ZzzzZzZZzzZZZz", R.drawable.ic_info, R.drawable.flower1));
        GridRow row3 = new GridRow();
        row3.addPage(new GridPage("THIS IS SPA... ops Row 3!", "Yes rows can have different sizes.", R.drawable.ic_info, R.drawable.flower3));
        mRows.add(row1);
        mRows.add(row2);
        mRows.add(row3);
    }

    @Override
    public Fragment getFragment(int row, int col) {
        GridPage page = mRows.get(row).getPage(col);
        CardFragment cardFragment = CardFragment.create(page.getTitle(), page.getText(), page.getIcon());
        return cardFragment;
    }

    /**
     * This method is Overridden so we can set a Custom Background for each element.
     * @param row
     * @param column
     * @return
     */
    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        GridPage page = mRows.get(row).getPage(column);
        return this.mContext.getResources().getDrawable(page.getBackground());
    }

    @Override
    public int getRowCount() {
        return mRows.size();
    }

    @Override
    public int getColumnCount(int row) {
        return mRows.get(row).getSize();
    }
}

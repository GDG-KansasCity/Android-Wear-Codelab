package com.jaywhitsitt.wear;

import java.util.ArrayList;

/**
 * Created by jay on 5/28/15.
 */
public class GridRow  {
    ArrayList<GridPage> mPages = new ArrayList<GridPage>();
    public GridRow() {}
    public GridRow(ArrayList<GridPage> mPages) {
        this.mPages = mPages;
    }
    public GridPage getPage(int index) {
        return mPages.get(index);
    }
    public void addPage(GridPage mPage) {
        mPages.add(mPage);
    }
    public int getSize() {
        return mPages.size();
    }
    public ArrayList<GridPage> getPagesArray() {
        return mPages;
    }
    public void setPages(ArrayList<GridPage> mPages) {
        this.mPages = mPages;
    }
}

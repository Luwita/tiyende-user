package com.shuttleclone.userapp.model;

import com.shuttleclone.userapp.utils.menu.AbstractItem;

import java.util.List;

public class EmptyItem extends AbstractItem {

    /*constructor*/

    public EmptyItem(List<SeatModel> aSeatModelList) {
        super(aSeatModelList);
    }

    /*getter*/
    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}

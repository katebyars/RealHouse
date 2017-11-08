package com.example.guest.realhouse.util;


public interface ItemTouchHelperAdapter {


    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}

package com.example.recyclermutiviewex;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);


    void onItemDismiss(int position);
}
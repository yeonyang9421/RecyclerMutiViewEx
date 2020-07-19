package com.example.recyclermutiviewex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MutiViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    private static final int LAYOUT_ONE = 0;
    private static final int LAYOUT_TWO = 1;


    List<Movie> mItem = new ArrayList<>();
    private final OnStartDragListener mDragStartListener;

    public MutiViewAdapter(List<Movie> items, Context context, OnStartDragListener dragStartListener) {
        mItem = items;
        mDragStartListener = dragStartListener;
    }

    public void addItem(Movie movie, int index) {
        mItem.add(index, movie);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItem, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mItem.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return LAYOUT_ONE;
        else
            return LAYOUT_TWO;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == LAYOUT_ONE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_moive, parent, false);
            viewHolder = new ViewHolderOne(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second, parent, false);
            viewHolder = new ViewHolderTwo(view);
        }


//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mShowListener != null) {
//                    Movie movie = mItem.get(viewHolder.getAdapterPosition());
//                    mShowListener.onShowTitleClick(viewHolder.getAdapterPosition());
//                }
//            }
//        });
//
//
//        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                mShowListener.onMyLongClick(viewHolder.getAdapterPosition());
//                return true;
//            }
//
//        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Movie mData = mItem.get(i);
        if (mData != null) {
            if (viewHolder.getItemViewType() == LAYOUT_ONE) {
                ViewHolderOne viewHolderOne = (ViewHolderOne) viewHolder;
                viewHolderOne.mImage.setImageResource(mData.getImage());
                viewHolderOne.mTextTitle.setText(mData.getTitle());
                viewHolderOne.mTextContent.setText(mData.getContent());

            } else {
                ViewHolderTwo viewHolderTwo = (ViewHolderTwo) viewHolder;
                Glide.with(viewHolderTwo.imageBig)
                        .load(mData.getImage())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(viewHolderTwo.imageBig);
//                viewHolderTwo.imageBig.setImageResource(mData.getImage());
                viewHolderTwo.textName.setText(mData.getTitle());
                viewHolderTwo.textId.setText(mData.getContent());
            }
        }
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) ==
                        MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTextTitle;
        private TextView mTextContent;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_movie);
            mTextTitle = itemView.findViewById(R.id.text_title);
            mTextContent = itemView.findViewById(R.id.text_content);

        }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder {
        private ImageView imageBig;
        private TextView textName;
        private TextView textId;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            imageBig = itemView.findViewById(R.id.image_big);
            textName = itemView.findViewById(R.id.text_name);
            textId = itemView.findViewById(R.id.text_id);

        }
    }


}

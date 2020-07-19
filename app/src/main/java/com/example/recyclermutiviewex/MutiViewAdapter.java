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
        if (position == 0 || position ==1 || position==2 )
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
                if(viewHolder.getAdapterPosition()==0 || viewHolder.getAdapterPosition()==2){
                    ViewHolderOne viewHolderOne = (ViewHolderOne) viewHolder;
                    viewHolderOne.mImage1.setImageResource(mData.getImage());
                    viewHolderOne.mTextTitle1.setText(mData.getTitle());
                    viewHolderOne.mTextContent1.setText(mData.getContent());
//                    viewHolderOne.mImage1.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            if (MotionEventCompat.getActionMasked(event) ==
//                                    MotionEvent.ACTION_DOWN) {
//                                mDragStartListener.onStartDrag(viewHolder);
//                            }
//                            return false;
//                        }
//                    });
                }else if(viewHolder.getAdapterPosition()==1){
                    ViewHolderOne viewHolderOne = (ViewHolderOne) viewHolder;
                    viewHolderOne.mImage2.setImageResource(mData.getImage());
                    viewHolderOne.mTextTitle2.setText(mData.getTitle());
                    viewHolderOne.mTextContent2.setText(mData.getContent());
//                    viewHolderOne.mImage2.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            if (MotionEventCompat.getActionMasked(event) ==
//                                    MotionEvent.ACTION_DOWN) {
//                                mDragStartListener.onStartDrag(viewHolder);
//                            }
//                            return false;
//                        }
//                    });
                }


            } else {
                ViewHolderTwo viewHolderTwo = (ViewHolderTwo) viewHolder;
                Glide.with(viewHolderTwo.mImage)
                        .load(mData.getImage())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(viewHolderTwo.mImage);
//                viewHolderTwo.imageBig.setImageResource(mData.getImage());
                viewHolderTwo.textName.setText(mData.getTitle());
                viewHolderTwo.textId.setText(mData.getContent());
//                viewHolderTwo.mImage.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (MotionEventCompat.getActionMasked(event) ==
//                                MotionEvent.ACTION_DOWN) {
//                            mDragStartListener.onStartDrag(viewHolder);
//                        }
//                        return false;
//                    }
//                });
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
        private ImageView mImage1,mImage2;
        private TextView mTextTitle1, mTextTitle2;
        private TextView mTextContent1,mTextContent2;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            mImage1 = itemView.findViewById(R.id.image_movie1);
            mImage2 = itemView.findViewById(R.id.image_movie2);
            mTextTitle1 = itemView.findViewById(R.id.text_title1);
            mTextTitle2 = itemView.findViewById(R.id.text_title2);
            mTextContent1 = itemView.findViewById(R.id.text_content1);
            mTextContent2 = itemView.findViewById(R.id.text_content2);

        }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView textName;
        private TextView textId;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_movie);
            textName = itemView.findViewById(R.id.text_name);
            textId = itemView.findViewById(R.id.text_id);

        }
    }


}

package com.example.recyclermutiviewex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnStartDragListener{

    RecyclerView mRecyclerView;
    MutiViewAdapter mAdapter;
    List<Movie> mMovieList = new ArrayList<>();
    private ItemTouchHelper mItemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList.add(new Movie(R.drawable.image22, "1공조", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image33, "2더킹", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image44, "3레지던트이블", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image55, "4럭키", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image66, "5아수라", " 재밌는 내용"));

        mMovieList.add(new Movie(R.drawable.image22, "6공조", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image33, "7더킹", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image44, "8레지던트이블", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image55, "9럭키", " 재밌는 내용"));
        mMovieList.add(new Movie(R.drawable.image66, "10아수라", " 재밌는 내용"));

        mRecyclerView=findViewById(R.id.recycler);
        mAdapter=new MutiViewAdapter(mMovieList, this, this );
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setOnShowTitleClickListener(this);
//        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        mRecyclerView.setLayoutManager(layoutManager);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }


/*    @Override
    public void onShowTitleClick(int position) {
        if(position==1){
//            Intent intent=new Intent(this, SecondActivity.class);
//            startActivity(intent);
        }
        Toast.makeText(this, position+"번재 아이템 선택", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMyLongClick(int position) {
        mMovieList.remove(position);
        mAdapter.notifyDataSetChanged();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add:
                mAdapter.addItem(new Movie(R.drawable.image22 , "공조" , "별루"),0);
                mAdapter.notifyDataSetChanged();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
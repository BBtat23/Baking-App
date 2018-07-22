package com.example.bea.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bea.bakingapp.R;
import com.example.bea.bakingapp.data.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> mRecipeData;
    final private ListItemClickListener mOnclicklistener;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public RecipeAdapter(List listRecipe, ListItemClickListener listener){
        this.mRecipeData = listRecipe;
        this.mOnclicklistener = listener;
    }
    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.list_item,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        holder.nameRecipe.setText(mRecipeData.get(position).getName());
        holder.mImageRecipe.setImageResource(R.mipmap.ic_launcher_foreground);
//        String videoURL = mStepsData.get(position).getVideoURL();

//        Picasso.get().load("")
    }

    @Override
    public int getItemCount() {
        return mRecipeData.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameRecipe;
        ImageView mImageRecipe;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            nameRecipe = itemView.findViewById(R.id.titleRecipe);
            mImageRecipe = itemView.findViewById(R.id.imageRecipe);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnclicklistener.onListItemClick(clickedPosition);
        }
    }


    public void swapData(List<Recipe> movieObjectArrayList) {
        if (movieObjectArrayList == null || movieObjectArrayList.size() == 0)
            return;
        if (mRecipeData != null && mRecipeData.size() > 0)
            mRecipeData.clear();
        mRecipeData.addAll(movieObjectArrayList);
        notifyDataSetChanged();
    }
}

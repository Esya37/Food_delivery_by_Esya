package com.example.fooddeliverybyesya;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverybyesya.Models.Category;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Category> categories;
    private ItemClickListener clickListener;

    private Category category;
    private int selectedCategoryPosition;

    public RecyclerViewAdapter(Context context, List<Category> list) {
        this.categories = list;
        this.inflater = LayoutInflater.from(context);
        selectedCategoryPosition = 0;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        category = categories.get(position);
        holder.categoryTextView.setText(category.getStrCategory());
        if (position == selectedCategoryPosition) {
            holder.underscoreCategory.setBackgroundResource(R.drawable.underscore_recycler_view_item_selected);
            holder.categoryTextView.setTextColor(Color.parseColor("#FA4A0C"));
        } else {
            holder.underscoreCategory.setBackgroundResource(R.drawable.underscore_recycler_view_item);
            holder.categoryTextView.setTextColor(Color.parseColor("#9A9A9D"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(v, position);
                }
                selectedCategoryPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView categoryTextView;
        View underscoreCategory;

        ViewHolder(View view) {
            super(view);
            categoryTextView = view.findViewById(R.id.categoryTextView);
            underscoreCategory = view.findViewById(R.id.underscoreCategory);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getLayoutPosition());
            }
        }
    }


}

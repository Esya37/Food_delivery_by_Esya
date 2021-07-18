package com.example.fooddeliverybyesya;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverybyesya.Models.SearchResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class RecyclerViewAdapterSearchResult extends RecyclerView.Adapter<RecyclerViewAdapterSearchResult.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<SearchResult> searchResults;
    private RecyclerViewAdapter.ItemClickListener clickListener;

    private SearchResult searchResult;

    public RecyclerViewAdapterSearchResult(Context context, List<SearchResult> list) {
        this.searchResults = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewAdapterSearchResult.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item_search, parent, false);
        return new RecyclerViewAdapterSearchResult.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterSearchResult.ViewHolder holder, int position) {
        searchResult = searchResults.get(position);
        holder.categoryNameTextView.setText(searchResult.getStrCategory());
        holder.mealNameTextView.setText(searchResult.getStrMeal());
        Picasso.with(holder.mealImageView.getContext()).load(searchResult.getStrMealThumb()).transform(new Transformation() {

            private final int mRadius = (int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.25);
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
                        Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);

                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());

                paint.setAntiAlias(true);
                paint.setFilterBitmap(true);
                paint.setDither(true);

                canvas.drawARGB(0, 0, 0, 0);

                paint.setColor(Color.parseColor("#BAB399"));

                if (mRadius == 0) {
                    canvas.drawCircle(source.getWidth() / 2f + 0.7f, source.getHeight() / 2f + 0.7f,
                            source.getWidth() / 2f - 1.1f, paint);
                } else {
                    canvas.drawCircle(source.getWidth() / 2f + 0.7f, source.getHeight() / 2f + 0.7f,
                            mRadius, paint);
                }

                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

                canvas.drawBitmap(source, rect, rect, paint);

                if (source != output) {
                    source.recycle();
                }
                return output;
            }

            @Override
            public String key() {
                return "circular" + mRadius;
            }
        }).into(holder.mealImageView);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryNameTextView;
        TextView mealNameTextView;
        ImageView mealImageView;

        ViewHolder(View view) {
            super(view);
            categoryNameTextView = view.findViewById(R.id.categoryNameTextView);
            mealNameTextView = view.findViewById(R.id.mealNameTextView);
            mealImageView = view.findViewById(R.id.mealImageView);
        }

    }

}

package com.example.tis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tis.R;

import com.example.tis.entity.SubCategory;

import java.util.List;

public class SubCategoryListAdapter extends ArrayAdapter<SubCategory> {

    private List<SubCategory> categoryList;
    private Context mContext;
    private int resource;

    public SubCategoryListAdapter(Context mContext, int resource, List<SubCategory> categoryList) {
        super(mContext, resource, categoryList);
        this.mContext = mContext;
        this.resource = resource;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)  {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(resource, null, false);

        LinearLayout listItem = view.findViewById(R.id.categorylistItem);
        TextView listTitle = view.findViewById(R.id.title_category);

        final SubCategory category = categoryList.get(position);
        listTitle.setText(category.getTitle());
        return view;

    }
}

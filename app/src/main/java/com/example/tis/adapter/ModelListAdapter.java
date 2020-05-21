package com.example.tis.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tis.R;
import com.example.tis.entity.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ModelListAdapter extends ArrayAdapter<Model> {

    private List<Model> modelList;
    private Context mContext;
    private int resource;
    private String document;
    private final String PATH_IMAGE = "img/models/";

    public ModelListAdapter(Context mContext, int resource, List<Model> modelList) {
        super(mContext, resource, modelList);
        this.mContext = mContext;
        this.resource = resource;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)  {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(resource, null, false);

        LinearLayout listItem = view.findViewById(R.id.listItem);
        ImageView listImage = view.findViewById(R.id.image_model);
        TextView listTitle = view.findViewById(R.id.title_model);

        final Model model = modelList.get(position);

        String fileName = PATH_IMAGE + model.getImage();

        try {
            listImage.setImageDrawable(getDrawable(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        listTitle.setText(model.getId() + " (" + model.getSeries() + " Series)");
        return view;
    }

    private Drawable getDrawable(String name) throws IOException {
        Drawable d;
        InputStream ims = mContext.getAssets().open(name);
        // загружаем как Drawable
        d = Drawable.createFromStream(ims, null);

        return d;
    }

}


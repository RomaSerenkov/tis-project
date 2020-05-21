package com.example.tis.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tis.CategoryActivity;
import com.example.tis.ModelsActivity;
import com.example.tis.NextSubCategoryActivity;
import com.example.tis.R;
import com.example.tis.WebActivity;
import com.example.tis.entity.Doc;
import com.example.tis.entity.Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DocListAdapter extends ArrayAdapter<Doc> {
    private List<Doc> docList;
    private Context mContext;
    private int resource;
    private String mountPath;
    private final String PATH_IMAGE = File.separator + "img/";

    public DocListAdapter(Context mContext, int resource, List<Doc> docList, String mountPath) {
        super(mContext, resource, docList);
        this.mContext = mContext;
        this.resource = resource;
        this.docList = docList;
        this.mountPath = mountPath;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)  {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(resource, null, false);

        LinearLayout listItem = view.findViewById(R.id.webListItem);
        ImageView listImage = view.findViewById(R.id.image_web);
        WebView webView = view.findViewById(R.id.web);

        final Doc doc = docList.get(position);

        String filePath = mountPath + PATH_IMAGE + doc.getImage();

        File expPath = new File(filePath);
        if(expPath.exists()){
            Toast.makeText(mContext, expPath.getAbsolutePath(), Toast.LENGTH_LONG).show();
            Bitmap myBitmap = BitmapFactory.decodeFile(expPath.getAbsolutePath());
            Bitmap resized = Bitmap.createScaledBitmap(myBitmap,(int)(myBitmap.getWidth()*1.8), (int)(myBitmap.getHeight()*1.8), true);
            listImage.setImageBitmap(resized);
        }

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(doc.getHtml(), "text/html", "UTF-8");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                Toast.makeText(mContext, urlx, Toast.LENGTH_LONG).show();
                return false;
            };
        });


        return view;
    }
}

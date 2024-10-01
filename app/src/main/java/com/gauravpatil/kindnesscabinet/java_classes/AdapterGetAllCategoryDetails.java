package com.gauravpatil.kindnesscabinet.java_classes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gauravpatil.kindnesscabinet.R;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterGetAllCategoryDetails  extends BaseAdapter
{
    // BaseAdapter => multiple view load show
    // AdapterGetAllCategoryDetails => show multiple view collect show ListView

    List<POJOGetAllCategoryDetails> pojoGetAllCategoryDetailsList;
    Activity activity;

    public AdapterGetAllCategoryDetails(List<POJOGetAllCategoryDetails> pojoGetAllCategoryDetailsList, Activity activity)
    {
        this.pojoGetAllCategoryDetailsList = pojoGetAllCategoryDetailsList;
        this.activity = activity;
    }

    @Override
    public int getCount()
    {
        return pojoGetAllCategoryDetailsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return pojoGetAllCategoryDetailsList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
       final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(view == null)
        {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.lv_get_all_category,null);
            holder.ivCategoryImage = view.findViewById(R.id.ivCategoryImage);
            holder.tvCategoryName = view.findViewById(R.id.tvCategoryName);

            view.setTag(holder);
        }
        else
        {
            holder =(ViewHolder) view.getTag();
        }

        final POJOGetAllCategoryDetails obj = pojoGetAllCategoryDetailsList.get(position);
        holder.tvCategoryName.setText(obj.getGetCategoryname());

        Glide.with(activity)
                .load(Urls.image +obj.getCategoryimage())
                .skipMemoryCache(true)
                .error(R.drawable.image_not_found)
                .into(holder.ivCategoryImage);

        return view;
    }
    class  ViewHolder
    {
        ImageView ivCategoryImage;
        TextView tvCategoryName;
    }
}

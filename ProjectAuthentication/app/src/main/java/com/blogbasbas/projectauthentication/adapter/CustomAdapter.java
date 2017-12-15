package com.blogbasbas.projectauthentication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.blogbasbas.projectauthentication.R;
import com.blogbasbas.projectauthentication.client.MyHolder;
import com.blogbasbas.projectauthentication.client.PicassoClient;
import com.blogbasbas.projectauthentication.model.Hewan;

import java.util.ArrayList;

/**
 * Created by Blackswan on 7/19/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Hewan> hewanArrayList;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Hewan> hewanArrayList) {

        this.c = c;
        this.hewanArrayList = hewanArrayList;
    }


    @Override
    public int getCount() {
        return hewanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return hewanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }if(convertView==null){
            convertView=inflater.inflate(R.layout.listview_layout,parent,false);
        }
        MyHolder holder = new MyHolder(convertView);
        holder.nameText.setText(hewanArrayList.get(position).getName());
        holder.infoText.setText(hewanArrayList.get(position).getInfo());
        PicassoClient.downloading(c,hewanArrayList.get(position).getUrl(),holder.img);

        return convertView;
    }
}

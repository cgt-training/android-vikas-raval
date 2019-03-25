package com.vikas.myproject1.Item_Details;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.R;
import com.vikas.myproject1.Item_Details.Item_Detail;

import java.util.ArrayList;

public class CustomItemAdapter extends ArrayAdapter<CustomObjectClass> implements View.OnClickListener{

    private ArrayList<CustomObjectClass> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtQty;
        TextView txtSiz;
        ImageView info;
    }

    public CustomItemAdapter(ArrayList<CustomObjectClass> data, Context context) {
        super(context, R.layout.my_oder_page_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CustomObjectClass dataModel=(CustomObjectClass)object;
        Toast.makeText(mContext,"Row Clicked",Toast.LENGTH_LONG).show();

    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomObjectClass dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.my_oder_page_list, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.iname);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.total_pric);
            viewHolder.txtQty = (TextView) convertView.findViewById(R.id.tqty);
            viewHolder.txtSiz = (TextView) convertView.findViewById(R.id.tsiz);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.iv);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getmName());
        viewHolder.txtPrice.setText(dataModel.getmPrice());
        viewHolder.txtQty.setText(dataModel.getmQty());
        viewHolder.txtSiz.setText(dataModel.getmSiz());
//        viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setImageBitmap(dataModel.getmImageDrawable());

        // Return the completed view to render on screen
        return convertView;
    }


}

package com.vikas.myproject1.Add_Item_Page;


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

public class CustomAddItemAdapter extends ArrayAdapter<CustomItemObjectClass> implements View.OnClickListener{

    private ArrayList<CustomItemObjectClass> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtPrice;
        Button btnDetail;
        ImageView info;
    }

    public CustomAddItemAdapter(ArrayList<CustomItemObjectClass> data, Context context) {
        super(context, R.layout.home_list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CustomItemObjectClass dataModel=(CustomItemObjectClass)object;
        Toast.makeText(mContext,"Row Clicked",Toast.LENGTH_LONG).show();

    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomItemObjectClass dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.home_list_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.i_n);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.i_p);
            viewHolder.btnDetail = (Button) convertView.findViewById(R.id.btnv);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.iv1);

            viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext,"Button Clicked"+position,Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(mContext,Item_Detail.class);
                    mContext.startActivity(intent);
                }
            });
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
//        viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setImageBitmap(dataModel.getmImageDrawable());

        // Return the completed view to render on screen
        return convertView;
    }


}



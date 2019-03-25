package com.vikas.myproject1.View_Order_Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.Item_Details.CustomObjectClass;
import com.vikas.myproject1.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CustomClass> implements View.OnClickListener{

    private ArrayList<CustomClass> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtQty;
        TextView txtSiz;
        ImageView info;
    }

    public CustomAdapter(ArrayList<CustomClass> data, Context context) {
        super(context, R.layout.my_oder_page_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CustomClass dataModel=(CustomClass)object;
        Toast.makeText(mContext,"Row Clicked",Toast.LENGTH_LONG).show();

    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomClass dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.view_order_list, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.iname);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.total_pric);
            viewHolder.txtQty = (TextView) convertView.findViewById(R.id.t_qty);
            viewHolder.txtSiz = (TextView) convertView.findViewById(R.id.t_s);
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

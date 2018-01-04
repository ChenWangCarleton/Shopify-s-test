package com.example.shopify;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;


public class CustomListview extends ArrayAdapter<Product> implements Filterable {
 Activity context;
 ArrayList<Product> products;
ArrayList<Product> forFilter;
private CustomeFilter customeFilter;

    public CustomeFilter getCustomeFilter() {
        if(customeFilter==null){
            customeFilter=new CustomeFilter();
        }
        return customeFilter;
    }

    public CustomListview(Activity context, ArrayList<Product> pro) {
        super(context, R.layout.layout,pro );

       this.context=context;
       this.products= pro;
       this.forFilter=pro;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
       ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout,null,true);
           viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
           viewHolder=(ViewHolder)r.getTag();
        }
        System.out.println("Position:   "+position);
        ImageView ivw=(ImageView) r.findViewById(R.id.imageView);

        Picasso.with(getContext()).load(products.get(position).getImg()).into(viewHolder.ivw);
           viewHolder.tvw1.setText(products.get(position).getTitle());
           viewHolder.tvw2.setText(products.get(position).getTags());

        return r;



    }

    class CustomeFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            FilterResults results=new FilterResults();
            if(constraint!=null&&constraint.length()>0){
                constraint=constraint.toString().toLowerCase();
                ArrayList<Product> toPresent=new ArrayList<>();
                for(int x=0;x<forFilter.size();x++){
                    if(forFilter.get(x).getTitle().toLowerCase().contains(constraint)){
                        Product p=new Product(forFilter.get(x));
                        toPresent.add(p);
                    }
                }
                results.count=toPresent.size();
                results.values=toPresent;
                System.out.println("Size:"+toPresent.size());
            }
            else{
                System.out.println("query not found:  "+constraint.toString());
                results.values=forFilter;
            }
            return results;
        }
        @Override
        protected  void publishResults(CharSequence constraint, FilterResults results){
            products=(ArrayList<Product>)results.values;
            notifyDataSetChanged();
        }


    }
    @Override
    public int getCount() {



        return products.size();
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.title);
            tvw2=(TextView)v.findViewById(R.id.description);
            ivw=(ImageView) v.findViewById(R.id.imageView);
        }
    }
}

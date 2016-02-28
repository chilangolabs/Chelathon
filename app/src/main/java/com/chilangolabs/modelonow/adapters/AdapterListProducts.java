package com.chilangolabs.modelonow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilangolabs.modelonow.Entitys.ItemProducto;
import com.like.LikeButton;

import java.util.List;

/**
 * Created by gorro on 27/02/16.
 */
public class AdapterListProducts extends RecyclerView.Adapter<AdapterListProducts.ListProductosHolder> {

    List<ItemProducto> itemProductos;
    Context context;

    public AdapterListProducts(List<ItemProducto> itemProductos, Context context) {
        this.itemProductos = itemProductos;
        this.context = context;
    }

    @Override
    public ListProductosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListProductosHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ListProductosHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtDescrip, txtPrice;
        ImageView imgProduct;
        LikeButton likeButton;

        public ListProductosHolder(View itemView) {
            super(itemView);
        }
    }

}

package com.chilangolabs.modelonow.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chilangolabs.modelonow.Entitys.ItemProducto;
import com.chilangolabs.modelonow.R;
import com.chilangolabs.modelonow.customwidgets.TextViewMontserrat;
import com.like.LikeButton;

import java.util.List;

/**
 * Created by gorro on 27/02/16.
 */
public class AdapterListProducts extends RecyclerView.Adapter<AdapterListProducts.ListProductosHolder> {

    List<ItemProducto> itemProductos;
    Context context;
    MaterialDialog dialog;

    public AdapterListProducts(Context context, List<ItemProducto> itemProductos) {
        this.itemProductos = itemProductos;
        this.context = context;
    }

    @Override
    public ListProductosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        return new ListProductosHolder(v);
    }

    @Override
    public void onBindViewHolder(ListProductosHolder holder, int position) {
        holder.txtName.setText(itemProductos.get(position).getNameBeer());
        holder.txtDescrip.setText(itemProductos.get(position).getDetailBeerPack());
        holder.txtPrice.setText(itemProductos.get(position).getPrice());
        holder.likeButton.setLiked(itemProductos.get(position).isFavorite());
        holder.imgProduct.setImageResource(itemProductos.get(position).getImgBeer());
        dialog = new MaterialDialog.Builder(context)
                .customView(R.layout.content_dialog_product, true)
                .positiveText("Añadir al carrito")
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .negativeText("Cancelar")
                .build();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        Button btnMinus = (Button) dialog.findViewById(R.id.dialogBtnMinus);
        Button btnPlus = (Button) dialog.findViewById(R.id.dialogBtnPlus);
        TextViewMontserrat txtName = (TextViewMontserrat) dialog.findViewById(R.id.dialogTxtName);
        final TextViewMontserrat txtNumerPack = (TextViewMontserrat) dialog.findViewById(R.id.dialogTxtNumber);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(txtNumerPack.getText().toString());
                number++;
                txtNumerPack.setText(number + "");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(txtNumerPack.getText().toString());
                if (number > 0) {
                    number--;
                    txtNumerPack.setText(number + "");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemProductos.size();
    }

    public static class ListProductosHolder extends RecyclerView.ViewHolder {

        TextViewMontserrat txtName, txtDescrip, txtPrice;
        ImageView imgProduct;
        LikeButton likeButton;
        CardView cardView;

        public ListProductosHolder(View itemView) {
            super(itemView);
            txtName = (TextViewMontserrat) itemView.findViewById(R.id.itemTxtName);
            txtDescrip = (TextViewMontserrat) itemView.findViewById(R.id.itemTxtDescrip);
            txtPrice = (TextViewMontserrat) itemView.findViewById(R.id.itemTxtPrice);
            imgProduct = (ImageView) itemView.findViewById(R.id.itemImgProduct);
            likeButton = (LikeButton) itemView.findViewById(R.id.itemBtnHeart);
            cardView = (CardView) itemView.findViewById(R.id.itemCardProduct);
        }
    }

}

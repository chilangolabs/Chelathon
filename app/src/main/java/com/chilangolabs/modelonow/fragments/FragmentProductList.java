package com.chilangolabs.modelonow.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilangolabs.modelonow.Entitys.ItemProducto;
import com.chilangolabs.modelonow.R;
import com.chilangolabs.modelonow.adapters.AdapterListProducts;

import java.util.ArrayList;
import java.util.List;

public class FragmentProductList extends Fragment {

    RecyclerView recyclerView;
    AdapterListProducts adapterListProducts;

    public FragmentProductList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_product_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rcProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        adapterListProducts = new AdapterListProducts(getActivity(), getProducts());
        recyclerView.setAdapter(adapterListProducts);
        return rootView;
    }

    private List<ItemProducto> getProducts() {
        List<ItemProducto> itemProductos = new ArrayList<>();
        itemProductos.add(new ItemProducto("Stella Artois", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.beer_stella, true));
        itemProductos.add(new ItemProducto("Bud Light", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.botait, true));
        itemProductos.add(new ItemProducto("Budweiser", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.butweiser, false));
        itemProductos.add(new ItemProducto("Modelo", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.modelo, false));
        itemProductos.add(new ItemProducto("Stella Artois", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.beer_stella, false));
        itemProductos.add(new ItemProducto("Bud Light", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.botait, false));
        itemProductos.add(new ItemProducto("Budweiser", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.butweiser, false));
        itemProductos.add(new ItemProducto("Modelo", "12 Pack • Botella importada 12 Oz", "$140.00", R.mipmap.modelo, false));
        return itemProductos;
    }

}

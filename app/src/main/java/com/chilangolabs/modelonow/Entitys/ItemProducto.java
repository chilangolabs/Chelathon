package com.chilangolabs.modelonow.Entitys;

/**
 * Created by gorro on 27/02/16.
 */
public class ItemProducto {

    String nameBeer, detailBeerPack, price;
    int imgBeer;
    boolean favorite;

    public ItemProducto(String nameBeer, String detailBeerPack, String price, int imgBeer, boolean favorite) {
        this.nameBeer = nameBeer;
        this.detailBeerPack = detailBeerPack;
        this.price = price;
        this.imgBeer = imgBeer;
        this.favorite = favorite;
    }

    public String getNameBeer() {
        return nameBeer;
    }

    public void setNameBeer(String nameBeer) {
        this.nameBeer = nameBeer;
    }

    public String getDetailBeerPack() {
        return detailBeerPack;
    }

    public void setDetailBeerPack(String detailBeerPack) {
        this.detailBeerPack = detailBeerPack;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImgBeer() {
        return imgBeer;
    }

    public void setImgBeer(int imgBeer) {
        this.imgBeer = imgBeer;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

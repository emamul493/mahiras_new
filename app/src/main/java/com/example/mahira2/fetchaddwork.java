package com.example.mahira2;

public class fetchaddwork {
    String  barcode , item,color,size;

    public fetchaddwork() {
    }

    public fetchaddwork(String barcode, String item, String color, String size) {
        this.barcode = barcode;
        this.item = item;
        this.color = color;
        this.size = size;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

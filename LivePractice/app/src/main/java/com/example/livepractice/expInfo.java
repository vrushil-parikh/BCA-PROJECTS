package com.example.livepractice;

public class expInfo {
    String particular;
    int amount;

    public expInfo(String particular, int amount) {
        this.particular = particular;
        this.amount = amount;
    }
    expInfo (){

    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

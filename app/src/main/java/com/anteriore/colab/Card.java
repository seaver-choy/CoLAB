package com.anteriore.colab;

public class Card {
    private String cardName;
    private int cardImage;

    public Card() {
    }

    public Card(String cardName, int cardImage) {
        this.cardName = cardName;
        this.cardImage = cardImage;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardImage() {
        return cardImage;
    }

    public void setCardImage(int cardImage) {
        this.cardImage = cardImage;
    }
}

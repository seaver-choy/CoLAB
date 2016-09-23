package com.anteriore.colab.Model;

/**
 * Created by Seaver on 9/12/2016.
 */
public class Like extends Interest{

    public static String FirebaseChildName = "like";

    public Like(String interestID, String interestName, String interestImage) {
        super(interestID, interestName, interestImage);
    }

    public enum LikesTypes{
        Organizations, Restaurants, Athletes, Artists
    }


}

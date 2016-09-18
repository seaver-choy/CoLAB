package com.anteriore.colab;

public class ProfileConnection {

    private String connectionName;
    private String connectionCount;
    private String commonInterestCount;
    private int connectionImageURL;

    public ProfileConnection(String connectionName, String connectionCount, String commonInterestCount, int connectionImageURL) {
        this.connectionName = connectionName;
        this.connectionCount = connectionCount;
        this.commonInterestCount = commonInterestCount;
        this.connectionImageURL = connectionImageURL;
    }

    public ProfileConnection(){
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(String connectionCount) {
        this.connectionCount = connectionCount;
    }

    public String getCommonInterestCount() {
        return commonInterestCount;
    }

    public void setCommonInterestCount(String commonInterestCount) {
        this.commonInterestCount = commonInterestCount;
    }

    public int getConnectionImageURL() {
        return connectionImageURL;
    }

    public void setConnectionImageURL(int connectionImageURL) {
        this.connectionImageURL = connectionImageURL;
    }
}

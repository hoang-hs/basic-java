package src.book.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class tokenResource {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("at_expires_in")
    private int atExpiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("rf_expires_in")
    private int rfExpiresIn;

    public tokenResource() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getAtExpiresIn() {
        return atExpiresIn;
    }

    public void setAtExpiresIn(int atExpiresIn) {
        this.atExpiresIn = atExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getRfExpiresIn() {
        return rfExpiresIn;
    }

    public void setRfExpiresIn(int rfExpiresIn) {
        this.rfExpiresIn = rfExpiresIn;
    }
}

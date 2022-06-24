package src.book.core.entities;

public class tokenEntity {
    private String AccessToken;
    private int AtExpireIn;
    private String RefreshToken;
    private int RfExpireIn;

    public tokenEntity(String accessToken, int atExpireIn, String refreshToken, int rfExpireIn) {
        AccessToken = accessToken;
        AtExpireIn = atExpireIn;
        RefreshToken = refreshToken;
        RfExpireIn = rfExpireIn;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public int getAtExpireIn() {
        return AtExpireIn;
    }

    public void setAtExpireIn(int atExpireIn) {
        AtExpireIn = atExpireIn;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }

    public int getRfExpireIn() {
        return RfExpireIn;
    }

    public void setRfExpireIn(int rfExpireIn) {
        RfExpireIn = rfExpireIn;
    }
}

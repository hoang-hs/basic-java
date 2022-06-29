package src.book.core.entities;

public class tokenEntity {
    private String AccessToken;
    private int AtExpiresIn;
    private String RefreshToken;
    private int RfExpiresIn;

    public tokenEntity(String accessToken, int atExpiresIn, String refreshToken, int rfExpiresIn) {
        AccessToken = accessToken;
        AtExpiresIn = atExpiresIn;
        RefreshToken = refreshToken;
        RfExpiresIn = rfExpiresIn;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public int getAtExpiresIn() {
        return AtExpiresIn;
    }

    public void setAtExpiresIn(int atExpiresIn) {
        AtExpiresIn = atExpiresIn;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }

    public int getRfExpiresIn() {
        return RfExpiresIn;
    }

    public void setRfExpiresIn(int rfExpiresIn) {
        RfExpiresIn = rfExpiresIn;
    }
}

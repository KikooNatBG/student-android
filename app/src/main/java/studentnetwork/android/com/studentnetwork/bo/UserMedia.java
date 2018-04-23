package studentnetwork.android.com.studentnetwork.bo;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class UserMedia {
    private int id;
    private String pictureUrl;
    private String smallPictureUrl;
    private String largePictureUrl;
    private Enum type;

    public UserMedia() {
    }

    public UserMedia(int id, String pictureUrl, String smallPictureUrl, String largePictureUrl, Enum type) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.smallPictureUrl = smallPictureUrl;
        this.largePictureUrl = largePictureUrl;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }

    public String getLargePictureUrl() {
        return largePictureUrl;
    }

    public void setLargePictureUrl(String largePictureUrl) {
        this.largePictureUrl = largePictureUrl;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserMedia{" +
                "id=" + id +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", smallPictureUrl='" + smallPictureUrl + '\'' +
                ", largePictureUrl='" + largePictureUrl + '\'' +
                ", type=" + type +
                '}';
    }
}

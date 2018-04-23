package studentnetwork.android.com.studentnetwork.bo;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class ContentMedia {
    private int id;
    private String mediaUrl;
    private Enum type;
    private Content content;

    public ContentMedia() {
    }

    public ContentMedia(int id, String mediaUrl, Enum type, Content content) {
        this.id = id;
        this.mediaUrl = mediaUrl;
        this.type = type;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentMedia{" +
                "id=" + id +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", type=" + type +
                ", content=" + content +
                '}';
    }
}

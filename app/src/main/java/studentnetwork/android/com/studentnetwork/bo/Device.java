package studentnetwork.android.com.studentnetwork.bo;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class Device {
    private int id;
    private Enum type;
    private String token;
    private User user;

    public Device() {
    }

    public Device(int id, Enum type, String token, User user) {

        this.id = id;
        this.type = type;
        this.token = token;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", type=" + type +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}

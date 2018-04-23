package studentnetwork.android.com.studentnetwork.bo;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class UserLink {
    private User user;
    private User contact;
    private Enum status;

    public UserLink() {
    }

    public UserLink(User user, User contact, Enum status) {
        this.user = user;
        this.contact = contact;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserLink{" +
                "user=" + user +
                ", contact=" + contact +
                ", status=" + status +
                '}';
    }
}

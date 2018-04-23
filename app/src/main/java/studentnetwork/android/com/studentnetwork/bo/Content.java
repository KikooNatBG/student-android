package studentnetwork.android.com.studentnetwork.bo;

import java.util.Date;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class Content {
    private int id;
    private String description;
    private Enum type;
    private Date time;
    private Date startTime;
    private Date endTime;
    private Date updatedTime;
    private Location location;
    private Content parent;

    public Content() {
    }

    public Content(int id, String description, Enum type, Date time, Date startTime, Date endTime, Date updatedTime, Location location, Content parent) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.updatedTime = updatedTime;
        this.location = location;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Content getParent() {
        return parent;
    }

    public void setParent(Content parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", time=" + time +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", updatedTime=" + updatedTime +
                ", location=" + location +
                ", parent=" + parent +
                '}';
    }
}

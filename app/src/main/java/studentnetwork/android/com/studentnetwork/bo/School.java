package studentnetwork.android.com.studentnetwork.bo;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class School {
    private int id;
    private String name;
    private Enum type;
    private Location location;

    public School() {
    }

    public School(int id, String name, Enum type, Location location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", location=" + location +
                '}';
    }
}

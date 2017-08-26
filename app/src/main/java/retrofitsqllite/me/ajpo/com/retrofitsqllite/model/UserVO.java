package retrofitsqllite.me.ajpo.com.retrofitsqllite.model;

/**
 * Created by ajperalt on 26-08-17.
 */

public class UserVO {

    int id;
    String name;
    String last_name;


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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}

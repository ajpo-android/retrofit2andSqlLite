package retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.GitHubExample;

/**
 * Created by ajperalt on 26-08-17.
 */

public class GitHubRepo {
    private int id;
    private String name;

    public GitHubRepo() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "DbVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

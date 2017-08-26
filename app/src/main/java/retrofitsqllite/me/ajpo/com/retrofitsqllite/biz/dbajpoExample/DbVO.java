package retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample;

import java.util.List;

/**
 * Created by ajperalt on 26-08-17.
 */

public class DbVO {
    String dbname;
    List<TableVO> tables;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    @Override
    public String toString() {
        return "TableVO{" +
                "dbname='" + dbname + '\'' +
                ", tables=" + tables +
                '}';
    }
}

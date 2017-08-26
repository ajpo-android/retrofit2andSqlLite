package retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample;

import java.util.List;

/**
 * Created by ajperalt on 26-08-17.
 */

public class TableVO {
    String tbl_name;
    String create;

    public String getTbl_name() {
        return tbl_name;
    }

    public void setTbl_name(String tbl_name) {
        this.tbl_name = tbl_name;
    }

    @Override
    public String toString() {
        return "TableVO{" +
                "tbl_name='" + tbl_name + '\'' +
                ", create='" + create + '\'' +
                '}';
    }
}

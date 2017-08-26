package retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajperalt on 26-08-17.
 */

public class AjpoDBHelper extends SQLiteOpenHelper {

    List<String> initQuerys = new ArrayList<>();
    public AjpoDBHelper(Context context,List<String> initQuerys) {
        super(context, "ajpo", null, 1);
        this.initQuerys = initQuerys;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String q : initQuerys){
            db.execSQL(q);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {

    }
}

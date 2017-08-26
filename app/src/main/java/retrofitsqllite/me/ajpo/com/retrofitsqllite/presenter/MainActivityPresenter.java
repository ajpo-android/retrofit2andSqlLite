package retrofitsqllite.me.ajpo.com.retrofitsqllite.presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.GitHubExample.GitHubClient;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.GitHubExample.GitHubClientBuilder;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.GitHubExample.GitHubRepo;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample.AjpoDBHelper;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample.Retrofit2andSqlLiteBuilder;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample.Retrofit2andSqlLiteClient;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.model.UserVO;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.view.MainActivity;

/**
 * Created by ajperalt on 26-08-17.
 */

public class MainActivityPresenter {

    MainActivity mView;

    public MainActivityPresenter(MainActivity mainActivity) {
        mView = mainActivity;
    }

    public void showGitHubInfo(){

        GitHubClient client = GitHubClientBuilder.build(mView.getApplicationContext());

        // Fetch a list of the Github repositories.
        Call<List<GitHubRepo>> call =
                client.reposForUser("ajperalt");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                // The network call was a success and we got a response
                StringBuilder sb = new StringBuilder("Repos");
                for(GitHubRepo ghr : response.body()){
                    sb.append(ghr.toString());
                }
                mView.mFrag.updateResult(sb);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // the network call was a failure
                mView.mFrag.updateResult(t.getMessage());

            }
        });
    }


    public void createDbAJPO(){
        Retrofit2andSqlLiteClient client = Retrofit2andSqlLiteBuilder.build(mView.getApplicationContext());
        Call<ResponseBody> call = client.getdbajpo();

        call.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 try {
                     updateDataBase(response.body().string());
                 } catch (IOException t) {
                     mView.mFrag.updateResult(t.getMessage());
                 }
             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 mView.mFrag.updateResult(t.getMessage());
             }
         });
    }

    private void updateDataBase(String json) {


        try {

            StringBuilder sb = new StringBuilder("querys: \n");
            JSONObject obj = new JSONObject(json);
            JSONArray tbls = obj.getJSONArray("tables");
            List<String> querys = new ArrayList<>();
            for(int i=0;i<tbls.length();i++){
                JSONObject tbl = tbls.getJSONObject(i);
                String query = tbl.getString("create");
                sb.append(i).append(":").append(query).append("\n");
                querys.add(query);
            }
            for(int i=0;i<tbls.length();i++){
                JSONObject tbl = tbls.getJSONObject(i);
                JSONArray lstq = tbl.getJSONArray("querys");
                for(int y=0;y<lstq.length();y++){
                    String q = lstq.getString(y);
                    querys.add(q);
                }
            }
            // Init database creation
            AjpoDBHelper dbH = new AjpoDBHelper(mView.getApplicationContext(),querys);
            // after init database try to find data
            SQLiteDatabase db = dbH.getReadableDatabase();
            String[] columns = new String[]{"id","name","last_name"};
            Cursor c = db.query(
                    "user",        // The table to query
                    columns,       // The columns to return
                    null,          // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,          // don't group the rows
                    null,          // don't filter by row groups
                    "last_name"    // The sort order
            );
            List<UserVO> lu = new ArrayList<>();

            c.moveToFirst();
            // create users in the database
            lu.add(createU(c,sb));
            while(c.moveToNext()){
                lu.add(createU(c,sb));
            }
            mView.mFrag.updateResult(sb);
            mView.mFrag.updateUsers(lu);
        } catch (JSONException e) {
            mView.mFrag.updateResult(e.getMessage());
        }
    }

    private UserVO createU(Cursor c,StringBuilder sb) {
        UserVO u;
        u = new UserVO();
        u.setId(c.getInt(0));
        u.setName(c.getString(1));
        u.setLast_name(c.getString(2));
        sb.append(u.toString()).append("\n");
        return u;
    }
}

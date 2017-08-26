package retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;

/**
 * Created by ajperalt on 26-08-17.
 */

public interface Retrofit2andSqlLiteClient {

    @Headers({"Accept:text/plain"})
    @GET("ajperalt/retrofit2andSqlLite-db/master/db.ajpo.6.json")
    Call<ResponseBody> getdbajpo();
}

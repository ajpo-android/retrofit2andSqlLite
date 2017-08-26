package retrofitsqllite.me.ajpo.com.retrofitsqllite.biz.dbajpoExample;

import android.content.Context;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ajperalt on 26-08-17.
 */

public class Retrofit2andSqlLiteBuilder {

    public static Retrofit2andSqlLiteClient build(Context context){
        String API_BASE_URL = "https://raw.githubusercontent.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // create the cache
        httpClient.cache(new Cache(new File(context.getCacheDir(), "ok-http-cache"),1024 * 1024 * 5)); // 5 MB cache

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        return retrofit.create(Retrofit2andSqlLiteClient.class);
    }
}

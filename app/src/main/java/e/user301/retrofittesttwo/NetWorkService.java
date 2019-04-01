package e.user301.retrofittesttwo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkService {
    //
    private static NetWorkService rInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit rRetrofit;
    //
    private NetWorkService(){
        // добавление логирования
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);

        rRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }
    //
    public static NetWorkService getInstance(){
        if (rInstance == null){
            rInstance = new NetWorkService();
        }
        return rInstance;
    }

    /**
     * Теперь нам нужно, чтобы Retrofit предоставил реализацию интерфейса J
     * SONPlaceHolderApi. Для этого используем метод create():
     */
    public JSONPlaceHolderApi getJSONApi(){
        return rRetrofit.create(JSONPlaceHolderApi.class);
    }
}

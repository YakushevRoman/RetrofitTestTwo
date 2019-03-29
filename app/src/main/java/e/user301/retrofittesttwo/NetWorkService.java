package e.user301.retrofittesttwo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkService {
    //
    private static NetWorkService rInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit rRetrofit;
    //
    private NetWorkService(){
        rRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //
    public static NetWorkService getInstance(){
        if (rInstance == null){
            rInstance = new NetWorkService();
        }
        return rInstance;
    }
}

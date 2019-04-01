package e.user301.retrofittesttwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
    Возвращённый объект Call содержит метод с именем enqueue, который принимает в качестве
    параметра Callback<T>. В onResponse мы получаем результат Response<Post>,
    содержащий возвращённый с сервера объект Post. Чтобы получаем сам объект Post, используем метод response.body().
     Остальная часть кода понятна без дополнительных пояснений.
     */
        NetWorkService.getInstance()
                .getJSONApi()
                .getPostWithID(10)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post post = response.body();

                        int id = post.getId();
                        int userId = post.getUserId();
                        String title = post.getTitle();
                        String bode = post.getBody();
                        Log.d(TAG, "onResponse: " + id + "\n" + userId + "\n" + title + "\n" + bode);
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.toString());
                    }
                });
       // получение всего списка
        /*NetWorkService.getInstance()
                .getJSONApi()
                .getAllPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        List <Post> postList = response.body();
                        postList.size();

                        Log.d(TAG, "onResponse: " + postList.get(1).getTitle());
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });*/
        // получение результата с параметрами
        /*NetWorkService.getInstance()
                .getJSONApi()
                .getPostOfUser(2)
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        List<Post> postList = response.body();

                        Log.d(TAG, "onResponse: " + postList.size());
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });*/
        // отправка пост запроса
        Post post = new Post();
        post.setId(101);
        post.setUserId(101);
        post.setBody("ququ");
        post.setTitle("zdraste");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        gson.toJson(post);
        NetWorkService.getInstance()
                .getJSONApi()
                .postData(post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.d(TAG, "onResponse: " + response.code());
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
    }
}

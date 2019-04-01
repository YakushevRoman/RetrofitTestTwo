package e.user301.retrofittesttwo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    /*
    аннотация показывает тип запроса
        внутрь ей передается конечная точка
    Call  - класс который предоставляется самой бибилиотекой
    И все методы в интерфейсе должны возвращать значения этого типа.
    Это generic-класс, принимающий в себя тип объекта,
    который мы хотим конвертировать в JSON.
    Мы передали Post, т.к. это именно тот объект,
    в который хотим преобразовать JSON-ответ.
    В параметры мы передали целое число и аннотировали его с помощью @Path,
    внутри которого записали id. Retrofit возьмёт это значение
    и в конечной точке заменит на него {id}. Таким образом,
    если мы передадим значение 1 в качестве параметра,
    то конечная точка будет выглядеть так — /posts/1,
    если передадим значение 10, то конечная точка получится — /posts/10.
     */
    @GET ("/posts/{id}")
    public Call<Post> getPostWithID (@Path("id") int id);

    @GET ("/posts")
    public Call<List<Post>> getAllPosts();
    // запрос с параметрами
    @GET ("/posts")
    public Call <List<Post>> getPostOfUser (@Query("userId") int id);
    // для другого запроса
    @POST("/posts")
    public Call <Post> postData (@Body Post dataPost);
}

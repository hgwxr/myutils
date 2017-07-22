package skill.android.wl.retrofit;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;

import dalvik.system.DexClassLoader;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("hgwxr");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
        String CONTENT_TYPE = "application/json; charset=utf-8";
        RequestBody requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE), "");
        String json ="{\n" +
                "    \"code\": 0, \n" +
                "    \"msg\": \"asd\", \n" +
                "    \"data\": {\n" +
                "        \"name\": \"hgwxr\", \n" +
                "        \"age\": \"18\", \n" +
                "        \"sex\": \"1\", \n" +
                "        \"projects\": [\n" +
                "            {\n" +
                "                \"name\": \"project1\", \n" +
                "                \"msg\": \"project  工程1\"\n" +
                "            }, \n" +
                "            {\n" +
                "                \"name\": \"project2\", \n" +
                "                \"msg\": \"project  工程2\"\n" +
                "            }, \n" +
                "            {\n" +
                "                \"name\": \"project3\", \n" +
                "                \"msg\": \"project  工程3\"\n" +
                "            }, \n" +
                "            {\n" +
                "                \"name\": \"project4\", \n" +
                "                \"msg\": \"project  工程4\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        String  json1="{code:\"0\",msg:\"asd\",data:{name:\"hgwxr\",age:\"18\",sex:1,projects:[{ name:\"project1\" ,msg:\"project  工程1\"},{ name:\"project2\" ,msg:\"project  工程2\" },{ name:\"project3\",msg:\"project  工程3\" },{ name:\"project4\" ,msg:\"project  工程4\" },]}}";
                   User user1 = JSON.parseObject(json1, User.class);
        User user = JSON.toJavaObject(JSON.parseObject(json), User.class);
       user.getAge();
        BaseBean<User> userBaseBean = new BaseBean<>();
        Type mapType = new TypeToken<LinkedHashMap<String, BaseBean<User>>>(){}.getType();
        BaseBean baseBean = new Gson().fromJson(json, new BaseBean<>().getClass());
        Demo user2 = new Gson().fromJson(json, Demo.class);
        user2.getMsg();


    }


    public void jump(View view){
        Intent mIntent = new Intent();
        mIntent.setClassName(this,
               "skill.android.wl.retrofit."+"Main2Activity");
        startActivity(mIntent);
       /* try {
            Class<?> main2Activity1 = ((DexClassLoader) getClassLoader()).loadClass("Main2Activity");
            Class<?> main2Activity = Class.forName("Main2Activity");
            if (main2Activity!=null){

                Intent intent = new Intent(this, main2Activity);
                startActivity(intent);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }
}

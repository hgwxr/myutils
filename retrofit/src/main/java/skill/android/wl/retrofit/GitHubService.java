package skill.android.wl.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 * @date 2017/7/7
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
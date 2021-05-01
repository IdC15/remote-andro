package com.example.remotecontrol;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface jsonplaceholder {
    @POST("/remote/starting")
    Call<PostObj>postobj(@Body PostObj PostObj);
    @POST("/remote/input/horizontal")
    Call<postHorizontal>posthorizontal(@Body postHorizontal postHorizontal);
}

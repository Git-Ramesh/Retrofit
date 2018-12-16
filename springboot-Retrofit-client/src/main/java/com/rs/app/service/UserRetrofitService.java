package com.rs.app.service;

import java.util.List;

import com.rs.app.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserRetrofitService {

	@POST("/user")
	public Call<User> saveUser(@Body User user);

	@GET("/user/{id}")
	public Call<User> getAvailableUser(@Path("id") long id);

	@GET("/user")
	public Call<List<User>> getAvailableUsers();

	@DELETE("/user")
	public Call<User> removeExistedUser(@Query("id") long id);
}

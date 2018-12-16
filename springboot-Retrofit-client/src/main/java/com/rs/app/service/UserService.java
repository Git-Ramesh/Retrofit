package com.rs.app.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rs.app.exception.UserAlreadyAvailableException;
import com.rs.app.exception.UserNotAvailableException;
import com.rs.app.model.User;
import com.rs.app.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Service
public class UserService {

	private static final String SERVER = "http://localhost:4014/";
	private Retrofit retrofit;
	UserRetrofitService userRetrofitService;

	@PostConstruct
	public void init() {
		this.retrofit = RetrofitUtil.getRetrofit(SERVER, false);
		this.userRetrofitService = this.retrofit.create(UserRetrofitService.class);
	}

	public User saveUser(User user) throws IOException {
		System.out.println("saveUser...");
		User u = null;
		Call<User> call = this.userRetrofitService.saveUser(user);
		Response<User> response = call.execute();
		if (response.isSuccessful()) {
			u = response.body();
		} else {
			int httpErrorCode = response.code();
			User errMsg = response.body();
			System.out.println("HttpErrorCode? " + httpErrorCode);
			System.out.println("errMsg??? " + errMsg);
			if (httpErrorCode == HttpStatus.CONFLICT.value()) {
				throw new UserAlreadyAvailableException("User already exists");
			}
		}
		return u;
	}

	public User getAvailableUser(long id) throws IOException {
		System.out.println("getAvailableUser...");
		User user = null;
		Call<User> call = this.userRetrofitService.getAvailableUser(id);
		Response<User> response = call.execute();
		if (response.isSuccessful()) {
			user = response.body();
		} else {
			int httpErrorCode = response.code();
			User errMsg = response.body();
			System.out.println("HttpErrorCode? " + httpErrorCode);
			System.out.println("errMsg? " + errMsg);
			if (httpErrorCode == HttpStatus.NOT_FOUND.value()) {
				throw new UserNotAvailableException("User not available");
			}
		}
		return user;
	}

	public List<User> getAvailableUsers() throws IOException {
		System.out.println("getAvailableUsers...");
		List<User> users = null;
		Call<List<User>> call = this.userRetrofitService.getAvailableUsers();
		//synchronous code
//		Response<List<User>> response = call.execute();
//		if (response.isSuccessful()) {
//			users = response.body();
//		} else {
//			int httpErrorCode = response.code();
//			List<User> errMsg = response.body();
//			System.out.println("HttpErrorCode? " + httpErrorCode);
//			System.out.println("errMsg? " + errMsg);
//		}
		//Async Code
		System.out.println("call hashCode? " + call.hashCode());
		call.enqueue(new Callback<List<User>>() {
			
			@Override
			public void onResponse(Call<List<User>> call, Response<List<User>> response) {
				System.out.println("onResponse call hashCode? " + call.hashCode());
				if(response.isSuccessful()) {
					List<User> users = response.body();
					System.out.println(users);
				} else {
					int httpErrorCode = response.code();
				}
			}
			
			@Override
			public void onFailure(Call<List<User>> call, Throwable th) {
				System.out.println("onFailure call hashCode? " + call.hashCode());
			}
		});
		System.out.println("Other operations...");
		return users;
	}

	public User removeExistedUser(long id) throws IOException {
		System.out.println("removeExistedUser...");
		User user = null;
		Call<User> call = this.userRetrofitService.removeExistedUser(id);
		Response<User> response = call.execute();
		if (response.isSuccessful()) {
			user = response.body();
		} else {
			int httpErrorCode = response.code();
			User errMsg = response.body();
			System.out.println("HttpErrorCode? " + httpErrorCode);
			System.out.println("errMsg? " + errMsg);
		}
		return user;
	}

}

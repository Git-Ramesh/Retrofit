package com.rs.app.util;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil {

	public static Retrofit getRetrofit(String targetUrl, boolean isPrimitiveResponse) {
		Converter.Factory factory = null;
		if(isPrimitiveResponse) {
			factory = ScalarsConverterFactory.create();
		} else {
			factory = GsonConverterFactory.create();
		}
			return new Retrofit.Builder().baseUrl(targetUrl)
					.addConverterFactory(factory).build();
	}
}

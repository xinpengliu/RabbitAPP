package com.rabbit.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class RabbitApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}

}
package com.jaydp.samplepayment;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.instamojo.android.Instamojo;
import com.jaydp.samplepayment.injectionbase.components.AppComponent;
import com.jaydp.samplepayment.injectionbase.components.DaggerAppComponent;
import com.jaydp.samplepayment.injectionbase.modules.AppModule;

public class MyApp extends Application {

  private static AppComponent sAppComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    // Dependency Injection
    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    sAppComponent.inject(this);

    Instamojo.initialize(this);
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}

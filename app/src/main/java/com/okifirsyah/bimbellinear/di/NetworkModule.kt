package com.okifirsyah.bimbellinear.di

import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.data.network.HeaderInterceptor
import com.okifirsyah.bimbellinear.data.network.services.BillService
import com.okifirsyah.bimbellinear.data.network.services.GroupService
import com.okifirsyah.bimbellinear.data.network.services.ScheduleService
import com.okifirsyah.bimbellinear.data.network.services.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        return@single OkHttpClient.Builder()
            .addInterceptor(
                getHeaderAppJsonInterceptor()
            )
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideAuthService(get()) }
    single { provideScheduleService(get()) }
    single { provideGroupService(get()) }
    single { provideBillService(get()) }
}

private fun getHeaderAppJsonInterceptor(): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers)
}


fun provideAuthService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)
fun provideScheduleService(retrofit: Retrofit): ScheduleService =
    retrofit.create(ScheduleService::class.java)

fun provideGroupService(retrofit: Retrofit): GroupService =
    retrofit.create(GroupService::class.java)

fun provideBillService(retrofit: Retrofit): BillService =
    retrofit.create(BillService::class.java)

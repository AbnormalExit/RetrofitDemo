package com.sxshi.retrofit.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.jetbrains.annotations.NotNull
import java.io.File
import java.util.*

/**
 * 公共参数封装
 * 项目公用的请求参数统一在这里处理
 */
object ParamUtils {
    fun getRequestBody(params: Map<String, String>): RequestBody {
        val json = Gson().toJson(params)
        return json.toRequestBody("application/json;charset=utf-8".toMediaType())
    }

    fun getRequestBody(key: String, value: String): RequestBody {
        val params = HashMap<String, String>()
        params[key] = value
        val json = Gson().toJson(params)
        return json.toRequestBody("application/json;charset=utf-8".toMediaType())
    }

    /**
     * MultipartBody.Part
     * 上传文件格式
     */
    fun producePart(@NotNull file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaType())
        return MultipartBody.Part.createFormData("authFile", file.name, requestFile)
    }

    /**
     * 上传文件 并且携带参数
     */
    fun uploadRequestBody(imageType: String, delta: String, file: File): RequestBody {
        val requestFile = file.asRequestBody("application/octet-stream".toMediaType())
        return MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageType", imageType)
                .addFormDataPart("delta", delta)
                .addFormDataPart("authFile", file.getName(), requestFile)
                .build()
    }
}
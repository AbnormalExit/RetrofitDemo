package com.sxshi.retrofit.model;

import com.sxshi.retrofit.base.BaseRepository;
import com.sxshi.retrofit.bean.BaseResponse;
import com.sxshi.retrofit.utils.ParamUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class DataRepository extends BaseRepository {

    /**
     * 登录
     *
     * @param param    参数
     * @param observer observer
     * @return Observable<BaseResponse < String>>
     */
    public void login(HashMap<String, String> param, Observer<BaseResponse<String>> observer) {
         addObserver(services.login(ParamUtils.getRequestBody(param)), observer);
    }
}

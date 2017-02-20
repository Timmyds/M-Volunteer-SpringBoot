package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by wangl on 2017/2/18.
 */
public interface ActivityService {
    //分页展示首页的活动列表的接口
    PageInfo<List<HomeActivity>> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat);

    //搜索活动列表
    //分页返回搜索结果
    PageInfo<List<HomeActivity>> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat, String activityName);
}
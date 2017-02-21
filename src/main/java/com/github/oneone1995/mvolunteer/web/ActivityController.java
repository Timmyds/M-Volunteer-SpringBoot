package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.Activity;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangl on 2017/2/18.
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<?> getActivities(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "coordLong") double coordLong,
            @RequestParam(value = "coordLat") double coordLat) {

        PageInfo<HomeActivity> homeActivityPageInfo = activityService.getHomeActivityPageInfo(
                page, rows, coordLong, coordLat);

        if (homeActivityPageInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ResultModel.ok(homeActivityPageInfo), HttpStatus.OK);
    }


    @GetMapping("/category")
    public ResponseEntity<?> getActivitiesByCategory(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "coordLong") double coordLong,
            @RequestParam(value = "coordLat") double coordLat,
            @RequestParam(value = "category") String category,
            @RequestParam(value = "collation", defaultValue = "0") Integer collation,
            @RequestParam(value = "district", required = false) String district
    ) {
        PageInfo<HomeActivity> categoryActivityPageInfo = activityService.getHomeActivityPageInfo(
                page, rows, coordLong, coordLat, category, collation, district);

        if (categoryActivityPageInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultModel.ok(categoryActivityPageInfo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityById(
            @PathVariable Integer id
    ) {
        Activity activity = activityService.getActivityById(id);

        if (activity == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultModel.ok(activity), HttpStatus.OK);
    }
}

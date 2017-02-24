package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunZyPhotoCover;
import com.ybt.model.work.SunZyPhotoGrapher;
import com.ybt.service.base.IBaseService;

@Component
public interface PhotoGrapherService extends IBaseService<SunZyPhotoGrapher, String>{

	SunZyPhotoGrapher savePhotoGrapher(SunZyPhotoCover photo);

}

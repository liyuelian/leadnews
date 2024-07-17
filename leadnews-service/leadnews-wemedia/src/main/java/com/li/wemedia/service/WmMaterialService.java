package com.li.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {
    /**
     * 素材图片上传
     * @param multipartFile
     * @return
     */
    public ResponseResult uploadPicture(MultipartFile multipartFile);
}

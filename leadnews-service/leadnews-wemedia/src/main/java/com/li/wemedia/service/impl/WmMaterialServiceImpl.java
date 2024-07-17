package com.li.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.file.service.FileStorageService;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.common.enums.AppHttpCodeEnum;
import com.li.model.wemedia.pojos.WmMaterial;
import com.li.utils.thread.WmThreadLocalUtil;
import com.li.wemedia.mapper.WmMaterialMapper;
import com.li.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: liyuelian
 * @Date: 2024/7/17 16:46
 * @Description:
 **/
@Service
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Resource
    private FileStorageService fileStorageService;

    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        //1.检查参数
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.上传图片到MinIO中
        String fileName = UUID.randomUUID().toString().replace("-", "");//前缀
        String originalFilename = multipartFile.getOriginalFilename();
        //aa.jpg - jpg
        String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));//后缀
        String fileUrl = null;
        try {
            fileUrl = fileStorageService.uploadImgFile("", fileName + postfix, multipartFile.getInputStream());
            log.info("上传图片到MinIO中，fileUrl:{}", fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("WmMaterialServiceImpl-上传文件失败");
        }

        //3.保存图片路径到数据库
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
        wmMaterial.setUrl(fileUrl);
        wmMaterial.setIsCollection((short) 0);//是否收藏
        wmMaterial.setType((short) 0);//上传文件类型
        wmMaterial.setCreatedTime(new Date());
        save(wmMaterial);

        //4.返回结果
        return ResponseResult.okResult(wmMaterial);
    }
}

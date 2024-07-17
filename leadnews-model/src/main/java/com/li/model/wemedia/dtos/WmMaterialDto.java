package com.li.model.wemedia.dtos;

import com.li.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @Author: liyuelian
 * @Date: 2024/7/17 19:13
 * @Description:
 **/
@Data
public class WmMaterialDto extends PageRequestDto {
    /**
     * 1-收藏；2-未收藏
     */
    Short isCollection;
}

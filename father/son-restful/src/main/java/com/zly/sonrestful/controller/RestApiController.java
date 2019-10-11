package com.zly.sonrestful.controller;

import com.zly.sonrestful.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest 风格接口测试
 */
@RestController // 等价 @Controller + @ResponseBody 返回Json格式数据
@RequestMapping("rest")
public class RestApiController {
    private static final Logger LOG = LoggerFactory.getLogger(RestApiController.class) ;
    /**
     * 保存
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert (UserInfo userInfo){
        LOG.info("===>>"+userInfo);
        return "success" ;
    }
    /**
     * 查询
     */
    @RequestMapping(value = "/select/{id}",method = RequestMethod.GET)
    public String select (@PathVariable Integer id){
        LOG.info("===>>"+id);
        return "success" ;
    }
}

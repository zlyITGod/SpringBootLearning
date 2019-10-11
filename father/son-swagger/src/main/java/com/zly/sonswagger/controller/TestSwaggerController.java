package com.zly.sonswagger.controller;



import com.zly.sonswagger.entity.JsonResult;
import com.zly.sonswagger.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;


@RestController
public class TestSwaggerController {
    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    /**
     * 添加用户
     */
    @ApiOperation(value="添加用户", notes="创建新用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addUser (@RequestBody User user){
        JsonResult result = new JsonResult();
        try {
            users.put(user.getId(), user);
            result.setResult(user.getId());
            result.setStatus("ok");
        } catch (Exception e) {
            result.setResult("服务异常");
            result.setStatus("500");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 根据ID查询用户
     */
    @ApiOperation(value="用户查询", notes="根据ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id){
        JsonResult result = new JsonResult();
        try {
            User user = users.get(id);
            result.setResult(user);
            result.setStatus("200");
        } catch (Exception e) {
            result.setResult("服务异常");
            result.setStatus("500");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 查询用户列表
     */
    @ApiOperation(value="用户列表", notes="查询用户列表")
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserList (){
        JsonResult result = new JsonResult();
        try {
            List<User> userList = new ArrayList<>(users.values());
            result.setResult(userList);
            result.setStatus("200");
        } catch (Exception e) {
            result.setResult("服务异常");
            result.setStatus("500");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 根据ID修改用户信息
     */
    @ApiOperation(value="更新用户", notes="根据Id更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户对象user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/updateById/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JsonResult> updateById (@PathVariable("id") Integer id, @RequestBody User user){
        JsonResult result = new JsonResult();
        try {
            User user1 = users.get(id);
            user1.setUsername(user.getUsername());
            user1.setAge(user.getAge());
            users.put(id, user1);
            result.setResult(user1);
            result.setStatus("ok");
        } catch (Exception e) {
            result.setResult("服务异常");
            result.setStatus("500");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 根据id删除用户
     */
    @ApiOperation(value="删除用户", notes="根据id删除指定用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> deleteById (@PathVariable(value = "id") Integer id){
        JsonResult result = new JsonResult();
        try {
            users.remove(id);
            result.setResult(id);
            result.setStatus("ok");
        } catch (Exception e) {
            result.setResult("服务异常");
            result.setStatus("500");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/ignoreApi", method = RequestMethod.GET)
    public String  ignoreApi() {
        return "Spring Boot Swagger !";
    }

}

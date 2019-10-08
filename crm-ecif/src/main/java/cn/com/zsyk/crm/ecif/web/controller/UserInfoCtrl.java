package cn.com.zsyk.crm.ecif.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcUserInfo;
import cn.com.zsyk.crm.ecif.service.user.UserService;
import sun.misc.BASE64Encoder;

@RestController
public class UserInfoCtrl {

	@Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CoreDaoImpl dao;

    /**
     * @api {get} /crm/ecif/users 查询用户列表
     * @apiName getUsers
     * @apiGroup User
     * @apiSuccess {Object[]} users 用户列表
     * @apiSuccess {String} users.id 用户id
     * @apiSuccess {String} users.name 用户姓名
     */
    @RequestMapping(path = "/crm/ecif/test/users", method = RequestMethod.GET)
    public Result getUsers() {
        Result s = new Result();
//		s.setData(dao.selectList("userInfoSelectAll"));
        PageBean p = dao.selectPageById("userInfoSelectAll", null);
        s.setData(p);
        return s;
    }


//	@RequestMapping(path = "/crm/ecif/users",method = RequestMethod.POST)
//	public Response addUser(Integer id,String username){
//		System.out.println(ContextContainer.getContext().get("user"));
//		Response s = new Response();
//		System.out.println("新增用户");
//		EcUserInfo user = userService.addUser(id, username);
//		s.setData(user);
//		return s;
//	}

    /**
     * @api {post} /crm/ecif/users 用户新增
     * @apiName addUser
     * @apiGroup User
     * @apiParam {Number} id 用户id
     * @apiParam {String} username 用户名称
     * @apiSuccess {EcUserInfo} data 用户对象
     * @apiSuccess {int} data.id 用户id
     * @apiSuccess {String} data.name 用户姓名
     */
    @RequestMapping(path = "/crm/ecif/users", method = RequestMethod.POST)
    public Result<EcUserInfo> addUser(EcUserInfo user) throws InterruptedException {
        System.out.println(ContextContainer.getContext().getUserId());
        System.out.println(user);
        Result<EcUserInfo> s = new Result<>();
        s.setData(user);
        Thread.sleep(1 * 1000);
//		throw new RuntimeException("xxxxxxxxxx");
        return s;
    }


    @RequestMapping(path = "/crm/ecif/users", method = RequestMethod.PUT)
    public Result modifyUser(String user) throws Exception {
        System.out.println(user);
        EcUserInfo ui = om.readValue(user, EcUserInfo.class);
        System.out.println(ui);
        Result s = new Result();
        return s;
    }

    @SuppressWarnings({ "restriction", "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/picture", method = RequestMethod.POST)
    public Result uploadPicture(@RequestParam("file") MultipartFile file) {
        String imgData = null;
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            imgData = encoder.encode(file.getBytes());
            System.out.println(imgData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Result s = new Result();
        s.setData(imgData);
        return s;
    }

}

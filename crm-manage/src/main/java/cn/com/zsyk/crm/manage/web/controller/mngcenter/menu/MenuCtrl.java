package cn.com.zsyk.crm.manage.web.controller.mngcenter.menu;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysMenu;
import cn.com.zsyk.crm.manage.entity.SysMenuInfo;
import cn.com.zsyk.crm.manage.service.mngcenter.menu.MenuService;

@RestController
public class MenuCtrl {
	@Autowired
	private MenuService service;

	@RequestMapping(path = "/crm/manage/getAllMenus", method = RequestMethod.GET)
	public Result getAllMenus() {
		Result res = new Result();
		System.out.println("获取菜单列表，更换logger形式");
		//modi by lujibing 0828
		//List<SysMenuInfo> menuList = service.getAllMenuWithOrder();
		List<SysMenu> menuList = service.getAllMenuWithOrderNew();
		res.setData(menuList);
		return res;
	}

	@RequestMapping(path = "/crm/manage/getOneMenu", method = RequestMethod.GET)
	public Result getOneMenu(String menuId) {
		Result res = new Result();
		System.out.println("获取单条菜单数据，更换logger形式");
		//modi by lujibing 0828
		//SysMenuInfo menuInfo = service.getOneMenu(menuCode);
		SysMenu menuInfo = service.getOneMenuNew(menuId);
		res.setData(menuInfo);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/getMenusByEntity 根据条件查询菜单信息
	 * @apiName getMenusByEntity
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysMenuInfo} menuInfo 岗位信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/getMenusByEntity", method = RequestMethod.GET)
	public Result getMenusByEntity(SysMenuInfo menuInfo) {
		Result res = new Result();
		System.out.println("获取岗位列表，更换logger形式");
		menuInfo.setRecStat("0");
		List<SysMenuInfo> menuList = service.getMenuByEntity(menuInfo);
		res.setData(menuList);

		return res;
	}

	@RequestMapping(path = "/crm/manage/addMenu", method = RequestMethod.POST)
	@SysOprtLog(model = Module.MENU, bizDesc = "新增菜单数据")
	//modi by lujibing 0828
	public Result addMenu(SysMenu menu) {
		Result res = new Result();
		System.out.println("新增单条菜单数据，更换logger形式");
		menu.setRecStat("0");
		if (menu.getMenuOrder().length() < 2) {
			menu.setMenuOrder("0" + menu.getMenuOrder());
		}

		int addCount = service.addMenu(menu);

		if (addCount > 0) {
			System.out.println("插入数据成功，更换logger形式");
		} else {
			throw new ServiceException("新增菜单数据数据失败");
		}

		return res;
	}

	@RequestMapping(path = "/crm/manage/modMenu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.MENU, bizDesc = "修改菜单数据")
	public Result modMenu(SysMenu menu) {
		Result res = new Result();
		System.out.println("修改单条菜单数据，更换logger形式");
		menu.setRecStat("0");
		int modCount = service.modMenuNew(menu);

		if (modCount > 0) {
			System.out.println("修改数据成功，更换logger形式");
		} else {
			throw new ServiceException("修改菜单数据数据失败");
		}

		return res;
	}

	@RequestMapping(path = "/crm/manage/delModMenu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.MENU, bizDesc = "删除菜单数据")
	public Result delModMenu(String menuId) {
		Result res = new Result();
		System.out.println("逻辑删除单条菜单数据，更换logger形式");
		//modiby lujibing 20190829
		SysMenu menuInfo = new SysMenu();
		menuInfo.setMenuId(menuId);
		int delCount = service.delModMenu(menuInfo);

		if (delCount > 0) {
			System.out.println("删除数据成功，更换logger形式");
		} else {
			throw new ServiceException("删除菜单数据数据失败");
		}

		return res;
	}

	@RequestMapping(path = "/crm/manage/delMenu", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.MENU, bizDesc = "删除菜单数据")
	public Result delMenu(String menuCode) {
		Result res = new Result();
		System.out.println("逻辑删除单条菜单数据，更换logger形式");

		int addCount = service.delMenu(menuCode);

		if (addCount > 0) {
			System.out.println("删除数据成功，更换logger形式");
		} else {
			throw new ServiceException("删除菜单数据数据失败");
		}

		return res;
	}

	@RequestMapping(path = "/crm/manage/maxOrder", method = RequestMethod.GET)
	public Result getMaxMenuOrder(String pareMenuId) {
		Result res = new Result();
		//modi by lujibing 0828
		SysMenu menuInfo = new SysMenu();
		menuInfo.setMenuPareid(pareMenuId);
		SysMenu maxOrderMenu = service.getMaxOrderByEntity(menuInfo);

		int retOrder = Integer.parseInt(maxOrderMenu.getMenuOrder()) + 1;
		res.setData(retOrder);
		return res;
	}

	@RequestMapping(path = "/crm/manage/menuAuth", method = RequestMethod.GET)
	public Result getUserMenuList(String userId) {
		Result res = new Result();

		//Set<String> userMenu = service.getUserMenu(userId);
		Set<String> userMenu = service.getUserMenuAuth(userId);

		res.setData(userMenu);
		return res;
	}

	@RequestMapping(path = "/crm/manage/dragUpdMenu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.MENU, bizDesc = "修改菜单数据")
	public Result dragUpdMenu(String menuId, String newPare, String newOrder) {

		Result res = new Result();
		//调整菜单数据来源 modiby lujibing 20190830
		List<SysMenu> updList = service.reWriteMenuOrderNew(menuId, newPare, newOrder);

		int modCount = 0;
		for (SysMenu info : updList) {
			modCount += service.modMenuNew(info);
		}

		res.setData(menuId);
		return res;
	}

	@RequestMapping(path = "/crm/manage/modAllMenu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.MENU, bizDesc = "更新所有菜单数据")
	public Result modAllMenu(@RequestBody String menuListStr) {
		Result res = new Result();
		
		List<SysMenu> menuList = JsonUtil.parseArray(menuListStr, SysMenu.class);
		
		int modCount = 0;
		if (menuList != null && menuList.size() > 0) {
			modCount = service.modAllMenu(menuList);
		}
		
		res.setData(modCount);
		return res;
	}
	
	@RequestMapping(path = "/crm/manage/getAllMenu", method = RequestMethod.GET)
	@SysOprtLog(model = Module.MENU, bizDesc = "获取所有菜单数据")
	public Result getAllMenu() {
		Result res = new Result();
		//modiby lujibing 20190830
		//List<SysMenu> menuList = service.getAllMenuData();
		List<SysMenu> menuList = service.getAllMenuWithOrderNew();

		res.setData(menuList);
		return res;
	}

}

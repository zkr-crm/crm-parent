package cn.com.zsyk.crm.manage.service.mngcenter.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.com.zsyk.crm.manage.bom.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysMenu;
import cn.com.zsyk.crm.manage.entity.SysMenuInfo;
import cn.com.zsyk.crm.manage.entity.SysRoleMenu;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.mapper.SysMenuInfoMapper;
import cn.com.zsyk.crm.manage.mapper.SysMenuMapper;
import cn.com.zsyk.crm.manage.mapper.SysRoleMenuMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.auth.MenuAuthService;
import cn.com.zsyk.crm.manage.service.mngcenter.user.UserMngService;

@Service
@Transactional
public class MenuService {

	@Autowired
	private SysMenuInfoMapper sysMenuInfoMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	@Autowired
	private AbstractDao dao;
	@Autowired
	private UserMngService userservice;
	@Autowired
	private MenuAuthService menuservice;

	/**
	 * 获得一条菜单信息的方法
	 * 
	 * @param menuCode
	 *            菜单代码
	 * @return 菜单信息
	 */
	public SysMenuInfo getOneMenu(String menuCode) {

		SysMenuInfo menuInfo = new SysMenuInfo();

		menuInfo = sysMenuInfoMapper.selectByPrimaryKey(menuCode);

		return menuInfo;
	}

	/**
	 * 获取所有菜单信息的方法
	 * 
	 * @return 所有菜单信息的列表
	 */
	public List<SysMenuInfo> getAllMenu() {

		List<SysMenuInfo> lstMenu = new ArrayList<SysMenuInfo>();

		lstMenu = sysMenuInfoMapper.selectAll();

		return lstMenu;
	}

	/**
	 * 按顺序获取所有菜单信息的方法
	 * 
	 * @return 所有菜单信息的列表
	 */
	public List<SysMenuInfo> getAllMenuWithOrder() {

		List<SysMenuInfo> lstMenu = new ArrayList<SysMenuInfo>();

		lstMenu = sysMenuInfoMapper.selectAllWithOrder();

		return lstMenu;
	}
	/**
	 * 按顺序获取所有菜单信息的方法
	 *
	 * @return 所有菜单信息的列表
	 */
	public List<SysMenu> getAllMenuWithOrderNew() {

		List<SysMenu> lstMenu = new ArrayList<SysMenu>();

		lstMenu = sysMenuMapper.selectAllWithOrder();

		return lstMenu;
	}
	/**
	 * 获得一条菜单信息的方法
	 *
	 * @param menuId
	 *            菜单代码
	 * @return 菜单信息
	 */
	public SysMenu getOneMenuNew(String menuId) {

		SysMenu menuInfo = new SysMenu();

		menuInfo = sysMenuMapper.selectByPrimaryKey(menuId);

		return menuInfo;
	}
	/**
	 * 根据入参对象获取所有菜单信息的方法
	 * 
	 * @return 所有菜单信息的列表
	 */
	public List<SysMenuInfo> getMenuByEntity(SysMenuInfo record) {

		List<SysMenuInfo> lstUser = dao.selectList("cn.com.zsyk.crm.manage.mapper.SysMenuInfoMapper.selectMenuByEntity",
				record);

		return lstUser;
	}
	/**add by lujibing 20190829
	 * 根据入参对象获取所有菜单信息的方法
	 *
	 * @return 所有菜单信息的列表
	 */
	public List<SysMenu> getMenuByEntityNew(SysMenu record) {

		List<SysMenu> lstUser = dao.selectList("cn.com.zsyk.crm.manage.mapper.SysMenuMapper.selectMenuByEntity",
				record);

		return lstUser;
	}
	/**
	 * 根据入参获取最大menuorder
	 * 
	 * @param record
	 * @return
	 */
	public SysMenu getMaxOrderByEntity(SysMenu record) {
		try {
			record = BeanUtil.setNullValue(record);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List<SysMenu> lstUser = dao.getSqlSessionTemplate()
				.selectList("cn.com.zsyk.crm.manage.mapper.SysMenuMapper.selectMaxOrderByEntity", record);
		if (lstUser == null || lstUser.size() <= 0) {
			SysMenu retBean = new SysMenu();
			retBean.setMenuOrder("0");
			return retBean;
		}
		return lstUser.get(0);
	}

	/**
	 * 增加菜单信息的方法
	 * 
	 * @param menu
	 *            需要增加的菜单信息
	 * @return 增加成功的条目数，0为失败
	 */
	public int addMenu(SysMenu menu) {

		int insCount = 0;
		SysMenu maxOrderBean = this.getMaxOrderByEntity(menu);
		String maxOrder = String.valueOf(Integer.parseInt(maxOrderBean.getMenuOrder()) + 1);
		while (maxOrder.length() < 2) {
			maxOrder = "0" + maxOrder;
		}
		menu.setMenuOrder(maxOrder);
		if (menu.getMenuPareid() == null) {
			menu.setMenuPareid("");
		}
		// 存在判断
		SysMenu menuInfoBean = this.getOneMenuNew(menu.getMenuId());
		if (menuInfoBean == null) {
			try {
				menu = BeanUtil.setNullValue(menu);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			insCount = sysMenuMapper.insert(menu);
		} else if (menuInfoBean != null && StringUtils.equals("1", menuInfoBean.getRecStat())) {
			throw new ServiceException(
					"菜单信息已经存在：菜单代码[" + menuInfoBean.getMenuId() + "]，菜单名称[" + menuInfoBean.getMenuTitle() + "]");
		} else {
			menuInfoBean.setMenuDesc(menu.getMenuDesc());
			menuInfoBean.setMenuLevel(menu.getMenuLevel());
			menuInfoBean.setMenuName(menu.getMenuName());
			menuInfoBean.setMenuOrder(menu.getMenuOrder());
			menuInfoBean.setMenuStateref(menu.getMenuStateref());
			menuInfoBean.setMenuPareid(menu.getMenuPareid());
			menuInfoBean.setMenuParename(menu.getMenuParename());
			menuInfoBean.setRecStat("0");
			insCount = sysMenuMapper.updateByPrimaryKey(menuInfoBean);
		}

		return insCount;

	}

	/**
	 * 修改某条菜单信息的方法
	 * 
	 * @param menuInfo
	 *            需要修改的菜单信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modMenu(SysMenuInfo menuInfo) {
		// 存在判断
		SysMenuInfo menuInfoBean = this.getOneMenu(menuInfo.getMenuCode());
		if (menuInfoBean == null || StringUtils.equals(menuInfoBean.getRecStat(), "1")) {
			throw new ServiceException("菜单信息不存在：菜单代码[" + menuInfo.getMenuCode() + "]");
		}

		menuInfoBean.setMenuDesc(menuInfo.getMenuDesc());
		menuInfoBean.setMenuLv(menuInfo.getMenuLv());
		menuInfoBean.setMenuName(menuInfo.getMenuName());
		menuInfoBean.setMenuOrder(menuInfo.getMenuOrder());
		menuInfoBean.setNodePath(menuInfo.getNodePath());
		menuInfoBean.setPareMenu(menuInfo.getPareMenu());
		menuInfoBean.setPareMenuCode(menuInfo.getPareMenuCode() == "#" ? "" : menuInfo.getPareMenuCode());

		int modCount = sysMenuInfoMapper.updateByPrimaryKey(menuInfoBean);

		return modCount;
	}
	/**
	 * 修改某条菜单信息的方法
	 *
	 * @param menu
	 *            需要修改的菜单信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modMenuNew(SysMenu menu) {
		// 存在判断
		SysMenu menuBean = this.getOneMenuNew(menu.getMenuId());
		if (menuBean == null || StringUtils.equals(menuBean.getRecStat(), "1")) {
			throw new ServiceException("菜单信息不存在：菜单代码[" + menu.getMenuId() + "]");
		}

		menuBean.setMenuDesc(menu.getMenuDesc());
		menuBean.setMenuLevel(menu.getMenuLevel());
		menuBean.setMenuName(menu.getMenuName());
		menuBean.setMenuOrder(menu.getMenuOrder());
		menuBean.setMenuStateref(menu.getMenuStateref());
		menuBean.setMenuParename(menu.getMenuParename());
		menuBean.setMenuPareid(menu.getMenuPareid() == "#" ? "" : menu.getMenuPareid());

		int modCount = sysMenuMapper.updateByPrimaryKey(menuBean);

		return modCount;
	}

	/**
	 * 根据主键物理删除某菜单信息的方法
	 * 
	 * @param menuCode
	 *            菜单代码
	 * @param menuCode
	 *            菜单名称
	 * @return
	 */
	public int delMenu(String menuCode) {
		// 存在判断
		SysMenuInfo menuInfoBean = this.getOneMenu(menuCode);
		if (menuInfoBean == null) {
			throw new ServiceException("菜单信息不存在：菜单代码[" + menuCode + "]");
		}

		int delCount = sysMenuInfoMapper.deleteByPrimaryKey(menuCode);

		return delCount;
	}

	/**
	 * 逻辑删除，即将菜单信息置为无效化的方法
	 * 
	 * @param menu
	 *            需要无效化的菜单信息
	 * @return 无效化修改完成的菜单信息数量，0为失败
	 */
	public int delModMenu(SysMenu menu) {
		// 存在判断
		SysMenu menuBean = this.getOneMenuNew(menu.getMenuId());
		if (menuBean == null || StringUtils.equals("1", menuBean.getRecStat())) {
			throw new ServiceException("菜单信息不存在：菜单代码[" + menu.getMenuId() + "]");
		}

		// 子菜单存在判断
		SysMenu subBean = new SysMenu();
		subBean.setMenuPareid(menu.getMenuId());
		subBean.setRecStat("0");
		List<SysMenu> subMenus = this.getMenuByEntityNew(subBean);
		if (subMenus != null && subMenus.size() > 0) {
			throw new ServiceException(
					"菜单[" + menuBean.getMenuId() + "|" + menuBean.getMenuTitle() + "]存在子菜单，请删除子菜单后再删除父菜单！");
		}

		menuBean.setRecStat("1");
		int modCount = sysMenuMapper.updateByPrimaryKey(menuBean);

		return modCount;
	}

	/**
	 * 根据传入的userid获取其对应的菜单列表
	 * 
	 * @param userID
	 * @return
	 */
	public Set<String> getUserMenu(String userID) {
		// 定义hashset对象，对多角色菜单列表去重
		Set<String> menuSet = new HashSet<String>();
		// 根据userID获取User信息，获取user角色（role）信息
		SysUserInfo param = new SysUserInfo();
		param.setUserId(userID);
		SysUserInfo userInfo = userservice.getUsersByEntity(param).get(0);// .getOneUser(userID);
		String roleCode = userInfo.getRoleCode();
		String roles[] = roleCode.split(",");
		SysRoleMenu roleMenu = new SysRoleMenu();
		// userinfo信息中每个角色代码（roles）后（包括最后一个角色代码）都会跟随一个逗号，组成roleCode，所以遍历长度要-1
		for (int i = 0; i < roles.length; i++) {
			// 根据每个角色信息查询角色授权的菜单列表
			roleMenu.setRoleCode(roles[i]);
			List<SysRoleMenu> roleMenuList = menuservice.getMenuAuthByEntity(roleMenu);// .getAllMenuByRoleCode(roles[i]);
			for (SysRoleMenu item : roleMenuList) {
				// 获取每个列表中的路由信息，写入hashset中
				String nodePath = this.getOneMenu(item.getMenuCode()).getNodePath();
				menuSet.add(nodePath);
			}
		}
		return menuSet;
	}

	public List<SysMenuInfo> getOtherNodes(SysMenuInfo menuInfo) {

		List<SysMenuInfo> nodesList = dao.selectList("selectOtherNodesWithPareCode", menuInfo);

		return nodesList;
	}

	public List<SysMenuInfo> reWriteMenuOrder(String menuId, String newPare, String newOrder) {

		SysMenuInfo searchBean = new SysMenuInfo();
		searchBean.setPareMenuCode(newPare.equals("#") ? "" : newPare);
		List<SysMenuInfo> nodesList = this.getOtherNodes(searchBean);

		List<SysMenuInfo> updList = new ArrayList<SysMenuInfo>();

		int i = 1;
		for (SysMenuInfo info : nodesList) {
			if (info.getMenuCode().equals(menuId)) {
				continue;
			}

			String menuOrder = "";
			if (i > Integer.parseInt(newOrder)) {
				menuOrder = String.valueOf(i + 1);
			} else {
				menuOrder = String.valueOf(i);
			}
			if (menuOrder.length() < 2) {
				menuOrder = "0" + menuOrder;
			}
			info.setMenuOrder(menuOrder);
			updList.add(info);
			i++;
		}
		SysMenuInfo record = new SysMenuInfo();
		record.setMenuCode(menuId);
		record.setRecStat("0");
		SysMenuInfo newNode = this.getMenuByEntity(record).get(0);
		if (newNode == null) {
			throw new ServiceException("菜单[" + menuId + "]信息不存在或已删除!");
		}

		newOrder = String.valueOf(Integer.parseInt(newOrder) + 1);
		if (newOrder.length() < 2) {
			newOrder = "0" + newOrder;
		}

		record = new SysMenuInfo();
		record.setMenuCode(newPare);
		record.setRecStat("0");
		List<SysMenuInfo> pareNodes = this.getMenuByEntity(record);

		if (pareNodes == null || pareNodes.size() <= 0) {
			newNode.setPareMenuCode("");
			newNode.setPareMenu("");
		} else {
			newNode.setPareMenuCode(newPare.equals("#") ? "" : newPare);
			newNode.setPareMenu(pareNodes.get(0).getMenuName());
		}

		newNode.setMenuOrder(newOrder);

		String menuLv = String.valueOf(Integer.parseInt(pareNodes.get(0).getMenuLv()) + 1);
		newNode.setMenuLv(menuLv);

		updList.add(newNode);

		return updList;
	}
	//add by lujibing 20190830 调整移动菜单存入表
	public List<SysMenu> getOtherNodesNew(SysMenu menu) {

		List<SysMenu> nodesList = dao.selectList("selectOtherNodesWithPareId", menu);

		return nodesList;
	}
	public List<SysMenu> reWriteMenuOrderNew(String menuId, String newPare, String newOrder) {

		SysMenu searchBean = new SysMenu();
		searchBean.setMenuPareid(newPare.equals("#") ? "" : newPare);
		List<SysMenu> nodesList = this.getOtherNodesNew(searchBean);

		List<SysMenu> updList = new ArrayList<SysMenu>();

		int i = 1;
		for (SysMenu info : nodesList) {
			if (info.getMenuId().equals(menuId)) {
				continue;
			}

			String menuOrder = "";
			if (i > Integer.parseInt(newOrder)) {
				menuOrder = String.valueOf(i + 1);
			} else {
				menuOrder = String.valueOf(i);
			}
			if (menuOrder.length() < 2) {
				menuOrder = "0" + menuOrder;
			}
			info.setMenuOrder(menuOrder);
			updList.add(info);
			i++;
		}
		SysMenu record = new SysMenu();
		record.setMenuId(menuId);
		record.setRecStat("0");
		SysMenu newNode = this.getMenuByEntityNew(record).get(0);
		if (newNode == null) {
			throw new ServiceException("菜单[" + menuId + "]信息不存在或已删除!");
		}

		newOrder = String.valueOf(Integer.parseInt(newOrder) + 1);
		if (newOrder.length() < 2) {
			newOrder = "0" + newOrder;
		}

		record = new SysMenu();
		record.setMenuId(newPare);
		record.setRecStat("0");
		List<SysMenu> pareNodes = this.getMenuByEntityNew(record);

		if (pareNodes == null || pareNodes.size() <= 0) {
			newNode.setMenuPareid("");
			newNode.setMenuParename("");
		} else {
			newNode.setMenuPareid(newPare.equals("#") ? "" : newPare);
			newNode.setMenuParename(pareNodes.get(0).getMenuTitle());
		}

		newNode.setMenuOrder(newOrder);

		String menuLv = String.valueOf(Integer.parseInt(pareNodes.get(0).getMenuLevel()) + 1);
		newNode.setMenuLevel(menuLv);

		updList.add(newNode);

		return updList;
	}

	/**
	 * 更新所有菜单信息的方法
	 * 
	 * @param menuList
	 *            更新所有菜单信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modAllMenu(List<SysMenu> menuList) {

		int count = 0;

		List<SysMenu> allMenuList = sysMenuMapper.selectAll();

		List<SysRoleMenu> roleMenuList = roleMenuMapper.selectAll();

		// 遍历菜单权限表
		for (SysRoleMenu roleMenu : roleMenuList) {
			// 遍历旧菜单表
			for (SysMenu oldMenu : allMenuList) {
				if (oldMenu.getMenuId().equals(roleMenu.getMenuCode())) {
					// 遍历新菜单表
					for (SysMenu newMenu : allMenuList) {
						if (oldMenu.getMenuName().equals(newMenu.getMenuName())) {
							//更新菜单权限表的菜单ID
							roleMenu.setMenuCode(newMenu.getMenuId());
							roleMenu.setMenuName(newMenu.getMenuTitle());
							roleMenuMapper.updateByPrimaryKey(roleMenu);
						}
					}
				}
			}
		}

		// 删除所有现存的menu数据
		sysMenuMapper.deleteAll();

		// 新增所有传入的menu数据
		for (SysMenu item : menuList) {
			item.setMenuIsshow("1");
			count += sysMenuMapper.insert(item);
		}

		return count;
	}

	/**
	 * 更新所有菜单信息的方法
	 * 
	 *
	 *            更新所有菜单信息
	 * @return 修改成功的条目数，0为失败
	 */
	public List<SysMenu> getAllMenuData() {

		List<SysMenu> menuList = new ArrayList<SysMenu>();

		menuList = sysMenuMapper.selectAll();

		return menuList;
	}

	/**
	 * 更新所有菜单信息的方法
	 * 
	 * @param menuId
	 *            更新所有菜单信息
	 * @return 修改成功的条目数，0为失败
	 */
	public SysMenu getOneMenuData(String menuId) {

		SysMenu menu = sysMenuMapper.selectByPrimaryKey(menuId);

		return menu;
	}

	/**
	 * 根据传入的userid获取其对应的菜单列表
	 * 
	 * @param userID
	 * @return
	 */
	public Set<String> getUserMenuAuth(String userID) {
		// 定义hashset对象，对多角色菜单列表去重
		Set<String> menuSet = new HashSet<String>();
		// 根据userID获取User信息，获取user角色（role）信息
		SysUserInfo param = new SysUserInfo();
		param.setUserId(userID);
		SysUserInfo userInfo = userservice.getUsersByEntity(param).get(0);// .getOneUser(userID);
		String roleCode = userInfo.getRoleCode();
		if(roleCode==null){
			throw new ServiceException("该用户无系统使用权限!");
		}
		String roles[] = roleCode.split(",");
		SysRoleMenu roleMenu = new SysRoleMenu();
		// userinfo信息中每个角色代码（roles）后（包括最后一个角色代码）都会跟随一个逗号，组成roleCode，所以遍历长度要-1
		for (int i = 0; i < roles.length; i++) {
			// 根据每个角色信息查询角色授权的菜单列表
			roleMenu.setRoleCode(roles[i]);
			List<SysRoleMenu> roleMenuList = menuservice.getMenuAuthByEntity(roleMenu);// .getAllMenuByRoleCode(roles[i]);
			for (SysRoleMenu item : roleMenuList) {
				// 获取每个列表中的路由信息，写入hashset中
				SysMenu sysMenu = this.getOneMenuData(item.getMenuCode());
				if(sysMenu!=null) {
					String nodePath = this.getOneMenuData(item.getMenuCode()).getMenuName();
					menuSet.add(nodePath);
				}
			}
		}
		return menuSet;
	}

}

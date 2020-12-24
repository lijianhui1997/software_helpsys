package tt.helpsys.service;

import tt.helpsys.entity.Admin;

/**
 * 管理员业务逻辑接口
 * @author TRY
 */
public interface IAdminService {
	/**
	 * 管理员登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	Admin login(String name,String pwd);
	
	/**
	 * 修改密码
	 * @param name
	 * @param pwd
	 * @return
	 */
	int updatepwd(Admin admin);
}

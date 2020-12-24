package tt.helpsys.service;

import tt.helpsys.entity.User;
import tt.helpsys.util.Page;
/**
 * 用户业务层接口
 * @author TRY
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	int userRegister(User user);
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	User userLogin(String username,String password);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	int update(User user);
	
	/**
	 * 根据用户名和分页信息查询数据
	 * @param username
	 * @param page
	 * @return
	 */
	Page searchByName(String username,Page page);
	
	/**
	 * 删除信息
	 * @param userid
	 * @return
	 */
	int deleteUser(int userid);
	
	/**
	 * 恢复状态
	 * @param userid
	 * @return
	 */
	int restoreUser(int userid);
	//修改密码
	int updatePw(User user);

	int modifyUser(int userid, int state);
	/**
	 * 验证用户名是否存在
	 * @param username 用户名
	 * @return  true：存在 false：不存在
	 */
    boolean isExist(String username);
}

 

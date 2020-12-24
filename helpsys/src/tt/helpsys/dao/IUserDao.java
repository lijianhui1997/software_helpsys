package tt.helpsys.dao;

import tt.helpsys.entity.User;
import tt.helpsys.util.Page;
/**
 * 用户数据访问接口
 * @author TRY
 *
 */
public interface IUserDao {
	int add(User user);
	int update(User user);
	User query(String username, String pw);
	Page queryByName(String username, Page page);
	/**
	 * 更新用户状态
	 * @param userid
	 * @param state
	 * @return
	 */
	int updateState(int userid, int state);
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	int updatePw(User user);
	/**
	 * 验证用户名是否存在
	 * @param username 用户名
	 * @return  true：存在 false：不存在
	 */
    boolean isExist(String username);
}

package tt.helpsys.dao.impl;

import java.util.Map;

import tt.helpsys.dao.IUserDao;
import tt.helpsys.entity.User;
import tt.helpsys.util.DBUtil;
import tt.helpsys.util.Page;
/**
 * 用户数据访问类
 * @author TRY
 *
 */
public class UserDaoImpl implements IUserDao {
	DBUtil dbutil = new DBUtil();
	
	@Override
	public User query(String username, String pw) {
		String sql = "SELECT * FROM user WHERE username = ? and password = ?";
		Object[] params = {username, pw};
		
		User user = null;

		try {
			user = (User) dbutil.getObject(User.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public int add(User user) {
		String sql = "INSERT INTO user (username, password, realname, sex,  birthday, city, email, qq) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";//hobbys,
		Object[] params = {user.getUsername(), user.getPassword(), user.getRealname(), user.getSex(),
				 user.getBirthday(), user.getCity(), user.getEmail(), user.getQq()};//user.getHobbys(),
		int res = 0;
		try {
			res = dbutil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE user SET "
				+ "password = ?, realname = ?, sex = ?, "
				+ " birthday = ?, city = ?, email = ?, qq = ? "
				+ "WHERE userid = ?";//hobbys = ?,
		Object[] params = {user.getPassword(), user.getRealname(), user.getSex(),
				user.getBirthday(), user.getCity(), user.getEmail(), user.getQq(),
				user.getUserid()};//user.getHobbys(),
		
		int res = 0;
		
		try {
			res = dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Page queryByName(String username, Page page) {
		String sql = "SELECT * FROM user WHERE username like ? ORDER BY createtime DESC";
		Object[] params = {"%"+username+"%"};
		
		Page resPage = null;
		
		try {
			resPage = dbutil.getQueryPage(User.class, sql, params, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resPage;
	}

	@Override
	public int updateState(int userid, int state) {
		String sql = "UPDATE user SET "
				+ "state = ? "
				+ "WHERE userid = ?";
		Object[] params = {state, userid};
		
		int res = 0;
		
		try {
			res = dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updatePw(User user) {
		String sql="UPDATE `user` SET  `password`=?  WHERE (`username`=?) ";
		Object[] params={user.getPassword(),user.getUsername()};
		int rs=0;
		try {
			rs=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean isExist(String username) {
		String sql="select count(*) as count from user where username=? ";
		boolean result=true;
		Map<String, Object> map=null;
				try {
					map=dbutil.getObject(sql, new Object[]{username});
					int count=Integer.parseInt(map.get("count").toString());
					if(count>0){
						result=false;//用户名已存在
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		return result;
	}


}

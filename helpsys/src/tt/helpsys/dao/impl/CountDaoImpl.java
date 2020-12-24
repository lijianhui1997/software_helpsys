package tt.helpsys.dao.impl;

import tt.helpsys.dao.ICountDao;
import tt.helpsys.util.DBUtil;
/**
 * 计数数据访问类
 * @author TRY
 *
 */
public class CountDaoImpl implements ICountDao {
		private DBUtil dbutil=new DBUtil();
	@Override
	public int updateAccessCount(int msgid) {
		String sql="UPDATE count SET accessCount=accessCount+1 WHERE msgid=?";
		Object[] params={msgid};
		int rs=0;
		try {
			rs=dbutil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}

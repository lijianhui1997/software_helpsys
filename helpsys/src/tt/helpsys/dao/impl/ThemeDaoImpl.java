package tt.helpsys.dao.impl;

import java.util.List;

import tt.helpsys.dao.IThemeDao;
import tt.helpsys.entity.Theme;
import tt.helpsys.util.DBUtil;
import tt.helpsys.util.Page;
/**
 * 主题数据访问类
 * @author TRY
 *
 */
public class ThemeDaoImpl implements IThemeDao{
		DBUtil dbutil= new DBUtil();
	@Override
	public int add(Theme theme) {
		String sql="INSERT INTO `theme` ( `thename`) VALUES (?)";
		Object[] params={theme.getThename()};
		int rs=0;
		try {
			rs=dbutil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int delete(int theid) {
		String sql="DELETE FROM theme WHERE theid=?";
		Object[] params={theid};
		int rs=0;
		try {
			rs=dbutil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List getAll() {
		String sql="SELECT * FROM theme";
		List list = null;
		try {
			list=dbutil.getQueryList(Theme.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Page query(String key, Page page) {
		String sql="SELECT * FROM theme WHERE thename LIKE ?";
		Object[] params={"%"+key+"%"};
		Page respage=null;
		respage=dbutil.getQueryPage(Theme.class, sql, params, page);
		return respage;
	}

}

package tt.helpsys.service.impl;

import java.util.List;

import tt.helpsys.dao.IThemeDao;
import tt.helpsys.dao.impl.ThemeDaoImpl;
import tt.helpsys.entity.Theme;
import tt.helpsys.service.IThemeService;
import tt.helpsys.util.Page;
/**
 * 主题业务实现类
 * @author TRY
 *
 */
public class ThemeServiceImpl implements IThemeService {
		private  IThemeDao themedao= new ThemeDaoImpl();
	@Override
	public int add(Theme theme) {
		return themedao.add(theme);
	}

	@Override
	public int delete(int theid) {
		return themedao.delete(theid);
	}

	@Override
	public List getAll() {
		return themedao.getAll();
	}

	@Override
	public Page query(String key, Page page) {
		return themedao.query(key, page);
	}

}

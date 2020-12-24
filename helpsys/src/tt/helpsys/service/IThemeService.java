package tt.helpsys.service;

import java.util.List;

import tt.helpsys.entity.Theme;
import tt.helpsys.util.Page;

/**
 * 主题业务层接口
 * @author TRY
 */
public interface IThemeService {
	int add(Theme theme);

	int delete(int theid);

	List getAll();

	Page query(String key, Page page);
}

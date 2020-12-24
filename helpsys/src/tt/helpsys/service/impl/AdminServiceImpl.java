package tt.helpsys.service.impl;

import tt.helpsys.dao.IAdminDao;
import tt.helpsys.dao.impl.AdminDaoImpl;
import tt.helpsys.entity.Admin;
import tt.helpsys.service.IAdminService;
/**
 * 管理员业务实现类
 * @author TRY
 *
 */
public class AdminServiceImpl implements IAdminService {
		IAdminDao adminDao= new AdminDaoImpl();
	@Override
	public Admin login(String username, String password) {
		return adminDao.login(username, password);
	}
	@Override
	public int updatepwd(Admin admin) {
		return adminDao.updatepwd(admin);
	}


}

package tt.helpsys.service.impl;

import java.util.Date;

import tt.helpsys.dao.ICountDao;
import tt.helpsys.dao.IMessageDao;
import tt.helpsys.dao.IReplyDao;
import tt.helpsys.dao.impl.CountDaoImpl;
import tt.helpsys.dao.impl.MessageDaoImpl;
import tt.helpsys.dao.impl.ReplyDaoImpl;
import tt.helpsys.entity.Message;
import tt.helpsys.entity.MessageCriteria;
import tt.helpsys.entity.MessageInfo;
import tt.helpsys.entity.Reply;
import tt.helpsys.service.IMessageService;
import tt.helpsys.util.Page;
/**
 * 帖子业务实现类
 * @author TRY
 *
 */

public class MessageServiceImpl implements IMessageService{
		private IMessageDao messagedao=new MessageDaoImpl();
		private ICountDao countdao=new CountDaoImpl();
		private IReplyDao replydao=new ReplyDaoImpl();
	@Override
	public int addMsg(Message message) {
		return messagedao.add(message);
	}

	@Override
	public int replyMsg(Reply reply) {
		return replydao.replyMsg(reply);
	}

	@Override
	public MessageInfo getMsg(int msgid) {
		//增加浏览量
		countdao.updateAccessCount(msgid);
		return messagedao.get(msgid);
	}

	@Override
	public Page getReply(int msgid, Page page) {
		return replydao.queryBymsgid(msgid, page);
	}

	@Override
	public Page queryNew(Page page) {
		return messagedao.queryNew(page);
	}

	@Override
	public Page queryHot(Page page) {
		return messagedao.queryHot(page);
	}

	@Override
	public Page queryTheme(Page page) {
		return messagedao.queryTheme(page);
	}

	@Override
	public int deleteMsg(int msgid) {
		return messagedao.updateState(msgid, -1);
	}

	@Override
	public int restoreMsg(int msgid) {
		return messagedao.updateState(msgid, 0);
	}

	@Override
	public int updateMsg(Message message) {
		return 0;
	}

	@Override
	public Page search(MessageCriteria messageCriteria, Page page) {
		return messagedao.query(messageCriteria, page);
	}
	
	@Override
	public long queryMsgCountByDate(Date startDate, Date endDate) {
		return messagedao.queryCountByDate(startDate, endDate);
	}

	@Override
	public long queryReplyCountByDate(Date startDate, Date endDate) {
		return replydao.queryCountByDate(startDate, endDate);
	}

}

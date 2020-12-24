package tt.helpsys.service;

import java.util.Date;

import tt.helpsys.entity.Message;
import tt.helpsys.entity.MessageCriteria;
import tt.helpsys.entity.MessageInfo;
import tt.helpsys.entity.Reply;
import tt.helpsys.util.Page;

/**
 * 帖子信息业务接口层
 * @author TRY
 */
public interface IMessageService {
	/**
	 * 发帖
	 * @param message
	 * @return
	 */
	int addMsg(Message message);
	/**
	 * 回帖
	 * @param reply
	 * @return
	 */
	int replyMsg(Reply reply);
	
	/**
	 * 根据帖子ID查询帖子信息 
	 * @param msgid
	 * @return
	 */
	MessageInfo getMsg(int msgid);
	
	/**
	 * 根据帖子ID查询回复信息
	 * @param msgid 帖子ID
	 * @param page
	 * @return
	 */
	Page getReply(int msgid,Page page);
	
	/**
	 * 查询最新
	 * @param page
	 * @return
	 */
	Page queryNew(Page page);
	
	Page queryHot(Page page);
	
	Page queryTheme(Page page);
	
	int deleteMsg(int msgid);
	
	int restoreMsg(int msgid);
	
	int updateMsg(Message message);
	
	Page search(MessageCriteria messageCriteria, Page page);
		
	long queryMsgCountByDate(Date startDate, Date endDate);	// 根据时间查发贴数量
	
	long queryReplyCountByDate(Date startDate, Date endDate);	// 根据时间回贴数量
	
}

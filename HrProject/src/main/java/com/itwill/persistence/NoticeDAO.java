package com.itwill.persistence;

import java.util.List;
import com.itwill.domain.NoticeVO;

public interface NoticeDAO {
    public List<NoticeVO> getNoticeList();
    public void insertNotice(NoticeVO vo);
    public NoticeVO getNotice(String not_id);
    public void updateNotice(NoticeVO vo);
    public void deleteNotice(String not_id);
}

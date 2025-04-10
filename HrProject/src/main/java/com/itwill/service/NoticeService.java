package com.itwill.service;

import java.util.List;
import com.itwill.domain.NoticeVO;

public interface NoticeService {
    public List<NoticeVO> getNoticeList();
    public void insertNotice(NoticeVO vo);
    public NoticeVO getNotice(String not_id);
    public void updateNotice(NoticeVO vo);
    public void deleteNotice(String not_id);
}

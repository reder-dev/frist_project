package com.itwill.employee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.employee.domain.NoticeVO;
import com.itwill.employee.persistence.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDAO noticeDAO;

    @Override
    public List<NoticeVO> getNoticeList() {
        return noticeDAO.getNoticeList();
    }

    @Override
    public void insertNotice(NoticeVO vo) {
        noticeDAO.insertNotice(vo);
    }

    @Override
    public NoticeVO getNotice(int not_id) {
        return noticeDAO.getNotice(not_id);
    }

    @Override
    public void updateNotice(NoticeVO vo) {
        noticeDAO.updateNotice(vo);
    }

    @Override
    public void deleteNotice(int not_id) {
        noticeDAO.deleteNotice(not_id);
    }
    @Override
    public void increaseViewCount(int not_id) {
        noticeDAO.increaseViewCount(not_id);
    }
    
}

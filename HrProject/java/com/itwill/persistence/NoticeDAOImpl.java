package com.itwill.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.domain.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	private static final String NAMESPACE = "com.itwill.persistence.NoticeDAO";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<NoticeVO> getNoticeList() {
        return sqlSession.selectList(NAMESPACE + ".getNoticeList");
    }

    @Override
    public void insertNotice(NoticeVO vo) {
        sqlSession.insert(NAMESPACE + ".insertNotice", vo);
    }

    @Override
    public NoticeVO getNotice(int not_id) {
        return sqlSession.selectOne(NAMESPACE + ".getNotice", not_id);
    }

    @Override
    public void updateNotice(NoticeVO vo) {
        sqlSession.update(NAMESPACE + ".updateNotice", vo);
    }

    @Override
    public void deleteNotice(int not_id) {
        sqlSession.delete(NAMESPACE + ".deleteNotice", not_id);
    }
}


package com.itwill.approval.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.approval.dto.FileDTO;

@Repository
public class FileDAO {

    private static final String NAMESPACE = "com.itwill.approval.mapper.FileMapper.";

    public void insertFile(SqlSession session, FileDTO fileDTO) {
        session.insert(NAMESPACE + "insertFile", fileDTO);
    }
}
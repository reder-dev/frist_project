package com.itwill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.domain.NoticeVO;
import com.itwill.service.NoticeService;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/main")
    public String main() {
        return "user/main";
    }
    
    // 인사관리 - 인사조회
    @GetMapping("/employee/info")
    public String personnelInfo() {
        return "user/employee/info";
    }
    
    // 인사관리 - 발령조회
    @GetMapping("/employee/appointment")
    public String personnelAppointment() {
        return "user/employee/appointment";
    }
    
    // 인사관리 - 조직도
    @GetMapping("/employee/organization")
    public String personnelOrganization() {
        return "user/employee/organization";
    }
    
    @Autowired
    private NoticeService noticeService;
    
    
    // 공지사항 목록
    @GetMapping("/notice/list")
    public String noticeList(Model model) {
        // 실제로는 서비스를 통해 공지사항 목록을 가져와 모델에 추가
    	List<NoticeVO> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "user/notice/list";
    }
    
    // 공지사항 상세
    @GetMapping("/notice/detail")
    public String noticeDetail(@RequestParam("not_id") int not_id, Model model) {
        // 실제로는 서비스를 통해 공지사항 상세 정보를 가져와 모델에 추가
    	NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "user/notice/detail";
    }
    
    // 첨부파일 다운로드
    @GetMapping("/notice/download")
    public void downloadFile(@RequestParam("fileId") String fileId) {
        // 실제로는 파일 다운로드 로직 구현
    }
}
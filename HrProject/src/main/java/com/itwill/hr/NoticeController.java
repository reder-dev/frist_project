package com.itwill.hr;

import com.itwill.domain.NoticeVO;
import com.itwill.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 공지 목록 보기
    @GetMapping("/list")
    public String list(Model model) {
        List<NoticeVO> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "notice/noticeList";
    }

    // 공지 상세 보기
    @GetMapping("/detail")
    public String detail(@RequestParam("not_id") String not_id, Model model) {
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "notice/noticeDetail";
    }

    // 공지 작성 폼
    @GetMapping("/write")
    public String writeForm() {
        return "notice/noticeWrite";
    }

    // 공지 등록 처리
    @PostMapping("/write")
    public String write(@ModelAttribute NoticeVO vo) {
        vo.setNot_register("admin");  // 예: 세션에서 추출 가능
        noticeService.insertNotice(vo);
        return "redirect:/notice/list";
    }

    // 공지 수정 폼
    @GetMapping("/edit")
    public String editForm(@RequestParam("not_id") String not_id, Model model) {
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "notice/noticeEdit";
    }

    // 공지 수정 처리
    @PostMapping("/edit")
    public String edit(@ModelAttribute NoticeVO vo) {
        vo.setNot_modifier("admin"); // 예: 로그인 사용자 ID
        noticeService.updateNotice(vo);
        return "redirect:/notice/detail?not_id=" + vo.getNot_id();
    }

    // 공지 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam("not_id") String not_id) {
        noticeService.deleteNotice(not_id);
        return "redirect:/notice/list";
    }
}

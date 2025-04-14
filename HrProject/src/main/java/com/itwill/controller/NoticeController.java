package com.itwill.controller;

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

    // ���� ��� ����
    @GetMapping("/list")
    public String list(Model model) {
        List<NoticeVO> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "notice/noticeList";
    }

    // ���� �� ����
    @GetMapping("/detail")
    public String detail(@RequestParam("not_id") String not_id, Model model) {
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "notice/noticeDetail";
    }

    // ���� �ۼ� ��
    @GetMapping("/write")
    public String writeForm() {
        return "notice/noticeWrite";
    }

    // ���� ��� ó��
    @PostMapping("/write")
    public String write(@ModelAttribute NoticeVO vo) {
        vo.setNot_register("admin");  // ��: ���ǿ��� ���� ����
        noticeService.insertNotice(vo);
        return "redirect:/notice/list";
    }

    // ���� ���� ��
    @GetMapping("/edit")
    public String editForm(@RequestParam("not_id") String not_id, Model model) {
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "notice/noticeEdit";
    }

    // ���� ���� ó��
    @PostMapping("/edit")
    public String edit(@ModelAttribute NoticeVO vo) {
        vo.setNot_modifier("admin"); // ��: �α��� ����� ID
        noticeService.updateNotice(vo);
        return "redirect:/notice/detail?not_id=" + vo.getNot_id();
    }

    // ���� ����
    @PostMapping("/delete")
    public String delete(@RequestParam("not_id") String not_id) {
        noticeService.deleteNotice(not_id);
        return "redirect:/notice/list";
    }
}

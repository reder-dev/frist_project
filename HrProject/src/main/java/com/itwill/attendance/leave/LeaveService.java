package com.itwill.attendance.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    // 연차 신청
    public Leave requestLeave(LeaveDTO dto) {
        Leave leave = new Leave(
            dto.getEmployeeId(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getReason(),
            "신청됨"
        );
        return leaveRepository.save(leave);
    }

    // 개인 연차 내역
    public List<Leave> getLeavesByEmployeeId(String employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    // 전체 연차 내역 (관리자용)
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // 상태 변경
    public Leave updateStatus(Long id, String status) {
        Optional<Leave> optional = leaveRepository.findById(id);
        if (optional.isPresent()) {
            Leave leave = optional.get();
            leave.setStatus(status);
            return leaveRepository.save(leave);
        } else {
            throw new RuntimeException("해당 연차 신청이 존재하지 않습니다. ID: " + id);
        }
    }
}

package com.itwill.approval.attendance.leave;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaveServiceTest {

    @Autowired
    private LeaveService leaveService;

    @Test
    void testLeaveRequest() {
        LeaveDTO dto = new LeaveDTO();
        dto.setEmployeeId("test003");
        dto.setStartDate(LocalDate.now().plusDays(1));
        dto.setEndDate(LocalDate.now().plusDays(2));
        dto.setReason("개인 사정");

        Leave saved = leaveService.requestLeave(dto);
        assertEquals("신청됨", saved.getStatus());
    }

    @Test
    void testChangeStatus() {
        Leave leave = leaveService.getAllLeaves().get(0);
        Leave updated = leaveService.updateStatus(leave.getId(), "반려");
        assertEquals("반려", updated.getStatus());
    }
}

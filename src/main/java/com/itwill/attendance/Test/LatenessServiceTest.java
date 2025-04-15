package com.itwill.approval.attendance.lateness;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LatenessServiceTest {

    @Autowired
    private LatenessService latenessService;

    @Test
    void testLatenessRequest() {
        LatenessDTO dto = new LatenessDTO();
        dto.setEmployeeId("test002");
        dto.setDate(LocalDate.now());
        dto.setExpectedTime(LocalTime.of(9, 0));
        dto.setActualTime(LocalTime.of(9, 20));
        dto.setReason("버스 지연");

        Lateness saved = latenessService.requestLateness(dto);
        assertEquals("신청됨", saved.getStatus());
        assertEquals("test002", saved.getEmployeeId());
    }

    @Test
    void testUpdateStatus() {
        Lateness lateness = latenessService.getAllLateness().get(0);
        Lateness updated = latenessService.updateStatus(lateness.getId(), "승인");
        assertEquals("승인", updated.getStatus());
    }
}

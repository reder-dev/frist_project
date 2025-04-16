package com.itwill.attendance.lateness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LatenessService {
	

    private final LatenessRepository latenessRepository;

    @Autowired
    public LatenessService(LatenessRepository latenessRepository) {
        this.latenessRepository = latenessRepository;
    }

    // 지각 사유 제출 (신청)
    public Lateness requestLateness(LatenessDTO dto) {
        Lateness lateness = new Lateness(
            dto.getEmployeeId(),
            dto.getDate(),
            dto.getCheckInTime(),
            dto.getReason()
        );
        return latenessRepository.save(lateness);
    }

    // 특정 사원의 지각 내역 조회
    public List<Lateness> getLatenessByEmployeeId(String employeeId) {
        return latenessRepository.findByEmployeeId(employeeId);
    }

    // 전체 지각 내역 조회 (관리자용)
    public List<Lateness> getAllLateness() {
        return latenessRepository.findAll();
    }

    // 지각 사유서 상태 변경 (승인/반려/대기)
    public Lateness updateStatus(Long id, String status) {
        Optional<Lateness> optional = latenessRepository.findById(id);	
        if (optional.isPresent()) {
            Lateness lateness = optional.get();
            lateness.setStatus(status);
            return latenessRepository.save(lateness);
        } else {
            throw new RuntimeException("지각 기록을 찾을 수 없습니다. ID: " + id);
        }
    }
}

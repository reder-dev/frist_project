package com.itwill.approval.attendance.lateness;

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

    // 지각 신청
    public Lateness requestLateness(LatenessDTO dto) {
        Lateness lateness = new Lateness(
            dto.getEmployeeId(),
            dto.getDate(),
            dto.getExpectedTime(),
            dto.getActualTime(),
            dto.getReason(),
            "신청됨"
        );
        return latenessRepository.save(lateness);
    }

    // 내 지각 신청 내역
    public List<Lateness> getLatenessByEmployeeId(String employeeId) {
        return latenessRepository.findByEmployeeId(employeeId);
    }

    // 전체 지각 내역
    public List<Lateness> getAllLateness() {
        return latenessRepository.findAll();
    }

    // 상태 업데이트
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

package com.itwill.attendance.record;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    //출근 처리
    public Attendance checkIn(String employeeId) {
        LocalDate today = LocalDate.now();

        // 이미 출근했는지 확인
        Attendance existing = attendanceRepository.findByEmployeeIdAndDate(employeeId, today);
        if (existing != null) {
            throw new IllegalStateException("이미 출근 기록이 있습니다.");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setDate(today);
        attendance.setCheckIn(LocalTime.now());

        // 지각 기준 설정 (예: 9시 이전까지 출근해야 정상)
        if (attendance.getCheckIn().isAfter(LocalTime.of(9, 0))) {
            attendance.setWorkType("지각");
            attendance.setLatenessMinutes((int) java.time.Duration.between(LocalTime.of(9, 0), attendance.getCheckIn()).toMinutes());
            attendance.setReason(""); // 사용자가 입력한 사유는 프론트에서 별도 제출 가능
            attendance.setApprovalStatus("대기"); // 관리자 승인 필요
        } else {
            attendance.setWorkType("일반");
        }

        return attendanceRepository.save(attendance);
    }

    //퇴근 처리
    public Attendance checkOut(String employeeId) {
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, today);

        if (attendance == null) {
            throw new IllegalStateException("출근 기록이 없습니다.");
        }

        if (attendance.getCheckOut() != null) {
            throw new IllegalStateException("이미 퇴근 처리된 상태입니다.");
        }

        attendance.setCheckOut(LocalTime.now());
        return attendanceRepository.save(attendance);
    }

    //오늘 내 근태 보기
    public Attendance getTodayAttendance(String employeeId) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, LocalDate.now());
    }

    //날짜별 전체 출결 조회 (관리자용)
    public List<Attendance> getAllAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    //내 출결 이력 조회
    public List<Attendance> getAttendanceHistory(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    //관리자: 특정 사원의 이력 조회 (PathVariable 기반)
    public List<Attendance> getAttendanceHistoryById(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    //관리자: 승인 상태 변경
    public String updateApprovalStatus(Long attendanceId, String status) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("출결 ID를 찾을 수 없습니다."));
        attendance.setApprovalStatus(status);
        attendanceRepository.save(attendance);
        return "승인 상태가 [" + status + "]로 변경되었습니다.";
    }

    //관리자: 부서별 출결 조회 (추가 개발 필요 - 부서 정보 테이블 필요 시 확장 가능)
    public List<Attendance> getByDepartment(String departmentName) {
        // 현재는 구현하지 않음 → 부서 기준 조회는 Employee Entity와 연동 필요
        throw new UnsupportedOperationException("부서별 출결 조회는 추후 구현 예정입니다.");
    }

    //지각 기록 조회 (해당 사원의 지각 내역만)
    public List<Attendance> getLatenessRecords(String employeeId, LocalDate start, LocalDate end) {
        List<Attendance> all = attendanceRepository.findByEmployeeId(employeeId);
        return all.stream()
                .filter(a -> a.getDate().compareTo(start) >= 0 && a.getDate().compareTo(end) <= 0)
                .filter(a -> "지각".equals(a.getWorkType()))
                .collect(Collectors.toList());
    }

    //누적 근무 시간/일수 요약 (간단 버전)
    public String getWorkSummary(String employeeId, LocalDate start, LocalDate end) {
        List<Attendance> list = attendanceRepository.findByEmployeeId(employeeId);
        long totalMinutes = 0;
        int totalDays = 0;

        for (Attendance a : list) {
            if (a.getDate().compareTo(start) >= 0 && a.getDate().compareTo(end) <= 0
                    && a.getCheckIn() != null && a.getCheckOut() != null) {
                totalMinutes += java.time.Duration.between(a.getCheckIn(), a.getCheckOut()).toMinutes();
                totalDays++;
            }
        }

        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        return "총 근무일: " + totalDays + "일, 누적 근무 시간: " + hours + "시간 " + minutes + "분";
    }

    //근무 형태로 조회
    public List<Attendance> getByWorkType(String employeeId, String workType) {
        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .filter(a -> workType.equals(a.getWorkType()))
                .collect(Collectors.toList());
    }

    //PDF/Excel 다운로드용 Stub (나중에 구현)
    public String exportExcel(String employeeId) {
        return "[Excel 파일 다운로드] 준비 중...";
    }

    public String exportPdf(String employeeId) {
        return "[PDF 파일 다운로드] 준비 중...";
    }
}

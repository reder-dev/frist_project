<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>퇴사 신청</h2>
    
    <div class="resignation-container">
        <div class="alert alert-warning">
            <i class="fas fa-exclamation-triangle"></i>
            <strong>주의:</strong> 퇴사 신청은 관리자 승인 후 처리됩니다. 신청 전 부서장과 충분한 협의를 진행해주세요.
        </div>
        
        <div class="employee-info-box">
            <h4>신청자 정보</h4>
            <table class="info-table">
                <tr>
                    <th>이름</th>
                    <td>${employee.name}</td>
                    <th>사원번호</th>
                    <td>${employee.emp_id}</td>
                </tr>
                <tr>
                    <th>부서</th>
                    <td>${employee.department}</td>
                    <th>직급</th>
                    <td>${employee.position}</td>
                </tr>
                <tr>
                    <th>입사일</th>
                    <td><fmt:formatDate value="${employee.hireDate}" pattern="yyyy-MM-dd" /></td>
                    <th>근속기간</th>
                    <td>${employee.yearsOfService}년 ${employee.monthsOfService}개월</td>
                </tr>
            </table>
        </div>
        
        <form action="<c:url value='/user/employee/resignation' />" method="post" class="resignation-form">
            <input type="hidden" name="emp_id" value="${employee.emp_id}">
            
            <div class="form-section">
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="resignationDate">희망 퇴사일 <span class="required">*</span></label>
                        <input type="date" id="resignationDate" name="resignationDate" class="form-control" required>
                        <small class="form-text">* 희망 퇴사일은 오늘로부터 최소 30일 이후로 설정해주세요.</small>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="resignationType">퇴사 유형 <span class="required">*</span></label>
                        <select id="resignationType" name="resignationType" class="form-control" required>
                            <option value="">선택해주세요</option>
                            <option value="개인사정">개인사정</option>
                            <option value="이직">이직</option>
                            <option value="건강상의 이유">건강상의 이유</option>
                            <option value="학업">학업</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="resignationReason">퇴사 사유 <span class="required">*</span></label>
                        <textarea id="resignationReason" name="resignationReason" rows="5" class="form-control" required></textarea>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="handoverPlan">업무 인수인계 계획 <span class="required">*</span></label>
                        <textarea id="handoverPlan" name="handoverPlan" rows="5" class="form-control" required></textarea>
                        <small class="form-text">* 현재 담당하고 있는 업무와 인수인계 계획을 상세히 작성해주세요.</small>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="contactAfterResignation">퇴사 후 연락처</label>
                        <input type="text" id="contactAfterResignation" name="contactAfterResignation" class="form-control" value="${employee.phone}">
                        <small class="form-text">* 퇴사 후 연락 가능한 연락처를 입력해주세요.</small>
                    </div>
                </div>
            </div>
            
            <div class="agreement-section">
                <h4>퇴사 신청 동의사항</h4>
                <div class="agreement-content">
                    <p>1. 퇴사 신청 후 관리자 승인이 필요하며, 승인 전까지는 취소가 가능합니다.</p>
                    <p>2. 퇴사일까지 성실히 근무하며, 업무 인수인계를 완료해야 합니다.</p>
                    <p>3. 회사 자산(노트북, 출입카드 등)은 퇴사일에 반납해야 합니다.</p>
                    <p>4. 퇴직금 및 미지급 급여는 퇴사 후 14일 이내에 지급됩니다.</p>
                    <p>5. 퇴사 후에도 회사의 기밀정보 보호 의무는 유지됩니다.</p>
                </div>
                <div class="agreement-check">
                    <input type="checkbox" id="agreement" name="agreement" required>
                    <label for="agreement">위 내용을 모두 확인하였으며, 이에 동의합니다.</label>
                </div>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-danger">퇴사 신청</button>
                <a href="<c:url value='/user/employee/info' />" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.resignation-form');
    const resignationDateInput = document.getElementById('resignationDate');
    
    // 오늘 날짜 구하기
    const today = new Date();
    
    // 최소 퇴사일 설정 (오늘로부터 30일 후)
    const minResignationDate = new Date(today);
    minResignationDate.setDate(today.getDate() + 30);
    
    // 날짜 포맷 변환 함수 (YYYY-MM-DD)
    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }
    
    // 최소 퇴사일 설정
    resignationDateInput.min = formatDate(minResignationDate);
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // 희망 퇴사일 유효성 검사
        const resignationDate = new Date(resignationDateInput.value);
        
        if (resignationDate < minResignationDate) {
            alert('희망 퇴사일은 오늘로부터 최소 30일 이후로 설정해주세요.');
            return;
        }
        
        // 폼 제출 전 확인
        if (confirm('퇴사 신청을 제출하시겠습니까?')) {
            this.submit();
        }
    });
});
</script>

<jsp:include page="../../common/footer.jsp" />
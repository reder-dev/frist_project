<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>인사정보 수정</h2>
    
    <div class="edit-form-container">
        <form action="<c:url value='/user/employee/edit' />" method="post" class="employee-form">
            <input type="hidden" name="emp_id" value="${employee.emp_id}">
            
            <div class="form-section">
                <h4>기본 정보</h4>
                <div class="form-row">
                    <div class="form-group">
                        <label for="name">이름</label>
                        <input type="text" id="name" name="name" value="${employee.name}" readonly class="form-control readonly">
                    </div>
                    <div class="form-group">
                        <label for="email">이메일</label>
                        <input type="email" id="email" name="email" value="${employee.email}" class="form-control">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="phone">연락처</label>
                        <input type="text" id="phone" name="phone" value="${employee.phone}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="birthDate">생년월일</label>
                        <input type="date" id="birthDate" name="birthDate" 
                            value="<fmt:formatDate value='${employee.birthDate}' pattern='yyyy-MM-dd' />" 
                            readonly class="form-control readonly">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="address">주소</label>
                        <input type="text" id="address" name="address" value="${employee.address}" class="form-control">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="emergencyContact">비상연락처</label>
                        <input type="text" id="emergencyContact" name="emergencyContact" value="${employee.emergencyContact}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="emergencyContactName">비상연락인</label>
                        <input type="text" id="emergencyContactName" name="emergencyContactName" value="${employee.emergencyContactName}" class="form-control">
                    </div>
                </div>
            </div>
            
            <div class="form-section">
                <h4>인사 정보</h4>
                <div class="form-row">
                    <div class="form-group">
                        <label for="emp_id">사원번호</label>
                        <input type="text" id="emp_id" value="${employee.emp_id}" readonly class="form-control readonly">
                    </div>
                    <div class="form-group">
                        <label for="hireDate">입사일</label>
                        <input type="date" id="hireDate" 
                            value="<fmt:formatDate value='${employee.hireDate}' pattern='yyyy-MM-dd' />" 
                            readonly class="form-control readonly">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="department">부서</label>
                        <input type="text" id="department" value="${employee.department}" readonly class="form-control readonly">
                    </div>
                    <div class="form-group">
                        <label for="position">직급</label>
                        <input type="text" id="position" value="${employee.position}" readonly class="form-control readonly">
                    </div>
                </div>
            </div>
            
            <div class="form-section">
                <h4>급여 정보</h4>
                <div class="form-row">
                    <div class="form-group">
                        <label for="bank">은행</label>
                        <select id="bank" name="bank" class="form-control">
                            <option value="국민은행" ${employee.bank == '국민은행' ? 'selected' : ''}>국민은행</option>
                            <option value="신한은행" ${employee.bank == '신한은행' ? 'selected' : ''}>신한은행</option>
                            <option value="우리은행" ${employee.bank == '우리은행' ? 'selected' : ''}>우리은행</option>
                            <option value="하나은행" ${employee.bank == '하나은행' ? 'selected' : ''}>하나은행</option>
                            <option value="농협은행" ${employee.bank == '농협은행' ? 'selected' : ''}>농협은행</option>
                            <option value="기업은행" ${employee.bank == '기업은행' ? 'selected' : ''}>기업은행</option>
                            <option value="카카오뱅크" ${employee.bank == '카카오뱅크' ? 'selected' : ''}>카카오뱅크</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="accountNumber">계좌번호</label>
                        <input type="text" id="accountNumber" name="accountNumber" value="${employee.accountNumber}" class="form-control">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="accountHolder">예금주</label>
                        <input type="text" id="accountHolder" name="accountHolder" value="${employee.accountHolder}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="baseSalary">기본급</label>
                        <input type="text" id="baseSalary" value="<fmt:formatNumber value='${employee.baseSalary}' pattern='#,###' />원" readonly class="form-control readonly">
                    </div>
                </div>
            </div>
            
            <div class="form-section">
                <h4>비밀번호 변경</h4>
                <div class="form-row">
                    <div class="form-group">
                        <label for="currentPassword">현재 비밀번호</label>
                        <input type="password" id="currentPassword" name="currentPassword" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="newPassword">새 비밀번호</label>
                        <input type="password" id="newPassword" name="newPassword" class="form-control">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="confirmPassword">비밀번호 확인</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control">
                    </div>
                    <div class="form-group">
                        <div class="password-rules">
                            <p>* 비밀번호는 8자 이상, 영문/숫자/특수문자 조합으로 입력해주세요.</p>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">저장하기</button>
                <a href="<c:url value='/user/employee/info' />" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.employee-form');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // 비밀번호 변경 시 유효성 검사
        const currentPassword = document.getElementById('currentPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        
        // 비밀번호를 변경하려는 경우
        if (newPassword || confirmPassword) {
            if (!currentPassword) {
                alert('현재 비밀번호를 입력해주세요.');
                return;
            }
            
            if (newPassword !== confirmPassword) {
                alert('새 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
                return;
            }
            
            // 비밀번호 규칙 검사 (8자 이상, 영문/숫자/특수문자 조합)
            const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
            if (!passwordRegex.test(newPassword)) {
                alert('비밀번호는 8자 이상, 영문/숫자/특수문자 조합으로 입력해주세요.');
                return;
            }
        }
        
        // 폼 제출
        this.submit();
    });
});
</script>

<jsp:include page="../../common/footer.jsp" />
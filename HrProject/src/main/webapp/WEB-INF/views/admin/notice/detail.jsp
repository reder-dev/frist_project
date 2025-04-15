<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 상세</h2>
    
    <div class="notice-detail">
        <div class="notice-header">
            <h3 class="notice-title">${notice.not_ti}</h3>
            <div class="notice-info">
                <span class="info-item">
                    <i class="fas fa-user"></i> 작성자: ${notice.not_register}
                </span>
                <span class="info-item">
                    <i class="fas fa-calendar-alt"></i> 작성일: 
                    <fmt:formatDate value="${notice.not_wd}" pattern="yyyy-MM-dd HH:mm" />
                </span>
                <span class="info-item">
                    <i class="fas fa-eye"></i> 조회수: ${notice.view_count}
                </span>
            </div>
        </div>
        
        <div class="notice-content">
            ${notice.not_cn}
        </div>
        
        <c:if test="${not empty notice.not_file}">
            <div class="notice-attachments">
                <h4>첨부파일</h4>
                <div class="attachment-item">
                    <i class="fas fa-file"></i>
                    <a href="<c:url value='/user/notice/download?fileId=${notice.not_file}' />" class="attachment-link">
                        첨부파일 다운로드
                    </a>
                </div>
            </div>
        </c:if>
        
        <div class="notice-actions">
            <a href="<c:url value='/user/notice/list' />" class="btn btn-secondary">목록으로</a>
        </div>
    </div>
    
    <!-- 샘플 데이터 (실제로는 컨트롤러에서 모델에 담아 전달) -->
    <c:if test="${empty notice}">
        <script>
            // 샘플 데이터 표시를 위한 스크립트
            document.querySelector('.notice-title').textContent = '2025년 상반기 인사발령 안내';
            document.querySelector('.notice-info .info-item:nth-child(1)').innerHTML = '<i class="fas fa-user"></i> 작성자: 관리자';
            document.querySelector('.notice-info .info-item:nth-child(2)').innerHTML = '<i class="fas fa-calendar-alt"></i> 작성일: 2025-03-27 09:30';
            document.querySelector('.notice-info .info-item:nth-child(3)').innerHTML = '<i class="fas fa-eye"></i> 조회수: 45';
            
            document.querySelector('.notice-content').innerHTML = `
                <p>안녕하세요, HR Genie 임직원 여러분.</p>
                <p>2025년 상반기 인사발령 내용을 아래와 같이 안내드립니다.</p>
                <br>
                <h4>1. 발령 일자</h4>
                <p>- 2025년 4월 1일부터 적용</p>
                <br>
                <h4>2. 주요 발령 내용</h4>
                <p>- 개발팀 김개발 대리 → 과장 승진</p>
                <p>- 인사팀 이인사 사원 → 대리 승진</p>
                <p>- 마케팅팀 박마케 과장 → 영업팀 전보</p>
                <p>- 신입사원 5명 인사팀 배치</p>
                <br>
                <h4>3. 기타 안내사항</h4>
                <p>- 발령 관련 상세 내용은 첨부파일을 참고해 주세요.</p>
                <p>- 문의사항은 인사팀(내선: 1234)으로 연락 바랍니다.</p>
                <br>
                <p>감사합니다.</p>
                <p>HR Genie 인사팀 드림</p>
            `;
            
            // 첨부파일 영역 표시
            document.querySelector('.notice-attachments').style.display = 'block';
        </script>
    </c:if>
</div>

<jsp:include page="../../common/footer.jsp" />
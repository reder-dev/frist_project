<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="dashboard" />
</jsp:include>

<div class="content">
    <div class="admin-dashboard">
        <h2>관리자 대시보드</h2>
        
        <div class="dashboard-stats">
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-info">
                    <h3>총 직원 수</h3>
                    <p class="stat-value">125</p>
                    <p class="stat-change positive">+3 (이번 달)</p>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-user-clock"></i>
                </div>
                <div class="stat-info">
                    <h3>오늘 출근</h3>
                    <p class="stat-value">118</p>
                    <p class="stat-change">출근율: 94.4%</p>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-file-alt"></i>
                </div>
                <div class="stat-info">
                    <h3>결재 대기</h3>
                    <p class="stat-value">12</p>
                    <p class="stat-change negative">+5 (어제 대비)</p>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="fas fa-calendar-alt"></i>
                </div>
                <div class="stat-info">
                    <h3>이번 달 휴가</h3>
                    <p class="stat-value">28</p>
                    <p class="stat-change">승인 대기: 5</p>
                </div>
            </div>
        </div>
        
        <div class="dashboard-row">
            <div class="dashboard-chart">
                <h3>부서별 인원 현황</h3>
                <div class="chart-container">
                    <div class="chart-bar">
                        <div class="bar-label">인사팀</div>
                        <div class="bar-value" style="width: 15%;">15</div>
                    </div>
                    <div class="chart-bar">
                        <div class="bar-label">개발팀</div>
                        <div class="bar-value" style="width: 35%;">35</div>
                    </div>
                    <div class="chart-bar">
                        <div class="bar-label">영업팀</div>
                        <div class="bar-value" style="width: 25%;">25</div>
                    </div>
                    <div class="chart-bar">
                        <div class="bar-label">마케팅팀</div>
                        <div class="bar-value" style="width: 20%;">20</div>
                    </div>
                    <div class="chart-bar">
                        <div class="bar-label">경영지원팀</div>
                        <div class="bar-value" style="width: 30%;">30</div>
                    </div>
                </div>
            </div>
            
            <div class="dashboard-calendar">
                <h3>이번 달 일정</h3>
                <table class="calendar-table">
                    <thead>
                        <tr>
                            <th class="sun">일</th>
                            <th>월</th>
                            <th>화</th>
                            <th>수</th>
                            <th>목</th>
                            <th>금</th>
                            <th class="sat">토</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                            <td>5</td>
                            <td>6</td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>8</td>
                            <td class="has-event">9</td>
                            <td>10</td>
                            <td>11</td>
                            <td>12</td>
                            <td>13</td>
                        </tr>
                        <tr>
                            <td>14</td>
                            <td>15</td>
                            <td>16</td>
                            <td class="has-event">17</td>
                            <td>18</td>
                            <td>19</td>
                            <td>20</td>
                        </tr>
                        <tr>
                            <td>21</td>
                            <td>22</td>
                            <td>23</td>
                            <td>24</td>
                            <td class="has-event">25</td>
                            <td>26</td>
                            <td>27</td>
                        </tr>
                        <tr>
                            <td>28</td>
                            <td>29</td>
                            <td>30</td>
                            <td>31</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div class="calendar-events">
                    <div class="event">
                        <span class="event-date">9일</span>
                        <span class="event-title">경영회의</span>
                    </div>
                    <div class="event">
                        <span class="event-date">17일</span>
                        <span class="event-title">신입사원 교육</span>
                    </div>
                    <div class="event">
                        <span class="event-date">25일</span>
                        <span class="event-title">부서장 회의</span>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="dashboard-row">
            <div class="recent-notices">
                <h3>최근 공지사항</h3>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><a href="#">2025년 상반기 인사발령 안내</a></td>
                            <td>관리자</td>
                            <td>2025-03-27</td>
                            <td>45</td>
                        </tr>
                        <tr>
                            <td><a href="#">사내 시스템 업데이트 안내</a></td>
                            <td>관리자</td>
                            <td>2025-03-25</td>
                            <td>38</td>
                        </tr>
                        <tr>
                            <td><a href="#">4월 회식 일정 안내</a></td>
                            <td>관리자</td>
                            <td>2025-03-23</td>
                            <td>42</td>
                        </tr>
                    </tbody>
                </table>
                <div class="view-more">
                    <a href="<c:url value='/admin/notice/manage' />" class="btn btn-outline">더보기</a>
                </div>
            </div>
            
            <div class="pending-approvals">
                <h3>결재 대기 문서</h3>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>문서번호</th>
                            <th>제목</th>
                            <th>신청자</th>
                            <th>신청일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>AP-2025-042</td>
                            <td><a href="#">휴가 신청서</a></td>
                            <td>홍길동</td>
                            <td>2025-03-27</td>
                        </tr>
                        <tr>
                            <td>AP-2025-041</td>
                            <td><a href="#">지출 결의서</a></td>
                            <td>김영희</td>
                            <td>2025-03-26</td>
                        </tr>
                        <tr>
                            <td>AP-2025-040</td>
                            <td><a href="#">업무 보고서</a></td>
                            <td>이철수</td>
                            <td>2025-03-26</td>
                        </tr>
                    </tbody>
                </table>
                <div class="view-more">
                    <a href="<c:url value='/admin/approval/manage' />" class="btn btn-outline">더보기</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
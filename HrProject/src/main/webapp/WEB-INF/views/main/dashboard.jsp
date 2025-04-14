<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/sidebar.jsp">
    <jsp:param name="menu" value="dashboard" />
</jsp:include>

<div class="content">
    <div class="row">
        <div class="col-md-8">
            <div class="calendar-container">
                <div class="calendar-header">
                    <h3>이번달 일정 캘린더</h3>
                </div>
                
                <table class="calendar-table">
                    <thead>
                        <tr>
                            <th class="sun">일 SUN</th>
                            <th>월 MON</th>
                            <th>화 TUE</th>
                            <th>수 WED</th>
                            <th>목 THU</th>
                            <th>금 FRI</th>
                            <th class="sat">토 SAT</th>
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
                            <td>9</td>
                            <td>10</td>
                            <td>11</td>
                            <td>12</td>
                            <td>13</td>
                        </tr>
                        <tr>
                            <td>14</td>
                            <td>15</td>
                            <td>16</td>
                            <td>17</td>
                            <td>18</td>
                            <td>19</td>
                            <td>20</td>
                        </tr>
                        <tr>
                            <td>21</td>
                            <td>22</td>
                            <td>23</td>
                            <td>24</td>
                            <td>25</td>
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
                
                <div class="todo-list">
                    <h4>TO DO LIST</h4>
                    <div class="todo-item">
                        <input type="checkbox" id="todo1">
                        <label for="todo1">업무 보고서 작성</label>
                    </div>
                    <div class="todo-item">
                        <input type="checkbox" id="todo2">
                        <label for="todo2">팀 미팅 준비</label>
                    </div>
                    <div class="todo-item">
                        <input type="checkbox" id="todo3">
                        <label for="todo3">프로젝트 일정 확인</label>
                    </div>
                    <div class="todo-item">
                        <input type="checkbox" id="todo4">
                        <label for="todo4">신입사원 교육 자료 준비</label>
                    </div>
                    <div class="todo-item">
                        <input type="checkbox" id="todo5">
                        <label for="todo5">월간 보고서 검토</label>
                    </div>
                </div>
                
                <div class="memo-area">
                    <h4>MEMO</h4>
                    <textarea placeholder="메모를 입력하세요..."></textarea>
                </div>
            </div>
        </div>
        
        <div class="col-md-4">
            <div class="profile-card">
                <img src="<c:url value='/resources/images/profile.png' />" alt="프로필 이미지" class="profile-image">
                <div class="profile-info">
                    <h3>홍길동 님</h3>
                    <p>부서/직급</p>
                </div>
            </div>
            
            <div class="company-info">
                <h3>회사 정보 및 회사 관련 내용</h3>
                <p>회사 정보 및 회사 관련 내용.....</p>
                <p>회사 정보 및 회사 관련 내용.....</p>
                <p>회사 정보 및 회사 관련 내용.....</p>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
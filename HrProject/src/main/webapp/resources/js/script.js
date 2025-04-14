document.addEventListener('DOMContentLoaded', function() {
    // 메뉴 토글 기능
    const menuToggles = document.querySelectorAll('.menu-toggle');
    
    menuToggles.forEach(toggle => {
        toggle.addEventListener('click', function(e) {
            e.preventDefault();
            
            // 현재 클릭된 메뉴 아이템
            const menuItem = this.parentElement;
            
            // 토글 클래스 추가/제거
            this.classList.toggle('open');
            
            // 서브메뉴 찾기
            const submenu = menuItem.querySelector('.submenu');
            if (submenu) {
                submenu.classList.toggle('open');
            }
        });
    });
    
    // 현재 활성화된 메뉴의 서브메뉴 자동 열기
    const activeMenuItems = document.querySelectorAll('.menu-item.active');
    activeMenuItems.forEach(item => {
        const submenu = item.querySelector('.submenu');
        if (submenu) {
            submenu.classList.add('open');
            const menuToggle = item.querySelector('.menu-toggle');
            if (menuToggle) {
                menuToggle.classList.add('open');
            }
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    // 세션 시작 시간 설정 (현재 시간)
    let sessionStartTime = new Date();
    
    // 세션 타임아웃 시간 (60분 = 3600초)
    const sessionTimeout = 60 * 60;
    
    // 세션 만료 시간 계산
    let sessionExpireTime = new Date(sessionStartTime.getTime() + (sessionTimeout * 1000));
    
    // 타이머 요소
    const timerElement = document.getElementById('session-time');
    
    // 세션 연장 버튼
    const extendButton = document.getElementById('extend-session');
    
    // 타이머 업데이트 함수
    function updateTimer() {
        const currentTime = new Date();
        const timeDiff = Math.floor((sessionExpireTime - currentTime) / 1000);
        
        if (timeDiff <= 0) {
            // 세션 만료
            clearInterval(timerInterval);
            alert('세션이 만료되었습니다. 로그인 페이지로 이동합니다.');
            window.location.href = 'login.jsp'; // 로그인 페이지 경로
        } else {
            // 남은 시간 계산
            const minutes = Math.floor(timeDiff / 60);
            const seconds = timeDiff % 60;
            
            // 타이머 표시 업데이트
            timerElement.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
            
            // 남은 시간이 5분 이하면 경고 스타일 적용
            if (timeDiff <= 300) {
                timerElement.classList.add('warning');
            } else {
                timerElement.classList.remove('warning');
            }
        }
    }
    
    // 세션 연장 함수
    function extendSession() {
        // 현재 시간 기준으로 60분 연장
        sessionExpireTime = new Date(new Date().getTime() + (sessionTimeout * 1000));
        
        // 경고 스타일 제거
        timerElement.classList.remove('warning');
        
        // 알림 표시
        const notification = document.createElement('div');
        notification.className = 'session-notification';
        notification.textContent = '세션이 60분 연장되었습니다.';
        document.body.appendChild(notification);
        
        // 3초 후 알림 제거
        setTimeout(() => {
            notification.classList.add('fade-out');
            setTimeout(() => {
                document.body.removeChild(notification);
            }, 500);
        }, 3000);
    }
    
    // 세션 연장 버튼 클릭 이벤트
    extendButton.addEventListener('click', extendSession);
    
    // 사용자 활동 감지 (마우스 이동, 키보드 입력)
    document.addEventListener('mousemove', resetInactivityTimer);
    document.addEventListener('keypress', resetInactivityTimer);
    
    let inactivityTimer;
    
    // 비활성 타이머 리셋 함수
    function resetInactivityTimer() {
        clearTimeout(inactivityTimer);
        inactivityTimer = setTimeout(() => {
            // 30분 동안 활동이 없으면 경고 표시
            const confirmExtend = confirm('30분 동안 활동이 없었습니다. 세션을 연장하시겠습니까?');
            if (confirmExtend) {
                extendSession();
            }
        }, 30 * 60 * 1000); // 30분
    }
    
    // 초기 비활성 타이머 설정
    resetInactivityTimer();
    
    // 1초마다 타이머 업데이트
    const timerInterval = setInterval(updateTimer, 1000);
    
    // 초기 타이머 업데이트
    updateTimer();
});





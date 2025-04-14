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
/**
 * post/modify.jsp 파일에 포함.
 * 포스트 업데이트 & 삭제 기능.
 */

document.addEventListener('DOMContentLoaded', () => {
    const modifyForm = document.querySelector('form#modifyForm');
    const inputId = document.querySelector('input#id');
    const inputTitle = document.querySelector('input#title');
    const textContent = document.querySelector('textarea#content');
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    // 삭제 버튼에 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click', () => {
        const result = confirm('삭제 하시겠습니까?');
        if (result) {
            // 사용자가 [확인]을 클릭하면 GET 방식의 /post/delete?id=번호 요청을 보냄.
            location.href = `delete?id=${inputId.value}`;
        }
    });
    
    btnUpdate.addEventListener('click', () => {
        // 제목과 내용이 비어있는지 확인.
        if (inputTitle.value === '' || textContent.value === '') {
            alert('제목과 내용은 반드시 입력하세요.');
            return;
        }
        
        // 업데이트한 내용을 저장할 것인지 확인.
        const result = confirm('변경 내용을 저장 하시겠습니까?');
        if (result) {
            // 폼 제출 방식(method)와 요청 주소(action)를 설정하고, 양식 데이터를 보냄.
            modifyForm.method = 'post'
            modifyForm.action = 'update';
            modifyForm.submit();
        }
        
    });
    
});
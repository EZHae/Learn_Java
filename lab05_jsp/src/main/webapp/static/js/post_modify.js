/**
 * modify.jsp에 포함
 */

document.addEventListener('DOMContentLoaded', () => {
    const modifyForm = document.querySelector('form#modifyForm');
    const inputId = document.querySelector('input#id');
    const inputTitle = document.querySelector('input#title');
    const inputContent = document.querySelector('textarea#content');
    const btnDelete = document.querySelector("button#btnDelete");
    const btnUpdate = document.querySelector("button#btnUpdate");
    
    btnDelete.addEventListener('click', () => {
        const delResult = confirm('삭제 하시겠습니까?');
        console.log(`delete confirm result = ${delResult}`);
        if (delResult) {
            // 새로운 요청 주소로 요청을 보냄.
            location.href = `delete?id=${inputId.value}`;
            // http://localhost:8080/jsp2/post/modify?id=* URL 에서
            // http://localhost:8080/jsp2/post/delete?id=* 로 요청 주소를 변경.
        }
    });

    
    btnUpdate.addEventListener('click', () => {
        // 제목에 입력된 값, 내용에 입력된 값을 읽음.
        const title = inputTitle.value;
        const content = inputContent.value;
        
        // 제목과 내용이 비어 있는 지를 체크 -> 비어 있으면 alert() 함수를 호출 & 함수 종료
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야합니다.');
            return;
        }
        
        // confirm() 함수를 호출해서 수정된 내용을 저장할 지 확인.
        const upResult = confirm('수정 하시겠습니까?');
        
        // 사용자가 [확인]을 선택한 경우 양식 데이터(form data)를 제출(submit)
        if (upResult) {
            modifyForm.method = 'post'; // 요청 방식을 post로 설정
            modifyForm.action = 'update'; // 요청 주소를 localhost:8080/jsp2/post/update 로 변경
            modifyForm.submit(); // 양식 데이터 제출. (서버로 요청을 보냄.)
        }
        
    });    
});

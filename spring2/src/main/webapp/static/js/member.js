/**
 * username 중복체크, email 중복체크
 * user/signup.jsp 파일에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    // username 중복 체크 결과를 저장할 변수.
    // true이면 회원가입이 가능한 username. false이면 [작성완료] 버튼은 비활성화.
    let isUsernameChecked = false;
    
    // password를 입력했는 지 여부를 저장할 변수. false이면 [작성완료] 버튼은 비활성화.
    let isPasswordChecked = false;
    
    // email 중복 체크 결과를 저장할 변수.
    // true이면 회원가입이 가능한 email. false이면 [작성완료] 버튼은 비활성화.
    let isEmailChecked = false;
    
    const inputUsername = document.querySelector('input#username');
    const checkUsernameResult = document.querySelector('div#checkUsernameResult');
    const inputPassword = document.querySelector('input#password');
    const checkPasswordResult = document.querySelector('div#checkPasswordResult');
    const inputEmail = document.querySelector('input#email');
    const checkEmailResult = document.querySelector('div#checkEmailResult');
    const btnSignUp = document.querySelector('button#btnSignUp');
    
    // inputUsername 요소에 'change' 이벤트 리스너를 설정.
    inputUsername.addEventListener('change', checkUsername);
    
    // inputPassword 요소에 'change' 이벤트 리스너를 설정.
    inputPassword.addEventListener('change', checkPassword);
    
    // inputEmail 요소에 'change' 이벤트 리스너를 설정.
    inputEmail.addEventListener('change', checkEmail);
    
    /* -------------------- 함수 선언 -------------------- */
    function changeButtonState() {
        if (isUsernameChecked && isPasswordChecked && isEmailChecked) {
            // 버튼 활성화 - class 속성들 중에서 'disabled'를 제거.
            btnSignUp.classList.remove('disabled');
        } else {
            // 버튼 비활성화 - class 속성에 'disabled'를 추가.
            btnSignUp.classList.add('disabled');
        }
    }
    
    function checkUsername() {
        const username = inputUsername.value;
        if (username === '') {
            checkUsernameResult.innerHTML = '사용자 아이디는 필수 입력 항목입니다.';
            checkUsernameResult.classList.add('text-danger');
            checkUsernameResult.classList.remove('text-success');
            
            isUsernameChecked = false;
            changeButtonState();
            
            return;
        }
        
        // 아이디 중복 체크 REST API(요청 URI)
        const uri = `./checkusername?username=${username}`;
        axios
        .get(uri)
        .then(handleCheckUsernameResp)
        .catch((error) => console.log(error));
    }
    
    function handleCheckUsernameResp({ data }) {
        // const {data} = response; //-> 구조분해 할당(destructuring assignment)
        console.log(data);
        if (data === 'Y') {
            checkUsernameResult.innerHTML = '멋진 아이디입니다.';
            checkUsernameResult.classList.add('text-success');
            checkUsernameResult.classList.remove('text-danger');
            isUsernameChecked = true;
        } else {
            checkUsernameResult.innerHTML = '사용할 수 없는 아이디입니다.';
            checkUsernameResult.classList.add('text-danger');
            checkUsernameResult.classList.remove('text-success');
            isUsernameChecked = false;
        }
        changeButtonState();
    }
    
    function checkPassword() {
        if (inputPassword.value === '') {
            checkPasswordResult.innerHTML = '비밀번호는 필수입력 항목입니다.';
            checkPasswordResult.classList.add('text-danger');
            checkPasswordResult.classList.remove('text-success');
            isPasswordChecked = false;
        } else {
            checkPasswordResult.innerHTML = '사용할 수 있는 비밀번호입니다.';
            checkPasswordResult.classList.add('text-success');
            checkPasswordResult.classList.remove('text-danger');
            isPasswordChecked = true;
        }
        changeButtonState();
    }
    
    function checkEmail() {
        // inputEmail에 입력된 값이 있는 지를 체크.
        if (inputEmail.value === '') {
            checkEmailResult.innerHTML = '이메일은 필수입력 항목입니다.';
            checkEmailResult.classList.add('text-danger');
            checkEmailResult.classList.remove('text-success');
            isEmailChecked = false;
            changeButtonState();
            
            return;
        }
        
        // 이메일 중복 체크 REST API(요청 URI)
        const uri = `./checkemail?email=${encodeURIComponent(inputEmail.value)}`;
        
        // Ajax 요청을 보냄.
        axios
        .get(uri)
        .then(handleCheckEmailResp)
        .catch((error) => console.log(error));
    }
    
    function handleCheckEmailResp({ data }) {
        console.log(data);
        if (data === 'Y') { // 회원 가입 가능한 이메일
            checkEmailResult.innerHTML = '사용가능한 이메일입니다.';
            checkEmailResult.classList.add('text-success');
            checkEmailResult.classList.remove('text-danger');
            isEmailChecked = true;
        } else { // 중복된 이메일
            checkEmailResult.innerHTML = '이미 사용중인 이메일입니다.';
            checkEmailResult.classList.add('text-danger');
            checkEmailResult.classList.remove('text-success');
            isEmailChecked = false;
        }
        changeButtonState();
    }
    
});
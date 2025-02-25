/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
    const inputUsername = document.querySelector('input#username');
    const inputUsernameCheck = document.querySelector('input#usernameCheck');
    const divUsernameCheckResult = document.querySelector('div#usernameCheckResult');
    
    const inputEmail = document.querySelector('input#email');
    const inputEmailCheck = document.querySelector('input#emailCheck');
    const divEmailCheckResult = document.querySelector('div#emailCheckResult');
    
    const inputPassword = document.querySelector('input#password');
    const divPasswordCheckResult = document.querySelector('div#passwordCheckResult');
    
    const signUpBtnStatus = document.querySelector('input#signUpBtn');
    
    let usernameChecked = false;
    let emailChecked = false;
    let passwordChecked = false;

    inputUsernameCheck.addEventListener("click", () => {
        const username = inputUsername.value.trim();
        if (!username) {
            usernameChecked = false;
            divUsernameCheckResult.innerHTML = "<span style='color: red;'>아이디를 입력해주세요.</span>";
            console.log(usernameChecked);
            return;
        }

        // FormData 객체 생성
        const formData = new FormData();
        formData.append("username", username);

        // iframe을 사용한 요청
        const iframe = document.createElement("iframe");
        iframe.name = "hiddenIframe";
        iframe.style.display = "none";
        document.body.appendChild(iframe);

        const form = document.createElement("form");
        form.action = "/myblog/member/usernamecheck";
        form.method = "post";
        form.target = "hiddenIframe";

        const hiddenInput = document.createElement("input");
        hiddenInput.type = "hidden";
        hiddenInput.name = "username";
        hiddenInput.value = username;

        form.appendChild(hiddenInput);
        document.body.appendChild(form);

        iframe.onload = () => {
            const iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
            const responseText = iframeDoc.body.textContent.trim();

            if (responseText === "1") {
                usernameChecked = false;
                divUsernameCheckResult.innerHTML = "<span style='color: red;'>중복된 아이디입니다.</span>";
                console.log(usernameChecked);
            } else if (responseText === "0") {
                usernameChecked = true;
                divUsernameCheckResult.innerHTML = "<span style='color: blue;'>사용 가능한 아이디입니다.</span>";
                console.log(usernameChecked);
            } else {
                usernameChecked = false;
                divUsernameCheckResult.innerHTML = "<span style='color: red;'>오류가 발생했습니다. 다시 시도해주세요.</span>";
            }
            changeSignUpBtn();

            // 요청이 끝나면 iframe과 form을 제거
            document.body.removeChild(iframe);
            document.body.removeChild(form);
        };

        form.submit();
    });
    
    
    inputEmailCheck.addEventListener("click", () => {
        const email = inputEmail.value.trim();
        if (!email) {
            emailChecked = false;
            divEmailCheckResult.innerHTML = "<span style='color: red;'>이메일을 입력해주세요.</span>";
            console.log(emailChecked);
            return;
        }

        // FormData 객체 생성
        const formData = new FormData();
        formData.append("email", email);

        // iframe을 사용한 요청
        const iframe = document.createElement("iframe");
        iframe.name = "hiddenIframe";
        iframe.style.display = "none";
        document.body.appendChild(iframe);

        const form = document.createElement("form");
        form.action = "/myblog/member/emailcheck";
        form.method = "post";
        form.target = "hiddenIframe";

        const hiddenInput = document.createElement("input");
        hiddenInput.type = "hidden";
        hiddenInput.name = "email";
        hiddenInput.value = email;

        form.appendChild(hiddenInput);
        document.body.appendChild(form);

        iframe.onload = () => {
            const iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
            const responseText = iframeDoc.body.textContent.trim();

            if (responseText === "1") {
                emailChecked = false;
                divEmailCheckResult.innerHTML = "<span style='color: red;'>중복된 이메일입니다.</span>";
                console.log(emailChecked);
            } else if (responseText === "0") {
                emailChecked = true;
                divEmailCheckResult.innerHTML = "<span style='color: blue;'>사용 가능한 이메일입니다.</span>";
                console.log(emailChecked);
            } else {
                emailChecked = false;
                divEmailCheckResult.innerHTML = "<span style='color: red;'>오류가 발생했습니다. 다시 시도해주세요.</span>";
            }
            changeSignUpBtn();

            // 요청이 끝나면 iframe과 form을 제거
            document.body.removeChild(iframe);
            document.body.removeChild(form);
        };

        form.submit();
    });
    
    
    inputPassword.addEventListener('change', () => {
        password = inputPassword.value.trim();
        if (!password) {
            passwordChecked = false;
            divPasswordCheckResult.innerHTML = "<span style='color: red;'>비밀번호를 입력해주세요.</span>";
            console.log(passwordChecked);
        } else {
            passwordChecked = true;
            divPasswordCheckResult.innerHTML = "<span style='color: blue;'>사용가능한 비밀번호입니다.</span>";
            console.log(passwordChecked);
        }
        changeSignUpBtn();
    })
    
    
    function changeSignUpBtn () {
        if (usernameChecked === true && emailChecked === true && passwordChecked === true) {
            signUpBtnStatus.classList.remove('disabled');
        } else {
            signUpBtnStatus.classList.add('disabled');
        }
    }
});
/**
 * 댓글 보기, 감추기, 댓글 CRUD 요청 AJAX
 * post/details.jsp 파일에 포함.
 */

document.addEventListener("DOMContentLoaded", () => {
    // btnToggleComment 요소를 찾음.
    const btnToggleComment = document.querySelector("button#btnToggleComment");

    // div#collapseComments 요소를 부트스트랩의 collapse 객체로 생성.
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false });

    // btnToggleComment버튼에 클릭 이벤트 리스너 등록 
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();

        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 감추기'
            //댓글 목록 가져오기 요청을 보냄.
            getALLComments();
        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }

        const btnRegisterComment = document.querySelector("button#btnRegisterComment");

        btnRegisterComment.addEventListener('click', registerComment);
    });
    
    // 부트스트랩 모달 객체 생성
    const commentModal = new bootstrap.Modal('div#commentModal',{backdrop: true});
    
    // 모달의 [저장] 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    const btnUpdateComnt = document.querySelector('button#btnUpdateComment');
    btnUpdateComnt.addEventListener('click',updateComment);

    /*---------------------------------------------------   -----------
    콜백 함수 선언 */

    function registerComment() {
        //input#id 요소의 값을 읽음, postId
        const postId = document.querySelector('input#id').value;

        //textarea#ctext 요소의 값을 읽음
        const ctext = document.querySelector('textarea#ctext').value;

        //input#username 요소의 값을 읽음
        const username = document.querySelector('input#username').value;

        //댓글 내용이 비어있는지 체크
        if (ctext == '') {
            alert("댓글 내용을 반드시 입력하세요");
            return;
        }

        //Ajax 요청으로 보낼 데이터 객체
        //const data={ postId:postId, username:username, ctext:ctext };
        const data = { postId, username, ctext }; // <- 프로퍼티이름과 지역변수 이름이 같을 때만! 배열아님XXX 배열은 [ ]
        console.log(data);

        //서버로 POST 방식의 AJAX 요청을 보낸다.
        //현재 주소인 /post/delete 현재 디렉토리 post에서 /api/comment로 바꾸기 위해서 ../를 넣는다.
        axios.post('../api/comment', data).then((response) => {
            console.log(response);
            if (response.data === 1) {
                alert('댓글 1개 등록 성공');
                document.querySelector('textarea#ctext').value = '';

                //업데이트된 내용을 보여준다.
                getALLComments();
            }

        }).catch((error) => {
            console.log(error);
        });

    }

    // 현재 보고있는 포스트에 달려있는 댓글 목록 가져오기
    function getALLComments() {
        // 댓글 목록을 요청하기 위한 postId
        const postId = document.querySelector('input#id').value;

        // 댓글 목록을 요청하기 위한 REST API(요청 URI)
        const uri = `../api/comment/all/${postId}`;

        // AJAX 요청을 보냄.
        axios.get(uri).then((response) => { // response.data 속성에 서버가 보내준 댓글 목록이 있음.
            console.log(response);

            // divComments 영역에 댓글목록을 출력.
            makeCommentElements(response.data);
        })
            .catch((error) => {
                console.log(error)
            });
    }

    // 댓글 배열(data)을 아규먼트로 전달받아서 details.jsp의 divComments 영역에 적성.
    function makeCommentElements(data) {
        // 자바스크립트는 변수 타입을 파라미터에 명시하지 않는다.


        const divComments = document.querySelector('div#divComments');

        // divComments 영역에 저장할 html 코드
        let html = '<ul class="list-group list-group-flush">';
        for (const comment of data) {
            // timestamp 를 date/time 포맷으로 변환
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();

            // 댓글 1개를 생성할 때 생성될 html 코드
            html +=
                `<li class="list-group-item d-flex justify-content-between align-items-start">
                     <div>         
                         <div class="text-secondary" style="font-size: 0.825rem">
                         <span>${comment.username}</span>
                         <span>${modifiedTime}</span> </div> 
                         <span>${comment.ctext}</span>
                     </div>
                `;
                
                // 로그인 사용자와 댓글 사용자가 같은 경우에만 삭제/수정 버튼 출력
                if (signedInUser === comment.username) {
                    html +=
                    `<div>
                         <button class="btnDeleteComment btn btn-outline-danger btn-sm" data-id="${comment.id}">삭제</button>
                         <button class="btnUpdateComment btn btn-outline-success btn-sm" data-id="${comment.id}">수정</button>
                     </div>
                     `;
                }
                html += '</li>';
        }
        html += '</ul>';
        divComments.innerHTML = html;

        // html 코드가 div에 삽입된 후에 삭제,수정 버튼들을 찾을 수 있음.
        //  => 이벤트 리스너를 설정할 수 있음.

        // 모든 댓글 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnDeletes = document.querySelectorAll("button.btnDeleteComment");
        for (const btn of btnDeletes) {
            btn.addEventListener('click', deleteComment);
        }

        // 모든 댓글 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnUpdates = document.querySelectorAll("button.btnUpdateComment");
        for (const btn of btnUpdates) {
            btn.addEventListener('click', showCommentModal);
        }
    }
    
    // 댓글 삭제 버튼의 클릭 이벤트 리스너 
    function deleteComment(event) {
        console.log(event.target);

        // 모든 이벤트 리스너들은 event 객체를 아규먼트로 전달받음.
        // 이벤트 객체를 target 속성(이벤트가 발생한 html 요소)을 가지고 있음

        // HTML 요소의 속성(attribute)의 값을 찾음:
        const commentId = event.target.getAttribute('data-id');

        const result = confirm("댓글을 삭제하시겠습니까?");

        if (!result) {
            return;
        } else {
            //Ajax 댓글 삭제 요청 REST API (요청 URI)
            const uri = `../api/comment/${commentId}`;

            //Ajax 요청을 보냄.
            axios
                .delete(uri)
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                });
            alert('댓글이 삭제됐습니다');

            getALLComments();
        }
    }
    
    // 댓글 수정 버튼의 클릭 이벤트 리스너 
    function showCommentModal(event) {
        const commentId = event.target.getAttribute('data-id');
        // commentModal.show();

        // 댓글 아이디로 댓글 1개 검색하기 Ajax 요청
        // -> 성공 콜백에서 모달(commentModal)의 input과 textarea를 채움.
        // -> 모달 보여주기
        const uri = `../api/comment/${commentId}`;
        axios
            .get(uri)
            .then((response) => {
                console.log(response);

                // 모달의 input에 댓글 아이디를 value 속성에 저장.
                document.querySelector('input#modalCommentId').value = response.data.id;

                // 모달 textarea에 댓글 내용을 value 속성으로 저장.
                document.querySelector('textarea#modalCommentText').value = response.data.ctext;

                commentModal.show() // bootstrap.modal 객체의 show() 메서드 호출 - 모달 보여주기

            })
            .catch((error) => {
                console.log(error);
            });
    }
    
    // 모달의 저장 버튼의 클릭 이벤트 리스너
    function updateComment() {
        const id = document.querySelector("input#modalCommentId").value;
        const ctext = document.querySelector("textarea#modalCommentText").value;
        if (ctext == '') {
            alert("댓글 내용을 반드시 입력하세요");
            return;
        }
        const data = { id, ctext };
        axios
            .put(`../api/comment/${id}`, data)
            .then((response) => {
                console.log(response);
                commentModal.hide();
                getALLComments();
        })
            .catch((error) => {
                console.log(error);
        });
    }

})

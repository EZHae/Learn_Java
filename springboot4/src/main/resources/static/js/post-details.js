// post/details.html에 추가

document.addEventListener('DOMContentLoaded', () => {
    console.log('post-details.js')

    const btnDelete = document.querySelector('button#btnDelete');
    const inputId = document.querySelector('input#id');

    btnDelete.addEventListener('click', () => {
        
        const result = confirm('삭제하시겠습니까?');

        if (result) {
            const url = `/post/delete/${inputId.value}`;
            axios.delete(url)
                .then(response => {
                    alert('삭제되었습니다.');
                    location.href = '/post/list';
                })
                .catch(error => {
                    alert('삭제 실패');
                    console.error(error);
                });
        };
    });

    ///
    getComments()
    
    const btnCommentCreate = document.querySelector('button#btnCommentCreate');
    btnCommentCreate.addEventListener('click', () => {
        const postId = document.querySelector('input#id').value;
        let text = document.querySelector('textarea#text').value;
        if (text === '') {
            alert('댓글을 입력해주세요.');
            return;
        }

        const url = `/api/comment/${postId}`
        const writer = '이지해';
        const data = {postId, writer, text};
        axios.post(url, data)
            .then(response => {
                alert('댓글이 작성하였습니다.');
                getComments();
                document.querySelector('textarea#text').value = '';
            })
            .catch(error => {
                alert('댓글 작성 실패');
                console.error(error);
            });
    })

    function getComments() {
        const postId = document.querySelector('input#id').value;

        const url = `/api/comment/${postId}`;
        axios.get(url)
            .then(response => {
                console.log(response.data);
                makeComments(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    };

    function makeComments(comments) {
        const divCommentLoadSection = document.querySelector('div#commentLoadSection');
        divCommentLoadSection.innerHTML = '';

        let html = '<ul class="list-unstyled">';
        for (const comment of comments) {
            html += `
                <li class="mt-2">
                    <div class="card-body border rounded-2">
                        <div class="d-flex w-100">
                            <span class="fw-bold fs-5" id="commentWriter">${comment.writer}</span>
                            <span class="fs-6 text-secondary ms-2">${comment.createdTime}</span>
                            <div class="ms-auto">
                                <button class="btnCommentUpdate btn btn-success btn-sm" data-id="${comment.id}">댓글 수정</button>
                                <button class="btnCommentDelete btn btn-danger btn-sm" data-id="${comment.id}">댓글 삭제</button>
                            </div>
                        </div>
                        <div class="mt-2">
                            <span id="commentText" data-id="${comment.id}">${comment.text}</span>
                        </div>
                    </div>
                </li>
            `
            
        };
        html += '</ul>'
        divCommentLoadSection.innerHTML = html;

        const btnCommentDeletes = document.querySelectorAll('button.btnCommentDelete');
        for (const btn of btnCommentDeletes) {
            btn.addEventListener('click', (event) => {
                console.log(event.target);
                const id = event.target.getAttribute('data-id');
                const url = `/api/comment/delete/${id}`;

                const result = confirm('댓글을 삭제하시겠습니까?');
                if (result) {
                    axios.delete(url)
                        .then(response => {
                            alert('댓글이 삭제되었습니다.');
                            getComments();
                        })
                        .catch(error => {
                            console.error(error);
                        });
                };
            });    
        };
    };


});
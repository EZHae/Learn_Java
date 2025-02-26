// post/update.html에 추가

document.addEventListener('DOMContentLoaded', () => {
    console.log('post-update.js');

    const btnUpdate = document.querySelector('button#btnUpdate');

    btnUpdate.addEventListener('click', () => {
        const result = confirm('수정하시겠습니까?');
        const title = document.querySelector('input#title').value;
        const content = document.querySelector('textarea#content').value;
        const id = document.querySelector('input#id').value;

        if (title === '' || content === '') {
            alert('제목과 내용을 모두 입력해주세요.');
            return;
        }

        if (result) {
            const url = `/post/update/${id}`;
            const data = {id, title, content};
            axios
                .put(url, data)
                .then(response => {
                    alert('수정되었습니다.');
                    location.href = `/post/details/${id}`;
                })
                .catch(error => {
                    alert('수정 실패');
                    console.error(error);
                });
        }
    });
});
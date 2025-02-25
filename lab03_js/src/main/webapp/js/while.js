/**
 * while.html 파일에 포함.
 */

// 아이디가 list인 ul 요소를 찾음.
const list = document.querySelector('ul#list');

// while 반복문을 사용해서 list에 li 요소 5개를 추가.
let x = 1;
let html = '';

while(x <= 5) {
    html += `<li>item${x}</li>`;
    x++;
}
list.innerHTML = html;

// table tr 5개 추가.
const body = document.querySelector('tbody#tableBody')
x = 1;
html = '';

while(x <= 5) {
    html += `<tr><td>${x}</td><td>title${x}</td></tr>`;
    x++;
}
body.innerHTML += html;
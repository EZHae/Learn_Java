/**
 * array.html에 포함.
 * 
 * JS 배열: 여러 개의 원소(아이템)들을 하나의 변수 이름으로 저장하기 위한 데이터 타입.
 * Java 배열: "한 가지" 타입의 값 여러 개를 저장하기 위한 데이터 타입.
 *      - int[], double[], String[], Object[]...
 * JS 배열은 다른 타입의 값들을 저장할 수 있음.
 */

// 아이디가 output인 div 요소를 찾음.
const output = document.querySelector('div#output');

// 배열 선언, 초기화
const numbers = [1, 2, -3, -4, 5, 0]; // (Java 비교) int[] numbers = {1, 2, -3, -4, 5, 0}

// 배열의 아이템들을 output 영역에 출력
let html = '';
for (let i = 0; i < numbers.length; i++){
    html += `${numbers[i]}, `;
}
output.innerHTML = html + '<br>';


// Java 향상된 for 문장: for(변수선언 : 배열) { ... }
// JS for-of 문장: 배열의 아이템들을 반복(iteration)
html = '';
for (const value of numbers) {
    html += `${value}, `
}
output.innerHTML += html + '<br>';


// JS for-in 문장: 배열의 인덱스를 반복(iteration)
html = '';
for (const i in numbers) {
    html += `${i} : ${numbers[i]}, `;
}
output.innerHTML += html + '<br>';

//////////////////////////////////////////////////////////////////////////////////////

// 연습문제 1: 배열 numbers의 원소들의 합과 평균을 output 영역에 출력.
html = '';
let x = 0;
for (const value of numbers) {
    x += value;
}
output.innerHTML += `합계 = ${x}, 평균 = ${x/numbers.length} <br>`


// 연습문제 2: 아이디가 list인 ul에 movies의 아이템들을 li로 추가.
const movies = ['1등', '소방관', '위키드']

html = '';
for (const value of movies) {
    html += `<li>${value}</li>`;
}
output.innerHTML += html + '<br>';

//////////////////////////////////////////////////////////////////////////////////////

// destructuring assignment(분해 할당)
const array = [1, 2, 3];
const [n, y , z] = array;
output.innerHTML += `n = ${n}, y = ${y}, z = ${z} <br>`; // n = 1, y = 2, z = 3

// 배열의 원소 개수보다 적은 destructuring assignment
const [a, b] = array;
output.innerHTML += `a = ${a}, b = ${b} <br>`; // a = 1, b = 2
// -> 선언된 원소의 순서대로 할당이 됨. 

// 배열의 원소 개수보다 많은 destructuring assignment
const [c, d, e, f] = array;
output.innerHTML += `c = ${c}, d = ${d}, e = ${e} f = ${f} <br>`; // c = 1, d = 2, e = 3, f = undefined
// -> f의 경우 할당할 원소가 없기 때문에 undefined 처리가 됨.

// rest 연산자(...)를 사용한 destructuring assignment
const [g, ... h] = array; // 첫번째 원소는 g에 그리고 남은 모든 원소를 h에 저장
output.innerHTML += `g = ${g}, h = ${h} <br>`; // g = 1, h = 2,3
//-> h는 여러개의 원소를 가지게 되므로 배열이 됨.



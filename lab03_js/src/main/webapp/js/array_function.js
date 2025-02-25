/**
 * array_function.html에 포함.
 * 
 * JS 배열 객체의 함수(메서드)들
 *      - mutable method: 원본 배열을 바꾸는 메서드.
 *          예) push, pop, sort, ...
 *      - immutable method: 원본 배열을 바꾸지 않고, 새로운 배열을 리턴하는 메서드.
 *          예) concat, slice, toSorted, ...
 */

const arr = [ 1, 2, 3 ];
console.log('원본 arr 배열');
console.log(arr, '<-- 원본 arr 배열');
console.log('');

// 1. Array.push(arg): 배열의 끝에 새로운 원소를 추가. *원본 배열이 바뀜!
console.log('1. Array.push(arg): 배열의 끝에 새로운 원소를 추가. *원본 배열이 바뀜!');
arr.push(100);
console.log(arr, '<-- 원본 arr 배열'); //-> [1, 2, 3, 100]
console.log('');

// 2. Array.concat(arg): 원본 배열을 바꾸지 않고, 원소가 추가된 새로운 배열을 리턴. 
console.log('2. Array.concat(arg): 원본 배열을 바꾸지 않고, 원소가 추가된 새로운 배열을 리턴.');
let result = arr.concat(10, 20);
console.log(arr, '<-- 원본 arr 배열'); //-> [1, 2, 3, 100]
console.log(result, '<-- 새로운 result 배열'); //-> [1, 2, 3, 100, 10, 20]
console.log('');

// 3.Array.pop(): 배열의 마지막 원소를 삭제. *원본 배열이 바뀜!
console.log('3.Array.pop(): 배열의 마지막 원소를 삭제. *원본 배열이 바뀜!');
arr.pop();
console.log(arr, '<-- 원본 arr 배열'); //-> [1, 2, 3]
console.log('');

// 4. Array.slice(start, end): 배열에서 인덱스 start부터 end까지의 원소들을 잘라낸 새로운 배열을 리턴.
console.log('4. Array.slice(start, end): 배열에서 인덱스 start부터 end까지의 원소들을 잘라낸 새로운 배열을 리턴.');
// arr.slice(0, 2) = arr.slice(0, -1); [1, 2, 3] -> [1, 2] 
// -> end 값이 음수일 경우 뒤에서부터 자름.
result = arr.slice(0, 2);
console.log(arr, '<-- 원본 arr 배열'); //-> [1, 2, 3] 원본 배열이 바뀌지 않음.
console.log(result, '<-- 새로운 result 배열'); //-> [1, 2]

result = arr.slice(0, -1);
console.log(result, '<-- 새로운 result 배열'); // -> [1, 2]
console.log('');

///////////////////////////////////////////

const arr2 = [10, 100, -1, 9, 80]; 
console.log('원본 arr2 배열')
console.log(arr2, '<-- 원본 arr2 배열');
console.log('');
// 5-1. Array.sort(): 배열의 아이템 순서를 오름차순으로 변경. *원본 배열이 바뀜!
//      - 배열의 원소들을 "문자열로 변환한 후" 크기를 비교함. (사전식 순서)
console.log('5-1. Array.sort(): 배열의 아이템 순서를 오름차순으로 변경. *원본 배열이 바뀜!');
arr2.sort();
console.log(arr2, '<-- 원본 arr2 배열');

// 5-2. Array.sort(callback): 원소의 크기비교에서 사용될 callback(함수)를 아규먼트로 전달해주는 메서드.
console.log('5-2. Array.sort(callback): 원소의 크기비교에서 사용될 callback(함수)를 아규먼트로 전달해주는 메서드.');
arr2.sort((x, y) => x-y);
console.log(arr2, '<-- 원본 arr2 배열');
console.log('');

// 6-1. Array.toSorted(): 오름차순으로 정렬된 새로운 배열을 리턴.
//      - 배열의 원소들을 "문자열로 변환한 후" 크기를 비교함. (사전식 순서)
console.log('6-1. Array.toSorted(): 오름차순으로 정렬된 새로운 배열을 리턴.');
console.log(arr2, '<-- 원본 arr2 배열');

result = arr2.toSorted();
console.log(result, '<-- 새로운 result 배열');

// 6-2. Array.toSorted(callback): 원소의 크기 비교에서 사용될 callback(함수)를 아규먼트로 전달해주는 메서드.
console.log('6-2. Array.toSorted(callback): 원소의 크기 비교에서 사용될 callback(함수)를 아규먼트로 전달해주는 메서드.');
// callback(x, y) => 숫자. (x가 크면 양수, y가 크면 음수 x와 y가 같으면 0을 리턴)
result = arr2.toSorted((x, y) => x-y);
// -> 원리: 10과 100 비교: x=10, y=100 결과:-90, 음수이기 때문에 100이 더 크다고 판단하고 정렬
//          10과 -1 비교: x=10, y=-1 결과:11, 양수기이 때문에 10이 더 크다고 판단하고 정렬
console.log(result, '<-- 새로운 result 배열');
console.log('');

console.log('--------------------------------------------------------------------');

const numbers = [ 1, 2, 3, 4, 5, 6 ];
console.log('원본 numbers 배열');
console.log(numbers, '<-- 원본 numbers 배열')
// numbers.forEach(x => console.log(x)); (x) => { console.log(x); }
console.log('');
let numbersResult = [];
// 연습 1: 배열 numbers의 원소들 중에서 홀수들로만 이루어진 새로운 배열을 만들고 출력. filter
console.log('연습 1: 배열 numbers의 원소들 중에서 홀수들로만 이루어진 새로운 배열을 만들고 출력.');
for (const number of numbers) {
    if (number % 2) {
        numbersResult.push(number);
    }
}
console.log(numbersResult, '<--새로운 numberResult 배열');

// Array.filter(function): 함수의 조건식에 맞는 원소를 출력.
// numbersResult = numbers.filter(function name (number) {
//     return number % 2 === 1;
// }) --> 이 문장을 아래와 같이 화살표 함수로 만들 수 있음.
numbersResult = numbers.filter(number => number % 2 === 1);
console.log(numbersResult, '<-- filter 사용');
console.log('');

// 연습 2: 배열 numbers의 원소들의 제곱을 원소로 갖는 새로운 배열을 만들고 출력. map
console.log('연습 2: 배열 numbers의 원소들의 제곱을 원소로 갖는 새로운 배열을 만들고 출력.');
numbersResult.splice(0);
for (const number of numbers) {
    numbersResult = numbersResult.concat(Math.pow(number, 2));
}
console.log(numbersResult, '<--새로운 numberResult 배열');

// Array.map(function): 배열의 모든 원소 값을 변경해서 원소를 출력.
// numbersResult = numbers.map(function name (number) {
//     return Math.pow(number, 2);
// }) --> 이 문장을 아래와 같이 화살표 함수로 만들 수 있음.
numbersResult = numbers.map(number => Math.pow(number, 2));
console.log(numbersResult, '<-- map 사용');
console.log('');

// 연습 3: 배열 numbers의 아이템들 중에서 홀수들의 제곱을 원소로 갖는 새로운 배열을 출력. filter map
console.log('연습 3: 배열 numbers의 아이템들 중에서 홀수들의 제곱을 원소로 갖는 새로운 배열을 출력.');
numbersResult.splice(0);
for (const number of numbers) {
    if (number % 2) {
        numbersResult.push(Math.pow(number, 2));
    }
}
console.log(numbersResult, '<--새로운 numberResult 배열');

numbersResult = numbers.filter(number => number % 2 === 1).map(number => Math.pow(number, 2));
console.log(numbersResult, '<-- filter, map 사용');
console.log('');

// 연습 4: 배열 numbers의 모든 원소들의 합계를 계산하고 출력. reduce
console.log('연습 4: 배열 numbers의 모든 원소들의 합계를 계산하고 출력.');
let sum = 0;
for (const number of numbers) {
    sum += number;
}
console.log(sum, '<-- 원소들의 합계');

// Array.reduce(funtion): 배열의 모든 원소의 값들의 조건에 맞는 누적계를 출력.
// sum = numbers.reduce(function name (accmulator, currentValue) {
//     return accmulator + currentValue;
// }, 0) --> 이 문장을 아래와 같이 화살표 함수로 만들 수 있음.
sum = numbers.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
console.log(sum, '<-- reduce 사용');
console.log('');

// 연습 5: numbers의 모든 원소들의 곱을 계산하고 출력. (reduce)
console.log('연습 5: numbers의 모든 원소들의 곱을 계산하고 출력. (reduce)');
// let multiply = numbers.reduce(function name (accmulator, currentValue) {
//     return accmulator * currentValue;
// }, 1) --> 이 문장을 아래와 같이 화살표 함수로 만들 수 있음.
let multiply = numbers.reduce((accumulator, currentValue) => accumulator * currentValue, 1);
console.log(multiply, '<-- reduce 사용');
console.log('');

// 연습 6:: numbers의 원소들 중에서 짝수의 합을 계산하고 출력. (filter, reduce)
console.log('연습 6: numbers의 원소들 중에서 짝수의 합을 계산하고 출력. (filter, reduce)');
sum = numbers.filter(number => number % 2 === 0).reduce((accumulator, currentValue) => accumulator + currentValue, 0);
console.log(sum, '<-- filter, reduce 사용');
console.log('');

// 연습 7: numbers의 제곱들의 합을 계산하고 출력. (map, reduce)
console.log('연습 7: numbers의 제곱들의 합을 계산하고 출력. (map, reduce)');
let powSum = numbers.map(number => Math.pow(number, 2)).reduce((accmulator, currentValue) => accmulator + currentValue, 1);
console.log(powSum, '<-- map, reduce 사용');
console.log('');

// 연습 8: numbers의 아이템들 중에서 짝수의 제곱들의 합을 계산하고 출력 (filter, map, reduce)
console.log('연습 8: numbers의 아이템들 중에서 짝수의 제곱들의 합을 계산하고 출력 (filter, map, reduce)');
let oddPowSum = numbers.filter(number => number % 2 === 0).map(number => Math.pow(number, 2))
                        .reduce((accmulator, currentValue) => accmulator + currentValue, 1);
console.log(oddPowSum, '<-- filter, map, reduce 사용');
console.log('');

/**
 * function.html에 포함.
 * 
 * JS 함수 선언 방법:
 *      function 함수이름([파라미터 선언, ...]) {
 *          실행코드;
 *          [retrun [값];]
 *      }
 * 
 * Java와 다르게 JS에서는 함수 이름 앞에 리턴 타입을 선언하지 않음.
 *      - Java: public void abc() {...} / private int abc() {...}
 *      - JS: function abc() {...}
 * Java와 다르게 파라미터를 선언할 때는 데이터 타입 키워드를 사용하지 않음.
 *      - Java: public void abc(int x) {...} / private int abc(String s) {...}
 *      - JS: funtion abc(x) {...}
 * 
 * !! JS 함수는 객체(object) !!
 * 
 * 함수의 특징
 *      1. 파라미터 타입을 검사하지 않음.
 *      2. 파라미터 개수를 검사하지 않음.
 *      3. 파라미터의 기본값을 설정할 수 있음.
 *      4. 파라미터를 구조분해 할당할 수 있음.
 *      5. 프로퍼티(property)를 가질 수 있음. (예) arguments
 *      6. 변수에 할당할 수 있음.
 *      7. 함수를 호출할 때 아규먼트로 함수 (객체)를 전달할 수 있음.
 *      8. 함수는 함수 (객체)를 리턴할 수 있음.
 *      9. 함수 내부에서 다른 함수를 선언(정의)할 수 있음.
 */

// 함수 선언
function add(x, y) {
    console.log(`add 함수 파라미터: x = ${x}, y = ${y}`);
    return x+y;
}
/** Java였다면...
 *      public int add (int x, int y) {
 *          System.out.println("add 함수 파라미터: x = " + x + "y = " + y);
 *          return x + y;
 *      }
 */


// 함수의 특징들
console.log('함수의 특징들');
let result;

// 1. JS 함수는 파라미터 타입을 검사하지 않음.
console.log('1. JS 함수는 파라미터 타입을 검사하지 않음.');
result = add(34, '안녕하세요'); // 정수타입과 문자열타입이 같이 있는 경우
console.log(`add_result = ${result}`);
console.log('');

// 2. JS 함수는 파라미터 개수를 검사하지 않음.
console.log('2. JS 함수는 파라미터 개수를 검사하지 않음.');
result = add(1, 2, 3); // 선언된 파라미터 개수보다 더 많은 아규먼트를 전달한 경우
console.log(`add_result = ${result}`); 
// result = 3 --> 앞에서 순서대로 아규먼트를 전달하고, 그 뒤의 아규먼트들은 버려짐.

result = add(1); // 선언된 파라미터 개수보다 적은 아규먼트를 전달한 경우
console.log(`add_result = ${result}`);
console.log('');
// x = 1, y = undefined | result = NaN --> y는 할당되지 않았기 때문에 undefined, result는 숫자가 아니게 됐으므로 NaN

// 3. JS에는 default parameter를 설정할 수 있음 (default parameter: 기본 값이 설정된 파라미터)
console.log('3. JS에는 default parameter를 설정할 수 있음 (default parameter: 기본 값이 설정된 파라미터)');
function multiply(x=3, y=1) {
    console.log(`multiply 함수 파라미터: x = ${x}, y = ${y}`);
    return x*y;
}
result = multiply();
console.log(`multiply_result = ${result}`);
// result = 3 --> 아규먼트를 할당하지 않았기 때문에 기본값으로 계산
console.log('');

// 4. JS의 함수 파라미터 destructuring assignment(구조분해 할당)
console.log('4. JS의 함수 파라미터 destructuring assignment(구조분해 할당)');
function divide(x, y, ...args) {
    console.log(`divide 함수 파라미터: x = ${x}, y = ${y}, args = ${args}`);
    return x/y; // -> x와 y를 제외한 모든 아규먼트를 args 배열에 저장한다.
}
result = divide(2, 4, 6, 8, 10, 12, 14);
console.log(`divide_result = ${result}`);
console.log('');

// 5. 모든 JS 함수는 arguments 속성(property)를 가지고 있음.
console.log('5. 모든 JS 함수는 arguments 속성(property)를 가지고 있음.');
// arguments: 함수를 호출하는 곳에서 전달한 모든 아규먼트들을 저장하고 있는 배열.
function testArgs() {
    console.log(arguments);
    for (const arg of arguments) {
        console.log(arg);
    }
}
testArgs(); //-> arguments: 원소가 0개인 배열 argument = []
testArgs('Hello'); //-> arguments: 원소가 1개인 배열 argument = ['Hello']
testArgs(1, '안녕'); //-> arguments: 원소가 2개인 배열 argument = [1, '안녕']
console.log('');

// 6-1. 함수를 변수에 할당.
console.log('6-1. 함수를 변수에 할당');
// const plus = add(); -> 함수 add()의 리턴 값을 변수 plus에 할당.
const plus = add; //-> 함수 add를 변수 plus에 할당. 
console.log(plus); // 이제 plus는 함수가 되었음.
console.log(plus(12, 34));
console.log('');

// 6-2. 이름이 없는 함수 (anonymous function)
console.log('6-2. 이름이 없는 함수 (anonymous function)');
const minus = function(x, y) { // minus -> 함수가 됨
    return x-y;
}
console.log(minus);
console.log(minus(2, 4));
console.log('');

// 6-3. 화살표 함수(arrow function): 익명 함수를 간단히 표현하는 문법. 자바의 람다표현식과 비숫
console.log('6-3. 화살표 함수(arrow function): 익명 함수를 간단히 표현하는 문법. 자바의 람다표현식과 비숫');
// 문법1: 파라미터 선언, ...) => { ...; }
// 문법2: 파라미터 선언, ...) => 리턴 값;
const multiplication = (x, y) => x*y;
console.log(multiplication);
console.log(multiplication(2, 3));
console.log('');

// 7. 함수(객체)를 아규먼트로 전달받는 함수
console.log('7. 함수(객체)를 아규먼트로 전달받는 함수');
function calculate(x, y, op) {
    return op(x, y); // 함수 op() 함수의 리턴값을 리턴.
}
console.log(calculate);
console.log(calculate(2, 4, divide));
console.log(calculate(2, 4, add));
console.log(calculate(2, 4, function (x, y) {return x-y * 5}));
console.log(calculate(2, 4, (x,y) => (x- y) * 2));
console.log('');
// -> 이벤트 리스너(핸들러)를 설정할 때 많이 사용되는 JS 코드 패턴
// 콜백(callback): (나중에 호출하기 위해서) 아규먼트로 전달되는 함수 객체.

// 8,9. 지역 함수(local function), 내부 함수(inner function): 함수 내부에서 다른 함수를 선언 
console.log('8,9. 지역 함수(local function), 내부 함수(inner function): 함수 내부에서 다른 함수를 선언');
function increase(n) {
    // 내부 함수 선언.
    function addN(x) {
        // 내부(지역) 함수는 외부 함수에서 선언된 파라미터, 지역변수들을 사용할 수 있음.
        return x + n;
    }
    
    // 함수 (객체 리턴)
    // return add(1); -> 함수 addN()의 리턴 값을 리턴.
    return addN; // -> addN 함수(객체) 리턴.
}
const increaseTwo = increase(2); // -> increaseTwo는 n 값이 2인 함수가 됨.
console.log(increaseTwo);
console.log(increaseTwo(100)); // -> 기존 가지고 있던 2에서 100을 더하는 함수.

const increaseTen = increase(10); // -> increaseTen는 n 값이 10인 함수가 됨.
console.log(increaseTen(100)); // -> 기존 가지고 있던 10에서 100을 더하는 함수.

console.log(increase(1)(100)); // -> increase의 리턴값이 함수이기 때문에 이와 같은 호출이 가능.
// -> increase(1) 함수를 호출한 후 리턴된 함수에 100을 아규먼트로 할당하는 함수를 다시 호출.
console.log('');


console.log('///////////////////////////////////////////////////////////////////////////////////////////');

// 연습1: 숫자 2개를 전달받아서 뺄셈 결과를 리턴하는 함수 
console.log('연습1: 숫자 2개를 전달받아서 뺄셈 결과를 리턴하는 함수');
function subtract(x, y) {
    return x-y;
}

result = subtract('안녕', 'Hello');
console.log(`subtract_result = ${result}`);
// result = NaN --> 문자열은 숫자가 아니므로 NaN

result = subtract(5, 2);
console.log(`subtract_result = ${result}`);


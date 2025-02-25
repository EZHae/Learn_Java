/**
 * destructure.html에 포함.
 */
/*
// 0. 일반적인 함수 선언과 구조분해 할당을 사용한 함수 선언.
console.log('0. 일반적인 함수 선언과 구조분해 할당을 사용한 함수 선언.');
function println1(student) {
    const no = student.no;
    const name = student.name;
    const grade = student.grade;
    const classNo = student.classNo;
    
    console.log(`번호: ${no}, 이름: ${name}, 학년: ${grade}, 반: ${classNo}`);
};
function println2(student) {
    console.log(`번호: ${student.no}, 이름: ${student.name}, 학년: ${student.grade}, 반: ${student.classNo}`);
};

// 구조분해 할당을 사용한 함수 선언
function println3({no, name, grade, classNo}) {
    console.log(`번호: ${no}, 이름: ${name}, 학년: ${grade}, 반: ${classNo}`);
};

console.log('println1 = ', println1);
console.log('');
console.log('println2 = ', println2);
console.log('');
console.log('println3 = ', println3);
console.log('');

// 1. 일반적인 함수와 구조분해 할당을 사용한 함수 활용 예제 
console.log('1. 일반적인 함수와 구조분해 할당을 사용한 함수 활용 예제');
const student1 = {
    no: 100,
    name: '오쌤',
    grade: 1,
    classNo: 2
};
console.log('student1 = ', student1);
console.log('');

println1(student1);
println2(student1);
println3(student1);
console.log('');

// 2. 참조값이 부족한 객체의 여러 함수 활용 예제
console.log('2. 참조값이 부족한 객체의 메서드 활용 예제');
const student2 = {
    no: 200,
    name: '홍길동'
}
console.log('student2 = ', student2);
console.log('');
println1(student2);
println2(student2);
println3(student2);
console.log('');
// student2는 grade(학년), classNo(반)이 없기 때문에 undefined 처리

// 3. 구조분해 할당의 ...rest 연산자를 아규먼트로 하는 함수 선언.
console.log('3. 구조분해 할당의 ...rest 연산자를 아규먼트로 하는 함수 선언');
function printStudent({ name, ... rest }) {
    console.log('name = ', name);
    console.log('rest = ', rest);
};
console.log('printStudent = ', printStudent);
console.log('');

printStudent(student1);
printStudent(student2);
printStudent({name:'이지해', no: 123, email:'wlgo9568@naver.com'});
console.log('');

// 4. 지역변수를 사용해서 객체 생성과 property 초기화
console.log('4. 지역변수를 사용해서 객체 생성과 property 초기화');
const x = 1;
const y = 2;
const point1 = { x: x, y: y }
console.log('point1 = ', point1);

// 객체의 property 이름을 지역 변수의 이름과 동일하게 할 경우, 간단히 쓸 수 있음.
const point2 = { x, y };
console.log('point2 = ', point2);
*/
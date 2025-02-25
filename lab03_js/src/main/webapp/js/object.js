/**
 * object.html에 포함.
 */

/** JSON(JavaScropt Object Notation): 자바스크립트 객체 표현법.
 * { property: value, ... }
 * JS에서 배열과 객체 표현법
 *      - [] : 배열(array)
 *      - {} : 객체(Object)
 */ 
/*
// 0. person 객체 생성
console.log('0. person 객체 생성');
const person = {
    name: '홍길동',
    age: 16,
    phone: ['010-0000-0000', '02-0000-0000']
};
console.log('person = ', person);
console.log('');

// 1. 객체의 프로퍼티에 접근하는 방법
console.log('1. 객체의 프로퍼티에 접근하는 방법')
// (1) 참조 연산자 (Obecjt.propertyName)
console.log('참조 연산자: ', `person.name = ${person.name}, person.age = ${person.age}`);

// (2) 인덱스 연산자 (Object['propertyName'])
console.log('인덱스 연산자: ', `person['name'] = ${person['name']}`);

console.log('- 예제 -');
console.log('person.phone =  ', person.phone);
console.log(`person.phone[0] = ${person.phone[0]}, person.phone[1] = ${person.phone[1]}`);
console.log(`person['name'] = ${person['name']}`);
console.log(`person['phone'][1] = ${person['phone'][1]}`);
console.log('');

// 2. 객체의 프로퍼티 값을 변경.
console.log('2. 객체의 프로퍼티 값을 변경.')
console.log('- person 객체의 age를 17로, 두번째 phone을 02-1111-1111로 바꾸기')
person.age = 17;
person.phone[1] = '02-1111-1111';
console.log(person);
console.log('');

// 3. JS 객체는 객체가 생성된 이후에 새로운 property를 동적으로 추가할 수 있음.
console.log('3. JS 객체는 객체가 생성된 이후에 새로운 property를 동적으로 추가할 수 있음.');
console.log('- person 객체에 email 추가')
person.email = 'hgd@naver.com';
console.log(person);
console.log('');

// 4. 메서드를 갖는 객체 만들기
console.log('4. 메서드를 갖는 객체 만들기');
const score = {
    // properties: 
    html: 100,
    css: 90,
    js: 85,
    
    // methods:
    sum: function() {
        // 메서드에서 객체의 property를 참조할 때 this 키워드 사용해야 함
        return this.html + this.css + this.js;
    },
    mean:function() {
        // 같은 객체의 다른 메서드를 호출 할 때도 this 키워드 사용해야 함
        return this.sum()/3;
    }
};
console.log('score = ', score);
console.log('score.sum() = ', score.sum());
console.log('score.mean() = ', score.mean());
console.log('');

// 5. 생성자 함수(constructor function): this 키워드를 사용해서 property(들)을 선언하고, 
//    같은 property(들)을 갖는 객체들을 생성할 수 있는 함수.
console.log('5. 생성자 함수 만들기');
function Score(html, css, js) {
    // 필드
    this.html = html;
    this.css = css;
    this.js = js;

    // 메서드
    this.sum = function() {
        return this.html + this.css + this.js;
    }
    this.mean = function() {
        return this.sum()/3;
    }
};
// new 키워드를 작성하여 새로운 객체를 생성할 수 있다.
const score1 = new Score(100, 100, 50);
const score2 = new Score(20);
console.log('score1 = ', score1);
console.log('score1.sum() = ', score1.sum());
console.log('score1.mean() = ', score1.mean());
console.log('score2 = ', score2); // html 제외, 값이 할당되지 않았기 때문에(기본 값도 없기 때문에) undefined 처리 된다.
console.log('score2.sum() = ', score2.sum()); // 필드 값이 모두 할당되지 않았기 때문에 NaN 처리 된다.
console.log('score2.mean() = ', score2.mean()); // 필드 값이 모두 할당되지 않았기 때문에 NaN 처리 된다.
console.log('');

// 6. JS 객체는 for-in 구문에서 사용할 수 있다.
console.log('6. JS 객체는 for-in 구문에서 사용할 수 있다.');
const student = {
    no: 123,
    name: '홍길동',
    grade: 1,
    classNo: 1
}
console.log('student = ', student);
console.log(`student['name'] = ${student['name']}`);
// for-in 구문은 객체의 프로퍼티 이름들을 순회(iteration)함.
for (const x in student) {
    console.log(`x: studenxt[x] = ${x}: ${student[x]}`);
    //-> for-in 구문에서 student[x]는 student['no'], student['name'], ... 과 같은 의미를 가진다.
}
console.log('');

// 7-1. JS 객체의 destructuring assignment(구조분해 할당)
console.log('7-1. JS 객체의 destructuring assignment(구조분해 할당)');
// const stuNo = student.no;
// const stuName = student.name;
// const stuGrade = student.grade;
// const stuClassNo = student.classNo;

// const/let { propertyName: 변수선언, ... } = Object;
// 지역 변수 이름을 프로퍼티 이름과 동일하게 선언할 경우: const/let { propertyName, ... } = Object;
const {no: stuNo, name: stuName, grade: stuGrade, classNo: stuClassNo} = student;
console.log(`stuNo: ${stuNo}, stuName: ${stuName}, stuGrade: ${stuGrade}, stuClassNo: ${stuClassNo}`);
const { no, name, grade, classNo} = student;
console.log(`no: ${no}, name: ${name}, grade: ${grade}, classNo: ${classNo}`);
console.log('');

// 7-2. JS객체의 구조분해 할당에서 rest 연산자(...)
//      ...rest를 사용하여 name을 제외한 필드 property들을 rest에 할당. 
console.log('7-2. JS객체의 구조분해 할당에서 rest 연산자');
const { name: studentName, ...rest } = student;
console.log('studentName: ',studentName);
console.log('rest: ', rest);
console.log('');

// 8-1. 클래스 선언과 객체 생성
console.log('8-1. 클래스 선언과 객체 생성')
class Rectangle {
    // 생성자(constructor): 필드 선언과 초기화. 생성자 이름은 반드시 constructor.
    constructor(width = 0, height = 0) {
        this.width = width;
        this.height = height;
    }
    
    // 메서드 - class 안에서는 function 키워드를 사용하지 않음!
    area() {
        return this.width * this.height;
    }
    
    perimeter() {
        return (this.width * this.height) * 2;
    }
}
const rect1 = new Rectangle(); // 클래스 이름으로 생성자 호출
console.log('rect1 = ', rect1);
console.log(`rect1.area(): ${rect1.area()}, rect1.perimeter(): ${rect1.perimeter()}`);

const rect2 = new Rectangle(3, 4);
console.log('rect2 = ', rect2);
console.log(`rect2.area(): ${rect2.area()}, rect2.perimeter(): ${rect2.perimeter()}`);
console.log('');

// 8-2. 8-1의 내용 연습: 원(Circle) 클래스 선언: 필드(radius), 메서드(area, perometer)
console.log('8-2. 8-1의 내용 연습: 원(Circle) 클래스 선언: 필드(radius), 메서드(area, perometer)')
class Circle {
    constructor(radius = 0) {
        this.radius = radius;
    }
    
    area() {
        return this.radius * this.radius * 3.14;
    }
    
    perimeter() {
        return this.radius * 3.14 * 2;
    }
}
const circle1 = new Circle(8);
console.log('circle1 = ', circle1);
console.log(`circle1.area(): ${circle1.area()}, circle1.perimeter(): ${circle1.perimeter()}`);
*/

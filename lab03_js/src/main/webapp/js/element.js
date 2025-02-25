/**
 * element.html에 포함
 * 
 * HTML 요소를 찾는 메서드들
 * document.getElementById
 * document.getElementsByClassName
 * document.getElementsByTagName
 * document.querySelector
 * document.querySelectorAll
 */


// btn1(id로 찾기)의 역할 설정
// button#btn1 요소를 찾음.
const btn1 = document.querySelector('button#btn1');

// btn1 요소에 클릭 이벤트 리스너를 설정.
btn1.addEventListener('click', function() {
     // document.getElementById 메서드를 사용해서 아이디가 div1인 요소를 찾음.
     const div1 = document.getElementById('div1'); // 아이디 앞에 #을 사용하지 않음. ById로 이미 명시 중이기 때문.
     
     // #div1 요소를 제외한 다른 div들의 바탕색을 초기화 하기 위한 설정.
     const divs = document.getElementsByTagName('div');
     for (const element of divs) {
        element.style.backgroundColor = 'white';
     }
     
     // div1 요소의 바탕색을 변경.
     div1.style.backgroundColor = 'lime';
});


// btn2(class로 찾기)의 역할 설정
// button#btn2 요소를 찾음.
const btn2 = document.querySelector('button#btn2');
//           document.getElementById('btn2');

// btn2 요소에 클릭 이벤트 리스너를 설정.
btn2.addEventListener('click', function() {
    // document.getElementsByClassName 메서드를 사용해서 클래스가 c1인 요소들을 찾음.
    const divs = document.getElementsByClassName('c1'); // 클래스 앞에 .을 사용하지 않음. ByClassName으로 명시 중이기 때문.
    console.log(divs); 
    console.log('--> HTMLCollection[]: getElements... 메서드는 요소들을 저장하는 "유사" 배열을 리턴한다.');
    console.log('    그렇기 때문에 for문을 사용할 수 있지만, forEach, filter 등과 같은 메서드는 사용할 수 없다.')
    
    // .c1 요소들을 제외한 다른 div들의 바탕색을 초기화 하기 위한 설정.
    const divs2 = document.getElementsByTagName('div');
    for (const element of divs2) {
       element.style.backgroundColor = 'white';
    }
    
    // divs 배열의 요소들의 바탕색을 변경.
    for (const element of divs) {  
        element.style.backgroundColor = 'tomato';
    }
});


// btn3(tag로 찾기)의 역할 설정
// button#btn3인 요소를 찾음.
const btn3 = document.getElementById('btn3');
//           document.querySelector('button#btn3');

// btn3 요소에 클릭 이벤트 리스너를 설정.
btn3.addEventListener('click', function() {
    // document.getElementsByTagName 메서드를 사용해서 태그가 div인 요소들을 찾음.
    const divs = document.getElementsByTagName('div');
    
    // divs 배열의 요소들의 바탕색을 변경.
    for (const element of divs) {
        element.style.backgroundColor = 'yellow';
    }
});


// btn4(querySelector)의 역할 설정
// button#btn4인 요소를 찾음.
const btn4 = document.querySelector('button#btn4');
//           document.getElementById('btn4');

// btn4 요소에 클릭 이벤트 리스너를 설정. (화살표 함수 사용)
btn4.addEventListener('click', () => {
    // document.querySelector 메서드를 사용해서 div요소 중 아이디가 div4인 요소를 찾음. 
    const div4 = document.querySelector('div#div4');
    
    // div#div4 요소를 제외한 다른 div들의 바탕색을 초기화 하기 위한 설정.
    const divs = document.getElementsByTagName('div');
    for (const element of divs) {
       element.style.backgroundColor = 'white';
    }
    
    // div4 요소들 바탕색을 변경.
    div4.style.backgroundColor = 'slateblue';
});


// btn5(querySelectorAll)의 역할 설정
// button#btn5인 요소를 찾음.
const btn5 = document.querySelector('button#btn5');
//           document.getElementById('btn5');

// btn5 요소에 클릭 이벤트 리스너를 설정. (화살표 함수 사용)
btn5.addEventListener('click', () => {
    // document.querySelectorAll 메서드를 사용해서 div요소 중 클래스가 c2인 요소를 찾음.
    const divs = document.querySelectorAll('div.c2');
    console.log(divs); 
    console.log('--> NodeList[]: querySelectorAll 메서드는 요소들을 저장하는 "유사" 배열을 리턴한다.');
    console.log('    그렇기 때문에 for문, forEach, filter 등과 같은 메서드들을 사용할 수 있다.')
    
    // div.c2 요소들을 제외한 다른 div들의 바탕색을 초기화 하기 위한 설정.
    const divs2 = document.getElementsByTagName('div');
    for (const element of divs2) {
       element.style.backgroundColor = 'white';
    }
    // divs2.forEach(element => element.style.backgroundColor = 'white');
    // -> HTMLCollection은 "유사" 배열이기 때문에 for-each, filter 등과 같은 메서드들을 사용할 수 없음.
    
    // divs 배열의 요소들의 바탕색을 변경. (forEach 사용)
    divs.forEach(element => element.style.backgroundColor = 'violet');
    // for (const element of divs) {
    //     element.style.backgroundColor = 'violet';
    // }
});

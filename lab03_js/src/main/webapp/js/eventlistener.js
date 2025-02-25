/**
 * eventlistener.html에 포함
 */

/** 
 *  DOM(Document Object Model)
 *      브라우저는 서버로 HTML 요청 -> 서버는 HTML을 보냄 -> 클라이언트는 HTML 문서의 모든 요소를 생성하면
 *      -> DOMConetentLoaded를 실행.
 * 
 *  이와 같은 방법을 사용하는 이유
 *      - 안전장치를 설계하기 위해서
 *      - NUllPointException이나 UndifinedException을 방지
 */   

// document(여기선 eventlistener.html)가 DOMContentLoaded를 실행하면 js 문서의 내용을 실행.
document.addEventListener('DOMContentLoaded', function() {
    /* ----------------- click 이벤트 ----------------- */
    // button#btnInput 요소를 찾음.
    const btnInput = document.querySelector('button#btnInput');
    
    // btnInput에 클릭 이벤트 리스너를 설정.
    btnInput.addEventListener('click', () => {
        // console.log(e); --> PointerEvent 객체
        // e.target(property): 이벤트가 발생한 HTML 요소.
        
        // input#itemInput 요소를 찾음.
        const itemInput = document.querySelector('input#itemInput')
            
        // ul#itemList 요소를 찾음.
        const itemList = document.querySelector('ul#itemList');
        
        // itemInput에 입력된 문자열을 itemList의 <li>로 추가(append).
        itemList.innerHTML += `<li>${itemInput.value}</li>`
        
        // itemInput에 입력된 문자열을 지움.
        itemInput.value = '';
    });
    
    
    /* ----------------- keydown 이벤트 ----------------- */
    // input#itemInput2 요소를 찾음
    const itemInput2 = document.querySelector('input#itemInput2');
    
    // itemInput2에 keydown 이벤트 리스너를 설정.
    itemInput2.addEventListener('keydown', (e) => {
        // console.log(e); --> KeyboardEvent 객체
        // e.key(property): 키보드의 어떤 키가 keydown 됐는지를 알려주는 property
        
        // Enter 키가 keydown 됐을 때
        if (e.key === 'Enter') {
            // ul#itemList2 요소를 찾음.
            const itemList2 = document.querySelector('ul#itemList2');
                    
            // itemInput2에 입력된 문자열을 itemList의 <li>로 추가(append).
            itemList2.innerHTML += `<li>${itemInput2.value}</li>`
                    
            // itemInput2에 입력된 문자열을 지움.
            itemInput2.value = '';
        }
    });
    
    
    /* ----------------- change 이벤트 ----------------- */
    // input#userid 요소를 찾음
    const userid = document.querySelector('input#userid');
    
    // userid에 change 이벤트 리스너를 설정.
    userid.addEventListener('change', () => {
        // console.log(e); --> Event 객체
        
        // div#result 요소를 찾음.
        const result = document.querySelector('div#result');
        
        // userid에 입력된 문자열을 result에 출력.
        result.innerHTML = `<span style='color: red'>${userid.value}</span>`; 
    });
    
    
    /* ----------------- mouseenter/leave 이벤트 ----------------- */
    // img#bulb 요소를 찾음
    const bulb = document.querySelector('img#bulb');
    
    // bulb에 mouseenter 이벤트 리스너를 설정.
    bulb.addEventListener('mouseenter', () => {
        // img를 bulb_on.gif로 교체
        bulb.src = 'images/bulb_on.gif';
        bulb.alt = 'bulb_on'
    });
    
    // bulb에 mouseleave 이벤트 리스너를 설정.
    bulb.addEventListener('mouseleave', () => {
        // img를 bulb_off.gif로 교체
        bulb.src = 'images/bulb_off.gif';
        bulb.alt = 'bulb_off'
    });
    
});

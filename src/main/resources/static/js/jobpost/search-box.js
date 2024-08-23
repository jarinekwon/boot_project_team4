const selectedItemsContainer = document.getElementById('selectedItems');// 선택한 아이템 출력될 영역
const items = document.querySelectorAll('.item'); //드롭다운 박스의 선택하는 검색 내용들

var cnt = 0; //갯수 제한(max: 10개까지 제한)

//검색 리스트 영역 마우스 오버 시
var searchbox = document.getElementById('searchbox');
searchbox.addEventListener('mouseover', ()=> {
	// 모든 .scrollable-content 숨기기
	var allScrollableContents = document.querySelectorAll(".scrollable-content");
	allScrollableContents.forEach(function(content) {
		content.style.display = "none";
	});
});

//각 항목에서의 선택한 아이템
//const job = document.getElementById('job');
const loc = document.getElementById('loc');
const career = document.getElementById('career');
const edu = document.getElementById('edu');
const wrkty = document.getElementById("wrkty");
function showsearchBox(event) {
	if (event.target.tagName === 'LI') {
		//console.log('클릭됨:', event.target.textContent);
		var searchbox = document.querySelector(".search-box");
		searchbox.classList.add("box-show");
	}
}
//job.addEventListener('click', showsearchBox);
loc.addEventListener('click', showsearchBox);
career.addEventListener('click', showsearchBox);
edu.addEventListener('click', showsearchBox);
wrkty.addEventListener('click', showsearchBox);

// 클릭된 항목을 검색창 영역에 추가시키는 함수
function addItemToSearchBox(id, value, text) {
	const additems = document.querySelectorAll(".selected-item");
	var flag = false;
	
	// 중복 체크
	additems.forEach((item)=>{
		if(item.textContent === text) {
			flag = true;
			return;
		}
	});
	
	if(flag) {
		return;
	}
	if(cnt > 9) {
		alert("검색 갯수는 최대 10개 입니다...");
		return;
	}
	
	const button = document.createElement('button');
	button.textContent = text;
	button.classList.add('selected-item');
	button.setAttribute("value", value);
	button.setAttribute("name", id);
	button.type = "button";

	//button에 span 추가
	const icon = document.createElement('span');
	icon.classList.add('ico');
	button.appendChild(icon);
	
	//클릭 시 삭제 이벤트 등록 -> 동적으로 할당된 요소라 생성과 동시에 이벤트 등록
	button.addEventListener('click', ()=>{
		button.remove();
		cnt--;
		if(cnt == 0) {
			searchbox.classList.remove("box-show");
			resetPage(); //jobpost_fn.js에 있음
		}
	});
	
	selectedItemsContainer.appendChild(button);
	cnt++;
}

//항목에서 선택할때 처리하는 이벤트
items.forEach(function(item) {
	item.addEventListener('click', function(){
		const text = item.textContent;
		const value = item.getAttribute('value');
		const id = $(this).closest('ul').attr('id');
		addItemToSearchBox(id, value, text);
	});
});
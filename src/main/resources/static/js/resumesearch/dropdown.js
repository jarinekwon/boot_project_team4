document.addEventListener("DOMContentLoaded", function() {
	var Post = document.querySelectorAll("#postArea");
	var dropdownButton = document.querySelectorAll(".dropdown-button");
	const closeButtons = document.querySelectorAll('.close-button');
	
	//공고 조회 데이터 영역에 마우스 오버 시 드롭박스 화면에서 미출력
	Post.forEach(function(post) {
		post.addEventListener("mouseover", () => {
			// 모든 .scrollable-content 숨기기
			var allScrollableContents = document.querySelectorAll(".scrollable-content");
			allScrollableContents.forEach(function(content) {
				content.style.display = "none";
			});
		});
	});

	dropdownButton.forEach(function(dropbtn) {
		dropbtn.addEventListener("mouseover", function() {
			// 모든 .scrollable-content 숨기기
			var allScrollableContents = document.querySelectorAll(".scrollable-content");
			allScrollableContents.forEach(function(content) {
				content.style.display = "none";
			});
			
			//모든 항목에서 'active' 클래스 제거
			document.querySelectorAll(".dropdown-button").forEach(i => {
				i.classList.remove("active");
			});
			
			// 현재 오버된 항목의 .scrollable-content 보이기
			var scrollableContent = this.nextElementSibling;
			scrollableContent.style.display = "block";
			this.classList.add("active"); //현재 오버된 항목에 'active' 클래스 추가
		});
		
		dropbtn.addEventListener("click", function () {
			var scrollableContent = this.nextElementSibling;
			if(scrollableContent.style.display == 'none') {
				scrollableContent.style.display = "block";
			} else {
				scrollableContent.style.display = "none";
			}
		});
	});
});
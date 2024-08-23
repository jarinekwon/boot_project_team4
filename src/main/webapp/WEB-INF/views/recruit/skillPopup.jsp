<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.nio.charset.StandardCharsets"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/recurit/skillPopup.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/recruit/recruit_fn.js"></script>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="skillPopup.css">
</head>
<body>
	<div class="popup">
	    <h3>스킬 검색</h3>
		<input type="hidden" id="skillno" name="skillno">
		<div id="show-skill">
		    <c:forEach items="${showskilldto}" var="skilldto">
		        <button type="button" class="skill-button" value="${skilldto.skillno}" data-no="${skilldto.skillno}">
					${skilldto.skillnm}
				</button> 
		    </c:forEach>
		</div>
		
		    <h3>내가 선택한 스킬 (<span id="selectedCount">0</span>/5)</h3>
			    <div class="selected-skills">
			        <span class="material-icons reset-icon" onclick="resetSelection()">refresh</span> <!-- 초기화 아이콘 -->
			    </div>
				<div class="button-container">
					<span class="close" onclick="window.close()">닫기</span>
				    <button type="button" class="save-button" onclick="saveSkills()">선택완료</button>
				</div>
			</div>
	
	<script>
		const maxSelection = 5;

		document.querySelectorAll('.skill-button').forEach(button => {
		    button.addEventListener('click', () => {
		        const skillNo = button.dataset.no;
		        addSkill(skillNo, button.textContent);
		    });
		});

		function addSkill(skillNo, skillName) {
		    const selectedSkillsDiv = document.querySelector('.selected-skills');
		    const selectedCountSpan = document.querySelector('#selectedCount');
		    const selectedSkills = Array.from(selectedSkillsDiv.querySelectorAll('.selected-skill')).map(el => el.dataset.no);

		    if (selectedSkills.length >= maxSelection) {
		        alert(`최대 5개의 스킬만 선택할 수 있습니다.`);
		        return; // 5개 이상 선택하지 않도록 종료
		    }
			
		    if (!selectedSkills.includes(skillNo)) {
		        const newSkill = document.createElement('span');
		        newSkill.className = 'selected-skill';
		        newSkill.dataset.no = skillNo;
		        newSkill.textContent = skillName;
		        newSkill.addEventListener('click', () => removeSkill(newSkill));
		        selectedSkillsDiv.appendChild(newSkill);
		        selectedCountSpan.textContent = selectedSkillsDiv.querySelectorAll('.selected-skill').length;
		    }
		}

		function removeSkill(skillElement) {
		    const selectedSkillsDiv = document.querySelector('.selected-skills');
		    selectedSkillsDiv.removeChild(skillElement);
		    const selectedCountSpan = document.querySelector('#selectedCount');
		    selectedCountSpan.textContent = selectedSkillsDiv.querySelectorAll('.selected-skill').length;
		}

		function resetSelection() {
		    const selectedSkillsDiv = document.querySelector('.selected-skills');
		    selectedSkillsDiv.innerHTML = '<span class="material-icons reset-icon" onclick="resetSelection()">refresh</span>'; // 초기화 아이콘 유지
		    document.querySelector('#selectedCount').textContent = '0'; // 카운트 초기화
		}

		function saveSkills() {
		    const selectedSkills = Array.from(document.querySelectorAll('.selected-skill'))
		        .map(el => el.textContent.trim())
		        .filter(skill => skill.length > 0)
		        .join(', ');

		    if (window.opener) {
		        window.opener.document.querySelector('#skills').value = selectedSkills;
		    }
		    window.close();  // 팝업을 닫습니다
		}
	</script>
</body>
</html>

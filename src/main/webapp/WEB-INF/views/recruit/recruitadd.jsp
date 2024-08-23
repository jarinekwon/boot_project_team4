<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/recurit/recurit.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/recruit/recruit_fn.js"></script>
	
	<script>
	    // 스킬 팝업창
		function searchSkill() {
		    // 팝업창의 크기
		    var width = 1000;
		    var height = 600;

		    // 화면의 크기
		    var screenWidth = window.innerWidth;
		    var screenHeight = window.innerHeight;

		    // 팝업창의 위치를 계산하여 중앙에 배치
		    var left = (screenWidth - width) / 2;
		    var top = (screenHeight - height) / 2;

		    // 팝업창 열기
		    window.open(
		        '${pageContext.request.contextPath}/skillPopup', 
		        'skillPopup', 
		        'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top + ',scrollbars=yes'
		    );
		}
	</script>
	
</head>
<jsp:include page="../../header.jsp"/>
<body>
    <div class="container">
        <form id="jobPostingForm" action="recruitinsert" method="get">
            <h2 div class="info">기업 소개</h2>
                <div class="infoTbBx">
					<div class="tbRow">
						<div class="tbCell">
							<div class="elWrap">
								<div class="schInpType">
									<input type="text" class="inpDefault" id="jobtitle" name="jobtitle" maxlength="50" placeholder=" ">
							        <label class="ph">제목 <span class="point">*<span class="skip">필수</span></span></label>
								</div>
							</div>
						</div>
						<div class="tbCell">
							<div class="elWrap">
								<div class="schInpType">
									<input type="text" class="inpDefault" id="jobsubtitle" name="jobsubtitle" maxlength="100" placeholder=" ">
							        <label class="ph">부제목 </span></label>
								</div>
							</div>
						</div>
						<div class="tbCell">
							<div class="elWrap">
								<div class="schInpType">
									<input type="text" class="inpDefault" id="content" name="content" maxlength="500" placeholder=" ">
							        <label class="ph">내용 </span></label>
								</div>
							</div>
						</div>
					<div>
                </div>
            </div>
		</div>
	</div>
	
	<div class="container">
		<h2 div class="info">포지션 및 자격요건</h2>
			<div class="infoTbBx">
				<div class="tbRow">
					<div class="tbCell">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault has-placeholder" id="position" name="position" maxlength="50" placeholder="최대한 직무명을 포함하여 입력하세요. ex) 웹프로그래머, 아트디렉터, java개발 등">
						        <label class="ph">담당업무 <span class="point">*<span class="skip">필수</span></span></label>
							</div>
						</div>
					</div>
					<div class="tbCell">
						<div class="elWrap">
							<div class="schInpType">
							    <input type="text" class="inpDefault" id="skills" name="skills" maxlength="50" placeholder=" ">
							    <label class="ph">스킬 <span class="point">*<span class="skip">필수</span></span></label>
							    <div class="postal-link-container">
							        <a href="javascript:void(0)" onclick="searchSkill()">스킬 검색</a>
							    </div>
							</div>
						</div>
					</div>
					<div class="tbCell2">
					    <div class="elWrap">
					        <div class="schInpType">
					            <select id="prsup" name="prsup" class="inpDefault">
					                <option value="" disabled selected>경력 선택</option>
					                <c:forEach items="${prsup}" var="prsup">
					                    <option value="${prsup.careerno }">${prsup.careernm}</option>
					                </c:forEach>
					            </select>
					            <label id="devCareerno" class="ph">
					                경력 <span class="point">*<span class="skip">필수</span></span>
					            </label>
					        </div>
					    </div>
					</div>
					<div class="tbCell2">
					    <div class="elWrap">
					        <div class="schInpType">
					            <select id="educa" name="educa" class="inpDefault">
					                <option value="" disabled selected>학력 선택</option>
					                <c:forEach items="${educa}" var="educa">
					                    <option value="${educa.eduno }">${educa.edunm}</option>
					                </c:forEach>
					            </select>
					            <label id="educa" class="ph">
					                학력 <span class="point">*<span class="skip">필수</span></span>
					            </label>
					        </div>
					    </div>
					</div>
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="duties" name="duties" maxlength="50" placeholder=" ">
						        <label class="ph">자격 요건 <span class="point">*<span class="skip">필수</span></span></label>
							</div>
						</div>
					</div>
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="pfntcd" name="pfntcd" maxlength="50" placeholder=" ">
						        <label class="ph">우대 조건 <span class="point">*<span class="skip">필수</span></span></label>
							</div>
						</div>
					</div>
				</div>
            </div>
        </div>
	</div>
	
	<div class="container">
		<h2 div class="info">근무 조건</h2>
			<div class="infoTbBx">
				<div class="tbRow">
					<div class="tbCell2">
					    <div class="elWrap">
					        <div class="schInpType">
					            <select id="wrkty" name="wrkty" class="inpDefault">
					                <option value="" disabled selected>고용 형태</option>
					                <c:forEach items="${wrkty}" var="wrkty">
					                    <option value="${wrkty.wrktyno }">${wrkty.wrktynm }</option>
					                </c:forEach>
					            </select>
					            <label id="wrkty" class="ph">
					                고용 형태 <span class="point">*<span class="skip">필수</span></span>
					            </label>
					        </div>
					    </div>
					</div>
					<form action="processForm.jsp" method="post">
						<div class="tbCell2">
						    <div class="elWrap">
						        <div class="schInpType">
						            <input type="text" class="inpDefault has-placeholder" id="salary" name="salary" 
						                   placeholder="미입력시 '면접후 결정'" 
						                   maxlength="50"/>
						            <label class="ph">급여 <span class="point">*<span class="skip">필수</span></span></label>
						        </div>
						    </div>
						</div>
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="loc01" name="loc01" maxlength="50" placeholder=" ">
						        <label class="ph">근무 지역 <span class="point">*<span class="skip">필수</span></span></label>
							</div>
						</div>
					</div>
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="wrktm" name="wrktm" maxlength="50" placeholder=" ">
						        <label class="ph">근무 요일/시간 <span class="point">*<span class="skip">필수</span></span></label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<h2 div class="info">복지 및 혜택</h2>
			<div class="infoTbBx">
				<div class="tbRow">
					<div class="tbCell">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="benefits" name="benefits" maxlength="50" placeholder=" ">
						        <label class="ph">복리후생 </label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<h2 div class="info">접수 기간 및 방법</h2>
			<div class="infoTbBx">
				<div class="tbRow">
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault has-placeholder" id="pamoa" name="pamoa" maxlength="50" 
								placeholder="미입력시 '온라인 이력서 제출'">
						        <label class="ph">접수 방법 </label>
							</div>
						</div>
					</div>
					<div class="tbCell2">
					    <div class="elWrap">
					        <div class="schInpType">
					            <input type="date" class="inpDefault" id="ddate" name="ddate"/>
					            <label class="ph" for="ddate">마감일자 <span class="point">*</span></label>
					        </div>
					    </div>
					</div>
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="recno" name="recno" maxlength="50" placeholder=" ">
						        <label class="ph">모집 인원 </label>
							</div>
						</div>
					</div>
					<div class="tbCell2">
						<div class="elWrap">
							<div class="schInpType">
								<input type="text" class="inpDefault" id="rcmpcd" name="rcmpcd" maxlength="50" placeholder=" ">
						        <label class="ph">채용 절차 </label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>                 
	
    <div class="button-container">
        <button type="submit" class="submit-button">저장 하기</button>
    </div>
    </form>
</div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Resume List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/resume/resume.css">
    <script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="container">
        <section class="resume-form">
            <form name="resumeupfrm" method="POST" action="resumeupdate" enctype="multipart/form-data">
                <input type="hidden" name="prono" value="${resumeselect.prono }">
                <input type="hidden" name="imgno" value="${resumeselect.imgno }">
                <div class="form-section">
					<h3>이력서 제목</h3>
                    <div class="form-container1">
                        <div class="input-container">
                            <input type="text" id="protitle" name="protitle" placeholder=" " value="${resumeselect.protitle }" />
                            <label for="protitle">제목 <span class="c-r">*</span></label>
                        </div>
                    </div>
                    <div class="form-section1">
                        <div class="photo-preview" id="photoPreview">
                            <!-- 선택한 사진 미리보기 영역 -->
                            <img src="show_resume_img?writer=${resumeselect.puserid}&prono=${resumeselect.prono}&imgno=${resumeselect.imgno}&imggubun=b">
                        </div>
                    </div>
                </div>
                <div class="form-section">
                    <h3>인적사항</h3>
                    <div class="form-container">
                        <div class="input-container">
                            <input type="text" id="nameInput1" name="pname" placeholder=" " value="${resumeselect.pname }" />
                            <label for="nameInput1">이름 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container">
                            <fmt:formatDate value="${resumeselect.birdy}" pattern="yyyy-MM-dd" var="formatbirdy" />
                            <input type="text" id="birthDateInput" name="birdy" placeholder="" maxlength="10" value="${formatbirdy }" required onkeyup="formatDate(event)"/>
                            <label for="birthDateInput">생년월일 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container w100">
                            <select id="gender" class="" required name="gender">
                                <option value="" disabled selected>성별</option>
                                <option value="1" ${resumeselect.gender eq 1 ? 'selected' : '' }>남성</option>
                                <option value="2" ${resumeselect.gender eq 2 ? 'selected' : '' }>여성</option>
                            </select>
                        </div>
                        <div class="input-container email">
                            <input type="text" id="email" name="email" placeholder=" " required value="${resumeselect.email }" />
                            <label for="email">이메일 <span class="c-r">*</span></label>
                        </div>
                        <div class="photo-upload">
                            <label for="photo">사진 추가</label>
                            <input type="file" id="photo" name="imgfile" accept="image/*" />
                        </div>
                    </div>
                    <div class="form-container">
                        <div class="input-container">
                            <input type="text" id="phoneNumber" name="phone" placeholder="EX)01012345678" maxlength="13" required value="${resumeselect.phone }" onkeyup="formatPhone(event)"/>
                            <label for="phoneNumber">휴대전화번호 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container address" style="display: flex;">
                            <input type="text" id="addRess" name="paddr" placeholder=" " value="${resumeselect.paddr }" />
                            <label for="addRess">주소</label>&nbsp;
                            <button type="button" class="postal-button" onclick="open_kakao_api()" style="width: 130px;">우편번호 찾기</button>
                        </div>
                    </div>
                    <div class="form-container"></div>
                </div>
                <div class="form-section skill-container">
                	<h3>직무</h3>
					<div class="job-selection">
					    <select class="selection" name="job" required>
					    	<option value="" >직무선택</option>
					    	<c:forEach items="${jobsort }" var="jobsort">
					    		<option value="${jobsort.sortno }" ${resumeselect.job eq jobsort.sortno ? 'selected':'' }>${jobsort.sortnm }</option>	
					    	</c:forEach>
					    </select>
					</div>
                    <h3>스킬</h3>
                    <input type="hidden" id="skillno" name="skillno">
                    <div id="show-skill">
                        <c:forEach items="${showskilldto }" var="showskilldto">
                            <button type="button" class="skill-button" value="${showskilldto.skillno }" data-no="${showskilldto.skillno }">${showskilldto.skillnm }</button>
                        </c:forEach>
                    </div>
                    <h3>내가 선택한 스킬 (<span id="selectedCount">0</span>/36)</h3>
                    <div class="selected-skills">
                    	<%-- <input type="hidden" id="selectedSkills" name="selectedSkills" value=""> --%>
                        <c:forEach items="${skilldto }" var="skilldto">
                            <button type="button" data-skill="${skilldto.skillname }" data-no="${skilldto.sequeno }" name="skillbutton" class="selected-skill" onclick="removeskill(this)">${skilldto.skillname }</button>
                        </c:forEach>
                    </div>
                    <button type="button" class="reset-button">초기화</button>
                </div>
                <div class="form-section">
                    <h3>학력</h3>
                    <div class="form-container">
                        <div class="input-container w100">
                            <select class="classGubun" name="classgb" required>
                                <option value="" disabled selected>학교구분</option>
                                <option value="1" ${resumeselect.classgb eq 1 ? 'selected' : '' }>고등학교 졸업</option>
                                <option value="2" ${resumeselect.classgb eq 2 ? 'selected' : '' }>대학교(2,3년)</option>
                                <option value="3" ${resumeselect.classgb eq 3 ? 'selected' : '' }>대학교(4년)</option>
                                <option value="4" ${resumeselect.classgb eq 4 ? 'selected' : '' }>대학원</option>
                            </select>
                        </div>
                        <div class="input-container fx-1">
                            <input type="text" id="schoolName" name="shoolnm" placeholder=" " required value="${resumeselect.shoolnm }" />
                            <label for="schoolName">학교명 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container w100">
                            <fmt:formatDate value="${resumeselect.sdate}" pattern="yyyy-MM-dd" var="formatsdate" />
                            <input type="text" id="sDate" name="sdate" placeholder="yyyy-MM-dd" required maxlength="10" value="${formatsdate }" onkeyup="formatDateym(event)"/>
                            <label for="sDate">입학년월 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container w100">
                            <fmt:formatDate value="${resumeselect.edate}" pattern="yyyy-MM-dd" var="formatedate" />
                            <input type="text" id="eDate" name="edate" placeholder="yyyy-MM-dd" required maxlength="10" value="${formatedate }" onkeyup="formatDateym(event)"/>
                            <label for="eDate">졸업년월 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container w100">
                            <select class="gradStatus" name="gradesta" required>
                                <option value="" disabled selected>졸업상태</option>
                                <option value="1" ${resumeselect.gradesta eq 1 ? 'selected' : ''}>휴학</option>
                                <option value="2" ${resumeselect.gradesta eq 2 ? 'selected' : ''}>중퇴</option>
                                <option value="3" ${resumeselect.gradesta eq 3 ? 'selected' : ''}>졸업</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-container">
                        <div class="input-container fx-1">
                            <input type="text" id="majorName" name="majornm" placeholder=" " required value="${resumeselect.majornm }" />
                            <label for="majorName">전공명 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container w100">
                            <input type="text" id="credit" name="credit" placeholder=" " value="${resumeselect.credit }" />
                            <label for="credit">학점</label>
                        </div>
                        <div class="input-container w100">
                            <select class="score" name="score" required>
                                <option value="" disabled selected>총점</option>
                                <option value="1" ${resumeselect.score eq 1 ? 'selected' : ''}>4.0</option>
                                <option value="2" ${resumeselect.score eq 2 ? 'selected' : ''}>4.3</option>
                                <option value="3" ${resumeselect.score eq 3 ? 'selected' : ''}>4.5</option>
                                <option value="4" ${resumeselect.score eq 4 ? 'selected' : ''}>5.0</option>
                                <option value="5" ${resumeselect.score eq 5 ? 'selected' : ''}>7.0</option>
                                <option value="6" ${resumeselect.score eq 6 ? 'selected' : ''}>100</option>
                            </select>
                        </div>
                        <div class="input-container">
                            <input type="text" id="pay" name="salary" placeholder=" " required value="${resumeselect.salary }" />
                            <label for="pay">희망연봉 <span class="c-r">*</span></label>
                        </div>
                        <div class="input-container w100">
                            <select id="gubun" name="armgu" required>
                                <option value="" disabled selected>병역구분</option>
                                <option value="1" ${resumeselect.armgu eq 1 ? 'selected' : ''}>필</option>
                                <option value="2" ${resumeselect.armgu eq 2 ? 'selected' : ''}>미필</option>
                                <option value="3" ${resumeselect.armgu eq 3 ? 'selected' : ''}>면제</option>
                            </select>
                        </div>
                    </div>
                    <h3>경력&nbsp;
		                <button type="button" class="postal-button" onclick="add_career()">+ 추가</button>
		                <button type="button" class="postal-button" onclick="removediv()">- 제거</button>
                    </h3>
                    <div id="addArea">
	                	<input type="hidden" id="corpnm_s" name="corpnm_s">
	                	<input type="hidden" id="sdate_s" name="sdate_s">
	                	<input type="hidden" id="edate_s" name="edate_s">
	                	<input type="hidden" id="wrkty_s" name="wrkty_s">
	                	<input type="hidden" id="position_s" name="position_s">
	                	<input type="hidden" id="task_s" name="task_s">
	                	<%-- 경력 입력란 추가 --%>
	                    <c:forEach var="career" items="${rcareerList}">
	                    	<div>
				                <div class="form-container">
				                    <div class="input-container">
				                        <input type="text" id="corporate" name="corpnm_c" value="${career.corpnm}" placeholder=" " />
				                        <label for="corporate">회사명</label>
				                    </div>
				                    <div class="input-container">
				                    	<fmt:formatDate value="${career.sdate}" var="sdate" pattern="yyyy-MM-dd"/>
				                        <input type="text" id="employment" name="sdate_c" value="${sdate}" placeholder=" " required maxlength="8" required onkeyup="formatDate(event)"/>
				                        <label for="employment">입사년월</label>
				                    </div>
				                    <div class="input-container">
				                    	<fmt:formatDate value="${career.edate}" var="edate" pattern="yyyy-MM-dd"/>
				                        <input type="text" id="resignation" name="edate_c" value="${edate}" placeholder=" " required maxlength="8" required onkeyup="formatDate(event)"/>
				                        <label for="resignation">퇴사년월</label>
				                    </div>
				                    <div class="wrkty-types">
				                        <select class="types" name="wrkty_c" required>
				                            <option value="">근무형태</option>
		                                    <option value="1" ${career.wrkty == 1 ? 'selected' : ''}>정규직</option>
		                                    <option value="2" ${career.wrkty == 2 ? 'selected' : ''}>계약직</option>
		                                    <option value="3" ${career.wrkty == 3 ? 'selected' : ''}>기간제</option>
				                        </select>
				                    </div>
				                    <div class="input-position">
				                        <input type="text" id="position" name="position_c" value="${career.position }" placeholder=" " />
				                        <label for="position">직급</label>
				                    </div>
				                </div>
				                <div class="input-container">
				                    <input type="text" id="business" name="task_c" value="${career.task }" placeholder=" " />
				                    <label for="business">주요업무</label>
				                </div>
			                </div>
		                </c:forEach>
	                </div>
					<div class="input-container">
						<h3>포트폴리오</h3>
						<input type="text" id="propo" name="propo" placeholder="git주소 및 url등록해주세요" value="${resumeselect.propo}" />
					</div>
                    <div class="form-section">
                        <h3>자기소개서</h3>
                        <div class="form-container">
                            <div class="input-container">
                                <textarea class="fixed-size-textareas" name="proself">${resumeselect.proself }</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
	                <button type="button" class="submit-button" onclick="Resumelist()">목록보기</button>&nbsp;
                </div>
				<div>
	                <button type="button" class="crystal-button" onclick="updateResume()">수정</button>
				</div>	
            </form>
        </section>
    </div>
    <script src="${pageContext.request.contextPath}/js/resume/resume_fn.js"></script>
</body>
</html>


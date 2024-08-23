<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/recurit/recruitinfo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/recruit/recruit_fn.js"></script>
    <title>기업 채용 공고</title>
    <script>
        function fnMove(seq) {
            var offset = $("#section" + seq).offset();
            $('html, body').animate({
                scrollTop: offset.top
            }, 400); //0.4초 느리게
        }
		
		function openResumePopup(writer, csrno, jobno) {
			var popupWidth = 600;
			var popupHeight = 400;

			// 현재 브라우저 창의 너비와 높이를 가져옵니다.
			var screenWidth = window.innerWidth;
			var screenHeight = window.innerHeight;

			// 팝업 창의 위치를 중앙으로 설정합니다.
			var left = (screenWidth - popupWidth) / 2 + window.screenX;
			var top = (screenHeight - popupHeight) / 2 + window.screenY;
			
		    var popupUrl = '${pageContext.request.contextPath}/resumePopup?writer='+writer+'&csrno='+csrno+'&jobno='+jobno;
			var win = window.open(popupUrl, 'ApplyPopup', 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + left + ',top=' + top);

		    // 팝업 창이 완전히 열린 후에 이력서 리스트를 가져오기 위해 onload 이벤트를 설정합니다.
		    win.onload = function() {
		        // 이력서 리스트를 가져와서 콤보박스에 추가합니다.
		        var resumelist = win.document.getElementById('resumelist');
		        var select = document.getElementById('resumelist'); // 콤보박스 id를 'resumelist'로 변경해야 합니다.

		        for (var i = 0; i < resumelist.options.length; i++) {
		            var option = document.createElement('option');
		            option.value = resumelist.options[i].value;
		            option.text = resumelist.options[i].text;
		            select.appendChild(option);
		        }
		    };
		}
	</script>
	
	<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=88cea8a6c9ebdd581d629441e46f8875&libraries=services"></script>
	<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=88cea8a6c9ebdd581d629441e46f8875"></script>
	<script>
		function initMap() {
			var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
			var options = { //지도를 생성할 때 필요한 기본 옵션
				center: new kakao.maps.LatLng(33.450701, 126.570667),  //지도의 중심좌표.
				level: 3 //지도의 레벨(확대, 축소 정도)
			};
			
			var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
			var geocoder = new kakao.maps.services.Geocoder();
            var address = document.getElementById('location').textContent.trim();
            console.log("Address:", address);

            if (!address) {
                console.error("주소 잘못됐다");
                return;
            }

            geocoder.addressSearch(address, function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                    console.log("좌표:", coords);

                    // 지도의 중심좌표를 변환된 좌표로 설정
                    map.setCenter(coords);

                    // 마커를 생성하여 지도에 표시
                    var marker = new kakao.maps.Marker({
                        position: coords
                    });
                    marker.setMap(map);
                } else {
                    console.error("주소 변환 실패. 상태 :", status);
                }
            });
        }
		// 문서가 완전히 로드된 후 지도 초기화
        document.addEventListener('DOMContentLoaded', initMap);
	</script>
   
</head>
<jsp:include page="../../header.jsp"/>
<body>
<div>
	<div class="container">
	    <table class="main-column">
	        <tr>
	            <td colspan="2">
	                <div class="header-container">
	                    <div class="company-details">
	                        <div class="company-name">
	                            <a href="/coinfoshow?writer=${jobinfoData.cuserid}&gubun=post">
	                            	<strong>${companyInfo.cusnm}</strong>
								</a>
	                            <c:if test="${usergubun == 'p'}">
		                            <div id="scribe_button">
		                                <!-- 관심 기업 구독 -->
		                                <button type="button" id="scribed_button" class="girBtn scribeButton ${scribe_tf == 'F' ? 'girBtnFav' : 'girBtnFavOn'}" onclick="scribed_p('${jobinfoData.cuserid}', ($(this).hasClass('girBtnFavOn') ? 'T' : 'F'))">
		                                    <span>관심 기업</span>
		                                </button>
		                            </div>
	                            </c:if>
	                        </div>
	                        <div class="job-title">
	                            <strong><h1>${jobinfoData.jobtitle}</h1></strong>
	                            <c:if test="${usergubun == 'p'}">
		                            <div id="scrap_button">
		                                <button type="button" id="scraped_button" class="girBtn1 scrapButton ${scrap_tf == 'F' ? 'girBtn1Fav' : 'girBtn1FavOn'}" 
		                                		onclick="scrap_p('${jobinfoData.cuserid}','${jobinfoData.jobno}', ($(this).hasClass('girBtn1FavOn') ? 'T' : 'F'))">
		                                </button>
		                            </div>
	                            </c:if>
	                        </div>
	                    </div>
	                </div>
	            </td>
	        </tr>
			<table class="style2">
		        <tr>
		            <td>
						<strong>경력 : </strong>
						<c:choose>
						    <c:when test="${jobinfoData.prsup == '0'}">신입/무관</c:when>
						    <c:when test="${jobinfoData.prsup == '1'}">1-3년</c:when>
						    <c:when test="${jobinfoData.prsup == '2'}">3-5년</c:when>
						    <c:when test="${jobinfoData.prsup == '3'}">5-10년</c:when>
						    <c:when test="${jobinfoData.prsup == '4'}">10년 이상</c:when>
						    <c:otherwise>미정</c:otherwise>
						</c:choose>
		            </td>
		            <td>
						<strong>담당업무 : </strong>${jobinfoData.position}
		            </td>
		        </tr>
		        <tr>
		            <td>
						<strong>학력 : </strong>
						<c:choose>
		                     <c:when test="${jobinfoData.educa == '0'}">무관</c:when>
		                     <c:when test="${jobinfoData.educa == '1'}">고졸</c:when>
		                     <c:when test="${jobinfoData.educa == '2'}">전문대졸</c:when>
		                     <c:when test="${jobinfoData.educa == '3'}">대졸</c:when>
		                     <c:when test="${jobinfoData.educa == '4'}">석사</c:when>
		                     <c:when test="${jobinfoData.educa == '5'}">박사</c:when>
		                     <c:otherwise>미정</c:otherwise>
						</c:choose>
		            </td>
		            <td>
						<strong>스킬 : </strong>${jobinfoData.skills}
		            </td>
		        </tr>
		        <tr>
		            <td>
		                <strong>고용 형태 : </strong>
		                <c:choose>
		                    <c:when test="${jobinfoData.wrkty == '0'}">정규직</c:when>
		                    <c:when test="${jobinfoData.wrkty == '1'}">계약직</c:when>
		                    <c:when test="${jobinfoData.wrkty == '2'}">기간제</c:when>
		                    <c:otherwise>미정</c:otherwise>
		                </c:choose>
		            </td>
		            <td>
						<strong>근무 요일/시간 : </strong>${jobinfoData.wrktm}
		            </td>
		        </tr>
		        <tr>
		            <td>
					<strong>급여 : </strong>
						<c:choose>
						    <c:when test="${jobinfoData.salary == 0}">
						        회사 내규에 따름 - 면접 후 결정
						    </c:when>
						    <c:otherwise>
						        ${jobinfoData.salary}
						    </c:otherwise>
						</c:choose>
		            </td>
		            <td>
						<strong>근무 지역 : </strong>${jobinfoData.loc01}
						<button class="map-button" onclick="fnMove(6)">지도보기</button></a>
		            </td>
		        </tr>
		    </table>
	    </table>
	</div>
	<%-- 
	<c:if test="${minileft != 'F' }">
		<div class="rightWing">
			<div class="SideBar">
				<div class="right_menu">
			      <h3>비슷한 제목의 채용 공고</h3>
			   	<ul>
				<c:forEach items="${similarTitles}" var="list" varStatus="loop">
					<c:if test="${loop.index < 5}">
						<li>
							<a href="/recruitshowform?writer=${list.cuserid}&csrno=${list.csrno}&jobno=${list.jobno}" class="listCell" target="_blank">
								${list.jobtitle}
		                    </a>
		                </li>
		            </c:if>
		        </c:forEach>
			    </ul>
			  	</div>
		  	</div>
	  	</div>
	</c:if>
	--%>
    <div class="container">
        <div class="tabs">
	        <a href="javascript:void(0);" onclick="fnMove(1)">기업 소개</a>
	        <a href="javascript:void(0);" onclick="fnMove(2)">담당업무 및<br>자격 요건</a>
	        <a href="javascript:void(0);" onclick="fnMove(3)">근무 조건</a>
	        <a href="javascript:void(0);" onclick="fnMove(4)">복지 및 혜택</a>
	        <a href="javascript:void(0);" onclick="fnMove(5)">접수기간 및 방법</a>
	    </div>
		
        <div id="section1">
            <div class="section-title">기업 소개</div>
            <div class="job-details">
            	<form method="get" id="infofrm">
            		<input type="hidden" name="csrno" value="${jobinfoData.csrno }">
            		<input type="hidden" name="jobno" value="${jobinfoData.jobno }">
            	</form>
                <p><strong>${jobinfoData.jobtitle}</strong></p>
                <p><strong>${jobinfoData.jobsubtitle}</strong></p><br><br>
				<p><strong>${jobinfoData.content}</strong></p>
			</div>
		</div>
		
		<div id="section2">
            <div class="section-title">담당업무 및 자격 요건</div>
            <div class="job-details">
                <p><strong>담당업무 : ${jobinfoData.position}</strong></p><br>
				<p><strong>스킬</strong></p>
					<ul>
						<li>${jobinfoData.skills}</li>
					</ul>
				<p><strong>자격 요건</strong></p>
				<ul>
					<li>경력 :
						<c:choose>
							<c:when test="${jobinfoData.prsup == '0'}">신입/무관</c:when>
							<c:when test="${jobinfoData.prsup == '1'}">1-3년</c:when>
							<c:when test="${jobinfoData.prsup == '2'}">3-5년</c:when>
							<c:when test="${jobinfoData.prsup == '3'}">5-10년</c:when>
							<c:when test="${jobinfoData.prsup == '4'}">10년 이상</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</li>
					<li>학력 :
						<c:choose>
							<c:when test="${jobinfoData.educa == '0'}">고졸</c:when>
							<c:when test="${jobinfoData.educa == '1'}">전문대졸</c:when>
							<c:when test="${jobinfoData.educa == '2'}">대졸</c:when>
							<c:when test="${jobinfoData.educa == '3'}">석사</c:when>
							<c:when test="${jobinfoData.educa == '4'}">박사</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</li>
					<li>${jobinfoData.duties}</li><br>
				</ul>
				<p><strong>우대 조건</strong></p>
				<ul>
					<li>${jobinfoData.pfntcd}</li>
				</ul>
            </div>
		</div>
		
		<div id="section3">
	        <div class="section-title">근무 조건</div>
	        <div class="job-details">
	            <ul>
	                <li>고용 형태 : 
						<c:choose>
							<c:when test="${jobinfoData.wrkty == '0'}">정규직</c:when>
	                        <c:when test="${jobinfoData.wrkty == '1'}">계약직</c:when>
	                        <c:when test="${jobinfoData.wrkty == '2'}">기간제</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</li>
					<li>급여 : 
	                    <c:choose>
	                        <c:when test="${jobinfoData.salary == null or jobinfoData.salary == ''}">회사 내규에 따름 - 면접 후 결정</c:when>
	                        <c:otherwise>${jobinfoData.salary}</c:otherwise>
	                    </c:choose>
					</li>
	                <li>근무 위치 : ${jobinfoData.loc01}</li>
	                <li>근무 요일/시간 : ${jobinfoData.wrktm}</li>
	            </ul>
	        </div>
	    </div>
		
		<div id="section4">
	        <div class="section-title">복지 및 혜택</div>
	        <div class="job-details">
	            <ul>
	                <li>${jobinfoData.benefits}</li>
	            </ul>
	        </div>
	    </div>
     	
		<div id="section5">
	        <div class="section-title">접수기간 및 방법</div>
	        <div class="job-details">
	            <ul>
	            	<fmt:formatDate value= "${jobinfoData.ddate}" pattern="yyyy-MM-dd" var="dateValue"/>
	                <li>접수기간 : ${dateValue}</li>
					<li>접수방법 :
						<c:choose>
						    <c:when test="${jobinfoData.pamoa eq '0'}">
						     	${jobinfoData.pamoa}
						    </c:when>
						    <c:otherwise>
						        온라인 이력서 제출
						    </c:otherwise>
						</c:choose>
					</li>
	                <li>채용 절차 : ${jobinfoData.rcmpcd}</li>
	            </ul>
	        </div>
	    </div>
		
		<br>
		<div class="apply-button">
			<c:set var="status" value="${status }"/>
			<c:set var="daycha" value="${jobinfoData.daycha }"/>
			<c:if test="${status == 'p'}">
				<c:if test="${daycha >= 0 }">
					<button type="button" onclick="openResumePopup('${jobinfoData.cuserid }','${jobinfoData.csrno }','${jobinfoData.jobno }');">지원 하기</button>
				</c:if>
				<c:if test="${daycha < 0 }">
					<button type="button">공고 마감</button>
				</c:if>
				<button type="button" onclick="move_mylist();">목록 보기</button>
			</c:if>
			<c:if test="${status == 'ps'}">
				<div class="tooltip">
					<a href="javascript:void(0)" class="buttonClose" onclick="closeTooltip(this)"></a>
					<p>이미 지원한 공고 입니다.</p>
				</div>
				<button type="button" onclick="move_mylist();">목록 보기</button>				
			</c:if>
			<c:if test="${status == 'cs'}">
					<button type="button" onclick="move_show();">수정 하기</button>
		            <button type="button" onclick="move_list();">공고 목록 보기</button> <%-- 기존 가는 화면 --%>
			</c:if>
			<c:if test="${status == 'c'}">
	            <button type="button" onclick="move_jobpost();">공고 목록 보기</button> <%-- jobpost 화면 이동 --%>
			</c:if>
		</div>
	</div>
	
	<div class="container" id="section6">
	    <div class="section-title">
			근무지 위치
		</div>
		    <ul>
		        <li id="location">${companyInfo.loc02}</li>
		    </ul>
				<div class="container2">
					<div id="map"></div>
				</div>	
	</div>
	
	<div class="container">
	    <div class="section-title">지원자 현황 통계</div>
	    <div class="statistics-container">
	        <div class="statistics-section">
	            <ul>
	                <li><strong><h3>지원자 수</h3></strong>
						 <h2>${jobinfoData.supno }명</h2></li>
	                <li><strong><h3>모집 인원</h3></strong>
						 <h2>${jobinfoData.recno }명</h2></li>
	            </ul>
	        </div>
			
	        <div class="statistics-section">
	            <h2>성별</h2>
	            <ul>
				    <!-- 남성과 여성 각각을 처리하기 위한 리스트 필터링 -->
				    <c:set var="maleCount" value="0"/>
				    <c:set var="femaleCount" value="0"/>

				    <c:forEach items="${genderStats}" var="gender">
				        <c:choose>
				            <c:when test="${gender.gender eq '1'}">
				                <c:set var="maleCount" value="${gender.gender_cnt}"/>
				            </c:when>
				            <c:when test="${gender.gender eq '2'}">
				                <c:set var="femaleCount" value="${gender.gender_cnt}"/>
				            </c:when>
				        </c:choose>
				    </c:forEach>

				    <!-- 남성 및 여성 출력 -->
				    <li><strong>남성 : </strong> ${maleCount} 명</li>
				    <li><strong>여성 : </strong> ${femaleCount} 명</li>
				</ul>
	        </div>

	        <div class="statistics-section">
	            <h2>연령</h2>
	            <ul>
				    <!-- 기본값 설정 -->
				    <c:set var="age20Count" value="0"/>
				    <c:set var="age30Count" value="0"/>
				    <c:set var="age40Count" value="0"/>
				    <c:set var="age50Count" value="0"/>

					<!-- 연령대별 통계 설정 -->
					<c:forEach items="${ageStats}" var="ageStat">
					    <c:choose>
					        <c:when test="${ageStat.age == '20'}">
					            <c:set var="age20Count" value="${ageStat.age_cnt}"/>
					        </c:when>
					        <c:when test="${ageStat.age == '30'}">
					            <c:set var="age30Count" value="${ageStat.age_cnt}"/>
					        </c:when>
					        <c:when test="${ageStat.age == '40'}">
					            <c:set var="age40Count" value="${ageStat.age_cnt}"/>
					        </c:when>
					        <c:when test="${ageStat.age == '50'}">
					            <c:set var="age50Count" value="${ageStat.age_cnt}"/>
					        </c:when>
					    </c:choose>
					</c:forEach>

				    <!-- 연령대별 출력 -->
				    <li><strong>20대 : </strong> ${age20Count} 명</li>
				    <li><strong>30대 : </strong> ${age30Count} 명</li>
				    <li><strong>40대 : </strong> ${age40Count} 명</li>
				    <li><strong>50대 이상 : </strong> ${age50Count} 명</li>
				</ul>
	        </div>

	        <div class="statistics-section">
	            <h2>학력</h2>
	            <ul>
				    <!-- 기본값 설정 -->
				    <c:set var="highSchoolCount" value="0"/>
				    <c:set var="associateDegreeCount" value="0"/>
				    <c:set var="bachelorDegreeCount" value="0"/>
				    <c:set var="masterDegreeCount" value="0"/>
				    <c:set var="doctorateCount" value="0"/>

				    <!-- 학력별 통계 설정 -->
					<c:forEach items="${educationStats}" var="eduStat">
					    <c:choose>
					        <c:when test="${eduStat.classgb == '1'}">
					            <c:set var="highSchoolCount" value="${eduStat.classgb_cnt}"/>
					        </c:when>
					        <c:when test="${eduStat.classgb == '2'}">
					            <c:set var="associateDegreeCount" value="${eduStat.classgb_cnt}"/>
					        </c:when>
					        <c:when test="${eduStat.classgb == '3'}">
					            <c:set var="bachelorDegreeCount" value="${eduStat.classgb_cnt}"/>
					        </c:when>
					        <c:when test="${eduStat.classgb == '4'}">
					            <c:set var="masterDegreeCount" value="${eduStat.classgb_cnt}"/>
					        </c:when>
					        <c:when test="${eduStat.classgb == '5'}">
					            <c:set var="doctorateCount" value="${eduStat.classgb_cnt}"/>
					        </c:when>
					    </c:choose>
					</c:forEach>

				    <!-- 학력별 출력 -->
				    <li><strong>고졸 : </strong> ${highSchoolCount} 명</li>
				    <li><strong>전문대졸 : </strong> ${associateDegreeCount} 명</li>
				    <li><strong>대졸 : </strong> ${bachelorDegreeCount} 명</li>
				    <li><strong>석사 : </strong> ${masterDegreeCount} 명</li>
				    <li><strong>박사 : </strong> ${doctorateCount} 명</li>
				</ul>
	        </div>
	    </div>
	</div>
	
	<div class="container">
		<div class="company-info">
			<table class="company-info-left">
				<tr>
					<td>
						<c:if test="${companyInfo.imgno == 0}">
                          		<img src="../../../resources/img/company.jpg" style="width: 250px; height: 165px;">
                          	</c:if>
                          	<c:if test="${companyInfo.imgno != 0}">
							<img src="show_coinfo_img?writer=${companyInfo.cuserid}&imgno=t&imggubun=b" style="width: 250px; height: 165px;">
						</c:if>
					</td>
					<td>
						<table class="company-info-right">
							<tr>
								<td>
									<h2><strong>${companyInfo.cusnm}</strong></h2>
								</td>
							</tr>
							<tr>
								<td>
									<strong>대표자 : </strong> ${companyInfo.bossnm}
								</td>
							</tr>
							<tr>
								<td>
									<strong>업종 : </strong> ${companyInfo.deptnm}
								</td>
							</tr>
							<tr>
								<td>
									<strong>사원수 : </strong> ${companyInfo.emnum} 명
								</td>
							</tr>
							<tr>
								<td>
									<strong>설립연도 : </strong> ${companyInfo.indue}
								</td>
							</tr>
							<tr>
								<td>
									<strong>사업내용 : </strong> ${companyInfo.binfo}
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>

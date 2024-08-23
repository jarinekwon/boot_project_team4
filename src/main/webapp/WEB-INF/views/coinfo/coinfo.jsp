<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coinfo/coinfo.js"></script>
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/coinfo/coinfo.css">

<script>
	$(document).ready(function() {
	     var logoUpload = document.getElementById('logoUpload');
	     var logoUploadLink = document.getElementById('logoUploadLink');
	     var logoPreview = document.getElementById('logoPreview');
	     var logoRemoveLink = document.getElementById('logoRemoveLink');

	     // 페이지가 로드될 때 이미지가 이미 존재하면
	     // 업로드 링크를 숨기고 삭제 링크를 보이게 함
	     if (logoPreview && logoPreview.src) {
	         logoUploadLink.style.display = 'none'; // 업로드 링크 숨김
	         logoRemoveLink.style.display = 'inline'; // 삭제 링크 보임
	     }

	     // 업로드 링크 클릭 시 파일 입력 요소 클릭
	     logoUploadLink.addEventListener('click', function() {
	         logoUpload.click(); // 파일 입력 클릭
	     });

	     // 파일이 선택되면 이미지 미리보기와 삭제 링크 표시
	     logoUpload.addEventListener('change', function(e) {
	         var reader = new FileReader();
	         reader.onload = function(e) {
	             logoPreview.src = e.target.result;
	             logoPreview.style.display = 'block'; // 이미지 미리보기 표시
	             logoUploadLink.style.display = 'none'; // 업로드 링크 숨김
	             logoRemoveLink.style.display = 'inline'; // 삭제 링크 보임
	         };
	         reader.readAsDataURL(this.files[0]);
	     });

	     // 삭제 링크 클릭 시 이미지와 업로드 링크 복원
	     logoRemoveLink.addEventListener('click', function() {
	         logoPreview.src = ''; // 이미지 미리보기 제거
	         logoPreview.style.display = 'none'; // 이미지 미리보기 숨김
	         logoUploadLink.style.display = 'inline'; // 업로드 링크 보임
	         logoRemoveLink.style.display = 'none'; // 삭제 링크 숨김
	         logoUpload.value = ''; // 파일 입력 값 초기화
	     });
	 });
	 
</script>

</head>
<jsp:include page="../../header.jsp"/>
<body>
    <c:choose>
		<c:when test="${not empty coinfotb.cuserid}">
		    <!-- 로그인한 경우 -->
			<form method="post" action="coinmodify" enctype="multipart/form-data">	
				<div class="container">
					<div class="form-group-combined">
					    <h3><div class="info">기업명/로고</div></h3>
					    <div class="infoTbBx">
					        <div class="tbRow">
					            <div class="tbCell">
					                <div class="elWrap">
					                    <div class="schInpType">
					                        <input type="text" id="cusnm" class="inpDefault" name="cusnm" value="${coinfotb.cusnm}" maxlength="25" placeholder=" ">
					                        <label class="ph" for="cusnm">기업명 <span class="point">*<span class="skip">필수</span></span></label>
					                    </div>
					                </div>
					            </div>
					            <div class="tbCell image-upload-container">
					            	<div class="thumbnailEx">
									<!-- <img src="/resumelist.png">-->
										이미지 권장 크기 :<br> 300px x 300px<br> (최소 300px 이상) / 10MB 이하의<br>JPG, PNG 파일
									</div>
				                    <c:if test="${not empty imgtb}">
				                        <img src="show_coinfo_img?writer=${coinfotb.cuserid}&imgno=t&imggubun=s" alt="썸네일 이미지" class="thumbnail" id="logoPreview">
				                    </c:if>
									<div class="image-upload">
										<a href="javascript:void(0);" id="logoUploadLink" class="file-upload-link">로고 업로드</a>
	                                    <a href="javascript:void(0);" id="logoRemoveLink" class="file-remove-link hidden">X</a>
	                                    <input type="file" name="file" id="logoUpload" accept="image/*" class="file-upload-input">
									</div>
					            </div>
					        </div>
					    </div>
					</div>
				</div>
				
					<div class="container">
					    <h3 class="info">기본정보</h3>
					    <div class="infoTbBx">
					        <div class="tbRow">
					            <div class="tbCell">
					                <div class="elWrap">
					                    <div class="schInpType">
					                        <input type="text" id="bossnm" class="inpDefault" name="bossnm" value="${coinfotb.bossnm}" maxlength="25" placeholder=" ">
					                        <label class="ph">대표자명 <span class="point">*<span class="skip">필수</span></span></label>
					                    </div>
					                </div>
					            </div>
					            <div class="tbCell">
					                <div class="elWrap">
					                    <div class="schInpType">
					                        <input type="text" id="indue" class="inpDefault" name="indue" value="${coinfotb.indue}" maxlength="10" placeholder=" ">
					                        <label class="ph">설립일 <span class="point">*<span class="skip">필수</span></span></label>
					                    </div>
					                </div>
					            </div>
					            <div class="tbCell">
					                <div class="elWrap">
					                    <div class="schInpType">
					                        <select id="devCompanyType" name="deptno" class="inpDefault">
					                            <option value="" disabled selected>업종을 선택하세요</option>
					                            <c:forEach items="${sector}" var="sector">
					                                <option value="${sector.deptno}" ${sector.deptno == coinfotb.deptno ? 'selected' : ''}>${sector.deptnm}</option>
					                            </c:forEach>
					                        </select>
					                        <label id="devCompanyLabel" class="ph" for="devCompanyType">
					                            업종명 <span class="point">*<span class="skip">필수</span></span>
					                        </label>
					                    </div>
					                </div>
					            </div>
								<div class="tbCell">
								    <div class="elWrap">
								        <div class="schInpType">
								            <input type="text" id="emnum" class="inpDefault" name="emnum" value="${coinfotb.emnum}" placeholder=" ">
								            <label class="ph">사원수 <span class="point">*<span class="skip">필수</span></span></label>
								        </div>
								    </div>
								</div>
								<div class="tbCell">
								    <div class="elWrap">
								        <div class="schInpType">
								            <input type="text" id="loc01" class="inpDefault" name="loc01" value="${coinfotb.loc01}" placeholder=" ">
								            <label class="ph">지역 <span class="point">*<span class="skip">필수</span></span></label>
								        </div>
								    </div>
								</div>
								<div class="tbCell">
								    <div class="elWrap">
								        <div class="schInpType">
								            <input type="text" id="binfo" class="inpDefault" name="binfo" value="${coinfotb.binfo}" placeholder=" ">
								            <label class="ph">사업내용 <span class="point">*<span class="skip">필수</span></span></label>
								        </div>
								    </div>
								</div>
								<div class="tbCell">
								    <div class="elWrap ">
								        <div class="schInpType">
								            <input type="text" id="loc02" class="inpDefault" name="loc02" value="${coinfotb.loc02}" placeholder=" ">
								            <label class="ph" >상세주소 <span class="point">*<span class="skip">필수</span></span></label>
											<div class="postal-link-container">
												<a href="javascript:void(0);" onclick="open_kakao_api()">주소 검색</a>
											</div>
								        </div>
									</div>
					        	</div>
					    	</div>
						</div>
						<div class="button-container">
							<button type="submit" class="btn btn-primary">수정완료</button>
						</div>
					</div>
				</form>
			</c:when>
            <c:otherwise>
				
	                <!-- 비로그인인 경우 -->
				<form method="post" action="insert" enctype="multipart/form-data">
					<div class="container">
		                <div class="form-group-combined">
		                    <h3><div class="info">기업명/로고</div></h3>
		                    <div class="infoTbBx">
		                        <div class="tbRow">
		                            <div class="tbCell2">
		                                <div class="elWrap ">
		                                    <div class="schInpType">
		                                        <input type="text" id="cusnm" class="inpDefault 2" name="cusnm" maxlength="25" placeholder=" ">
		                                        <label class="ph " for="cusnm">기업명 <span class="point">*<span class="skip">필수</span></span></label>
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="tbCell image-upload-container">
										<div class="image-upload">
										    <c:if test="${not empty imgtb}">
										        <img src="show_coinfo_img?writer=${coinfotb.cuserid}&imgno=t&imggubun=s" alt="썸네일 이미지" class="thumbnail" id="logoPreview">
										    </c:if>
											<a href="javascript:void(0);" id="logoUploadLink" class="file-upload-link">로고 업로드</a>
						                    <input type="file" name="file" id="logoUpload" accept="image/*" class="file-upload-input">
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
					</div>
		
					<div class="container">
					    <h3 class="info">기본정보</h3>
					    <div class="infoTbBx">
					        <div class="tbRow">
					            <div class="tbCell">
					                <div class="elWrap ">
					                    <div class="schInpType">
					                        <input type="text" id="bossnm" class="inpDefault " name="bossnm" value="${coinfotb.bossnm}" maxlength="25" placeholder=" ">
					                        <label class="ph " for="txtBossName">대표자명 <span class="point">*<span class="skip">필수</span></span></label>
					                    </div>
					                </div>
					            </div>
					            <div class="tbCell">
					                <div class="elWrap ">
					                    <div class="schInpType">
					                        <input type="text" id="indue" class="inpDefault " name="indue" value="${coinfotb.indue}" maxlength="10" placeholder=" ">
					                        <label class="ph " >설립일 <span class="point">*<span class="skip">필수</span></span></label>
					                    </div>
					                </div>
					            </div>
					            <div class="tbCell">
					                <div class="elWrap ">
					                    <div class="schInpType">
					                        <select id="devCompanyType" name="deptno" class="inpDefault">
					                            <option value="" disabled selected>업종을 선택하세요</option>
					                            <c:forEach items="${sector}" var="sector">
					                                <option value="${sector.deptno}" ${sector.deptno == coinfotb.deptno ? 'selected' : ''}>${sector.deptnm}</option>
					                            </c:forEach>
					                        </select>
					                        <label id="devCompanyLabel" class="ph" for="devCompanyType">
					                            업종명 <span class="point">*<span class="skip">필수</span></span>
					                        </label>
					                    </div>
					                </div>
					            </div>
								<div class="tbCell">
								    <div class="elWrap ">
								        <div class="schInpType">
								            <input type="text" id="emnum" class="inpDefault " name="emnum" value="${coinfotb.emnum}" placeholder=" ">
								            <label class="ph " >사원수 <span class="point">*<span class="skip">필수</span></span></label>
								        </div>
								    </div>
								</div>
								<div class="tbCell">
								    <div class="elWrap ">
								        <div class="schInpType">
								            <input type="text" id="loc01" class="inpDefault " name="loc01" value="${coinfotb.loc01}" placeholder=" ">
								            <label class="ph " >지역 <span class="point">*<span class="skip">필수</span></span></label>
								        </div>
								    </div>
								</div>
								<div class="tbCell">
								    <div class="elWrap ">
								        <div class="schInpType">
								            <input type="text" id="binfo" class="inpDefault " name="binfo" value="${coinfotb.binfo}" placeholder=" ">
								            <label class="ph " >사업내용 <span class="point">*<span class="skip">필수</span></span></label>
								        </div>
								    </div>
								</div>
								<div class="tbCell">
								    <div class="elWrap ">
								        <div class="schInpType">
								            <input type="text" id="loc02" class="inpDefault " name="loc02" value="${coinfotb.loc02}" placeholder=" ">
								            <label class="ph " >상세주소 <span class="point">*<span class="skip">필수</span></span></label>
											<div class="postal-link-container">
												<a href="javascript:void(0);" onclick="open_kakao_api()">주소 검색</a>
											</div>
								        </div>
									</div>
					        	</div>
					    	</div>
						</div>
						<div class="button-container">
							<button type="submit" class="btn btn-primary">등록하기</button>
						</div>
					</div>
				</form>
		    </c:otherwise>
		</c:choose>
	</div>
</body>
</html>

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
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/coinfo/coinfoshow.css">
</head>
<jsp:include page="../../header.jsp"/>
<body>
	<form method="post" action="/coinfo" enctype="multipart/form-data">	
		<div class="container">
			<div class="form-group-combined">
			    <h3><div class="info">기업명/로고</div></h3>
			    <div class="infoTbBx">
			        <div class="tbRow">
			            <div class="tbCell">
			                <div class="elWrap ">
			                    <div class="schInpType">
			                        <div class="inpDefault">${coinfotb.cusnm}</div>
			                        <label class="ph">기업명</label>
			                    </div>
			                </div>
			            </div>
			            <div class="tbCell image-upload-container">
			            	<div class="thumbnailEx">
							</div>
		                    <c:if test="${not empty imgtb}">
		                        <img src="show_coinfo_img?writer=${coinfotb.cuserid}&imgno=t&imggubun=s" alt="썸네일 이미지" class="thumbnail" id="logoPreview">
		                    </c:if>
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
		                        <div class="inpDefault">${coinfotb.bossnm}</div>
		                        <label class="ph">대표자명</label>
		                    </div>
		                </div>
		            </div>
		            <div class="tbCell">
		                <div class="elWrap">
		                    <div class="schInpType">
		                        <div class="inpDefault">${coinfotb.indue}</div>
		                        <label class="ph">설립일</label>
		                    </div>
		                </div>
		            </div>
					<div class="tbCell">
					    <div class="elWrap">
					        <div class="schInpType">
					            <label class="ph ">업종명</label>
					            <div class="inpDefault">
					                <c:forEach items="${sector}" var="sector">
					                    <c:if test="${sector.deptno == coinfotb.deptno}">
					                        ${sector.deptnm}
					                    </c:if>
					                </c:forEach>
					            </div>
					        </div>
					    </div>
					</div>
					<div class="tbCell">
					    <div class="elWrap">
					        <div class="schInpType">
					            <div class="inpDefault">${coinfotb.emnum}</div>
					            <label class="ph">사원수</label>
					        </div>
					    </div>
					</div>
					<div class="tbCell">
					    <div class="elWrap">
					        <div class="schInpType">
					            <div class="inpDefault">${coinfotb.loc01}</div>
					            <label class="ph">지역 </label>
					        </div>
					    </div>
					</div>
					<div class="tbCell">
					    <div class="elWrap">
					        <div class="schInpType">
					            <div class="inpDefault">${coinfotb.binfo} </div>
					            <label class="ph">사업내용</label>
					        </div>
					    </div>
					</div>
					<div class="tbCell">
					    <div class="elWrap">
					        <div class="schInpType">
					            <div class="inpDefault">${coinfotb.loc02}</div>
					            <label class="ph">상세주소</label>
					        </div>
						</div>
		        	</div>
		    	</div>
			</div>
			<c:set var="isJobPoster" value="${writer == coinfotb.cuserid}" />
	        <div class="button-container">
				<c:if test="${isJobPoster}">
					<button type="submit" class="btn btn-primary">수정하기</button>
				</c:if>
			</div>
		</div>
	</form>
</body>
</html>

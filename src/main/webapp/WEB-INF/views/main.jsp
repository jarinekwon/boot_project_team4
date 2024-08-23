<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/main/main.css">
<html>
<head>
<meta charset="UTF-8">
<title>Job Postings</title>
</head>
<jsp:include page="../header.jsp"/>
<body>
    <div id="recruitWrap" class="recruitWrap">
    	<div id="monthRecommend">
	        <h1>이 달의 핫한 공고</h1>
	    </div>
        <div id="loading dev-wrap-loading"></div>
        <div class="secContents">
            <div id="searchLog"></div>
            <div id="searchList" class="recruitList">
                <c:forEach items="${main}" var="list" varStatus="loop">     
                    <li style="list-style-type: none">
                        <a href="/recruitshowform?writer=${list.cuserid}&csrno=${list.csrno}&jobno=${list.jobno}" class="listCell" target="_blank">
                            <div class="listLogo">
                            	<c:if test="${list.imgno == 0 }">
                            		<img src="../../../resources/img/company.jpg" style="width: 200px; height: 120px;">
                            	</c:if>
                            	<c:if test="${list.imgno != 0 }">
                                	<img src="show_coinfo_img?writer=${list.cuserid}&imgno=t&imggubun=b" style="width: 200px; height: 120px;">
                                </c:if>
                            </div>
                            <div class="listContent">
                                <div class="Content">
                                    <span class="cName">${list.cusnm}</span>
                                </div>
                            </div>
                            <c:choose>
							    <c:when test="${fn:length(list.jobtitle) > 12}">
							        <div class="listTitle"> ${fn:substring(list.jobtitle, 0, 12)}...</div>
							    </c:when>
							    <c:otherwise>
							        <div class="listTitle">${list.jobtitle}</div>
							    </c:otherwise>
							</c:choose>
                            <div class="listInfo">
                            	<c:choose>
	                            	<c:when test="${fn:length(list.position) > 10}">
	                                	<span class="Position">${fn:substring(list.position, 0, 10)}...</span>
	                                </c:when>
	                                <c:otherwise>
							        	<span class="Position">${list.position}</span>
							    	</c:otherwise>
                                </c:choose>
                                <span class="partition">|</span>
                                <span class="Addr">${list.loc01}</span>
                            </div>
                            <div class="listSkill">
                                <%-- <span class="Skill"> --%>
                                    <c:forEach items="${list.skills.split(',')}" var="skills">
                                        <span class="tag tag-skill">${skills}</span>
                                    </c:forEach>
                                <%-- </span> --%>
                            </div>
                            <div class="ddate">
                               ~ ${fn:substring(list.ddate, 0, 10)} 마감
                            </div>
                        </a>
                    </li>                 
                </c:forEach>
            </div>
        </div>
    </div>
    <%-- <hr> --%>
    <div id="recruitWrap" class="recruitWrap">
    	<div id="monthRecommend">
	        <h1>전체 공고</h1>
	    </div>
        <div id="loading dev-wrap-loading"></div>
        <div class="secContents">
            <div id="searchLog"></div>
            <div id="searchList" class="recruitList">
               <c:forEach items="${mainAllList}" var="list" varStatus="loop">    
                    <li style="list-style-type: none">
                        <a href="/recruitshowform?writer=${list.cuserid}&csrno=${list.csrno}&jobno=${list.jobno}" class="listCell" target="_blank">
                            <div class="listLogo">
                               <c:if test="${list.imgno == 0 }">
                                  <img src="../../../resources/img/company.jpg" style="width: 200px; height: 120px;">
                               </c:if>
                               <c:if test="${list.imgno != 0 }">
                                   <img src="show_coinfo_img?writer=${list.cuserid}&imgno=t&imggubun=b" style="width: 200px; height: 120px;">
                                </c:if>
                            </div>
                            <div class="listContent">
                                <div class="Content">
                                    <span class="cName">${list.cusnm}</span>
                                </div>
                            </div>
                            <c:choose>
                         		<c:when test="${fn:length(list.jobtitle) > 12}">
                             		<div class="listTitle"> ${fn:substring(list.jobtitle, 0, 12)}...</div>
                         		</c:when>
                         		<c:otherwise>
                             		<div class="listTitle">${list.jobtitle}</div>
                         		</c:otherwise>
                     		</c:choose>
                            <div class="listInfo">
                               <c:choose>
                                  <c:when test="${fn:length(list.position) > 10}">
                                      <span class="Position">${fn:substring(list.position, 0, 10)}...</span>
                                   </c:when>
									<c:otherwise>
                             			<span class="Position">${list.position}</span>
                         			</c:otherwise>
                                </c:choose>
                                <span class="partition">|</span>
                                <span class="Addr">${list.loc01}</span>
                            </div>
                            <div class="listSkill">
                                <%-- <span class="Skill"> --%>
                                    <c:forEach items="${list.skills.split(',')}" var="skills">
                                        <span class="tag tag-skill">${skills}</span>
                                    </c:forEach>
                                <%-- </span> --%>
                            </div>
                            <div class="ddate">
                               ~ ${fn:substring(list.ddate, 0, 10)} 마감
                            </div>
                        </a>
                    </li>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
<jsp:include page="../footer.jsp"/>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/register.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/register.js"></script>
<script>

</script>

<body>
	<jsp:include page="../../header.jsp" />
	<div class="container">
		<div class="header-container">
			<h2>회원가입</h2>
			<div class="btn-group">
				<button type="button" id="generalBtn" class="active" onclick="geneform()">일반회원</button>
				<button type="button" id="businessBtn" onclick="busiform()">기업회원</button>
			</div>
		</div>
		<form method="post" id="generalForm" action="RestUserInsert_p"><%-- action="pRegisterInsert"> --%>
			<input type="hidden" name="gubun" value="p">
			<input type="text" name="puserid" id="puserid" class="input_id" placeholder="아이디를 입력해주세요(알파벳,숫자만 가능)" required oninput="validateInput(event)">
			<button type="button" class="onclick_1" onclick="id_chk('puserid','p');">중복 확인</button><br>
			<input type="password" name="ppass" id="ppass" class="input_pw" placeholder="비밀번호를 입력해주세요" required><br>
			<input type="text" name="pname" id="pname" class="input_text" placeholder="이름(실명)을 입력해주세요" required><br>
			<input type="tel" name="phone" id="phone" class="input_tel" placeholder="휴대폰 번호를 입력해주세요(-제외)" required  maxlength="20" oninput="onlyNumbers(event)"><br>
			<input type="email" id="puser_email" name="email" class="input_email" placeholder="이메일을 입력해주세요" required>
			<button type="button" id="psendVerificationButton" class="onclick_2" onclick="psendVerificationCode()">인증번호
				전송</button><br>
			<input type="text" id="puser_checkNo" name="puser_checkNo" class="input_ck" placeholder="인증번호" required>
			<button type="button" class="onclick_2" onclick="pverifyCode()">인증번호 확인</button><br>
            <div class="terms-container">
				<div class="terms-header">
					필수동의 항목 모두 동의합니다.
					<input type="checkbox" id="pagree-all" class="agree_all" onclick="AllCheck(this)">
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<strong style="color: blue">[필수]</strong> 만 15세 이상입니다
					<input type="checkbox" name="agreement" class="checkbox" value="age">
					</label>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<input type="checkbox" name="agreement" class="checkbox" value="terms">
						<strong style="color: blue">[필수]</strong> 이용약관 동의
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						<jsp:include page="../../terms.jsp" />
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<strong style="color: blue">[필수]</strong> 개인정보 수집 및 이용 동의
						<input type="checkbox" name="agreement" class="checkbox" value="privacy">
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						<p>
						    codnect 서비스 이용을 위해 아래와 같이 개인정보를 수집 및 이용합니다. <br>
						    동의를 거부할 권리가 있으며, 동의 거부 시 codnect 회원서비스 이용이 불가합니다.
						</p>
						
						<table class="policy-table">
						    <colgroup>
						        <col width="33%">
						        <col width="33%">
						        <col width="*">
						    </colgroup>
						    <thead>
						        <tr>
						            <th>목적</th>
						            <th>항목</th>
						            <th>보유 및 이용기간</th>
						        </tr>
						    </thead>
						    <tbody>
						        <tr>
						            <td>
						                본인여부 확인, 각종 맞춤형 서비스 제공, 서비스 개선 및 신규 서비스 개발을 위한 통계 활용, 계약이행 및 약관변경 등의 고지를 위한 연락, 본인의사확인 및 민원 등의 고객불만처리
						            </td>
						            <td>
						                이름, 아이디, 비밀번호, 생년월일, 성별, 휴대폰번호, 이메일
						            </td>
						            <td rowspan="2" style="text-decoration: underline;">
						                회원 탈퇴 시 즉시 파기
						            </td>
						        </tr>
						    </tbody>
						</table>
					</div>
				</div>
			</div>
			<button type="button" id="pbutton" class="submit-btn disabled" onclick="register_p_ok()">가입하기</button>
		</form>
		<form method="post" id="businessForm" class="hidden" action="RestUserInsert_c"> <%-- action="register_c_ok"> --%>
			<input type="hidden" name="gubun" value="c">
			<input type="text" name="cuserid" id="cuserid" class="input_id" placeholder="아이디를 입력해주세요(알파벳,숫자만 가능)" required oninput="validateInput(event)">
			<button type="button" class="onclick_1" onclick="id_chk('cuserid', 'c');">중복 확인</button><br>
			<input type="password" name="cpass" id="cpass" class="input_pw" placeholder="비밀번호를 입력해주세요" required><br>
			<input type="text" name="bsnum" id="bsnum" class="input_text" placeholder="사업자등록번호를 입력해주세요" required maxlength="10" onkeyup="formatDate(event)"><br>
			<input type="text" name="cleader" id="cleader" class="input_text" placeholder="대표자명을 입력해주세요" required><br>
			<input type="text" name="cusnm" id="cusnm" class="input_text" placeholder="회사명을 입력해주세요" required><br>
			<input type="text" name="caddr" id="caddr" class="input_text" placeholder="회사주소를 입력해주세요" required><br>
			<input type="tel" name="phone" id="cphone"  class="input_tel" placeholder="전화번호를 입력해주세요" required maxlength="20" oninput="onlyNumbers(event)"><br>
			<input type="email" id="cuser_email" name="cmail" class="input_email" placeholder="이메일을 입력해주세요"
				required>
			<button type="button" id="csendVerificationButton" class="onclick_2" onclick="csendVerificationCode()">인증번호
				전송</button>
			<input type="text" id="cuser_checkNo" name="cuser_checkNo" class="input_ck" placeholder="인증번호" required>
			<button type="button" class="onclick_2" onclick="cverifyCode()">인증번호 확인</button><br>
			<div class="terms-container">
				<div class="terms-header">
					필수동의 항목 모두 동의합니다.
					<input type="checkbox" id="cagree-all" class="agree_all" onclick="AllCheck(this)">
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<strong style="color: blue">[필수]</strong> 만 15세 이상입니다
					<input type="checkbox" name="agreement" class="checkbox" value="age">
					</label>
					<div class="details">
						여기에 만 15세 이상에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<input type="checkbox" name="agreement" class="checkbox" value="terms">
						<strong style="color: blue">[필수]</strong> 이용약관 동의
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						<jsp:include page="../../terms.jsp" />
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<input type="checkbox" name="agreement" class="checkbox" value="privacy">
						<strong style="color: blue">[필수]</strong> 개인정보 수집 및 이용 동의
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						<p>
						    codnect 서비스 이용을 위해 아래와 같이 개인정보를 수집 및 이용합니다. <br>
						    동의를 거부할 권리가 있으며, 동의 거부 시 codnect 회원서비스 이용이 불가합니다.
						</p>
						
						<table class="policy-table">
						    <colgroup>
						        <col width="33%">
						        <col width="33%">
						        <col width="*">
						    </colgroup>
						    <thead style="background-color: darkgray">
						        <tr>
						            <th>목적</th>
						            <th>항목</th>
						            <th>보유 및 이용기간</th>
						        </tr>
						    </thead>
						    <tbody>
						    </tbody>
						    <tbody>
						        <tr>
						            <td>
						                본인여부 확인, 각종 맞춤형 서비스 제공, 서비스 개선 및 신규 서비스 개발을 위한 통계 활용, 계약이행 및 약관변경 등의 고지를 위한 연락, 본인의사확인 및 민원 등의 고객불만처리
						            </td>
						            <td>
						                이름, 아이디, 비밀번호, 생년월일, 성별, 휴대폰번호, 이메일
						            </td>
						            <td rowspan="2" style="text-decoration: underline;">
						                회원 탈퇴 시 즉시 파기
						            </td>
						        </tr>
						    </tbody>
						</table>
					</div>
				</div>
			</div>
			<button type="button" id="cbutton" class="submit-btn disabled" onclick="register_c_ok()">가입하기</button>
		</form>
	</div>
</body>
<jsp:include page="../../footer.jsp" />

</html>
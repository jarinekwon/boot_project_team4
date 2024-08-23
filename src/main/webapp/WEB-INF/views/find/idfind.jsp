<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello World</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/idfind.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login_fn.js"></script>
<%--     <script type="text/javascript" src="${pageContext.request.contextPath}/js/find/idfind.js"></script> --%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- 이메일 인증 -->
    <script>
	    $(document).ready(function () {
	    	var message = "${message}";
	    	if (message) {
	    	    alert(message);
	    	}
	    });
	    
	  //일반 이메일 인증
	    function pverifyAndSubmit(event) {
	        event.preventDefault();  // 폼의 기본 제출 동작을 막음

	        var name = document.querySelector('input[name="pname"]').value;
	        var email = document.getElementById("email").value;

	        if (!name || !email) {
	            alert("이름과 이메일을 입력하세요.");
	            return;
	        }
	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/pidfind_yn",
	            data: { name: name, email: email },
	            success: function (response) {
	                // 성공하면 다음 화면으로 이동
	                // window.location.href = "nextPage"; // 여기를 원하는 URL로 변경
	                window.location.href = "${pageContext.request.contextPath}/pidview"; // 리다이렉트 URL
	            },
	            error: function () {
	                alert("이름과 이메일이 일치하지 않습니다.");
	            }
	        });
	    }

	    function psendVerificationCode() {
	        var email = document.getElementById("email").value;
	        var sendButton = document.getElementById("psendVerificationButton");

	        if (!email) {
	            alert("이메일을 입력하세요.");
	            return;
	        }

	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/email/idfind",
	            data: { email: email },
	            success: function (response) {
	                alert(response);
	                // 버튼 텍스트를 '재전송'으로 변경
	                sendButton.innerText = "재전송";
	                // 버튼을 비활성화 후 60초 후에 활성화
	                sendButton.disabled = true;
	                setTimeout(function () {
	                    sendButton.disabled = false;
	                }, 60000); // 60초
	            },
	            error: function () { // 에러발생시 
	                alert("인증번호 전송에 실패했습니다.");
	            }
	        });
	    }


	    let pisVerified = false;
	    // 이 변수가 코드에서 가지는 역할은 이메일 인증 상태를 추적하는 것입니다. 
	    // 초기 값이 false인 것은 사용자가 아직 이메일 인증을 완료하지 않았음을 나타냅니다.

	    function pverifyCode() {
	        var email = document.getElementById("email").value;
	        var code = document.getElementById("user_checkNo").value;
	        const pverifyCodeButton = document.querySelector('button[onclick="pverifyCode()"]');

	        if (!email || !code) {
	            alert("이메일과 인증번호를 입력하세요.");
	            return;
	        }


	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/email/verify",
	            data: { email: email, code: code },
	            success: function (response) {
	                alert(response);
	                pverifyCodeButton.disabled = true;  // 중복 확인 후 버튼 비활성화
	                pisVerified = true;  // 인증된 상태로 변경
	            },
	            error: function () {
	                alert("인증번호 확인에 실패했습니다.");
	            }
	        });
	    }

	    $(document).ready(function () {
	        const psubmitButton = document.querySelector('button[type="submit"]');// 완료버튼 제어하기위한 변수
	        psubmitButton.addEventListener('click', function (event) {
	            if (!pisVerified) {
	                event.preventDefault();
	                alert("인증을 완료해 주세요.");
	            }
	        });
	    });

	    // 기업 이메일 인증 
	    function cverifyAndSubmit(event) {
	        event.preventDefault(); // 폼의 기본 제출 동작을 막음

	        var name = document.querySelector('input[name="cusnm"]').value;
	        var BusinessNum = document.querySelector('input[name="bsnum"]').value;
	        var email = document.getElementById("cmail").value;

	        if (!name || !email || !BusinessNum) {
	            alert("입력정보를 확인해주세요.");
	            return;
	        }

	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/cidfind_yn",
	            data: { name: name, email: email, BusinessNum: BusinessNum },
	            success: function (response) {
	                window.location.href = "${pageContext.request.contextPath}/cidview"; // 리다이렉트 URL
	            },
	            error: function () {
	                alert("이름과 이메일과 사업자번호가 일치하지 않습니다.");
	            }
	        });
	    }

	    function csendVerificationCode() {
	        var email = document.getElementById("cmail").value;
	        var sendButton = document.getElementById("csendVerificationButton");

	        if (!email) {
	            alert("이메일을 입력하세요.");
	            return;
	        }

	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/email/idfind",
	            data: { email: email },
	            success: function (response) {
	                alert(response);
	                sendButton.innerText = "재전송";
	                sendButton.disabled = true;
	                setTimeout(function () {
	                    sendButton.disabled = false;
	                }, 60000); // 60초
	            },
	            error: function () {
	                alert("인증번호 전송에 실패했습니다.");
	            }
	        });
	    }

	    let cisVerified = false;

	    function cverifyCode() {
	        var email = document.getElementById("cmail").value;
	        var code = document.getElementById("corp_checkNo").value;
	        const cverifyCodeButton = document.querySelector('button[onclick="cverifyCode()"]');

	        if (!email || !code) {
	            alert("이메일과 인증번호를 입력하세요.");
	            return;
	        }

	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/email/verify",
	            data: { email: email, code: code },
	            success: function (response) {
	                alert(response);
	                cverifyCodeButton.disabled = true; // 중복 확인 후 버튼 비활성화
	                cisVerified = true; // 인증된 상태로 변경
	            },
	            error: function () {
	                alert("인증번호 확인에 실패했습니다.");
	            }
	        });
	    }

	    $(document).ready(function () {
	        const csubmitButton = document.querySelector('#businessForm button[type="submit"]'); // 기업 회원 완료 버튼 제어
	        csubmitButton.addEventListener('click', function (event) {
	            if (!cisVerified) {
	                event.preventDefault();
	                alert("인증을 완료해 주세요.");
	            }
	        });
	    });


    </script>
</head>
<jsp:include page="../../header.jsp" />

<body>
    <div class="container">
        <div class="header-container">
            <div class="btn-group">
                <button type="button" id="generalBtn" class="form-button active" data-form="generalForm"
                    onclick="geloform()">일반회원 아이디 찾기</button>
                <button type="button" id="businessBtn" class="form-button" data-form="businessForm"
                    onclick="buloform()">기업회원 아이디 찾기 </button>
            </div>
        </div>
        <!--			일반 회원-->
        <form method="post" id="generalForm" class="visible" action="${pageContext.request.contextPath}/pidfind_yn">
            이름<input type="text" name="pname" placeholder="이름을 입력해주세요" required><br>
            <!-- 이메일 인증 -->
            이메일<input type="text" id="email" name="email" placeholder="Email을 입력해주세요" >
            <button type="button" id="psendVerificationButton" onclick="psendVerificationCode()" class="mailsend">인증번호 전송</button>
            <input type="text" id="user_checkNo" name="user_checkNo" class="corp_check" placeholder="인증번호를 입력하세요" >
            <button type="button"  onclick="pverifyCode()" class="mail_ck">인증확인</button>
            <button type="submit" class="submit-btn">완료</button>
        </form>

        <!--기업 회원-->
        <form method="post" id="businessForm" action="${pageContext.request.contextPath}/cidfind_yn">
			대표자 명<input type="text" name="cusnm" placeholder="대표자명을 입력해주세요" required><br>
			사업자 번호<input type="text" name="bsnum" placeholder="사업자번호를 입력해주세요" maxlength="10" onkeyup="formatBsnum(event)" required="required"><br>
			<!-- 이메일 인증 -->
			이메일<input type="text" id="cmail" name="cmail" placeholder="Email을 입력해주세요" >
			<button type="button" id="csendVerificationButton" onclick="csendVerificationCode()" class="mailsend">인증번호 전송</button>
			<input type="text" id="corp_checkNo" name="corp_checkNo" class="corp_check" placeholder="인증번호를 입력하세요" >
			<button type="button" onclick="cverifyCode()" class="mail_ck">확인</button>
			<button type="submit" class="submit-btn">완료</button>
		</form>
	</div>
</body>
<jsp:include page="../../footer.jsp" />

</html>
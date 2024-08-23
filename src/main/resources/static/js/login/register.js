function chkspace(inputId) {
    if (inputId.indexOf(' ') !== -1) {
        return true; // 공백이 포함되어 있음
    } else {
        return false; // 공백이 포함되어 있지 않음
    }
}


function id_chk(id, gubun) {
	var id = $("#"+id).val();

	if(id == '') {
		alert('아이디를 입력하세요');
		//$("#" + id).focus();
		return false;
	}
	
	if(chkspace(id)) {
		alert('아이디에 공백이 포함되었습니다.');
		return false;
	}
	
	$.ajax({
		type: 'post',
		data: {id: id, gubun: gubun},
		url: "/idchk",
		success: function(response) {
			console.log("response: "+response);
			var msg = JSON.parse(response);
			alert(msg.message);
			
			if(msg.flag == 't') {
				$("#"+msg.gubun+"button").removeClass("disabled");
			} else {
				if(!$("#"+msg.gubun+"button").hasClass("disabled")) {
					$("#"+msg.gubun+"button").addClass("disabled");
				}
			}
		},
		error: function() {
			console.log("ajax 에러...");
		}
	});
}
// 데이터 입력시 영어,숫자만 입력가능하게 하기
function validateInput(event) {
    const input = event.target;
    let value = input.value;

    // 영어 알파벳과 숫자만 허용하고, 나머지 문자는 제거
    value = value.replace(/[^a-zA-Z0-9]/g, '');

    // 변환된 값을 입력 필드에 설정
    input.value = value;
}
function onlyNumbers(event) {
    const input = event.target;
    let value = input.value;

    // 영어 알파벳과 숫자만 허용하고, 나머지 문자는 제거
    value = value.replace(/[^0-9]/g, '');

    // 변환된 값을 입력 필드에 설정
    input.value = value;
}

// 사업자등록번호 
function formatDate(event){
	const input = event.target;

	let value = input.value;

	value = value.replace(/\D/g, '');

	if (value.length >= 10) {
		value = value.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-$3');
		input.value = value;
	}
}

function inputtextreset(form) {
	const inputs = form.find('input');
	
	// 각 input 요소의 값을 빈 문자열로 설정
    inputs.each(function() { 
		$(this).val(''); 
	});
}

//회원 가입
function geneform() {
	document.getElementById('generalBtn').classList.add('active');
	document.getElementById('generalForm').classList.remove('hidden');
    
	document.getElementById('businessForm').classList.add('hidden');
    document.getElementById('businessBtn').classList.remove('active');
	
	if(!$("#pbutton").hasClass("disabled")) {
		$("#pbutton").addClass("disabled");
	}
	
	inputtextreset($("#generalForm"));
}

function busiform(){
	document.getElementById('generalBtn').classList.remove('active');
	document.getElementById('generalForm').classList.add('hidden');
	
    document.getElementById('businessForm').classList.remove('hidden');
    document.getElementById('businessBtn').classList.add('active');
	
	if(!$("#cbutton").hasClass("disabled")) {
		$("#cbutton").addClass("disabled");
	}
	
	inputtextreset($("#businessForm"));
}

// -----------------회원가입 완료시 팝업창알림--------

function register_ok(){
    alert("가입완료!! 로그인페이지로 이동합니다");
}

//입력 필드들이 모두 채워져 있는지 확인하는 유효성 검사
function validateForm(form) {
    var isValid = true;
    $(form).find('input[required]').each(function() {
        if ($(this).val() === '') {
            isValid = false;
            return false; //반복문 종료
        }
    });
    return isValid;
}

//필수 입력 필드들이 모두 채워졌는지 실시간으로 확인하고, 모든 필수 필드가 채워졌을 때만 제출 버튼을 활성화
$(document).ready(function() {
    $('#generalForm input[required], #businessForm input[required]').on('input', function() {
        var form = $(this).closest('form');
        var isValid = validateForm(form);
        if (isValid) {
            form.find('button[type="submit"]').removeClass('disabled');
        } else {
            form.find('button[type="submit"]').addClass('disabled');
        }
    });

	//폼이 제출될 때 특정 조건을 확인하고, 조건이 만족되지 않으면 폼 제출을 중단하는 기능을 구현
    $('#generalForm').on('submit', function(e) {
        if (!validateForm(this)) {
            e.preventDefault();
            return false;
        }
		if (!emailVerified || !areAllRequiredCheckboxesChecked()) {
            e.preventDefault();
            alert("모든 필수 입력란을 채우고 이메일 인증 및 약관 동의가 완료되어야 합니다.");
			return false;
        }
        register_ok();
    });

    $('#businessForm').on('submit', function(e) {
        if (!validateForm(this)) {
            e.preventDefault();
            return false;
        }
		if (!emailVerified || !areAllRequiredCheckboxesChecked()) {
            e.preventDefault();
            alert("모든 필수 입력란을 채우고 이메일 인증 및 약관 동의가 완료되어야 합니다.");
			return false;
        }
        register_ok();
    });
});

var emailVerified = false; // 이메일 인증 상태 변수
//이메일 인증
//인증번호 보내는 부분
function psendVerificationCode() {
    // var email = document.getElementById("user_email").value;
    var email = document.getElementById("puser_email").value;
    var sendButton = document.getElementById("psendVerificationButton");
    
    if (!email) {
        alert("이메일을 입력하세요.");
        return;
    }

    $.ajax({
        type: "POST",
        // url: "email/register",
        url: "email/idfind",
        data: { email: email },
        success: function(response) {
            alert(response);
            // 버튼 텍스트를 '재전송'으로 변경
             sendButton.innerText = "재전송";
            // 버튼을 비활성화 후 60초 후에 활성화
            sendButton.disabled = true;
            setTimeout(function() {
                sendButton.disabled = false;
//            }, 60000); // 60초
            }, 60000); // 60초
        },
        error: function() { // 에러발생시 
            alert("인증번호 전송에 실패했습니다.");
        }
    });
}

//인증번호 확인부분 개인회원
function pverifyCode() {
    // var email = document.getElementById("user_email").value;
    var email = document.getElementById("puser_email").value;
    var code = document.getElementById("puser_checkNo").value;
    const verifyCodeButton = document.querySelector('button[onclick="pverifyCode()"]');

    if (!email || !code) {
        alert("이메일과 인증번호를 입력하세요.");
        return;
    }

    $.ajax({
        type: "POST",
        url: "email/verify",
        data: { email: email, code: code },
        success: function(response) {
            alert(response);
            verifyCodeButton.disabled = true;  // 중복 확인 후 버튼 비활성화
			emailVerified = true;
        },
        error: function() {
            alert("인증번호 확인에 실패했습니다.");
        }
    });
}

//기업인증번호 보내는 부분
function csendVerificationCode() {
    var email = document.getElementById("cuser_email").value;
    var sendButton = document.getElementById("csendVerificationButton");
    
    if (!email) {
        alert("이메일을 입력하세요.");
        return;
    }

    $.ajax({
        type: "POST",
        // url: "email/register",
        url: "email/idfind",
        data: { email: email },
        success: function(response) {
            alert(response);
            // 버튼 텍스트를 '재전송'으로 변경
             sendButton.innerText = "재전송";
            // 버튼을 비활성화 후 60초 후에 활성화
            sendButton.disabled = true;
            setTimeout(function() {
                sendButton.disabled = false;
            }, 60000); // 60초
        },
        error: function() { // 에러발생시 
            alert("인증번호 전송에 실패했습니다.");
        }
    });
}

//인증번호 확인부분 기업회원
function cverifyCode() {
    // var email = document.getElementById("user_email").value;
    var email = document.getElementById("cuser_email").value;
    var code = document.getElementById("cuser_checkNo").value;
    const verifyCodeButton = document.querySelector('button[onclick="cverifyCode()"]');

    if (!email || !code) {
        alert("이메일과 인증번호를 입력하세요.");
        return;
    }

    $.ajax({
        type: "POST",
        url: "email/verify",
        data: { email: email, code: code },
        success: function(response) {
            alert(response);
            verifyCodeButton.disabled = true;  // 중복 확인 후 버튼 비활성화
			emailVerified = true;
        },
        error: function() {
            alert("인증번호 확인에 실패했습니다.");
        }
    });
}

// 회원가입 내용보기
function toggleDetails(element) {
	var details = element.nextElementSibling;
	if (details.classList.contains('open')) {
		details.classList.remove('open');
		element.innerText = '내용보기 ▾';
	} else {
		details.classList.add('open');
		element.innerText = '내용닫기 ▴';
	}
}
	
// 회원가입 체크박스
function AllCheck(masterCheckbox) {
	var checkboxes = document.querySelectorAll('input[type="checkbox"][name="agreement"]');
	checkboxes.forEach(function (checkbox) {
		checkbox.checked = masterCheckbox.checked;
	});
}


//이메일 체크박스로 인해 추가 된부분



//체크박스가 다 체크되었는지 확인하는 함수
function areAllRequiredCheckboxesChecked() {
    var checkboxes = document.querySelectorAll('input[type="checkbox"][name="agreement"]');
    for (var checkbox of checkboxes) {
        if (!checkbox.checked) {
            return false;
        }
    }
    return true;
}

// 가입하기 비활성화부분 
function updateSubmitButtonState() {
    var generalFormValid = validateForm($('#generalForm'));
    var businessFormValid = validateForm($('#businessForm'));

    if (generalFormValid) {
        $('#pbutton').removeClass('disabled');
    } else {
        $('#pbutton').addClass('disabled');
    }

    if (businessFormValid) {
        $('#cbutton').removeClass('disabled');
    } else {
        $('#cbutton').addClass('disabled');
    }
}

// 체크박스 상태 변경 시 가입하기 버튼 상태 업데이트
// 회원가입 체크박스
function AllCheck(masterCheckbox) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"][name="agreement"]');
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = masterCheckbox.checked;
    });
    // 전체 동의 체크박스 상태 변경 시에도 업데이트
    updateSubmitButtonState();
}

function register_p_ok() {
	var puserid = $("#puserid").val();
	var ppass = $("#ppass").val();
	var pname = $("#pname").val();
	var phone = $("#phone").val();
	var email = $("#puser_email").val();
	
	$('#generalForm input[required]').on('input', function() {
        var form = $(this).closest('form');
        var isValid = validateForm(form);
        if (isValid) {
            form.find('button[type="submit"]').removeClass('disabled');
        } else {
            form.find('button[type="submit"]').addClass('disabled');
        }
    });

	//폼이 제출될 때 특정 조건을 확인하고, 조건이 만족되지 않으면 폼 제출을 중단하는 기능을 구현
    if (!validateForm(this)) {
        return false;
    }
	if (!emailVerified || !areAllRequiredCheckboxesChecked()) {
        alert("모든 필수 입력란을 채우고 이메일 인증 및 약관 동의가 완료되어야 합니다.");
		return false;
    }
	
	$.ajax({
		type:'post',
		data: { puserid: puserid, ppass: ppass, pname: pname, phone: phone, email: email },
		url: "/RestUserInsert_p",
		success: function(response) {
			if(response == 'success') {
				register_ok();
				window.location.href = "/login";
			}
		}, error: function(xhr, status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function register_c_ok() {
	var cuserid = $("#cuserid").val();
	var cpass = $("#cpass").val();
	var cusnm = $("#cusnm").val();
	var bsnum = $("#bsnum").val();
	var cleader = $("#cleader").val();
	var caddr = $("#caddr").val();
	var phone = $("#cphone").val();
	var cmail = $("#cuser_email").val();
	
	$('#businessForm input[required]').on('input', function() {
        var form = $(this).closest('form');
        var isValid = validateForm(form);
        if (isValid) {
            form.find('button[type="submit"]').removeClass('disabled');
        } else {
            form.find('button[type="submit"]').addClass('disabled');
        }
    });

	//폼이 제출될 때 특정 조건을 확인하고, 조건이 만족되지 않으면 폼 제출을 중단하는 기능을 구현
    if (!validateForm(this)) {
        return false;
    }
	if (!emailVerified || !areAllRequiredCheckboxesChecked()) {
        alert("모든 필수 입력란을 채우고 이메일 인증 및 약관 동의가 완료되어야 합니다.");
		return false;
    }
	
	$.ajax({
		type:'post',
		data: { cuserid: cuserid, cpass: cpass, cusnm: cusnm, phone: phone, cmail: cmail, bsnum: bsnum, cleader: cleader, caddr: caddr },
		url: "/RestUserInsert_c",
		success: function(response) {
			if(response == 'success') {
				window.location.href = "/login";
			}
		}, error: function(xhr, status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}
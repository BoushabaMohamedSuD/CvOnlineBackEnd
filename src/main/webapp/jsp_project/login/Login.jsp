<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jsp_project/login/Login.css">


</head>

<body onload="initialize()">

	<div id="Login">
		<svg class="Rectangle_1">
			<radialGradient spreadMethod="pad" id="RadialGradientFill1">
				<stop offset="0" stop-color="#fff" stop-opacity="1"></stop>
				<stop offset="0" stop-color="#000" stop-opacity="1"></stop>
				<stop offset="0.20506399869918823" stop-color="#fff"
				stop-opacity="1"></stop>
				<stop offset="0.47291499376296997" stop-color="#000"
				stop-opacity="1"></stop>
				<stop offset="0.5565320253372192" stop-color="#fff" stop-opacity="1"></stop>
				<stop offset="0.6442999839782715" stop-color="#343434"
				stop-opacity="1"></stop>
				<stop offset="1" stop-color="#000" stop-opacity="1"></stop>
			</radialGradient>
			<rect id="Rectangle_1" rx="0" ry="0" x="0" y="0" width="1534"
				height="1460">
			</rect>
		</svg>
		<div id="Header">
			<div id="Make_your_cv_Online">
				<a href="index" id=LOGO_link><span>Make your cv<br />Online
				</span></a>

			</div>
			<list id="nav_bar">

			<ul id="contact_us" class="ul">
				<span><a href="contact_us" class="ul_link">Contact_US</a></span>
			</ul>

			<ul id="Login_A0_Text_2" class="ul">
				<span><a href="login" class="ul_link">Login</a></span>
			</ul>

			<ul id="User" class="ul">
				<span><a href="profile" class="ul_link">profile</a></span>
			</ul>
			</list>
		</div>
		<div id="Welcome">
			<svg class="Holder_Welcome">
				<radialGradient spreadMethod="pad" id="RadialGradientFill2">
					<stop offset="0" stop-color="#000" stop-opacity="1"></stop>
					<stop offset="0.20510199666023254" stop-color="#e5e5e5"
					stop-opacity="1"></stop>
					<stop offset="0.4895569980144501" stop-color="#060606"
					stop-opacity="1"></stop>
					<stop offset="0.7281519770622253" stop-color="#fff"
					stop-opacity="1"></stop>
					<stop offset="1" stop-color="#aeaeae"
					stop-opacity="0.45098039215686275"></stop>
					<stop offset="1" stop-color="#fff" stop-opacity="1"></stop>
				</radialGradient>
				<ellipse id="Holder_Welcome" rx="174.5" ry="105.5" cx="174.5"
					cy="105.5">
				</ellipse>
			</svg>
			<div id="Welcome_A0_Text_5">
				<span>Welcome</span>
			</div>
		</div>
		<div id="Main_Login">
			<div id="Login_A0_Group_5">
				<svg class="Holder_signup">
					<rect id="Holder_signup" rx="0" ry="0" x="0" y="0" width="441.842"
						height="195">
					</rect>
				</svg>
				<div id="Login_to_your_acount">
					<span>Login to your acount</span>
				</div>
			</div>
			<div id="Field_Login">
				<form:form method="post" class="form_input"
					action="${pageContext.request.contextPath}/loginProcess"
					name="loginForm">
					<c:if test="${param.error != null}">

						<p class="failed" style="font-size: 30px; color: red;">Sorry!
							You entered invalid username/password.</p>

					</c:if>

					<c:if test="${param.logout != null}">

						<p style="font-size: 30px; color: green;">You have been logged
							out.</p>

					</c:if>
					<p style="font-size: 35px; color: yellow;">${password}</p>
					<c:if test="${username != null}">

						<script>
							function initialize() {
								submitForm()
							}

							function submitForm() {
								document.forms["loginForm"].submit();
							}
						</script>

					</c:if>
					<input name="username" type="text" placeholder="Pseudo"
						required="required" value="${username}" />
					<input name="password" type="password" placeholder="Passworld"
						required="required" value="${password}" />

					<button type="submit" id="Button_Login" class="submit">
						Login</button>


				</form:form>


			</div>
		</div>
		<div id="Main_Signup">
			<div id="signup">
				<svg class="Holder_signup_A0_Rectangle_6">
					<radialGradient spreadMethod="pad" id="RadialGradientFill2">
						<stop offset="0" stop-color="#000" stop-opacity="1"></stop>
						<stop offset="1" stop-color="#000" stop-opacity="1"></stop>
					</radialGradient>
					<rect id="Holder_signup_A0_Rectangle_6" rx="0" ry="0" x="0" y="0"
						width="441.842" height="195">
					</rect>
				</svg>
				<div id="Create_a_new_Account___">
					<span>Create a new Account !!</span>
				</div>
			</div>
			<div id="signup_Field">
				<form:form method="post" class="form_input"
					action="${pageContext.request.contextPath}/login">
					<input name="username" type="text" placeholder="Pseudo"
						required="required" />
					<input name="email" type="email" placeholder="Email"
						required="required" />
					<input name="password" type="password" placeholder="Passworld"
						required="required" />
					<input name="confirm_password" type="password"
						placeholder="Confirm your Passworld" required="required" />
					<button type="submit" id="Buttton_Signin" class="submit">
						signin</button>
				</form:form>
			</div>
		</div>
		<div id="OR">
			<svg class="or">
				<radialGradient spreadMethod="pad" id="RadialGradientFill3">
					<stop offset="0" stop-color="#e7f700" stop-opacity="1"></stop>
					<stop offset="0.5020549893379211" stop-color="#ffed00"
					stop-opacity="1"></stop>
					<stop offset="1" stop-color="#000" stop-opacity="1"></stop>
				</radialGradient>
				<ellipse id="or" rx="67" ry="42.5" cx="67" cy="42.5">
				</ellipse>
			</svg>
			<div id="OR_A0_Text_15">
				<span>OR</span>
			</div>
		</div>


	</div>
</body>
</html>

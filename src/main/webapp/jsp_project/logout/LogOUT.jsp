<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>LogOUT</title>
<link rel="stylesheet" type="text/css" id="applicationStylesheet" href="${pageContext.request.contextPath}/jsp_project/logout/lgo.css"/>
</head>
<body>
<div id="Logout">
	<svg class="background">
		<radialGradient spreadMethod="pad" id="RadialGradientFill1">
			<stop offset="0" stop-color="#fff" stop-opacity="1"></stop>
			<stop offset="0" stop-color="#000" stop-opacity="1"></stop>
			<stop offset="0.20506399869918823" stop-color="#fff" stop-opacity="1"></stop>
			<stop offset="0.47291499376296997" stop-color="#000" stop-opacity="1"></stop>
			<stop offset="0.5565320253372192" stop-color="#fff" stop-opacity="1"></stop>
			<stop offset="0.6442999839782715" stop-color="#343434" stop-opacity="1"></stop>
			<stop offset="1" stop-color="#000" stop-opacity="1"></stop>
		</radialGradient>
		<rect id="background" rx="0" ry="0" x="0" y="0" width="1534" height="1460">
		</rect>
	</svg>
	<div id="Header">
		<div id="Make_your_cv_Online">
				<a href="index" id=LOGO_link><span>Make your cv<br />Online</span></a>

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
				<stop offset="0.20510199666023254" stop-color="#e5e5e5" stop-opacity="1"></stop>
				<stop offset="0.4895569980144501" stop-color="#060606" stop-opacity="1"></stop>
				<stop offset="0.7281519770622253" stop-color="#fff" stop-opacity="1"></stop>
				<stop offset="1" stop-color="#aeaeae" stop-opacity="0.45098039215686275"></stop>
				<stop offset="1" stop-color="#fff" stop-opacity="1"></stop>
			</radialGradient>
			<ellipse id="Holder_Welcome" rx="158.39645385742188" ry="117.41715240478516" cx="158.39645385742188" cy="117.41715240478516">
			</ellipse>
		</svg>
		<div id="Welcome_A1_Text_5">
			<span>Welcome</span>
		</div>
	</div>
	<div id="Log_out">
		<form:form method="post" class="form_input" action="${pageContext.request.contextPath}/logout">
					
			<button type="submit" id="log_out" class="log_out"> LogOUT</button>


		</form:form>

	</div>
</div>
</body>
</html>
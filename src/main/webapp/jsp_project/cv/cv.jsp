<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>cv</title>
	<link rel="stylesheet" type="text/css" id="applicationStylesheet" href="${pageContext.request.contextPath}/jsp_project/cv/cv.css" />
</head>

<body>
	<div id="cv">
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
					<span><a href="profile" class="ul_link">Profile</a></span>
				</ul>
			</list>
		</div>
		<div id="style">
			<div id="choose_your_style">
				<span>choose your style</span>
			</div>
			<div id="style_input">
				<a href="#">
					<svg class="CV">
						<pattern elementId="CV_A2_Ellipse_2" id="CV_A2_Ellipse_2_pattern" x="0" y="0" width="100%" height="100%">
							<image x="0" y="0" width="100%" height="100%" href="${pageContext.request.contextPath}/jsp_project/cv/CV_A2_Ellipse_2_pattern.png" xlink:href="CV_A2_Ellipse_2_pattern.png"></image>
						</pattern>
						<ellipse id="CV" rx="89" ry="89" cx="89" cy="89">
						</ellipse>
					</svg>

				</a>
				<a href="#">
					<svg class="ID54_modele_cv_perspicace">
						<pattern elementId="ID54_modele_cv_perspicace_A2_Ellipse_4" id="ID54_modele_cv_perspicace_A2_Ellipse_4_pattern" x="0" y="0" width="100%" height="100%">
							<image x="0" y="0" width="100%" height="100%" href="${pageContext.request.contextPath}/jsp_project/cv/ID54_modele_cv_perspicace_A2_Ellipse_4_pattern.png" xlink:href="ID54_modele_cv_perspicace_A2_Ellipse_4_pattern.png"></image>
						</pattern>
						<ellipse id="ID54_modele_cv_perspicace" rx="89" ry="89" cx="89" cy="89">
						</ellipse>
					</svg>

				</a>
				<a href="#">
					<svg class="cv_avec_photo_12">
						<pattern elementId="cv_avec_photo_12_A2_Ellipse_6" id="cv_avec_photo_12_A2_Ellipse_6_pattern" x="0" y="0" width="100%" height="100%">
							<image x="0" y="0" width="100%" height="100%" href="${pageContext.request.contextPath}/jsp_project/cv/cv_avec_photo_12_A2_Ellipse_6_pattern.png" xlink:href="cv_avec_photo_12_A2_Ellipse_6_pattern.png"></image>
						</pattern>
						<ellipse id="cv_avec_photo_12" rx="89" ry="89" cx="89" cy="89">
						</ellipse>
					</svg>

				</a>
				<a href="#">
					<svg class="CV_Yoann_Mod_le_3_3_1">
						<pattern elementId="CV_Yoann_Mod_le_3_3_1_A2_Ellipse_8" id="CV_Yoann_Mod_le_3_3_1_A2_Ellipse_8_pattern" x="0" y="0" width="100%" height="100%">
							<image x="0" y="0" width="100%" height="100%" href="${pageContext.request.contextPath}/jsp_project/cv/CV_Yoann_Mod_le_3_3_1_A2_Ellipse_8_pattern.png" xlink:href="CV_Yoann_Mod_le_3_3_1_A2_Ellipse_8_pattern.png"></image>
						</pattern>
						<ellipse id="CV_Yoann_Mod_le_3_3_1" rx="89" ry="89" cx="89" cy="89">
						</ellipse>
					</svg>

				</a>




			</div>
		</div>
		<svg class="Rectangle_3">
			<linearGradient spreadMethod="pad" id="LinearGradientFill1" x1="0.4639196991920471" x2="0.5" y1="0.8985056281089783" y2="1">
				<stop offset="0" stop-color="#5a5353" stop-opacity="1"></stop>
				<stop offset="1" stop-color="#000" stop-opacity="1"></stop>
			</linearGradient>
			<rect id="Rectangle_3" rx="0" ry="0" x="0" y="0" width="1534" height="362">
			</rect>
		</svg>
		<svg class="Line_1">
			<path id="Line_1" d="M 0 0 L 706 0">
			</path>
		</svg>
		<div id="add_your_own_style_to_help_thi">
			<span>add your own style to help this community</span>
		</div>
		<div id="upload_style">
			<div id="We_will_apreciate_your_help_an">
				<span>We will apreciate your help and your cooperation<br />to involve our community so you can put your <br />own design whether it's as a pdf or an image <br />file but we will help us so much if you put <br />as html and css formats.</span>
			</div>
			<div id="group_upload">
				<div id="unput_upload">
					<form method="post" class="form_input">
						<input name="Uplaod" type="file" id="Upload" />
						<input name="Uplaod_input" type="button" id="uplaod_input" onclick="document.getElementById('Upload').click()" value="Upload" />



						<button type="submit" id="Upload_Button"> Send</button>


					</form>
				</div>

			</div>
		</div>
	</div>
</body>

</html>

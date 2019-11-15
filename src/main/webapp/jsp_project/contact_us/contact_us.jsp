<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>conatct_us</title>
	<link rel="stylesheet" type="text/css" id="applicationStylesheet" href="${pageContext.request.contextPath}/jsp_project/contact_us/contact_us.css" />
</head>

<body>
	<div id="contact_us">
		<div id="Header">
			<div id="Make_your_cv_Online">
				<a href="index" id=LOGO_link><span>Make your cv<br />Online</span></a>

			</div>
			<list id="nav_bar">

				<ul class="ul">
					<span><a href="contact_us" class="ul_link">Contact_US</a></span>
				</ul>

				<ul class="ul">
					<span><a href="login" class="ul_link">Login</a></span>
				</ul>

				<ul class="ul">
					<span><a href="profile" class="ul_link">Profile</a></span>
				</ul>
			</list>
		</div>
		<div id="field_input">
			<div id="input_object">
				<form method="post">
					<input type="text" placeholder="Object" name="object" class="object" id="object" maxlength="20">

					<div id="input_message">

						<textarea  placeholder="your message her" name="mesage" maxlength="1000"  required ></textarea>
							

						

					</div>

				</form>
			</div>

		</div>
		<div id="Welcome_Boushaba_Mohamed">
			<span>Welcome<br />Boushaba Mohamed</span>
		</div>
		<form method="post">
			<button type="submit" class="Rectangle_11"> send</button>
		</form>
	</div>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sign-Up/Login Form</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> 
	<link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
	<div class="form">
		<ul class="tab-group">
			<li class="tab active"><a href="#signup">Sign Up</a></li>
			<li class="tab"><a href="#login">Log In</a></li>
		</ul>

		<div class="tab-content">
			<div id="signup">

				<form action="${pageContext.request.contextPath}/signUpController" method="post">

					<div class="top-row">
						<div class="field-wrap">
							<label> First Name<span class="req">*</span>
							</label> <input name="inputName" type="text" required autocomplete="off" />
						</div>

						<div class="field-wrap">
							<label> Last Name<span class="req">*</span>
							</label> <input name="inputSurname" type="text" required autocomplete="off" />
						</div>
					</div>

					<div class="field-wrap">
						<label> Email Address<span class="req">*</span>
						</label> <input name="inputEmail" type="email" required autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> Set A Password<span class="req">*</span>
						</label> <input name="inputPassword" type="password" required autocomplete="off" />
					</div>
					
					<div class="top-row">
						<div class="field-wrap">
							<img src="${pageContext.request.contextPath}/simpleCaptcha.jpg">
						</div>
						<div class="field-wrap">
						<label> Captcha	<span class="req">*</span></label>
						    <input name="formCaptchaCode" type="text" required autocomplete="off" />
					   	</div>
				    </div>
					

					<button type="submit" class="button button-block">
					Sign Up
					</button>

				</form>

			</div>

			<div id="login">
				<h1>Welcome Back!</h1>

				<form action="${pageContext.request.contextPath}/signInController" method="post">

					<div class="field-wrap">
						<label> Email Address<span class="req">*</span>
						</label> <input name="inputEmailSignIn" type="email" required autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> Password<span class="req">*</span>
						</label> <input name="inputPasswordSignIn" type="password" required autocomplete="off" />
					</div>
					<button class="button button-block">
					Log In
					</button>

				</form>

			</div>

		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->
	

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>







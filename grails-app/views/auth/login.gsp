<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="login.title"/></title>
	</head>
	<body>
		<h3><g:message code="login.header.label"/></h3>
			<g:form action="signIn">
				<input type="hidden" name="targetURI" value="${targetURI}" />
				<table>
					<tbody>
						<tr><td>
							<label><g:message code="login.username.label"/></label>
							<input type="text" name="username" value="${username}" />
						</td></tr>
						<tr><td>
							<label><g:message code="login.password.label"/></label>
							<input type="password" name="password" value="" />
							<a href="${createLink(controller:'auth', action:'forgotPassword')}"><g:message code="login.forgot.password.label"/></a>
						</td></tr>
						<tr><td>
							<div><button type="submit">${message(code:'login.signin.label')}</button></div>
							<div><g:checkBox name="rememberMe" value="${rememberMe}" /><label><g:message code="login.rememberme.label"/></label></div>
						</td></tr>
					</tbody>
				</table>
			</g:form>
	</body>
</html>
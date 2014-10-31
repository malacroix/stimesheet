
/**
 * Login : take the select value (the marketplace baseUrl) and redirect to it.
 */
function login(context) {
	window.location.href = context + '/j_spring_openid_security_check?openid_identifier=' + $('#marketplace').val() + "/openid/id";
}
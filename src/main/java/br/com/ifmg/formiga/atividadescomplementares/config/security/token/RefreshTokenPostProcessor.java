package br.com.ifmg.formiga.atividadescomplementares.config.security.token;

import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Profile("oauth-security")
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return (Objects.requireNonNull(methodParameter.getMethod()).getName().equals("getAccessToken")) || (Objects.requireNonNull(methodParameter.getMethod()).getName().equals("postAccessToken"));
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken oAuth2AccessToken, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest req = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;

        assert oAuth2AccessToken != null;
        String refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
        addRefreshTokenCookie(refreshToken, req, resp);
        removeRefreshTokenDoBody(token);

        return oAuth2AccessToken;
    }

    private void removeRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

    private void addRefreshTokenCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);
        resp.addCookie(refreshTokenCookie);
    }
}

package br.com.ifmg.formiga.atividadescomplementares.config.security.token;

import br.com.ifmg.formiga.atividadescomplementares.config.security.user.UsuarioLogado;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        UsuarioLogado usuarioLogado = (UsuarioLogado) oAuth2Authentication.getPrincipal();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("usuario", usuarioLogado.getUsuario().getNome());
        addInfo.put("role", usuarioLogado.getUsuario().getTipoUsuario().toString());
        addInfo.put("id", usuarioLogado.getUsuario().getId().toString());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(addInfo);
        return oAuth2AccessToken;
    }

}

package org.soygaia.msvc.gaiaclub.providers;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Provider
public class BearerTokenFilter implements ClientRequestFilter {

    @Inject
    JsonWebToken jwt;

    @Override
    public void filter(ClientRequestContext ctx) {

        // Solo propaga si el request entrante traía un token válido
        String token = jwt.getRawToken();
        if (jwt != null && token!=null) {
            ctx.getHeaders().putSingle("Authorization", "Bearer " + token);
        }
    }
}


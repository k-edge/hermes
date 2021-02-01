package pl.allegro.tech.hermes.frontend.publishing.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import pl.allegro.tech.hermes.frontend.server.auth.AuthenticationConfiguration;

public class AuthenticatedRequestHandler implements HttpHandler {

  private final HttpHandler next;
  private final AuthenticationConfiguration authenticationConfiguration;
  private boolean authenticationRequired;

  public AuthenticatedRequestHandler(HttpHandler next,
      AuthenticationConfiguration authenticationConfiguration
      ) {
    this.next = next;
    this.authenticationConfiguration = authenticationConfiguration;
  }

  @Override
  public void handleRequest(HttpServerExchange exchange) throws Exception {
    if (exchange.isInIoThread()) {
      exchange.dispatch(this);
      return;
    }

    exchange.setSecurityContext(new FactoryPalSecurityContext(exchange, authenticationConfiguration));
    next.handleRequest(exchange);
  }
}


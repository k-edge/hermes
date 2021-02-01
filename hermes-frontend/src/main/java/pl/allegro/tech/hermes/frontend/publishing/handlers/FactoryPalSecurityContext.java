package pl.allegro.tech.hermes.frontend.publishing.handlers;

import io.undertow.security.api.AuthenticationMechanism;
import io.undertow.security.api.AuthenticationMechanism.AuthenticationMechanismOutcome;
import io.undertow.security.idm.Account;
import io.undertow.security.idm.Credential;
import io.undertow.security.idm.IdentityManager;
import io.undertow.security.idm.PasswordCredential;
import io.undertow.security.impl.AbstractSecurityContext;
import io.undertow.security.impl.BasicAuthenticationMechanism;
import io.undertow.server.HttpServerExchange;
import java.util.ArrayList;
import java.util.List;
import pl.allegro.tech.hermes.frontend.server.auth.AuthenticationConfiguration;
import pl.allegro.tech.hermes.frontend.server.auth.ZookeeperIdentityManager;
import pl.allegro.tech.hermes.frontend.server.auth.ZookeeperIdentityManager.ClientAccount;

public class FactoryPalSecurityContext extends AbstractSecurityContext {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final HttpServerExchange exchange;
  private Account account;
  private AuthenticationMechanismOutcome currentAuthState;

  protected FactoryPalSecurityContext(HttpServerExchange exchange,
      AuthenticationConfiguration authenticationConfiguration) {
    super(exchange);
    this.exchange = exchange;
    this.authenticationConfiguration = authenticationConfiguration;
  }

  @Override
  public boolean authenticate() {
    currentAuthState = authenticationConfiguration.getAuthMechanisms().get(0)
        .authenticate(exchange, this);
    return true;
  }

  @Override
  public boolean isAuthenticated() {
    return currentAuthState != null &&
        currentAuthState == AuthenticationMechanismOutcome.AUTHENTICATED;
  }

  @Override
  public Account getAuthenticatedAccount() {
    authenticate();
    return this.account;
  }

  @Override
  public boolean login(String username, String password) {
    return true;
  }

  @Override
  public void addAuthenticationMechanism(AuthenticationMechanism mechanism) {
    authenticationConfiguration.getAuthMechanisms().add(mechanism);
  }

  @Override
  public void authenticationComplete(Account account, String mechanism, boolean cachingRequired) {
    super.authenticationComplete(account, mechanism, cachingRequired);
    this.account = account;
  }

  @Override
  public List<AuthenticationMechanism> getAuthenticationMechanisms() {
    return authenticationConfiguration.getAuthMechanisms();
  }

  @Override
  public IdentityManager getIdentityManager() {
    return authenticationConfiguration.getIdentityManager();
  }
}

package pl.allegro.tech.hermes.frontend.server.auth;

import io.undertow.security.idm.Account;
import io.undertow.security.idm.Credential;
import io.undertow.security.idm.IdentityManager;
import io.undertow.security.idm.PasswordCredential;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

  public class ZookeeperIdentityManager implements IdentityManager {

  private final Map<String, String> credentials;
  private static final Logger logger = LoggerFactory.getLogger(ZookeeperIdentityManager.class);

  public ZookeeperIdentityManager() {
    this.credentials = seed();
  }

  private Map<String,String> seed() {
    Map<String, String> clients = new HashMap<>();
    clients.putIfAbsent("api.factorypal.com","z_A7CwTQtVTcFGJQBrdfNGBY");
    clients.putIfAbsent("domain.service.actualcenterlines","zmpULbwndoQvDofWNnqU8cDD");
    return clients;
  }

  @Override
  public Account verify(Account account) {
    return null;
  }

  @Override
  public Account verify(String id, Credential credential) {
    String password = new String(((PasswordCredential) credential).getPassword());
    String actualPassword = this.credentials.getOrDefault(id, "");
    if(password.equals(actualPassword)) {
      return new ClientAccount(id);
    }
    return null;
  }

  @Override
  public Account verify(Credential credential) {
    return null;
  }

  public static final class ClientAccount implements Account {

    private final ClientPrincipal clientPrincipal;

    public ClientAccount(String clientId) {
      clientPrincipal = new ClientPrincipal(clientId);
    }

    @Override
    public Principal getPrincipal() {
      return this.clientPrincipal;
    }

    @Override
    public Set<String> getRoles() {
      return Collections.singleton(Roles.PUBLISHER);
    }
  }

  private static final class ClientPrincipal implements Principal {

    private final String name;

    private ClientPrincipal(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }
  }
}

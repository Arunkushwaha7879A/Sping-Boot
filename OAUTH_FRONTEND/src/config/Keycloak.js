import Keycloak from "keycloak-js";

const keycloak = new Keycloak({

  realm: "master",
  url: "http://localhost:8086",
  clientId: "react-app",
  pkceMethod: "S256",
});

export default keycloak;
import { createContext, use, useEffect, useState } from "react";
import keycloak from "./Keycloak";

export const AuthContext = createContext()

export const AuthProvider = ({ children }) => {

    const[isAuthenticated, setIsAuthenticated] = useState(false);
    const[keyCloakInstance, setKeyCloakInstance] = useState(null);

    useEffect(() => {   

        keycloak.init({ 
            onLoad: "login-required" ,
            pkceMethod: "S256",

        }).then((authenticated) => {
            console.log("user logged in ");
            setIsAuthenticated(authenticated);
            setKeyCloakInstance(keycloak);
        })
        .catch(error=>{
            console.log("user is not logged in ");
            console.log(error);
        })
    }, []);

    return <AuthContext.Provider value={{
        isAuthenticated,
        setIsAuthenticated,
        keyCloakInstance,
        setKeyCloakInstance
    }}>{children}</AuthContext.Provider>
}

export const useAuth = () => use(AuthContext);
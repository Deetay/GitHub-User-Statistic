package com.recruittask.githubuserstatistics.Service;



import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${GITHUB_USER}")
    private String user;

    @Value("${GITHUB_PASSWORD}")
    private String password;


    public boolean hasCredentials(){
        return this.user != null && this.password != null;

    }

    public HttpHeaders setRequestHeader(){

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.set("Accept", "application/vnd.github.v3+json");
        if(hasCredentials()){
            String credentials=user+":"+password;
            byte[] base64Bytes= Base64.encodeBase64(credentials.getBytes());
            String base64Credentials = new String(base64Bytes);
            requestHeader.set("Authorization", "Basic "+base64Credentials);
        }
        return requestHeader;
    }


}

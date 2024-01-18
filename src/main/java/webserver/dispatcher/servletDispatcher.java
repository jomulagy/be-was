package webserver.dispatcher;

import http.HttpStatus;
import http.Request;
import http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.DataOutputStream;
import java.nio.file.Files;

public class servletDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(servletDispatcher.class);
    public static void forward(Request request, Response response, String viewPath,DataOutputStream dos) throws IOException {
        File file = new File(viewPath);
        byte[] body;
        if (file.exists() && file.isFile()) {
            body = Files.readAllBytes(file.toPath());
            response.setStatus(HttpStatus.OK);
        }
        else{
            body = Files.readAllBytes(new File("/not-found.html").toPath());
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        response.send(dos,body,request);
    }

    public static void sendRedirect(Request request, Response response, String viewPath, DataOutputStream dos) {
        response.setLocation(viewPath);
        response.setStatus(HttpStatus.MOVED_PERMANENTLY);
        response.send(dos,request);
    }



}
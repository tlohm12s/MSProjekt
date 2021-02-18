package de.hbrs.tlohm12s.gatewayservice;

import de.hbrs.tlohm12s.gatewayservice.exceptions.ServiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Autowired
    GatewayServiceController gatewayServiceController;

    //https://www.baeldung.com/spring-mvc-handlerinterceptor
    /**
     * Redirects to instance with given instanceID if url of format /service/{instanceid}/** and intercepts this request
     * @param requestServlet
     * @param responseServlet
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {
        String path = (String) requestServlet.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] requestPath = path.split("/", 4);

        if (requestPath.length > 3) {
            String request = requestPath[1];
            String instanceID = requestPath[2];
            String forward = requestPath[3];

            if (request.equals("service")) {
                String host = gatewayServiceController.getHostByInstanceID(instanceID);
                String url = host + forward;

                if (host.equals("")) throw new ServiceNotFoundException();

                responseServlet.sendRedirect(url);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return false;
    }

}

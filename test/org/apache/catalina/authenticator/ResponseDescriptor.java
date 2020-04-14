
package org.apache.catalina.authenticator;

import java.util.List;
import java.util.Map;

/**
 * This class incorporates test response data
 */
class ResponseDescriptor {
    private Map<String, List<String>> headers;
    private String body;
    private int responseCode;


    public Map<String, List<String>> getHeaders() {
        return headers;
    }


    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public int getResponseCode() {
        return responseCode;
    }


    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
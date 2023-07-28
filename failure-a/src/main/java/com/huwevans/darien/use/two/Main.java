package org.apache.commons.use.three;

import org.apache.commons.failure.*;
import org.apache.commons.failure.impl.*;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class Main {
	
	public Success<String> getPage(String url) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
        final HttpGet httpget = new HttpGet(url);

        Result result = httpclient.execute(httpget, response -> {
            return new Result(response.getCode(), EntityUtils.toString(response.getEntity()));
        });

        if(result.status_code >= 200 && result.status_code <= 299) {
                return new SuccessImpl<String>(result.page);
        } else {
                return new FailureValueImpl<String>(result.status_code);
        }

    } catch(java.io.IOException ioe) {
            return new FailureExceptionImpl<String>(ioe);
    } catch(Exception e) {
            return new FailureExceptionImpl<String>(e);
    }
}

public static void main(String[] argv) {
        Main m = new Main();

        Success<String> page = m.getPage("https://www.example.com");
        if(page.eval()) {
                System.out.println("Success");
        } else {
                System.out.println("Failure");
        }

        page = m.getPage("https://www.example.com/nosuchpage"); // 404 is returned
        if(page.eval()) {
                System.out.println("Success");
        } else {
                switch (page) {
                    case FailureValue<String> fv -> System.out.println(fv.getValue());
                    case FailureException<String> fe -> System.out.println(fe.getException());
                    default  -> System.out.println("As currently written, not possible.");
                }
        }

        page = m.getPage("https://www.cannotfindthisdomain.com"); // an exception is generated
        if(page.eval()) {
                System.out.println("Success");
        } else {
                switch (page) {
                    case FailureValue<String> fv -> System.out.println(fv.getValue());
                    case FailureException<String> fe -> System.out.println(fe.getException());
                    default  -> System.out.println("As currently written, not possible.");
                }
        }
}
    private static class Result {
        public final int status_code;
        public final String page;

        public Result(int i, String str) {
            this.status_code = i;
            this.page = str;
        }
    }
}

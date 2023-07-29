package com.huwevans.darien.use.nogenerics.two;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.huwevans.darien.nogeneric.types.*;
import com.huwevans.darien.nogeneric.types.impl.*;
import com.huwevans.darien.nogenerics.utils.FailureUtils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class Main {
	
	/* Attempting to retrieve a resource from outside a program is an operation that can fail.
	 * 
	 * getPage below tries to retrieve the resource indicated by the input url.
	 * 
	 * @returns 
	 */
	public Success getPage(String url) {
		if(FailureUtils.oneIsNull(url)) {
			return FailureUtils.theNull(url);
		}
	    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
	        final HttpGet httpget = new HttpGet(url);
	
	        Result result = httpclient.execute(httpget, response -> {
	            return new Result(response.getCode(), EntityUtils.toString(response.getEntity()));
	        });
	
	        if(result.status_code >= 200 && result.status_code <= 299) {
	                return new SuccessImpl(result.page);
	        } else {
	                return new FailureValueImpl(result.status_code);
	        }
	
	    } catch(java.io.IOException ioe) {
	            return new FailureExceptionImpl(ioe);
	    } catch(Exception e) {
	            return new FailureExceptionImpl(e);
	    }
	}

public static void main(String[] argv) {
        Main m = new Main();

        Success page = m.getPage(null);
        if(page.eval()) {
                System.out.println("Success");
        } else {
            switch (page) {
                case FailureArgIsNull fa -> System.out.println(fa);
                case FailureException fe -> System.out.println(fe.getException());
                default  -> System.out.println("As currently written, not possible.");
            }
        }

        page = m.getPage("https://www.example.com");
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
                    case FailureArgIsNull fa -> System.out.println(fa.unwrap());
                    case FailureValue fv -> System.out.println(fv.getValue());
                    case FailureException fe -> System.out.println(fe.getException());
                    default  -> System.out.println("As currently written, not possible.");
                }
        }

        page = m.getPage("https://www.cannotfindthisdomain.com"); // an exception is generated
        if(page.eval()) {
                System.out.println("Success");
        } else {
                switch (page) {
                    case FailureArgIsNull fa -> System.out.println(fa.unwrap());
                    case FailureValue fv -> System.out.println(fv.getValue());
                    case FailureException fe -> System.out.println(fe.getException());
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

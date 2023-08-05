package org.darien.types.use.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.darien.types.*;
import org.darien.types.impl.*;
import org.darien.types.utils.FailureUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class Main {
	
	/* Attempting to retrieve a resource from outside a program is an operation that can fail.
	 * 
	 * getPage below tries to retrieve the resource indicated by the input url.
	 * 
	 * @returns 
	 */
    
	public S getPage(String url) {
		if(FailureUtils.oneIsNull(url)) {
			return FailureUtils.theNull(url);
		}
		
	    boolean[] bs = {url.startsWith("https"),url.startsWith("https")};
	    
		if(FailureUtils.oneIsFalse(bs)) {
			return FailureUtils.theFalse(bs);
		}
		
	    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
	        final HttpGet httpget = new HttpGet(url);
	
	        Result result = httpclient.execute(httpget, response -> {
	            return new Result(response.getCode(), EntityUtils.toString(response.getEntity()));
	        });
	
	        if(result.status_code >= 200 && result.status_code <= 299) {
	                return new Success(result.page);
	        } else {
	                return new FV(result.status_code);
	        }
	
	    } catch(java.io.IOException ioe) {
	            return new FE(ioe);
	    } catch(Exception e) {
	            return new FE(e);
	    }
	}

public static void main(String[] argv) {
        Main m = new Main();

        S page = m.getPage(null);
        if(page.eval()) {
                System.out.println(page);
        } else {
            switch (page) {
                case FAIN fain -> System.out.println("fain: " + fain.getLocation());
                case FE fe -> System.out.println(fe.getException());
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
                System.out.println("S");
        } else {
                switch (page) {
                    case FAIN fa -> System.out.println(fa.unwrap());
                    case FV fv -> System.out.println(fv.getValue());
                    case FE fe -> System.out.println(fe.getException());
                    default  -> System.out.println("As currently written, not possible.");
                }
        }

        page = m.getPage("https://www.cannotfindthisdomain.com"); // an exception is generated
        if(page.eval()) {
                System.out.println("Success");
        } else {
                switch (page) {
                    case FAIN fa -> System.out.println(fa.unwrap());
                    case FV fv -> System.out.println(fv.getLocation());
                    case FE fe -> System.out.println(fe.getException());
                    default  -> System.out.println("As currently written, not possible.");
                }
        }

        page = m.getPage("http://www.example.com");
        if(page.eval()) {
                System.out.println("Success");
        } else {
                switch (page) {
                    case FAIN fa -> System.out.println(fa.getLocation());
                    case FAIF ff -> System.out.println("ff: " + ff.getLocation());
                    case FV fv -> System.out.println(fv.getLocation());
                    case FE fe -> System.out.println(fe.getException());
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

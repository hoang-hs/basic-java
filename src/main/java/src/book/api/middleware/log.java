package src.book.api.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import src.book.core.usecases.getUserUseCase;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class log extends OncePerRequestFilter {
    private final int maxPayloadLength = 1000;

    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);

    private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, this.maxPayloadLength);
        try {
            return new String(buf, 0, length, charsetName);
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String reqInfo = String.format("id :%s, method: %s, url: %s, query: %s, user agent: %s",
                uuid, request.getMethod(), request.getRequestURL(), request.getQueryString(), request.getHeader("User-Agent"));
        logger.info("req: [{}] ", reqInfo);

        // ========= Log request and response payload ("body") ========
        // We CANNOT simply read the request payload here, because then the InputStream would be consumed and cannot be read again by the actual processing/server.
        //    String reqBody = DoogiesUtil._stream2String(request.getInputStream());   // THIS WOULD NOT WORK!
        // So we need to apply some stronger magic here :-)
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(wrappedRequest, wrappedResponse);
        long duration = System.currentTimeMillis() - startTime;
        // I can only log the request's body AFTER the request has been made and ContentCachingRequestWrapper did its work.
        String requestBody = this.getContentAsString(wrappedRequest.getContentAsByteArray(), this.maxPayloadLength, request.getCharacterEncoding());
        if (requestBody.length() > 0) {
            logger.debug("   Request body:\n" + requestBody);
        }
        String resInfo = String.format("id :%s, http status: %s, time: %sms", uuid, response.getStatus(), duration);
        logger.info("res: [{}]", resInfo);

        byte[] buf = wrappedResponse.getContentAsByteArray();
        logger.debug("   Response body:\n" + getContentAsString(buf, this.maxPayloadLength, response.getCharacterEncoding()));

        wrappedResponse.copyBodyToResponse();  // IMPORTANT: copy content of response back into original response
    }
}

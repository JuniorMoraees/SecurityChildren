package br.com.jmtech.application.enums;

import lombok.Getter;

@Getter
public enum ErrorType {

    BAD_REQUEST("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400", "Bad Request"),
    FORBIDDEN("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/403", "Forbidden"),
    UNAUTHORIZED("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401", "Unauthorized"),
    INTERNAL_SERVER_ERROR("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500", "Internal Server Error"),
    UNPROCESSABLE_ENTITY("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/422", "Unprocessable Entity"),
    NOT_FOUND("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404", "Not Found"),
    BAD_GATEWAY("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/502", "Bad Gateway");

    private String title;

    private String uri;

    ErrorType(String path, String title) {
        this.uri = path;
        this.title = title;
    }
}

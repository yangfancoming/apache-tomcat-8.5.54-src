
package org.apache.catalina.servlet4preview.http;

/**
 * Represents the ways that a request can be mapped to a servlet
 *
 * @since 4.0
 */
public enum MappingMatch {

    CONTEXT_ROOT,
    DEFAULT,
    EXACT,
    EXTENSION,
    PATH
}

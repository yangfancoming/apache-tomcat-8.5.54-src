
package org.apache.catalina.core;

import org.apache.catalina.mapper.MappingData;
import org.apache.catalina.servlet4preview.http.HttpServletMapping;
import org.apache.catalina.servlet4preview.http.MappingMatch;

public class ApplicationMapping {

    private final MappingData mappingData;

    private volatile HttpServletMapping mapping = null;

    public ApplicationMapping(MappingData mappingData) {
        this.mappingData = mappingData;
    }

    public HttpServletMapping getHttpServletMapping() {
        if (mapping == null) {
            if (mappingData == null) {
                // This can happen when dispatching from an application provided
                // request object that does not provide the Servlet 4.0 mapping
                // data.
                mapping = new MappingImpl("", "", null, "");
            } else {
                String servletName;
                if (mappingData.wrapper == null) {
                    servletName = "";
                } else {
                    servletName = mappingData.wrapper.getName();
                }
                if (mappingData.matchType == null) {
                    mapping = new MappingImpl("", "", null, servletName);
                } else {
                    switch (mappingData.matchType) {
                        case CONTEXT_ROOT:
                            mapping = new MappingImpl("", "", mappingData.matchType, servletName);
                            break;
                        case DEFAULT:
                            mapping = new MappingImpl("", "/", mappingData.matchType, servletName);
                            break;
                        case EXACT:
                            mapping = new MappingImpl(mappingData.wrapperPath.toString().substring(1),
                                    mappingData.wrapperPath.toString(), mappingData.matchType, servletName);
                            break;
                        case EXTENSION:
                            String path = mappingData.wrapperPath.toString();
                            int extIndex = path.lastIndexOf('.');
                            mapping = new MappingImpl(path.substring(1, extIndex),
                                    "*" + path.substring(extIndex), mappingData.matchType, servletName);
                            break;
                        case PATH:
                            String matchValue;
                            if (mappingData.pathInfo.isNull()) {
                                matchValue = null;
                            } else {
                                matchValue = mappingData.pathInfo.toString().substring(1);
                            }
                            mapping = new MappingImpl(matchValue, mappingData.wrapperPath.toString() + "/*",
                                    mappingData.matchType, servletName);
                            break;
                    }
                }
            }
        }

        return mapping;
    }

    public void recycle() {
        mapping = null;
    }

    private static class MappingImpl implements HttpServletMapping {

        private final String matchValue;
        private final String pattern;
        private final MappingMatch mappingType;
        private final String servletName;

        public MappingImpl(String matchValue, String pattern, MappingMatch mappingType,
                String servletName) {
            this.matchValue = matchValue;
            this.pattern = pattern;
            this.mappingType = mappingType;
            this.servletName = servletName;
        }

        @Override
        public String getMatchValue() {
            return matchValue;
        }

        @Override
        public String getPattern() {
            return pattern;
        }

        @Override
        public MappingMatch getMappingMatch() {
            return mappingType;
        }

        @Override
        public String getServletName() {
            return servletName;
        }
    }
}

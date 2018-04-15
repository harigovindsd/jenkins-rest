/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cdancy.jenkins.rest.filters;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.cdancy.jenkins.rest.JenkinsApi;
import com.cdancy.jenkins.rest.JenkinsAuthentication;
import com.cdancy.jenkins.rest.auth.AuthenticationType;

import org.jclouds.http.HttpException;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpRequestFilter;

import com.google.common.net.HttpHeaders;

@Singleton
public class JenkinsAuthenticationFilter implements HttpRequestFilter {
    private final JenkinsAuthentication creds;
    private final JenkinsApi jenkinsApi;
    private static volatile String crumbValue = null; // can be shared across requests
    private static final String CRUMB_HEADER = "Jenkins-Crumb";

    @Inject
    JenkinsAuthenticationFilter(final JenkinsAuthentication creds, final JenkinsApi jenkinsApi) {
        this.creds = creds;
        this.jenkinsApi = jenkinsApi;
    }

    @Override
    public HttpRequest filter(final HttpRequest request) throws HttpException {
        if (creds.authType() == AuthenticationType.Anonymous) {
            return request;
        } else {
            final String authHeader = creds.authType() + " " + creds.authValue();
            return request.toBuilder()
                    .addHeader(HttpHeaders.AUTHORIZATION, authHeader)
                    .addHeader(CRUMB_HEADER, getCrumbValue())
                    .build();
        }
    }

    private String getCrumbValue() {
        String crumbValueInit = JenkinsAuthenticationFilter.crumbValue;
        if (crumbValueInit == null) {
            synchronized(this) {
                crumbValueInit = JenkinsAuthenticationFilter.crumbValue;
                if (crumbValueInit == null) {
                    JenkinsAuthenticationFilter.crumbValue = crumbValueInit = jenkinsApi.crumbIssuerApi().crumb().split(":")[1];
                }
            }
        }
        return crumbValueInit;
    }
}

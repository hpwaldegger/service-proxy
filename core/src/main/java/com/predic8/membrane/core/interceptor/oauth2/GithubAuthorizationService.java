/*
 * Copyright 2016 predic8 GmbH, www.predic8.com
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.predic8.membrane.core.interceptor.oauth2;

import com.predic8.membrane.annot.MCAttribute;
import com.predic8.membrane.annot.MCElement;
import com.predic8.membrane.core.Constants;
import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.http.Header;
import com.predic8.membrane.core.http.Request;
import com.predic8.membrane.core.http.Response;
import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.core.interceptor.authentication.session.SessionManager;
import com.predic8.membrane.core.util.Util;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashMap;
import java.util.Map;

@MCElement(name="github", topLevel=false)
public class GithubAuthorizationService extends AuthorizationService {
    @Override
    protected void init() {
    }

    @Override
    public String getLoginURL(String securityToken, String publicURL, String pathQuery) {
        return "https://github.com/login/oauth/authorize?"+
                "client_id=" + getClientId() + "&"+
                "response_type=code&"+
                "scope=openid%20email%20profile&"+
                "redirect_uri=" + publicURL + "oauth2callback&"+
                "state=security_token%3D" + securityToken + "%26url%3D" + pathQuery
                //+"&login_hint=jsmith@example.com"
                ;
    }

    @Override
    protected String getUserInfoEndpoint() {
        return "https://api.github.com/user";
    }

    @Override
    protected String getUserIDProperty() {
        return "login";
    }

    @Override
    protected String getTokenEndpoint() {
        return "https://github.com/login/oauth/access_token";
    }
}
package com.org.peysen.bootshiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/17 14:12
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException{
        Session session = null;
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest servletRequest =null;
        if (sessionKey instanceof WebSessionKey){
            servletRequest = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (servletRequest != null && sessionId != null){
            session = (Session) servletRequest.getAttribute(sessionId.toString());

            if(session != null){
                return session;
            }
        }

        session = super.retrieveSession(sessionKey);

        if (servletRequest != null && sessionId !=null){
            if(session != null ){
                servletRequest.setAttribute(sessionId.toString(),session);
            }
        }

        return session;
    }
}

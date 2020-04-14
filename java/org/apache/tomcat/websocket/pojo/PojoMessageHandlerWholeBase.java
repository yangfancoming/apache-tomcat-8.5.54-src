
package org.apache.tomcat.websocket.pojo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.websocket.DecodeException;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.apache.tomcat.websocket.WsSession;

/**
 * Common implementation code for the POJO whole message handlers. All the real
 * work is done in this class and in the superclass.
 *
 * @param <T>   The type of message to handle
 */
public abstract class PojoMessageHandlerWholeBase<T>
        extends PojoMessageHandlerBase<T> implements MessageHandler.Whole<T> {

    public PojoMessageHandlerWholeBase(Object pojo, Method method,
            Session session, Object[] params, int indexPayload,
            boolean convert, int indexSession, long maxMessageSize) {
        super(pojo, method, session, params, indexPayload, convert,
                indexSession, maxMessageSize);
    }


    @Override
    public final void onMessage(T message) {

        if (params.length == 1 && params[0] instanceof DecodeException) {
            ((WsSession) session).getLocal().onError(session,
                    (DecodeException) params[0]);
            return;
        }

        // Can this message be decoded?
        Object payload;
        try {
            payload = decode(message);
        } catch (DecodeException de) {
            ((WsSession) session).getLocal().onError(session, de);
            return;
        }

        if (payload == null) {
            // Not decoded. Convert if required.
            if (convert) {
                payload = convert(message);
            } else {
                payload = message;
            }
        }

        Object[] parameters = params.clone();
        if (indexSession != -1) {
            parameters[indexSession] = session;
        }
        parameters[indexPayload] = payload;

        Object result = null;
        try {
            result = method.invoke(pojo, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            handlePojoMethodException(e);
        }
        processResult(result);
    }

    protected Object convert(T message) {
        return message;
    }


    protected abstract Object decode(T message) throws DecodeException;
    protected abstract void onClose();
}

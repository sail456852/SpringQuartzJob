package spring.springshiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/26<br/>
 * Time: 11:15<br/>
 * To change this template use File | Settings | File Templates.
 */
public class RedisSessionDao extends AbstractSessionDAO {

    private static final int SESSION_TIME_OUT = 7200;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        System.err.println("RedisSessionDao.doCreate");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(sessionId.toString(), session, SESSION_TIME_OUT, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.err.println("RedisSessionDao.doReadSession");
        Session session = null;
        try {
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            session = (Session) ops.get(sessionId.toString());
        } catch (Exception e) {
            System.err.println("e = " + e);
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        System.err.println("RedisSessionDao.update");
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(session.getId().toString(), session, SESSION_TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
        System.err.println("RedisSessionDao.delete");
        redisTemplate.delete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        System.err.println("RedisSessionDao.getActiveSessions");
        return Collections.emptySet();
    }
}

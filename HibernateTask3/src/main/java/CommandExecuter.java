
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;

import com.mustafa.trial.bags.XBag;
import com.mustafa.trial.entity.Commands;

public class CommandExecuter {
		
	public Object execute(String CommandName,Session session,XBag inbag) {
		List<?> commands = session.createQuery("from Commands").list();
        for(Object acommand: commands) {
        	Commands acomm = (Commands) acommand;
        	
        	if(acomm.getCommandName().equals(CommandName)) {
        			Method method;
					try {
						Class<?> clazz = Class.forName(acomm.getClassName());
						Constructor<?> ctor = clazz.getConstructor();
						Object object = ctor.newInstance();
						method = clazz.getDeclaredMethod(acomm.getMethodName(),Session.class,XBag.class);
						method.setAccessible(true);
						return method.invoke(object, session,inbag);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
        	}
        }
		return null;
	}
}

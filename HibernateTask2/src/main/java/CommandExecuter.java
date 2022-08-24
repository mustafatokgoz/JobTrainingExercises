
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;

import com.mustafa.trial.entity.Commands;

public class CommandExecuter {
		
	public boolean execute(String CommandName,Session session,Object obj) {
		List<?> commands = session.createQuery("from Commands").list();
        for(Object acommand: commands) {
        	Commands acomm = (Commands) acommand;
        	
        	if(acomm.getCommandName().equals(CommandName)) {
					Method method1;
					try {
						method1 = obj.getClass().getSuperclass().getDeclaredMethod(acomm.getMethodName(),Session.class);
						method1.setAccessible(true);
						method1.invoke(obj, session);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return true;
        	}
        }
		return false;
	}
}

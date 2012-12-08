package cours.ulaval.glo4003.aspect;

import java.security.Principal;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import cours.ulaval.glo4003.domain.User;

@Aspect
public class LoggerAspect {

	private static Logger logger = Logger.getLogger("ConnexionLog");

	public LoggerAspect() {

		FileHandler fh = null;
		try {
			fh = new FileHandler("Connexions.log", true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before("execution( * cours.ulaval.glo4003.controller.LoginController.menu(..))")
	public void onConnect(JoinPoint pjp) throws Exception {
		String idul = ((Principal) pjp.getArgs()[1]).getName();
		log("Connexion de l'utilisateur " + idul);

	}

	@Before("execution(* cours.ulaval.glo4003.controller.LoginController.logout(..))")
	private void onDisconnect(JoinPoint pjp) throws Exception {
		User user = (User) ((HttpServletRequest) pjp.getArgs()[1]).getSession().getAttribute("user");
		String idul = user.getIdul();
		log("Déconnexion de l'utilisateur " + idul);
	}

	private void log(String message) {
		logger.info(message);
	}
}

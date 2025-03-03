package se.narstrom.myr.langmodel.test;

import org.jboss.cdi.lang.model.tck.EnumMembers;
import org.jboss.cdi.lang.model.tck.LangModelVerifier;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;

public class LangModelTCKLauncherTest {
	public static void main(String[] args) {
		final ClassInfo info = new ClassInfoImpl(EnumMembers.class);
		for (final MethodInfo method : info.constructors()) {
			System.out.println(method.parameters().get(0).type().getClass());
		}

		// System.exit(0);

		LangModelVerifier.verify(new ClassInfoImpl(LangModelVerifier.class));
	}
}

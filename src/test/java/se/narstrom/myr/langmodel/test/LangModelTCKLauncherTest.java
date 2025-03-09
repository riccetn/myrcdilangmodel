package se.narstrom.myr.langmodel.test;

import org.jboss.cdi.lang.model.tck.LangModelVerifier;

import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;

public class LangModelTCKLauncherTest {
	public static void main(String[] args) {
		LangModelVerifier.verify(new ClassInfoImpl(LangModelVerifier.class));
	}
}

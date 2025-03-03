package se.narstrom.myr.langmodel.types;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.ClassType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;

public final class ClassTypeImpl extends AnnotationTargetImpl implements ClassType {
	private final Class<?> reflectionInstance;

	public ClassTypeImpl(final Class<?> reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public ClassInfo declaration() {
		return new ClassInfoImpl(reflectionInstance);
	}
}

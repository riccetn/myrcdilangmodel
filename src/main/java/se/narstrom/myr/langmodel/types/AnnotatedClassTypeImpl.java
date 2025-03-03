package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedType;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.ClassType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;

public final class AnnotatedClassTypeImpl extends AnnotationTargetImpl implements ClassType {
	private AnnotatedType reflectiveInstance;

	public AnnotatedClassTypeImpl(final AnnotatedType reflectionInstance) {
		super(reflectionInstance);
		this.reflectiveInstance = reflectionInstance;
	}

	@Override
	public ClassInfo declaration() {
		return new ClassInfoImpl((Class<?>) reflectiveInstance.getType());
	}
}

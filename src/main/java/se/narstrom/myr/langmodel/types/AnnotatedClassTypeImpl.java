package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedType;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.ClassType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;

public final class AnnotatedClassTypeImpl extends AnnotationTargetImpl<AnnotatedType> implements ClassType {
	public AnnotatedClassTypeImpl(final AnnotatedType reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public ClassInfo declaration() {
		return new ClassInfoImpl((Class<?>) reflectionInstance.getType());
	}
}

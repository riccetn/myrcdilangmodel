package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedType;

import jakarta.enterprise.lang.model.types.PrimitiveType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class PrimitiveTypeImpl extends AnnotationTargetImpl<AnnotatedType> implements PrimitiveType {
	public PrimitiveTypeImpl(final AnnotatedType reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public String name() {
		return reflectionInstance.getType().getTypeName();
	}

	@Override
	public PrimitiveKind primitiveKind() {
		final Class<?> reflectionClazz = (Class<?>) reflectionInstance.getType();
		if (reflectionClazz == Boolean.TYPE)
			return PrimitiveKind.BOOLEAN;
		if (reflectionClazz == Byte.TYPE)
			return PrimitiveKind.BYTE;
		if (reflectionClazz == Short.TYPE)
			return PrimitiveKind.SHORT;
		if (reflectionClazz == Integer.TYPE)
			return PrimitiveKind.INT;
		if (reflectionClazz == Long.TYPE)
			return PrimitiveKind.LONG;
		if (reflectionClazz == Float.TYPE)
			return PrimitiveKind.FLOAT;
		if (reflectionClazz == Double.TYPE)
			return PrimitiveKind.DOUBLE;
		if (reflectionClazz == Character.TYPE)
			return PrimitiveKind.CHAR;
		throw new RuntimeException("Not a primitive");
	}
}

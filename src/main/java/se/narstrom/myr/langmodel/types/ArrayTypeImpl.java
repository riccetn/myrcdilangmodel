package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedArrayType;

import jakarta.enterprise.lang.model.types.ArrayType;
import jakarta.enterprise.lang.model.types.Type;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class ArrayTypeImpl extends AnnotationTargetImpl<AnnotatedArrayType> implements ArrayType {
	public ArrayTypeImpl(final AnnotatedArrayType reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public Type componentType() {
		return Helper.convertReflectionTypeToCdiLangModelType(reflectionInstance.getAnnotatedGenericComponentType());
	}

}

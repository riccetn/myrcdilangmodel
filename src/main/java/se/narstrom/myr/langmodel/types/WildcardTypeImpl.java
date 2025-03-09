package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedWildcardType;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.WildcardType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class WildcardTypeImpl extends AnnotationTargetImpl<AnnotatedWildcardType> implements WildcardType {
	public WildcardTypeImpl(final AnnotatedWildcardType reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public Type upperBound() {
		final AnnotatedType[] reflectionBounds = reflectionInstance.getAnnotatedUpperBounds();

		if (isUnannotatedObject(reflectionBounds[0]) && reflectionInstance.getAnnotatedLowerBounds().length != 0)
			return null;

		return Helper.convertReflectionTypeToCdiLangModelType(reflectionBounds[0]);
	}

	@Override
	public Type lowerBound() {
		final AnnotatedType[] reflectionBounds = reflectionInstance.getAnnotatedLowerBounds();

		if (reflectionBounds.length == 0)
			return null;

		return Helper.convertReflectionTypeToCdiLangModelType(reflectionBounds[0]);
	}

	private static boolean isUnannotatedObject(final AnnotatedType type) {
		return type.getAnnotations().length == 0 && type.getType() == Object.class;
	}
}

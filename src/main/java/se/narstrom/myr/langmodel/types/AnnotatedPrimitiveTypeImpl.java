package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedType;

import jakarta.enterprise.lang.model.types.PrimitiveType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class AnnotatedPrimitiveTypeImpl extends AnnotationTargetImpl implements PrimitiveType {
	private AnnotatedType reflectionInstance;

	protected AnnotatedPrimitiveTypeImpl(final AnnotatedType reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public String name() {
		return reflectionInstance.getType().getTypeName();
	}

	@Override
	public PrimitiveKind primitiveKind() {
		return Helper.convertReflectionClassToCdiLangPrimitiveKind((Class<?>) reflectionInstance.getType());
	}

}

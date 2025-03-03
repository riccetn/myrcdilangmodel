package se.narstrom.myr.langmodel.types;

import jakarta.enterprise.lang.model.types.PrimitiveType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class PrimitiveTypeImpl extends AnnotationTargetImpl implements PrimitiveType {
	private final Class<?> reflectionInstance;

	public PrimitiveTypeImpl(final Class<?> reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public String name() {
		return primitiveKind().name().toLowerCase();
	}

	@Override
	public PrimitiveKind primitiveKind() {
		return Helper.convertReflectionClassToCdiLangPrimitiveKind(reflectionInstance);
	}

}

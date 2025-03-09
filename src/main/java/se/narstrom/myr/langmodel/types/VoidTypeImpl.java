package se.narstrom.myr.langmodel.types;

import jakarta.enterprise.lang.model.types.VoidType;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class VoidTypeImpl extends AnnotationTargetImpl<Class<?>> implements VoidType {
	public VoidTypeImpl() {
		super(Void.TYPE);
	}

	@Override
	public String name() {
		return "void";
	}
}

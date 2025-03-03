package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class AnnotatedTypeVariableImpl extends AnnotationTargetImpl implements TypeVariable {
	private AnnotatedTypeVariable reflectionInstance;

	protected AnnotatedTypeVariableImpl(final AnnotatedTypeVariable reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public String name() {
		return reflectionInstance.getType().getTypeName();
	}

	@Override
	public List<Type> bounds() {
		final AnnotatedType[] reflectionBounds = reflectionInstance.getAnnotatedBounds();

		final List<Type> bounds = new ArrayList<>(reflectionBounds.length);
		for (final AnnotatedType reflectionBound : reflectionBounds)
			bounds.add(Helper.convertReflectionTypeToCdiLangModelType(reflectionBound));

		return Collections.unmodifiableList(bounds);
	}
}

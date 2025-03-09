package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.lang.model.types.ClassType;
import jakarta.enterprise.lang.model.types.ParameterizedType;
import jakarta.enterprise.lang.model.types.Type;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class ParameterizedTypeImpl extends AnnotationTargetImpl<AnnotatedParameterizedType> implements ParameterizedType {
	public ParameterizedTypeImpl(final AnnotatedParameterizedType reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public ClassType genericClass() {
		return new ClassTypeImpl(
				(Class<?>) ((java.lang.reflect.ParameterizedType) reflectionInstance.getType()).getRawType());
	}

	@Override
	public List<Type> typeArguments() {
		final AnnotatedType[] reflectionArguments = reflectionInstance.getAnnotatedActualTypeArguments();

		final List<Type> arguments = new ArrayList<>(reflectionArguments.length);
		for (final AnnotatedType reflectionArgument : reflectionArguments)
			arguments.add(Helper.convertReflectionTypeToCdiLangModelType(reflectionArgument));

		return Collections.unmodifiableList(arguments);
	}
}

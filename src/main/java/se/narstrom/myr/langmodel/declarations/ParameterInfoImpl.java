package se.narstrom.myr.langmodel.declarations;

import java.lang.reflect.Parameter;

import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;
import jakarta.enterprise.lang.model.types.Type;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.types.Helper;

public final class ParameterInfoImpl extends AnnotationTargetImpl implements ParameterInfo {
	private final Parameter reflectionInstance;

	public ParameterInfoImpl(final Parameter reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public String name() {
		return reflectionInstance.getName();
	}

	@Override
	public Type type() {
		return Helper.convertReflectionTypeToCdiLangModelType(reflectionInstance.getAnnotatedType());
	}

	@Override
	public MethodInfo declaringMethod() {
		return new MethodInfoImpl(reflectionInstance.getDeclaringExecutable());
	}

}

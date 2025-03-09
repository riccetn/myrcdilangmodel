package se.narstrom.myr.langmodel.declarations;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;
import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.types.Helper;
import se.narstrom.myr.langmodel.types.TypeVariableImpl;

public final class MethodInfoImpl extends AnnotationTargetImpl<Executable> implements MethodInfo {
	public MethodInfoImpl(final Executable reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public ClassInfo declaringClass() {
		return new ClassInfoImpl(reflectionInstance.getDeclaringClass());
	}

	@Override
	public boolean isAbstract() {
		return reflectionInstance.accessFlags().contains(AccessFlag.ABSTRACT);
	}

	@Override
	public boolean isConstructor() {
		return reflectionInstance instanceof Constructor<?>;
	}

	@Override
	public boolean isFinal() {
		return reflectionInstance.accessFlags().contains(AccessFlag.FINAL);
	}

	@Override
	public boolean isStatic() {
		return reflectionInstance.accessFlags().contains(AccessFlag.STATIC);
	}

	@Override
	public int modifiers() {
		return reflectionInstance.getModifiers();
	}

	@Override
	public String name() {
		return reflectionInstance.getName();
	}

	@Override
	public List<ParameterInfo> parameters() {
		final Parameter[] parameters = reflectionInstance.getParameters();

		final List<ParameterInfo> infos = new ArrayList<>(parameters.length);
		for (final Parameter parameter : parameters) {
			if (!parameter.isSynthetic())
				infos.add(new ParameterInfoImpl(parameter));
		}

		return Collections.unmodifiableList(infos);
	}

	@Override
	public Type receiverType() {
		return Helper.convertReflectionTypeToCdiLangModelType(reflectionInstance.getAnnotatedReceiverType());
	}

	@Override
	public Type returnType() {
		return Helper.convertReflectionTypeToCdiLangModelType(reflectionInstance.getAnnotatedReturnType());
	}

	@Override
	public List<Type> throwsTypes() {
		return Helper.convertReflectionTypesToCdiLangModelTypes(reflectionInstance.getAnnotatedExceptionTypes());
	}

	@Override
	public List<TypeVariable> typeParameters() {
		final java.lang.reflect.TypeVariable<?>[] reflectionParameters = reflectionInstance.getTypeParameters();

		final List<TypeVariable> parameters = new ArrayList<>();
		for (final java.lang.reflect.TypeVariable<?> reflectionParameter : reflectionParameters) {
			parameters.add(new TypeVariableImpl(reflectionParameter));
		}

		return parameters;
	}

}

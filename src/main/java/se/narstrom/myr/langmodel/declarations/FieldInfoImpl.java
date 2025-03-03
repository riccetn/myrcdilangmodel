package se.narstrom.myr.langmodel.declarations;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.FieldInfo;
import jakarta.enterprise.lang.model.types.Type;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.types.Helper;

public final class FieldInfoImpl extends AnnotationTargetImpl implements FieldInfo {
	private final Field reflectionInstance;

	protected FieldInfoImpl(final Field reflectionInstance) {
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
	public boolean isStatic() {
		return reflectionInstance.accessFlags().contains(AccessFlag.STATIC);
	}

	@Override
	public boolean isFinal() {
		return reflectionInstance.accessFlags().contains(AccessFlag.FINAL);
	}

	@Override
	public int modifiers() {
		return reflectionInstance.getModifiers();
	}

	@Override
	public ClassInfo declaringClass() {
		return new ClassInfoImpl(reflectionInstance.getDeclaringClass());
	}

}

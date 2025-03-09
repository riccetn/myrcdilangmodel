package se.narstrom.myr.langmodel.declarations;

import java.lang.reflect.RecordComponent;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.FieldInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.RecordComponentInfo;
import jakarta.enterprise.lang.model.types.Type;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.types.Helper;

public final class RecordComponentInfoImpl extends AnnotationTargetImpl<RecordComponent> implements RecordComponentInfo {
	public RecordComponentInfoImpl(final RecordComponent reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public MethodInfo accessor() {
		return new MethodInfoImpl(reflectionInstance.getAccessor());
	}

	@Override
	public ClassInfo declaringRecord() {
		return new ClassInfoImpl(reflectionInstance.getDeclaringRecord());
	}

	@Override
	public FieldInfo field() {
		try {
			return new FieldInfoImpl(
					reflectionInstance.getDeclaringRecord().getDeclaredField(reflectionInstance.getName()));
		} catch (final ReflectiveOperationException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public String name() {
		return reflectionInstance.getName();
	}

	@Override
	public Type type() {
		return Helper.convertReflectionTypeToCdiLangModelType(reflectionInstance.getAnnotatedType());
	}
}

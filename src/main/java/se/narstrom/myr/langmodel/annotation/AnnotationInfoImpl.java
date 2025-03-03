package se.narstrom.myr.langmodel.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.AnnotationMember;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;

public final class AnnotationInfoImpl implements AnnotationInfo {
	private final Annotation reflectionType;

	public AnnotationInfoImpl(final Annotation reflectionType) {
		this.reflectionType = reflectionType;
	}

	@Override
	public ClassInfo declaration() {
		return new ClassInfoImpl(reflectionType.annotationType());
	}

	@Override
	public boolean hasMember(final String name) {
		for (final Method method : reflectionType.getClass().getDeclaredMethods()) {
			if (name.equals(method.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AnnotationMember member(final String name) {
		try {
			for (final Method method : reflectionType.getClass().getDeclaredMethods()) {
				if (name.equals(method.getName())) {
					return new AnnotationMemberImpl(method.invoke(reflectionType));
				}
			}
			return null;
		} catch (final ReflectiveOperationException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Map<String, AnnotationMember> members() {
		try {
			final Map<String, AnnotationMember> map = new HashMap<>();
			for (final Method method : reflectionType.getClass().getDeclaredMethods()) {
				map.put(method.getName(), new AnnotationMemberImpl(method.invoke(reflectionType)));
			}
			return map;
		} catch (final ReflectiveOperationException ex) {
			throw new RuntimeException(ex);
		}
	}
}

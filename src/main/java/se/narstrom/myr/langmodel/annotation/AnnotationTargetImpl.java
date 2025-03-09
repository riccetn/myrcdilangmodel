package se.narstrom.myr.langmodel.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.AnnotationTarget;

public abstract class AnnotationTargetImpl<E extends AnnotatedElement> implements AnnotationTarget {
	protected final E reflectionInstance;

	protected AnnotationTargetImpl(final E reflectionInstance) {
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public <T extends Annotation> AnnotationInfo annotation(final Class<T> annotationType) {
		final T annotation = reflectionInstance.getAnnotation(annotationType);
		if (annotation == null)
			return null;
		return new AnnotationInfoImpl(annotation);
	}

	@Override
	public Collection<AnnotationInfo> annotations() {
		final Annotation[] annotations = reflectionInstance.getAnnotations();
		final Collection<AnnotationInfo> infos = new ArrayList<>(annotations.length);
		for (final Annotation annotation : annotations) {
			infos.add(new AnnotationInfoImpl(annotation));
		}
		return infos;
	}

	@Override
	public Collection<AnnotationInfo> annotations(final Predicate<AnnotationInfo> predicate) {
		final Collection<AnnotationInfo> infos = new ArrayList<>();
		for (final Annotation annotation : reflectionInstance.getAnnotations()) {
			final AnnotationInfo info = new AnnotationInfoImpl(annotation);
			if (predicate.test(info))
				infos.add(info);
		}
		return infos;
	}

	@Override
	public boolean hasAnnotation(final Class<? extends Annotation> annotationType) {
		return reflectionInstance.isAnnotationPresent(annotationType);
	}

	@Override
	public boolean hasAnnotation(final Predicate<AnnotationInfo> predicate) {
		for (final Annotation annotation : reflectionInstance.getAnnotations()) {
			if (predicate.test(new AnnotationInfoImpl(annotation)))
				return true;
		}
		return false;
	}

	@Override
	public <T extends Annotation> Collection<AnnotationInfo> repeatableAnnotation(final Class<T> annotationType) {
		final T[] annotations = reflectionInstance.getAnnotationsByType(annotationType);
		final Collection<AnnotationInfo> infos = new ArrayList<>(annotations.length);
		for (final T annotation : annotations) {
			infos.add(new AnnotationInfoImpl(annotation));
		}
		return infos;
	}

	@Override
	public int hashCode() {
		return reflectionInstance.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof AnnotationTargetImpl type)
			return this.reflectionInstance.equals(type.reflectionInstance);
		else
			return false;
	}

}

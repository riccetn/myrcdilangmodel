package se.narstrom.myr.langmodel.types;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.AnnotatedWildcardType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.PrimitiveType;
import jakarta.enterprise.lang.model.types.PrimitiveType.PrimitiveKind;

public final class Helper {
	private Helper() {
	}

	public static List<Type> convertReflectionTypesToCdiLangModelTypes(final AnnotatedType[] reflectionTypes) {
		final List<Type> types = new ArrayList<>();
		for (final AnnotatedType reflectionType : reflectionTypes)
			types.add(convertReflectionTypeToCdiLangModelType(reflectionType));
		return Collections.unmodifiableList(types);
	}

	public static Type convertReflectionTypeToCdiLangModelType(final AnnotatedType reflectionType) {
		if (reflectionType == null)
			return null;
		if (reflectionType instanceof AnnotatedArrayType reflectionArrayType)
			return new ArrayTypeImpl(reflectionArrayType);
		if (reflectionType instanceof AnnotatedParameterizedType reflectionParameterizedType)
			return new ParameterizedTypeImpl(reflectionParameterizedType);
		if (reflectionType instanceof AnnotatedTypeVariable reflectionTypeVariable)
			return new AnnotatedTypeVariableImpl(reflectionTypeVariable);
		if (reflectionType instanceof AnnotatedWildcardType reflectionWildcardType)
			return new WildcardTypeImpl(reflectionWildcardType);
		if (reflectionType.getType() == Void.TYPE)
			return new VoidTypeImpl();
		if (reflectionType.getType() instanceof Class<?> clazz && clazz.isPrimitive())
			return new AnnotatedPrimitiveTypeImpl(reflectionType);
		// System.out.println(reflectionType.getType());
		return new AnnotatedClassTypeImpl(reflectionType);
	}

	public static PrimitiveType.PrimitiveKind convertReflectionClassToCdiLangPrimitiveKind(
			final Class<?> reflectionClazz) {
		if (reflectionClazz == Boolean.TYPE)
			return PrimitiveKind.BOOLEAN;
		if (reflectionClazz == Byte.TYPE)
			return PrimitiveKind.BYTE;
		if (reflectionClazz == Short.TYPE)
			return PrimitiveKind.SHORT;
		if (reflectionClazz == Integer.TYPE)
			return PrimitiveKind.INT;
		if (reflectionClazz == Long.TYPE)
			return PrimitiveKind.LONG;
		if (reflectionClazz == Float.TYPE)
			return PrimitiveKind.FLOAT;
		if (reflectionClazz == Double.TYPE)
			return PrimitiveKind.DOUBLE;
		if (reflectionClazz == Character.TYPE)
			return PrimitiveKind.CHAR;
		throw new RuntimeException("Not a primitive");
	}
}

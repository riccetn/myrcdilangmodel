package se.narstrom.myr.langmodel.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.AnnotationMember;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;
import se.narstrom.myr.langmodel.declarations.ClassInfoImpl;
import se.narstrom.myr.langmodel.types.ClassTypeImpl;

public final class AnnotationMemberImpl implements AnnotationMember {
	private final Object member;

	public AnnotationMemberImpl(final Object member) {
		this.member = member;
	}

	@Override
	public List<AnnotationMember> asArray() {
		final int length = Array.getLength(member);

		final List<AnnotationMember> ret = new ArrayList<>(length);
		for (int i = 0; i < length; ++i)
			ret.add(new AnnotationMemberImpl(Array.get(member, i)));

		return ret;
	}

	@Override
	public boolean asBoolean() {
		return (boolean) member;
	}

	@Override
	public byte asByte() {
		return (byte) member;
	}

	@Override
	public char asChar() {
		return (char) member;
	}

	@Override
	public double asDouble() {
		return (double) member;
	}

	@Override
	public <E extends Enum<E>> E asEnum(final Class<E> enumType) {
		return enumType.cast(member);
	}

	@Override
	public ClassInfo asEnumClass() {
		return new ClassInfoImpl(member.getClass());
	}

	@Override
	public String asEnumConstant() {
		return ((Enum<?>) member).name();
	}

	@Override
	public float asFloat() {
		return (float) member;
	}

	@Override
	public int asInt() {
		return (int) member;
	}

	@Override
	public long asLong() {
		return (long) member;
	}

	@Override
	public AnnotationInfo asNestedAnnotation() {
		return new AnnotationInfoImpl((Annotation) member);
	}

	@Override
	public short asShort() {
		return (short) member;
	}

	@Override
	public String asString() {
		return (String) member;
	}

	@Override
	public Type asType() {
		return new ClassTypeImpl((Class<?>) member);
	}

	@Override
	public Kind kind() {
		final Class<?> type = member.getClass();
		if (type == Boolean.class)
			return Kind.BOOLEAN;
		if (type == Byte.class)
			return Kind.BYTE;
		if (type == Short.class)
			return Kind.SHORT;
		if (type == Integer.class)
			return Kind.INT;
		if (type == Long.class)
			return Kind.LONG;
		if (type == Float.class)
			return Kind.FLOAT;
		if (type == Double.class)
			return Kind.DOUBLE;
		if (type == Character.class)
			return Kind.CHAR;
		if (type == String.class)
			return Kind.STRING;
		if (type.isEnum())
			return Kind.ENUM;
		if (type == Class.class)
			return Kind.CLASS;
		if (Annotation.class.isAssignableFrom(type))
			return Kind.NESTED_ANNOTATION;
		if (type.isArray())
			return Kind.ARRAY;
		throw new RuntimeException("Invalid type: " + type.getName());
	}

}

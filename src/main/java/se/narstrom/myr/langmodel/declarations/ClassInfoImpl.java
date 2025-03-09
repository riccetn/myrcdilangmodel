package se.narstrom.myr.langmodel.declarations;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.FieldInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.PackageInfo;
import jakarta.enterprise.lang.model.declarations.RecordComponentInfo;
import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;
import se.narstrom.myr.langmodel.types.Helper;
import se.narstrom.myr.langmodel.types.TypeVariableImpl;

public final class ClassInfoImpl extends AnnotationTargetImpl implements ClassInfo {
	private final Class<?> reflectionInstance;

	public ClassInfoImpl(final Class<?> reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public Collection<MethodInfo> constructors() {
		final Constructor<?>[] constructors = reflectionInstance.getDeclaredConstructors();

		final Collection<MethodInfo> infos = new ArrayList<>(constructors.length);

		for (final Constructor<?> constructor : constructors) {
			if (constructor.isSynthetic())
				System.out.println("Skipping syntetic constructor " + constructor);
			if (!constructor.isSynthetic())
				infos.add(new MethodInfoImpl(constructor));
		}

		return Collections.unmodifiableCollection(infos);
	}

	@Override
	public Collection<FieldInfo> fields() {
		if (reflectionInstance == Object.class)
			return List.of();

		final Collection<FieldInfo> infos = new ArrayList<>();

		final ClassInfo superInfo = superClassDeclaration();
		if (superInfo != null)
			infos.addAll(superInfo.fields());

		for (final ClassInfo superInterfaceInfo : superInterfacesDeclarations())
			infos.addAll(superInterfaceInfo.fields());

		for (final Field field : reflectionInstance.getDeclaredFields())
			infos.add(new FieldInfoImpl(field));

		return Collections.unmodifiableCollection(infos);
	}

	@Override
	public boolean isAbstract() {
		return reflectionInstance.accessFlags().contains(AccessFlag.ABSTRACT);
	}

	@Override
	public boolean isAnnotation() {
		return reflectionInstance.isAnnotation();
	}

	@Override
	public boolean isEnum() {
		return reflectionInstance.isEnum();
	}

	@Override
	public boolean isFinal() {
		return reflectionInstance.accessFlags().contains(AccessFlag.FINAL);
	}

	@Override
	public boolean isInterface() {
		return reflectionInstance.isInterface() && !reflectionInstance.isAnnotation();
	}

	@Override
	public boolean isPlainClass() {
		return !isInterface() && !isEnum() && !isAnnotation() && !isRecord();
	}

	@Override
	public boolean isRecord() {
		return reflectionInstance.isRecord();
	}

	@Override
	public Collection<MethodInfo> methods() {
		if (reflectionInstance == Object.class)
			return List.of();

		final Collection<MethodInfo> infos = new ArrayList<>();

		final ClassInfo superInfo = superClassDeclaration();
		if (superInfo != null)
			infos.addAll(superInfo.methods());

		for (final ClassInfo superInterfaceInfo : superInterfacesDeclarations())
			infos.addAll(superInterfaceInfo.methods());

		for (final Method method : reflectionInstance.getDeclaredMethods())
			if (!method.isSynthetic())
				infos.add(new MethodInfoImpl(method));

		return Collections.unmodifiableCollection(infos);
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
	public PackageInfo packageInfo() {
		return new PackageInfoImpl(reflectionInstance.getPackage());
	}

	@Override
	public Collection<RecordComponentInfo> recordComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String simpleName() {
		return reflectionInstance.getSimpleName();
	}

	@Override
	public Type superClass() {
		return Helper.convertReflectionTypeToCdiLangModelType(reflectionInstance.getAnnotatedSuperclass());
	}

	@Override
	public ClassInfo superClassDeclaration() {
		final Class<?> reflectionSuper = reflectionInstance.getSuperclass();
		if (reflectionSuper == null)
			return null;
		return new ClassInfoImpl(reflectionSuper);
	}

	@Override
	public List<Type> superInterfaces() {
		final AnnotatedType[] reflectionInterfaces = reflectionInstance.getAnnotatedInterfaces();

		final List<Type> interfaces = new ArrayList<>(reflectionInterfaces.length);
		for (final AnnotatedType reflectionInterface : reflectionInterfaces) {
			interfaces.add(Helper.convertReflectionTypeToCdiLangModelType(reflectionInterface));
		}

		return interfaces;
	}

	@Override
	public List<ClassInfo> superInterfacesDeclarations() {
		final Class<?>[] interfaces = reflectionInstance.getInterfaces();
		final List<ClassInfo> infos = new ArrayList<>(interfaces.length);
		for (final Class<?> iface : interfaces) {
			infos.add(new ClassInfoImpl(iface));
		}
		return Collections.unmodifiableList(infos);
	}

	@Override
	public List<TypeVariable> typeParameters() {
		final java.lang.reflect.TypeVariable<?>[] reflectionTypeParameters = reflectionInstance.getTypeParameters();

		final List<TypeVariable> typeParameters = new ArrayList<>();
		for (final java.lang.reflect.TypeVariable<?> reflectionTypeParameter : reflectionTypeParameters) {
			typeParameters.add(new TypeVariableImpl(reflectionTypeParameter));
		}

		return Collections.unmodifiableList(typeParameters);
	}

}

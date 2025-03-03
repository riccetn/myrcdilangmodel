package se.narstrom.myr.langmodel.declarations;

import jakarta.enterprise.lang.model.declarations.PackageInfo;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class PackageInfoImpl extends AnnotationTargetImpl implements PackageInfo {
	private final Package reflectionInstance;

	public PackageInfoImpl(final Package reflectionInstance) {
		super(reflectionInstance);
		this.reflectionInstance = reflectionInstance;
	}

	@Override
	public String name() {
		return reflectionInstance.getName();
	}
}

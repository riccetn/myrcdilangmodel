package se.narstrom.myr.langmodel.declarations;

import jakarta.enterprise.lang.model.declarations.PackageInfo;
import se.narstrom.myr.langmodel.annotation.AnnotationTargetImpl;

public final class PackageInfoImpl extends AnnotationTargetImpl<Package> implements PackageInfo {
	public PackageInfoImpl(final Package reflectionInstance) {
		super(reflectionInstance);
	}

	@Override
	public String name() {
		return reflectionInstance.getName();
	}
}

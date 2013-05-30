package factories;

import java.nio.file.Path;

import models.ResourceFile;

public class ResourceFileFactory {
	private static Class<?> klass = ResourceFile.class;
	
	public static ResourceFile create(Path path) {
		return new ResourceFile(path.toFile(), UserProfileFactory.create().getPath());
	}
}

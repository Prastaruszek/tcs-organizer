package models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import factories.ResourceFileFactory;

public class ResourceFileTest {
	private static String tempPath = "src/test/ResourceTest";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Files.createDirectory(Paths.get(tempPath));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Files.deleteIfExists(Paths.get(tempPath));
	}
	
	@Test
	public void testCopyRemove() {
		Path filePath = Paths.get(tempPath + "/testFile");
		assertFalse(Files.exists(filePath));
		try {
			Files.createFile(filePath);
			assertTrue(Files.exists(filePath));
			
			ResourceFile resource = ResourceFileFactory.create(filePath);
			resource.copyToResourcesDirectory();
			assertTrue(Files.exists(filePath));
			assertTrue(Files.exists(Paths.get(resource.getPath())));
			
			resource.removeFromResourcesDirectory();
			assertTrue(Files.exists(filePath));
			assertFalse(Files.exists(Paths.get(resource.getPath())));
			
			Files.delete(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

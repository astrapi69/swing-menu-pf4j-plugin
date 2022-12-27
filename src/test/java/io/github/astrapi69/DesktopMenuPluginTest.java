package io.github.astrapi69;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.pf4j.DefaultExtensionFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFinder;
import org.pf4j.PluginManager;

import io.github.astrapi69.menu.pf4j.extension.DesktopMenuExtensionPoint;

public class DesktopMenuPluginTest
{

	@Test
	public void testPlugin()
	{
		// create the plugin manager
		final PluginManager pluginManager = new DefaultPluginManager()
		{

			protected ExtensionFinder createExtensionFinder()
			{
				DefaultExtensionFinder extensionFinder = (DefaultExtensionFinder)super.createExtensionFinder();
				extensionFinder.addServiceProviderExtensionFinder(); // to activate "HowdyGreeting"
																		// extension

				return extensionFinder;
			}

		};
		// start and load all plugins of application
		pluginManager.loadPlugins();
		pluginManager.startPlugins();

		// retrieve all extension points for "Greeting" extension point
		List<DesktopMenuExtensionPoint> extensionPoints = pluginManager
			.getExtensions(DesktopMenuExtensionPoint.class);
		for (DesktopMenuExtensionPoint extensionPoint : extensionPoints)
		{
			assertNotNull(extensionPoint);
		}
		Set<String> extensionClassNames = pluginManager.getExtensionClassNames(null);
		assertTrue(extensionClassNames.contains("org.pf4j.processor.ExtensionAnnotationProcessor"));
		assertTrue(extensionClassNames
			.contains("io.github.astrapi69.menu.pf4j.extension.DesktopMenuExtension"));

		// stop and unload all plugins
		pluginManager.stopPlugins();
		pluginManager.unloadPlugins();
	}


}
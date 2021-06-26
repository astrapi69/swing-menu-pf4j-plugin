package io.github.astrapi69;

import io.github.astrapi69.delete.DeleteFileExtensions;
import io.github.astrapi69.gradle.migration.data.CopyGradleRunConfigurations;
import io.github.astrapi69.gradle.migration.data.DependenciesInfo;
import io.github.astrapi69.gradle.migration.data.GradleRunConfigurationsCopier;
import io.github.astrapi69.io.file.filter.PrefixFileFilter;
import io.github.astrapi69.menu.pf4j.extension.DesktopMenuExtensionPoint;
import io.github.astrapi69.modify.ModifyFileExtensions;
import io.github.astrapi69.search.PathFinder;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.pf4j.DefaultPluginManager;
import org.pf4j.JarPluginManager;
import org.pf4j.PluginManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DesktopMenuPluginTest
{

	@Test
	public void testPlugin() throws IOException
	{
		// create the plugin manager
		PluginManager pluginManager = new DefaultPluginManager(); // or "new ZipPluginManager() / new DefaultPluginManager()"

		// start and load all plugins of application
		pluginManager.loadPlugins();
		pluginManager.startPlugins();

		// retrieve all extensions for "Greeting" extension point
		List<DesktopMenuExtensionPoint> greetings = pluginManager.getExtensions(DesktopMenuExtensionPoint.class);
		for (DesktopMenuExtensionPoint greeting : greetings) {
			System.out.println(">>> " + greeting);
		}

		// stop and unload all plugins
		pluginManager.stopPlugins();
		pluginManager.unloadPlugins();
	}


}

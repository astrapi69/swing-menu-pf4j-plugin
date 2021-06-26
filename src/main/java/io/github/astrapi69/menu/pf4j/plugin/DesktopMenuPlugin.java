package io.github.astrapi69.menu.pf4j.plugin;

import io.github.astrapi69.menu.pf4j.extension.DesktopMenuExtensionPoint;
import org.pf4j.DefaultExtensionFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFinder;
import org.pf4j.Plugin;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;

import java.util.List;
import java.util.Set;

public class DesktopMenuPlugin extends Plugin
{
	public DesktopMenuPlugin(PluginWrapper wrapper)
	{
		super(wrapper);
		// you can use "wrapper" to have access to the plugin context (plugin manager, descriptor, ...)
	}

	@Override public void start()
	{
		System.err.println("DesktopMenuPlugin.start()");
	}

	@Override public void stop()
	{
		System.err.println("DesktopMenuPlugin.stop()");
	}

	@Override public void delete()
	{
		System.err.println("DesktopMenuPlugin.delete()");
	}

	public static void main(String[] args) {
		// create the plugin manager
		// create the plugin manager
		final PluginManager pluginManager = new DefaultPluginManager() {

			protected ExtensionFinder createExtensionFinder() {
				DefaultExtensionFinder extensionFinder = (DefaultExtensionFinder) super.createExtensionFinder();
				extensionFinder.addServiceProviderExtensionFinder(); // to activate "HowdyGreeting" extension

				return extensionFinder;
			}

		};
		// start and load all plugins of application
		pluginManager.loadPlugins();
		pluginManager.startPlugins();

		// retrieve all extensions for "Greeting" extension point
		List<DesktopMenuExtensionPoint> extensions = pluginManager.getExtensions(DesktopMenuExtensionPoint.class);
		for (DesktopMenuExtensionPoint menuExtensionPoint : extensions) {
			System.out.println(">>> " + menuExtensionPoint);
		}
		Set<String> extensionClassNames = pluginManager.getExtensionClassNames(null);
		for (String extension : extensionClassNames) {
			System.out.println("   " + extension);
		}
		// stop and unload all plugins
		pluginManager.stopPlugins();
		pluginManager.unloadPlugins();
	}
}

/**
 * The MIT License
 *
 * Copyright (C) 2023 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.menu.pf4j.plugin;

import java.util.List;
import java.util.Set;

import org.pf4j.DefaultExtensionFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFinder;
import org.pf4j.Plugin;
import org.pf4j.PluginManager;

import io.github.astrapi69.menu.pf4j.extension.DesktopMenuExtensionPoint;

public class DesktopMenuPlugin extends Plugin
{
	public DesktopMenuPlugin()
	{
		// you can use "wrapper" to have access to the plugin context (plugin manager, descriptor,
		// ...)
	}

	public static void main(String[] args)
	{
		// create the plugin manager
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

		// retrieve all extensions for "Greeting" extension point
		List<DesktopMenuExtensionPoint> extensions = pluginManager
			.getExtensions(DesktopMenuExtensionPoint.class);
		for (DesktopMenuExtensionPoint menuExtensionPoint : extensions)
		{
			System.out.println(">>> " + menuExtensionPoint);
		}
		Set<String> extensionClassNames = pluginManager.getExtensionClassNames(null);
		for (String extension : extensionClassNames)
		{
			System.out.println("   " + extension);
		}
		// stop and unload all plugins
		pluginManager.stopPlugins();
		pluginManager.unloadPlugins();
	}

	@Override
	public void start()
	{
		System.err.println("DesktopMenuPlugin.start()");
	}

	@Override
	public void stop()
	{
		System.err.println("DesktopMenuPlugin.stop()");
	}

	@Override
	public void delete()
	{
		System.err.println("DesktopMenuPlugin.delete()");
	}
}

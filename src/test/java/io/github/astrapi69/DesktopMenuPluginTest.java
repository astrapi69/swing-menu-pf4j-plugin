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
package io.github.astrapi69;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pf4j.DefaultExtensionFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFinder;
import org.pf4j.PluginManager;

import io.github.astrapi69.awt.action.NoAction;
import io.github.astrapi69.awt.window.adapter.CloseWindow;
import io.github.astrapi69.collection.list.ListExtensions;
import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.junit.jupiter.callback.before.test.IgnoreHeadlessExceptionExtension;
import io.github.astrapi69.menu.pf4j.extension.DesktopMenuExtensionPoint;
import io.github.astrapi69.swing.action.ExitApplicationAction;
import io.github.astrapi69.swing.action.ToggleFullScreenAction;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

public class DesktopMenuPluginTest
{

	public static void main(String... args)
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
		Optional<DesktopMenuExtensionPoint> desktopMenuExtensionPointOptional = ListExtensions
			.getFirstElement(extensionPoints);

		if (desktopMenuExtensionPointOptional.isPresent())
		{
			DesktopMenuExtensionPoint desktopMenuExtensionPoint = desktopMenuExtensionPointOptional
				.get();

			Map<String, ActionListener> actionListenerMap;
			String filename;
			filename = "app-tree-menubar.xml";

			actionListenerMap = new LinkedHashMap<>();
			actionListenerMap.put(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey(),
				new ToggleFullScreenAction("Fullscreen", new JFrame("Test Menu with xml")));
			actionListenerMap.put(BaseMenuId.EXIT.propertiesKey(),
				new ExitApplicationAction("Exit"));
			actionListenerMap.put(BaseMenuId.FILE.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.MENU_BAR.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_CONTENT.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_DONATE.propertiesKey(), new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC.propertiesKey(), new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_ACTIVITY.propertiesKey(),
				new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_PROFILE.propertiesKey(),
				new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_USAGE.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_LICENSE.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_INFO.propertiesKey(), new NoAction());

			File xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(),
				filename);
			String xml = RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.fromFile(xmlFile));
			JMenuBar jMenuBar = desktopMenuExtensionPoint.buildMenuBar(xml, actionListenerMap);

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new BorderLayout());
			frame.addWindowListener(new CloseWindow());
			frame.setTitle("Menu Plugin Test Panel");
			frame.setJMenuBar(jMenuBar);
			frame.setSize(500, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	@ExtendWith(IgnoreHeadlessExceptionExtension.class)
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
		Optional<DesktopMenuExtensionPoint> desktopMenuExtensionPointOptional = ListExtensions
			.getFirstElement(extensionPoints);

		if (desktopMenuExtensionPointOptional.isPresent())
		{
			DesktopMenuExtensionPoint desktopMenuExtensionPoint = desktopMenuExtensionPointOptional
				.get();

			Map<String, ActionListener> actionListenerMap;
			String filename;
			filename = "app-tree-menubar.xml";

			actionListenerMap = new LinkedHashMap<>();
			actionListenerMap.put(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey(),
				new ToggleFullScreenAction("Fullscreen", new JFrame("Test Menu with xml")));
			actionListenerMap.put(BaseMenuId.EXIT.propertiesKey(),
				new ExitApplicationAction("Exit"));
			actionListenerMap.put(BaseMenuId.FILE.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.MENU_BAR.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_CONTENT.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_DONATE.propertiesKey(), new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC.propertiesKey(), new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_ACTIVITY.propertiesKey(),
				new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_PROFILE.propertiesKey(),
				new NoAction());
			actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_USAGE.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_LICENSE.propertiesKey(), new NoAction());
			actionListenerMap.put(BaseMenuId.HELP_INFO.propertiesKey(), new NoAction());

			File xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(),
				filename);
			String xml = RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.fromFile(xmlFile));
			JMenuBar jMenuBar = desktopMenuExtensionPoint.buildMenuBar(xml, actionListenerMap);
			assertNotNull(jMenuBar);
		}

		// stop and unload all plugins
		pluginManager.stopPlugins();
		pluginManager.unloadPlugins();
	}


}
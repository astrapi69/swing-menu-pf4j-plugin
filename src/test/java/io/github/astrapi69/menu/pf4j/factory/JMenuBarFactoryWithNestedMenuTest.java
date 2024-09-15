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
package io.github.astrapi69.menu.pf4j.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.awt.window.adapter.CloseWindow;
import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.file.write.StoreFileExtensions;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.gen.tree.TreeIdNode;
import io.github.astrapi69.gen.tree.convert.BaseTreeNodeTransformer;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.menu.pf4j.transform.MenuInfoTreeNodeConverter;
import io.github.astrapi69.reflection.InstanceFactory;
import io.github.astrapi69.swing.action.ToggleFullScreenAction;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

/**
 * The unit test class for the {@link JMenuBarFactory} class. It validates the creation of nested
 * {@link JMenuBar} structures from XML using a generated tree of {@link BaseTreeNode} objects.
 */
public class JMenuBarFactoryWithNestedMenuTest
{

	File xmlFile;
	String xml;

	/**
	 * Main method for running the test in a graphical user interface.
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args)
	{
		JFrame frame;
		String filename;

		frame = new JFrame("Test Menu with xml");
		filename = "app-tree-menubar.xml";
		File xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), filename);
		String xml = RuntimeExceptionDecorator.decorate(() -> ReadFileExtensions.fromFile(xmlFile));

		Map<String, ActionListener> actionListenerMap;
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(xml);
		actionListenerMap = getActionListenerMap(
			menuInfoLongBaseTreeNode);
		// actionListenerMap = new LinkedHashMap<>();
		//
		// actionListenerMap.put(BaseMenuId.MENU_BAR.propertiesKey(), new NoAction());
		// actionListenerMap.put(BaseMenuId.FILE.propertiesKey(), new NoAction());
		// actionListenerMap.put(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey(),
		// new ToggleFullScreenAction("Fullscreen", frame));
		// actionListenerMap.put(BaseMenuId.EXIT.propertiesKey(), new
		// ExitApplicationAction("Exit"));
		// actionListenerMap.put(BaseMenuId.HELP.propertiesKey(), new NoAction());
		// actionListenerMap.put(BaseMenuId.HELP_CONTENT.propertiesKey(), new NoAction());
		// actionListenerMap.put(BaseMenuId.HELP_DONATE.propertiesKey(), new NoAction());
		// actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC.propertiesKey(), new NoAction());
		// actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_ACTIVITY.propertiesKey(), new
		// NoAction());
		// actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_PROFILE.propertiesKey(), new
		// NoAction());
		// actionListenerMap.put(TestMenuId.HELP_DIAGNOSTIC_USAGE.propertiesKey(), new NoAction());
		// actionListenerMap.put(BaseMenuId.HELP_LICENSE.propertiesKey(), new NoAction());
		// actionListenerMap.put(BaseMenuId.HELP_INFO.propertiesKey(), new NoAction());

		ToggleFullScreenAction toggleFullScreenAction = (ToggleFullScreenAction)actionListenerMap
			.get(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey());
		toggleFullScreenAction.setFrame(frame);

		final JMenuBar menuBar = JMenuBarFactory.buildMenuBar(menuInfoLongBaseTreeNode,
			actionListenerMap);

		frame.setJMenuBar(menuBar);
		frame.addWindowListener(new CloseWindow());
		frame.setSize(400, 200);
		frame.setVisible(true);
	}

	public static Map<String, ActionListener> getActionListenerMap(
		BaseTreeNode<MenuInfo, Long> root)
	{
		Map<Long, TreeIdNode<MenuInfo, Long>> treeIdNodeMap = BaseTreeNodeTransformer
			.toKeyMap(root);
		Map<String, ActionListener> actionListenerMap = new LinkedHashMap<>();
		treeIdNodeMap.forEach((key, value) -> {
			MenuInfo menuInfo = value.getValue();
			String actionCommand = menuInfo.getActionCommand();
			Optional<ActionListener> actionListenerOptional = InstanceFactory
				.newOptionalInstance(actionCommand);
			if (actionListenerOptional.isEmpty())
			{
				throw new IllegalArgumentException(
					"actionListenerClass cannot be instantiated with actionCommand:"
						+ actionCommand);
			}
			ActionListener actionListener = actionListenerOptional.get();
			actionListenerMap.put(menuInfo.getName(), actionListener);
		});
		return actionListenerMap;
	}

	/**
	 * Sets up the XML file and loads it before each test.
	 */
	@BeforeEach
	public void beforeEach()
	{
		String filename;
		filename = "app-tree-menubar.xml";
		xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), filename);
		xml = RuntimeExceptionDecorator.decorate(() -> ReadFileExtensions.fromFile(xmlFile));
	}

	/**
	 * Test for building a root tree node from XML and validating the structure.
	 */
	@Test
	public void testBuildRootTreeNodeFromXml()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory
			.getTestFileAndHelpMenuWithMenubar();
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		String treeNodeAsXml;

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);
		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-tree-menubar.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(xml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

}

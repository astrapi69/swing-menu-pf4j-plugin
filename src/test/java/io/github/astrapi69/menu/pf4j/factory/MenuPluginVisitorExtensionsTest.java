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

import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.swing.menu.model.MenuInfo;

/**
 * The test class {@link MenuPluginVisitorExtensionsTest} provides unit tests for the class
 * {@link MenuPluginVisitorExtensions}
 */
class MenuPluginVisitorExtensionsTest
{

	private BaseTreeNode<MenuInfo, Long> testNode;
	private Map<String, JMenu> menuMap;
	private Map<String, JMenuItem> menuItemMap;
	private Map<String, JMenuBar> menuBarMap;

	@BeforeEach
	void setUp()
	{
		menuMap = new HashMap<>();
		menuItemMap = new HashMap<>();
		menuBarMap = new HashMap<>();

		// Initialize test data
		MenuInfo menuInfo = new MenuInfo();
		testNode = new BaseTreeNode<>(menuInfo);
	}

	/**
	 * Test for {@link MenuPluginVisitorExtensions#visitAndAddToMenu(BaseTreeNode, Map, Map, Map)}
	 */
	@Test
	void testVisitAndAddToMenu()
	{
		// assertDoesNotThrow(() -> MenuPluginVisitorExtensions.visitAndAddToMenu(testNode, menuMap,
		// menuItemMap, menuBarMap));
	}

	/**
	 * Test for {@link MenuPluginVisitorExtensions#visitAndAddToMap(BaseTreeNode, Map, Map, Map)}
	 */
	@Test
	void testVisitAndAddToMap()
	{
		// assertDoesNotThrow(() -> MenuPluginVisitorExtensions.visitAndAddToMap(testNode, menuMap,
		// menuItemMap, menuBarMap));
	}

}

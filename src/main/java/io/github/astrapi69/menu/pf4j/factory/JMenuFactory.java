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

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.swing.menu.factory.MenuVisitorExtensions;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import lombok.NonNull;

/**
 * A factory {@link JMenuFactory} provides factory methods for creating {@link JMenu} objects.
 */
public final class JMenuFactory
{

	/**
	 * Private constructor to prevent instantiation
	 */
	private JMenuFactory()
	{
	}


	/**
	 * Builds a {@link JMenu} from the given root {@link BaseTreeNode} object.
	 *
	 * @param root
	 *            the root node of the {@link BaseTreeNode} tree structure
	 * @return the constructed {@link JMenu} object
	 */
	public static JMenu buildMenu(final @NonNull BaseTreeNode<MenuInfo, Long> root)
	{
		return buildMenu(root.getValue().getName(), root);
	}

	/**
	 * Builds a {@link JMenu} from the given root {@link BaseTreeNode} object and a specific menu
	 * ID.
	 *
	 * @param menuId
	 *            the identifier of the menu
	 * @param root
	 *            the root node of the {@link BaseTreeNode} tree structure
	 * @return the constructed {@link JMenu} object
	 */
	public static JMenu buildMenu(final @NonNull String menuId,
		final @NonNull BaseTreeNode<MenuInfo, Long> root)
	{
		final Map<String, JMenu> menuMap = new HashMap<>();
		final Map<String, JMenuItem> menuItemMap = new HashMap<>();
		final Map<String, JMenuBar> menuBarMap = new HashMap<>();
		MenuPluginVisitorExtensions.visitAndAddToMap(root, menuMap, menuItemMap, menuBarMap);
		root.accept(menuInfoLongBaseTreeNode -> MenuPluginVisitorExtensions
			.visitAndAddToMap(menuInfoLongBaseTreeNode, menuMap, menuItemMap, menuBarMap));
		root.accept(menuInfoLongBaseTreeNode -> MenuPluginVisitorExtensions
			.visitAndAddToMenu(menuInfoLongBaseTreeNode, menuMap, menuItemMap, menuBarMap));
		return menuMap.get(menuId);
	}

	/**
	 * Builds a {@link JMenu} from the given root {@link BaseTreeNode} object with a map of
	 * {@link ActionListener}.
	 *
	 * @param root
	 *            the root node of the {@link BaseTreeNode} tree structure
	 * @param actionListenerMap
	 *            the map of {@link ActionListener} objects
	 * @return the constructed {@link JMenu} object
	 */
	public static JMenu buildMenu(final @NonNull BaseTreeNode<MenuInfo, Long> root,
		final @NonNull Map<String, ActionListener> actionListenerMap)
	{
		return buildMenu(root.getValue().getName(), root, actionListenerMap);
	}

	/**
	 * Builds a {@link JMenu} from the given root {@link BaseTreeNode} object, a specific menu ID,
	 * and a map of {@link ActionListener}.
	 *
	 * @param menuId
	 *            the identifier of the menu
	 * @param root
	 *            the root node of the {@link BaseTreeNode} tree structure
	 * @param actionListenerMap
	 *            the map of {@link ActionListener} objects
	 * @return the constructed {@link JMenu} object
	 */
	public static JMenu buildMenu(final @NonNull String menuId,
		final @NonNull BaseTreeNode<MenuInfo, Long> root,
		final @NonNull Map<String, ActionListener> actionListenerMap)
	{
		final Map<String, JMenu> menuMap = new HashMap<>();
		final Map<String, JMenuItem> menuItemMap = new HashMap<>();
		final Map<String, JMenuBar> menuBarMap = new HashMap<>();
		MenuVisitorExtensions.visitAndAddToMap(root, actionListenerMap, menuMap, menuItemMap,
			menuBarMap);
		root.accept(menuInfoLongBaseTreeNode -> MenuVisitorExtensions.visitAndAddToMap(
			menuInfoLongBaseTreeNode, actionListenerMap, menuMap, menuItemMap, menuBarMap));
		root.accept(menuInfoLongBaseTreeNode -> MenuVisitorExtensions
			.visitAndAddToMenu(menuInfoLongBaseTreeNode, menuMap, menuItemMap, menuBarMap));
		return menuMap.get(menuId);
	}

}

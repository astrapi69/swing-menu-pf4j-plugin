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
package io.github.astrapi69.menu.pf4j.extension;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import org.pf4j.Extension;

import io.github.astrapi69.menu.pf4j.factory.JMenuBarFactory;
import io.github.astrapi69.menu.pf4j.factory.JMenuFactory;
import io.github.astrapi69.menu.pf4j.transform.MenuInfoTreeNodeConverter;


@Extension
public class DesktopMenuExtension implements DesktopMenuExtensionPoint
{

	/**
	 * Builds a {@link JMenuBar} from the given XML representation of {@link MenuInfo} objects
	 *
	 * @param xml
	 *            the XML representation of {@link MenuInfo} objects
	 * @return the constructed {@link JMenuBar} object
	 */
	@Override
	public JMenuBar buildMenuBar(String xml)
	{
		return JMenuBarFactory.buildMenuBar(MenuInfoTreeNodeConverter.toMenuInfoTreeNode(xml));
	}

	/**
	 * Builds a {@link JMenu} from the given XML representation of {@link MenuInfo} objects
	 *
	 * @param xml
	 *            the XML representation of {@link MenuInfo} objects
	 * @return the constructed {@link JMenu} object
	 */
	@Override
	public JMenu buildAndAddMenuToExistingJMenuBar(String xml)
	{
		return JMenuFactory.buildMenu(MenuInfoTreeNodeConverter.toMenuInfoTreeNode(xml));
	}

	/**
	 * Builds a {@link JMenuBar} from the given XML representation of {@link MenuInfo} objects
	 * 	  and a map of
	 * {@link ActionListener}.
	 *
	 * @param xml
	 *            the XML representation of {@link MenuInfo} objects
	 * @param actionListenerMap
	 *            the map of {@link ActionListener} objects
	 * @return the constructed {@link JMenuBar} object
	 */
	public JMenuBar buildMenuBar(String xml, Map<String, ActionListener> actionListenerMap)
	{
		return JMenuBarFactory.buildMenuBar(MenuInfoTreeNodeConverter.toMenuInfoTreeNode(xml),
			actionListenerMap);
	}


	/**
	 * Builds a {@link JMenu} from the given XML representation of {@link MenuInfo} objects
	 * 	  with a map of
	 * {@link ActionListener}.
	 *
	 * @param xml
	 *            the XML representation of {@link MenuInfo} objects
	 * @param actionListenerMap
	 *            the map of {@link ActionListener} objects
	 * @return the constructed {@link JMenu} object
	 */
	@Override
	public JMenu buildAndAddMenuToExistingJMenuBar(String xml,
		Map<String, ActionListener> actionListenerMap)
	{
		return JMenuFactory.buildMenu(MenuInfoTreeNodeConverter.toMenuInfoTreeNode(xml),
			actionListenerMap);
	}
}

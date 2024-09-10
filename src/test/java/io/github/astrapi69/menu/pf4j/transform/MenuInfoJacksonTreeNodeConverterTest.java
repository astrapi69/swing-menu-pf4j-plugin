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
package io.github.astrapi69.menu.pf4j.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;

/**
 * Test class for {@link MenuInfoJacksonTreeNodeConverter}
 */
class MenuInfoJacksonTreeNodeConverterTest
{

	/**
	 * Test method for building a tree node from XML with xstream for the file menu and verifying
	 * the integrity of the structure after conversion to and from XML
	 *
	 * The methods that are tested in this test case include:
	 * <ul>
	 * <li>{@link MenuInfoJacksonTreeNodeConverter#toXml(BaseTreeNode)} - Converts a tree node to
	 * its XML representation</li>
	 * <li>{@link MenuInfoJacksonTreeNodeConverter#toMenuInfoTreeNode(String)} - Converts an XML
	 * representation back to a tree node</li>
	 * </ul>
	 */
	@Test
//	@Disabled
	public void testBuildRootTreeNodeFromJacksonXmlForFileMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		String treeNodeAsXml;

		treeNodeAsXml = MenuInfoJacksonTreeNodeConverter.toXml(menuBarTreeNode);

		menuInfoLongBaseTreeNode = MenuInfoJacksonTreeNodeConverter
			.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

}
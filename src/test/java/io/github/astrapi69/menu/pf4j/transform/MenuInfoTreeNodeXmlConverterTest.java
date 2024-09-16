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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;

/**
 * Test class for {@link MenuInfoTreeNodeXmlConverter}
 */
class MenuInfoTreeNodeXmlConverterTest
{
	private String testXml;

	@BeforeEach
	void setUp()
	{
		// Sample XML data for testing
		testXml = MenuInfoTreeNodeXmlConverter.toXml(TestDataFactory.getTestFileMenuWithMenubar());
	}

	/**
	 * Test method for building a tree node from XML with xstream for the file menu and verifying
	 * the integrity of the structure after conversion to and from XML
	 *
	 * The methods that are tested in this test case include:
	 * <ul>
	 * <li>{@link MenuInfoTreeNodeXmlConverter#toXml(BaseTreeNode)} - Converts a tree node to its
	 * XML representation</li>
	 * <li>{@link MenuInfoTreeNodeXmlConverter#toMenuInfoTreeNode(String)} - Converts an XML
	 * representation back to a tree node</li>
	 * </ul>
	 */
	@Test
	public void testBuildRootTreeNodeFromJaxbXmlForFileMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		String treeNodeAsXml;

		// Convert the tree node to XML
		treeNodeAsXml = MenuInfoTreeNodeXmlConverter.toXml(menuBarTreeNode);

		// Convert the XML back to a tree node and verify
		menuInfoLongBaseTreeNode = MenuInfoTreeNodeXmlConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);

		// Verify that the converted tree node matches the original tree node
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

	/**
	 * Test for {@link MenuInfoTreeNodeXmlConverter#toMenuInfoTreeNode(String)}
	 */
	@Test
	void testToMenuInfoTreeNode()
	{
		BaseTreeNode<MenuInfo, Long> treeNode = MenuInfoTreeNodeXmlConverter
			.toMenuInfoTreeNode(testXml);
		assertNotNull(treeNode);
	}

	/**
	 * Test for {@link MenuInfoTreeNodeXmlConverter#toXml(BaseTreeNode)}
	 */
	@Test
	void testToXml()
	{
		BaseTreeNode<MenuInfo, Long> treeNode = MenuInfoTreeNodeXmlConverter
			.toMenuInfoTreeNode(testXml);
		String xml = MenuInfoTreeNodeXmlConverter.toXml(treeNode);
		assertNotNull(xml);
	}

	/**
	 * Test for {@link MenuInfoTreeNodeXmlConverter#mergeMenuInfoTreeNode(String...)}
	 */
	@Test
	void testMergeMenuInfoTreeNode()
	{
		String[] xmls = { testXml, testXml };
		BaseTreeNode<MenuInfo, Long> mergedTreeNode = MenuInfoTreeNodeXmlConverter
			.mergeMenuInfoTreeNode(xmls);
		assertNotNull(mergedTreeNode);
	}

}

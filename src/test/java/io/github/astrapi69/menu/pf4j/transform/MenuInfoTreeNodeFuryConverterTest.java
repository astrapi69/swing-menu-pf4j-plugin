package io.github.astrapi69.menu.pf4j.transform;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;

class MenuInfoTreeNodeFuryConverterTest
{

	@Test
	void toMenuInfoTreeNode() throws IOException
	{
		File file = PathFinder.getRelativePath(PathFinder.getSrcTestResourcesDir(), "byte-object",
			"test-menu.bts");
		byte[] fileToBytearray = ReadFileExtensions.readFileToBytearray(file);
		BaseTreeNode<MenuInfo, Long> menuInfoTreeNode = MenuInfoTreeNodeFuryConverter
			.toMenuInfoTreeNode(fileToBytearray);
		assertNotNull(menuInfoTreeNode);
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		assertEquals(menuBarTreeNode, menuInfoTreeNode);
	}

	@Test
	void toBytes()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		byte[] bytes = MenuInfoTreeNodeFuryConverter.toBytes(menuBarTreeNode);
		assertNotNull(bytes);
	}
}
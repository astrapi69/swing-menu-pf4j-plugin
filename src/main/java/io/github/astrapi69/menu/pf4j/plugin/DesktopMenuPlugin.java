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

import org.pf4j.Plugin;

/**
 * Plugin class for the DesktopMenuPlugin, providing lifecycle methods for starting, stopping, and
 * deleting the plugin
 */
public class DesktopMenuPlugin extends Plugin
{

	/**
	 * Constructor for {@link DesktopMenuPlugin}. Provides access to the plugin context, including
	 * plugin manager, descriptor, etc.
	 */
	public DesktopMenuPlugin()
	{
		// you can use "wrapper" to have access to the plugin context (plugin manager, descriptor,
		// ...)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start()
	{
		System.err.println("DesktopMenuPlugin.start()");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop()
	{
		System.err.println("DesktopMenuPlugin.stop()");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete()
	{
		System.err.println("DesktopMenuPlugin.delete()");
	}
}

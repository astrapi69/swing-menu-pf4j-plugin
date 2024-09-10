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
package io.github.astrapi69.menu.pf4j.test;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * The enum {@link TestMenuId} represents a set of predefined menu IDs for the diagnostic section of
 * the help menu in an application.
 */
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum TestMenuId
{

	/**
	 * Menu ID for the "Help Diagnostic" menu.
	 */
	HELP_DIAGNOSTIC(TestMenuId.HELP_DIAGNOSTIC_KEY),

	/**
	 * Menu ID for the "Help Diagnostic Activity" menu.
	 */
	HELP_DIAGNOSTIC_ACTIVITY(TestMenuId.HELP_DIAGNOSTIC_ACTIVITY_KEY),

	/**
	 * Menu ID for the "Help Diagnostic Profile" menu.
	 */
	HELP_DIAGNOSTIC_PROFILE(TestMenuId.HELP_DIAGNOSTIC_PROFILE_KEY),

	/**
	 * Menu ID for the "Help Diagnostic Usage" menu.
	 */
	HELP_DIAGNOSTIC_USAGE(TestMenuId.HELP_DIAGNOSTIC_USAGE_KEY);

	/**
	 * The key for the "Help Diagnostic" menu.
	 */
	public static final String HELP_DIAGNOSTIC_KEY = "global.menu.help.diagnostic";

	/**
	 * The key for the "Help Diagnostic Activity" menu.
	 */
	public static final String HELP_DIAGNOSTIC_ACTIVITY_KEY = "global.menu.help.diagnostic.activity";

	/**
	 * The key for the "Help Diagnostic Profile" menu.
	 */
	public static final String HELP_DIAGNOSTIC_PROFILE_KEY = "global.menu.help.diagnostic.profile";

	/**
	 * The key for the "Help Diagnostic Usage" menu.
	 */
	public static final String HELP_DIAGNOSTIC_USAGE_KEY = "global.menu.help.diagnostic.usage";

	/** The properties key associated with the current menu ID. */
	String propertiesKey;
}

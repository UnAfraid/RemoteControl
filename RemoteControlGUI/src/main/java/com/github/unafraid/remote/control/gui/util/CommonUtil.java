/*
 * Copyright (C) 2016-2017 Remote Control
 * 
 * This file is part of Remote Control.
 * 
 * Remote Control is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Remote Control is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.unafraid.remote.control.gui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author UnAfraid
 */
public class CommonUtil
{
	private static final Pattern GUID_PATTERN = Pattern.compile("\\{([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})\\}$");
	
	/**
	 * @param input
	 * @return GUID string from given input or null if not matching
	 */
	public static String extractGUID(String input)
	{
		final Matcher matcher = GUID_PATTERN.matcher(input);
		if (matcher.find())
		{
			return matcher.group(1);
		}
		return null;
	}
}

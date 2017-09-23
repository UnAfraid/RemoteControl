/*
 * Copyright (C) 2004-2017 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.unafraid.remote.control.api.util;

public enum OperationSystemType
{
	WINDOWS_x86("win32/x86/", ".dll"),
	WINDOWS_x64("win32/x64/", ".dll"),
	MACOS_x86(null, null),
	MACOS_x64("macos/x64/", ".dylib"),
	LINUX_x86("linux/x86.", ".so"),
	LINUX_x64("linux/x64.", ".so"),
	OTHER(null, null);
	
	private final String _osPath;
	private final String _extension;
	
	private OperationSystemType(String osPath, String extension)
	{
		_osPath = osPath;
		_extension = extension;
	}
	
	public String getOSPath()
	{
		return _osPath;
	}
	
	public String getExtension()
	{
		return _extension;
	}
}
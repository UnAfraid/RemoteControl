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
package com.github.unafraid.remote.control.api.util;

import java.util.Locale;

/**
 * @author UnAfraid
 */
public class OperationSystem
{
	protected static OperationSystemType OPERATION_SYSTEM;
	
	public static OperationSystemType getCurrentOSType()
	{
		if (OPERATION_SYSTEM == null)
		{
			final String operatingSystem = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			final boolean is64bit = System.getProperty("os.arch").contains("64");
			if ((operatingSystem.contains("mac")) || (operatingSystem.contains("darwin")))
			{
				OPERATION_SYSTEM = is64bit ? OperationSystemType.MACOS_x64 : OperationSystemType.MACOS_x86;
			}
			else if (operatingSystem.contains("win"))
			{
				OPERATION_SYSTEM = is64bit ? OperationSystemType.WINDOWS_x64 : OperationSystemType.WINDOWS_x86;
			}
			else if (operatingSystem.contains("nux"))
			{
				OPERATION_SYSTEM = is64bit ? OperationSystemType.LINUX_x64 : OperationSystemType.LINUX_x86;
			}
			else
			{
				OPERATION_SYSTEM = OperationSystemType.OTHER;
			}
		}
		return OPERATION_SYSTEM;
	}
}

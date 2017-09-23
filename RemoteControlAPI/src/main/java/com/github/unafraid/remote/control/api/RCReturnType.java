/*
 * Copyright (C) 2004-2016 Remote Control
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
package com.github.unafraid.remote.control.api;

/**
 * @author UnAfraid
 */
public enum RCReturnType
{
	SUCCESS,
	HID_INIT_FAILED,
	DEVICE_NOT_FOUND,
	UNABLE_TO_OPEN_DEVICE,
	UNSUPPORTED_DEVICE_TYPE,
	FAILED_TO_GENERATE_PACKET,
	FAILED_TO_SEND_PACKET,
	UNKNOWN_ERROR;
	
	public static RCReturnType ofId(int id)
	{
		if ((id < 0) || (id > values().length))
		{
			return UNKNOWN_ERROR;
		}
		return values()[id];
	}
}

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
package com.github.unafraid.remote.control.api.drivers.sang.model;

/**
 * @author UnAfraid
 */
public enum SangFanMode
{
	AUTO(0),
	AUTO_SLEEP(1),
	LOW(2),
	MEDIUM(3),
	HIGH(5);
	private byte _type;
	
	private SangFanMode(int type)
	{
		_type = (byte) type;
	}
	
	public byte getValue()
	{
		return _type;
	}
}

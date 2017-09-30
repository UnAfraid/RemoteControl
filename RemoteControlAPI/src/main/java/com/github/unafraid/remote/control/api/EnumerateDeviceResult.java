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
package com.github.unafraid.remote.control.api;

import com.github.unafraid.remote.control.api.enums.RCHasEmitterReturnType;

/**
 * @author UnAfraid
 */
public class EnumerateDeviceResult
{
	private final RCHasEmitterReturnType result;
	private final String device;
	
	/**
	 * @param result
	 * @param device
	 */
	public EnumerateDeviceResult(RCHasEmitterReturnType result, String device)
	{
		this.result = result;
		this.device = device;
	}
	
	/**
	 * @return the result
	 */
	public RCHasEmitterReturnType getResult()
	{
		return result;
	}
	
	/**
	 * @return the device
	 */
	public String getDevice()
	{
		return device;
	}
}
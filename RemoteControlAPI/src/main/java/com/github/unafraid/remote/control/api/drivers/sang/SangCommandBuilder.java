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
package com.github.unafraid.remote.control.api.drivers.sang;

import com.github.unafraid.remote.control.api.drivers.sang.model.SangFanMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangState;
import com.github.unafraid.remote.control.api.enums.RCReturnType;

/**
 * @author UnAfraid
 */
public class SangCommandBuilder
{
	private final SangDriver driver;
	private SangMode mode;
	private SangState state;
	private int temperature;
	private SangFanMode fanMode;
	
	protected SangCommandBuilder(SangDriver driver, SangMode mode, SangState state, int temperature, SangFanMode fanMode)
	{
		this.driver = driver;
		this.mode = mode;
		this.state = state;
		this.temperature = temperature;
		this.fanMode = fanMode;
	}
	
	public SangCommandBuilder state(SangState state)
	{
		this.state = state;
		return this;
	}
	
	public SangCommandBuilder mode(SangMode mode)
	{
		this.mode = mode;
		return this;
	}
	
	public SangCommandBuilder temperature(int temperature)
	{
		this.temperature = temperature;
		return this;
	}
	
	public SangCommandBuilder fanMode(SangFanMode fanMode)
	{
		this.fanMode = fanMode;
		return this;
	}
	
	public RCReturnType send(String devicePath)
	{
		return driver.sendPacket(devicePath, state, mode, temperature, fanMode);
	}
}
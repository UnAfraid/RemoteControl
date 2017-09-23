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
package com.github.unafraid.remote.control.api.drivers.sang;

import com.github.unafraid.remote.control.api.RCReturnType;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangFanMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangState;

/**
 * @author UnAfraid
 */
public class SangCommandBuilder
{
	private SangMode _mode;
	private SangState _state;
	private int _temperature;
	private SangFanMode _fanMode;
	
	public SangCommandBuilder(SangMode mode, SangState state, int temperature, SangFanMode fanMode)
	{
		_mode = mode;
		_state = state;
		_temperature = temperature;
		_fanMode = fanMode;
	}
	
	public SangCommandBuilder state(SangState state)
	{
		_state = state;
		return this;
	}
	
	public SangCommandBuilder mode(SangMode mode)
	{
		_mode = mode;
		return this;
	}
	
	public SangCommandBuilder temperature(int temperature)
	{
		_temperature = temperature;
		return this;
	}
	
	public SangCommandBuilder fanMode(SangFanMode fanMode)
	{
		_fanMode = fanMode;
		return this;
	}
	
	public RCReturnType send()
	{
		return SangDriver.getInstance().sendPacket(_state, _mode, _temperature, _fanMode);
	}
}
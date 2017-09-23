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

import com.github.unafraid.remote.control.api.RCDeviceType;
import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.RCReturnType;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangFanMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangState;

/**
 * @author UnAfraid
 */
public class SangDriver extends RCDriver
{
	private SangMode _lastMode = SangMode.HEAT;
	private SangState _lastState = SangState.ON;
	private int _lastTemperature = 24;
	private SangFanMode _lastFanMode = SangFanMode.AUTO;
	
	public RCReturnType sendPacket(SangState state, SangMode mode, int temperature, SangFanMode fanMode)
	{
		_lastState = state;
		_lastMode = mode;
		_lastTemperature = temperature;
		_lastFanMode = fanMode;
		return RCReturnType.ofId(sendPacket(RCDeviceType.SANG.getValue(), state == SangState.ON ? (byte) 1 : 0, mode.getValue(), (byte) temperature, fanMode.getValue()));
	}
	
	public SangMode getLastMode()
	{
		return _lastMode;
	}
	
	public SangState getLastState()
	{
		return _lastState;
	}
	
	public int getLastTemperature()
	{
		return _lastTemperature;
	}
	
	public SangFanMode getLastFanMode()
	{
		return _lastFanMode;
	}
	
	public SangCommandBuilder newBuilder()
	{
		return new SangCommandBuilder(_lastMode, _lastState, _lastTemperature, _lastFanMode);
	}
	
	public static SangDriver getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final SangDriver INSTANCE = new SangDriver();
	}
}

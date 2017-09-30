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

import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangFanMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangState;
import com.github.unafraid.remote.control.api.enums.RCDeviceType;
import com.github.unafraid.remote.control.api.enums.RCReturnType;

/**
 * @author UnAfraid
 */
public class SangDriver extends RCDriver
{
	private String lastDevicePath;
	private SangMode lastMode = SangMode.HEAT;
	private SangState lastState = SangState.ON;
	private int lastTemperature = 24;
	private SangFanMode lastFanMode = SangFanMode.AUTO;
	
	protected RCReturnType sendPacket(String devicePath, SangState state, SangMode mode, int temperature, SangFanMode fanMode)
	{
		this.lastDevicePath = devicePath;
		this.lastState = state;
		this.lastMode = mode;
		this.lastTemperature = Math.min(Math.max(temperature, 17), 31);
		this.lastFanMode = fanMode;
		return sendPacket(devicePath, RCDeviceType.SANG.getValue(), state == SangState.ON ? (byte) 1 : 0, mode.getValue(), (byte) temperature, fanMode.getValue());
	}
	
	public String getLastDevicePath()
	{
		return lastDevicePath;
	}
	
	public SangMode getLastMode()
	{
		return lastMode;
	}
	
	public SangState getLastState()
	{
		return lastState;
	}
	
	public int getLastTemperature()
	{
		return lastTemperature;
	}
	
	public SangFanMode getLastFanMode()
	{
		return lastFanMode;
	}
	
	public SangCommandBuilder newBuilder()
	{
		return new SangCommandBuilder(this, lastMode, lastState, lastTemperature, lastFanMode);
	}
}

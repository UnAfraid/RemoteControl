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
package com.github.unafraid.remote.control.api.drivers.huawei;

import com.github.unafraid.remote.control.api.RCDeviceType;
import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.RCReturnType;

/**
 * @author UnAfraid
 */
public class HuaweiDriver extends RCDriver
{
	public RCReturnType sendButton(HuaweiButtonsType buttonType)
	{
		return RCReturnType.ofId(sendPacket(RCDeviceType.HUAWEI.getValue(), buttonType.getCode(), (byte) 0, (byte) 0, (byte) 0));
	}
}
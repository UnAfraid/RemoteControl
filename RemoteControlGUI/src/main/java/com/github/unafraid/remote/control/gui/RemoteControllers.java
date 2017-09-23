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
package com.github.unafraid.remote.control.gui;

/**
 * @author UnAfraid
 */
public enum RemoteControllers
{
	Huawei_EC2108V5("Huawei EC2108V5 IR Remote", "/views/Huawei-EC2108V5.fxml");
	
	private final String name;
	private final String fxml;
	
	private RemoteControllers(String name, String fxml)
	{
		this.name = name;
		this.fxml = fxml;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getFXml()
	{
		return fxml;
	}
}

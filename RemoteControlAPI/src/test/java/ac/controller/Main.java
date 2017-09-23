/*
 * Copyright (C) 2004-2016 L2J Unity
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
package ac.controller;

import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.RCReturnType;
import com.github.unafraid.remote.control.api.drivers.sang.SangDriver;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangFanMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangState;

/**
 * @author UnAfraid
 */
public class Main
{
	public Main() throws Exception
	{
		RCReturnType result = null;
		System.out.println("Initializing library");
		RCDriver.init();
		
		System.out.println("Turning on..");
		//@formatter:off
		result = SangDriver.getInstance().newBuilder()
			.state(SangState.ON)
			.mode(SangMode.HEAT)
			.temperature(26)
			.fanMode(SangFanMode.AUTO)
			.send();
		//@formatter:on
		System.out.println("Done result: " + result);
		
		Thread.sleep(3000);
		
		for (int i = 20; i < 26; i++)
		{
			System.out.println("Switching temperature to: " + i + "..");
			//@formatter:off
			result = SangDriver.getInstance().newBuilder()
				.temperature(i)
				.send();
			//@formatter:on
			System.out.println("Done result: " + result);
			Thread.sleep(3000);
		}
		
		Thread.sleep(3000);
		
		System.out.println("Turning off..");
		//@formatter:off
		result = SangDriver.getInstance().newBuilder()
			.state(SangState.OFF)
			.send();
		//@formatter:on
		System.out.println("Done result: " + result);
		
		System.out.println("Complete, press any key to exit!");
		System.in.read();
	}
	
	public static void main(String[] args) throws Exception
	{
		new Main();
	}
}

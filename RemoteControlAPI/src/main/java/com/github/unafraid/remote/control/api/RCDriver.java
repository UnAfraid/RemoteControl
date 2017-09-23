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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import com.github.unafraid.remote.control.api.util.OperationSystem;
import com.github.unafraid.remote.control.api.util.OperationSystemType;

/**
 * @author UnAfraid
 */
public class RCDriver
{
	private static final String NATIVE_PATH_PATH = "/native/impl/";
	private static final String HID_API_PATH = "hidapi";
	private static final String IR_TX_PATH = "ir_tx";
	
	private static boolean INITIALIZED = false;
	
	public static void init() throws IOException
	{
		final OperationSystemType osType = OperationSystem.getCurrentOSType();
		final String osPath = osType.getOSPath();
		if (osPath == null)
		{
			throw new RuntimeException("Unsupported OS: " + System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH) + " " + System.getProperty("os.arch"));
		}
		loadJarDll(NATIVE_PATH_PATH + osType.getOSPath() + HID_API_PATH + osType.getExtension());
		loadJarDll(NATIVE_PATH_PATH + osType.getOSPath() + IR_TX_PATH + osType.getExtension());
		INITIALIZED = true;
	}
	
	public static boolean isInitialized()
	{
		return INITIALIZED;
	}
	
	private static void loadJarDll(String name) throws IOException
	{
		final byte[] buffer = new byte[1024];
		final File dir = new File(System.getProperty("user.home"), ".remote_control");
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		
		final File file = new File(dir, name.substring(1));
		final File parentDir = file.getParentFile();
		if (!parentDir.exists())
		{
			parentDir.mkdirs();
		}
		
		if (file.exists())
		{
			file.delete();
		}
		
		try (InputStream in = RCDriver.class.getResourceAsStream(name);
			FileOutputStream fos = new FileOutputStream(file))
		{
			int read = -1;
			while ((read = in.read(buffer)) != -1)
			{
				fos.write(buffer, 0, read);
			}
		}
		catch (FileNotFoundException e)
		{
			
		}
		
		System.load(file.getAbsolutePath());
	}
	
	/**
	 * @param acType =
	 *            <ul>
	 *            <li>0 = test</li>
	 *            <li>1 = Neo</li>
	 *            <li>2 = Midea</li>
	 *            <li>3 = Sang</li>
	 *            </ul>
	 * @param onOff
	 * @param mode =
	 *            <ul>
	 *            <li>1 = heat</li>
	 *            <li>2 = dry</li>
	 *            <li>3 = cool</li>
	 *            <li>7 = fan</li>
	 *            <li>8 = feel</li>
	 *            </ul>
	 * @param temperature
	 * @param fanMode
	 * @return
	 *         <ul>
	 *         <li>0 = Success</li>
	 *         <li>1 = HID Init Failed</li>
	 *         <li>2 = Device Not Found</li>
	 *         <li>3 = Unable to open device</li>
	 *         <li>4 = Unsupported device type</li>
	 *         <li>5 = Failed to generate packet</li>
	 *         <li>6 = Failed to send packet</li>
	 *         </ul>
	 */
	protected native int sendPacket(int acType, byte onOff, byte mode, byte temperature, byte fanMode);
}

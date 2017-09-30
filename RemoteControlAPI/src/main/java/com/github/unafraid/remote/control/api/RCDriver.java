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

import com.github.unafraid.remote.control.api.enums.RCHasEmitterReturnType;
import com.github.unafraid.remote.control.api.enums.RCReturnType;
import com.github.unafraid.remote.control.api.jna.IRTXLibrary;
import com.github.unafraid.remote.control.api.util.OperationSystem;
import com.github.unafraid.remote.control.api.util.OperationSystemType;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * @author UnAfraid
 */
public class RCDriver
{
	private static IRTXLibrary IR_TX_LIBRARY;
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
		
		IR_TX_LIBRARY = Native.loadLibrary(NATIVE_PATH_PATH + osType.getOSPath() + IR_TX_PATH + osType.getExtension(), IRTXLibrary.class);
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
			// Sometimes when application is already running it throws FileNotFoundException that file is in use
		}
		
		System.load(file.getAbsolutePath());
	}
	
	protected static RCReturnType sendPacket(String devicePath, byte acType, byte onOff, byte mode, byte temperature, byte fanMode)
	{
		final Pointer memoryPointer = new Memory(devicePath.length() + 1);
		memoryPointer.setString(0, devicePath);
		return RCReturnType.ofId(IR_TX_LIBRARY.sendPacket(memoryPointer, acType, onOff, mode, temperature, fanMode));
	}
	
	public static EnumerateDeviceResult enumerateDevices()
	{
		Pointer pointer = new Memory(2048);
		final RCHasEmitterReturnType result = RCHasEmitterReturnType.ofId(IR_TX_LIBRARY.enumerateDevices(pointer));
		final String response = pointer.getString(0);
		return new EnumerateDeviceResult(result, response);
	}
}
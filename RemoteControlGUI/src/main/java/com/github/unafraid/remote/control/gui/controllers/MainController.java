/*
 * Copyright (C) 2004-2017 Remote Control
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
package com.github.unafraid.remote.control.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.drivers.huawei.HuaweiButtonsType;
import com.github.unafraid.remote.control.api.drivers.huawei.HuaweiDriver;
import com.github.unafraid.remote.control.gui.Main;
import com.github.unafraid.remote.control.gui.util.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

/**
 * @author UnAfraid
 */
public class MainController implements Initializable
{
	private final HuaweiDriver driver = new HuaweiDriver();
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			RCDriver.init();
		}
		catch (Exception e)
		{
			Dialogs.showExceptionDialog(AlertType.ERROR, "Error", "Failed to initialize driver", e);
		}
	}
	
	@FXML
	private void onExitRequest(ActionEvent event)
	{
		Main.shutdown();
	}
	
	@FXML
	private void onAboutRequest(ActionEvent event)
	{
		
	}
	
	@FXML
	private void onKeyPressed(KeyEvent event)
	{
		// TODO: Mapping
	}
	
	@FXML
	private void onPower(ActionEvent event)
	{
		try
		{
			driver.sendButton(HuaweiButtonsType.BUTTON_POWER);
		}
		catch (Exception e)
		{
			Dialogs.showExceptionDialog(AlertType.ERROR, "Error", "Failed to send button", e);
		}
	}
}

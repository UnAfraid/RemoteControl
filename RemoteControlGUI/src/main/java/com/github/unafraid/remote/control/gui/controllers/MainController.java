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
package com.github.unafraid.remote.control.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.gui.Main;
import com.github.unafraid.remote.control.gui.RemoteControllers;
import com.github.unafraid.remote.control.gui.util.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author UnAfraid
 */
public class MainController implements Initializable
{
	@FXML
	private TabPane devices;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			RCDriver.init();
			
			for (RemoteControllers controller : RemoteControllers.values())
			{
				try
				{
					final FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getFXml()));
					
					final Tab tab = new Tab(controller.getName());
					tab.setContent(loader.load());
					devices.getTabs().add(tab);
				}
				catch (Exception e)
				{
					Dialogs.showExceptionDialog(AlertType.ERROR, "Error", "Failed to initialize view for " + controller, e);
				}
			}
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
}

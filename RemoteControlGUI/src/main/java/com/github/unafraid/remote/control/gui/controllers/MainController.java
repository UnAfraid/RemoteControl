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

import com.github.unafraid.remote.control.api.EnumerateDeviceResult;
import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.gui.Main;
import com.github.unafraid.remote.control.gui.RemoteControllers;
import com.github.unafraid.remote.control.gui.util.CommonUtil;
import com.github.unafraid.remote.control.gui.util.Dialogs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author UnAfraid
 */
public class MainController implements Initializable
{
	
	@FXML
	private TabPane devices;
	
	@FXML
	private Text emitterStatusText;
	
	@FXML
	private Text emitterNameText;
	
	@FXML
	private Text emitterVersionText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			RCDriver.init();
			
			if (!RCDriver.isInitialized())
			{
				Dialogs.showDialog(AlertType.ERROR, "Error", "Initialization failed", "Failed to initialize driver ");
				return;
			}
			
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
			
			final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), this::determinateEmitterAvailability));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			try
			{
				determinateEmitterAvailability(null);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			Dialogs.showExceptionDialog(AlertType.ERROR, "Error", "Failed to initialize driver", e);
		}
	}
	
	/**
	 * Checks for emitter status
	 * @param event
	 */
	private void determinateEmitterAvailability(ActionEvent event)
	{
		final EnumerateDeviceResult enumerationResult = RCDriver.enumerateDevices();
		switch (enumerationResult.getResult())
		{
			case SUCCESS:
			{
				final String guid = CommonUtil.extractGUID(enumerationResult.getDevice());
				emitterStatusText.setText(guid != null ? "Emitter available" : "Not available");
				emitterNameText.setText(guid != null ? guid : "Not available");
				break;
			}
			case DEVICE_NOT_FOUND:
			{
				emitterStatusText.setText("Not available");
				emitterNameText.setText("Not available");
				emitterVersionText.setText("Not available");
				break;
			}
			case HID_INIT_FAILED:
			{
				emitterStatusText.setText("HID Init Failed");
				emitterNameText.setText("Not available");
				emitterVersionText.setText("Not available");
				break;
			}
			case UNABLE_TO_OPEN_DEVICE:
			{
				emitterStatusText.setText("Unable to open device");
				emitterNameText.setText("Not available");
				emitterVersionText.setText("Not available");
				break;
			}
			case UNSUPPORTED_DEVICE_TYPE:
			{
				emitterStatusText.setText("Unsupported device type");
				emitterNameText.setText("Not available");
				emitterVersionText.setText("Not available");
				break;
			}
			case UNKNOWN_ERROR:
			{
				emitterStatusText.setText("Unknown error");
				emitterNameText.setText("Not available");
				emitterVersionText.setText("Not available");
				break;
			}
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
		Dialogs.showUtilityStage("/views/About.fxml", "RemoteControl - About", emitterNameText.getScene().getWindow(), new Image("/images/app.jpg"));
	}
}

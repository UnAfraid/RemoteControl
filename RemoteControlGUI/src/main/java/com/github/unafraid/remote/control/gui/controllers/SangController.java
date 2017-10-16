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
import java.util.function.Function;

import com.github.unafraid.remote.control.api.EnumerateDeviceResult;
import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.drivers.sang.SangCommandBuilder;
import com.github.unafraid.remote.control.api.drivers.sang.SangDriver;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangFanMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangMode;
import com.github.unafraid.remote.control.api.drivers.sang.model.SangState;
import com.github.unafraid.remote.control.api.enums.RCHasEmitterReturnType;
import com.github.unafraid.remote.control.api.enums.RCReturnType;
import com.github.unafraid.remote.control.gui.util.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

/**
 * @author UnAfraid
 */
public class SangController implements Initializable
{
	@FXML
	private Label modeLabel;
	
	@FXML
	private Label tempLabel;
	
	@FXML
	private Label fanLabel;
	
	private final SangDriver driver = new SangDriver();
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		modeLabel.setText(driver.getLastMode().name());
		tempLabel.setText(Integer.toString(driver.getLastTemperature()));
		fanLabel.setText(driver.getLastFanMode().name() + " / " + driver.getLastState().name());
	}
	
	@FXML
	private void onSleep(ActionEvent event)
	{
		handleEvent(builder -> builder.fanMode(SangFanMode.AUTO_SLEEP));
	}
	
	@FXML
	private void onUp(ActionEvent event)
	{
		handleEvent(builder -> builder.temperature(driver.getLastTemperature() + 1));
	}
	
	@FXML
	private void onDown(ActionEvent event)
	{
		handleEvent(builder -> builder.temperature(driver.getLastTemperature() - 1));
	}
	
	@FXML
	private void onFan(ActionEvent event)
	{
		final SangFanMode mode;
		switch (driver.getLastFanMode())
		{
			case AUTO:
			case AUTO_SLEEP:
			{
				mode = SangFanMode.LOW;
				break;
			}
			case LOW:
			{
				mode = SangFanMode.MEDIUM;
				break;
			}
			case MEDIUM:
			{
				mode = SangFanMode.HIGH;
				break;
			}
			case HIGH:
			default:
			{
				mode = SangFanMode.AUTO;
				break;
			}
		}
		handleEvent(builder -> builder.fanMode(mode));
	}
	
	@FXML
	private void onTimer(ActionEvent event)
	{
		Dialogs.showDialog(AlertType.INFORMATION, "Not handled", "Function not handled", "This function is still not implemented.");
	}
	
	@FXML
	private void onMode(ActionEvent event)
	{
		final SangMode mode;
		switch (driver.getLastMode())
		{
			case HEAT:
			{
				mode = SangMode.DRY;
				break;
			}
			case DRY:
			{
				mode = SangMode.COOL;
				break;
			}
			case COOL:
			{
				mode = SangMode.FAN;
				break;
			}
			case FAN:
			{
				mode = SangMode.FEEL;
				break;
			}
			case FEEL:
			default:
			{
				mode = SangMode.HEAT;
				break;
			}
		}
		handleEvent(builder -> builder.mode(mode));
	}
	
	@FXML
	private void onSwing(ActionEvent event)
	{
		Dialogs.showDialog(AlertType.INFORMATION, "Not handled", "Function not handled", "This function is still not implemented.");
	}
	
	@FXML
	private void onOnOff(ActionEvent event)
	{
		handleEvent(builder -> builder.state(driver.getLastState() == SangState.ON ? SangState.OFF : SangState.ON));
	}
	
	private void handleEvent(Function<SangCommandBuilder, SangCommandBuilder> event)
	{
		try
		{
			final EnumerateDeviceResult result = RCDriver.enumerateDevices();
			if (result.getResult() != RCHasEmitterReturnType.SUCCESS)
			{
				Dialogs.showDialog(AlertType.WARNING, "Warning", "Failed to send button", "enumerateDevices returned " + result.getResult());
				return;
			}
			
			final SangCommandBuilder commandBuilder = event.apply(driver.newBuilder());
			final RCReturnType type = commandBuilder.send(result.getDevice());
			if (type != RCReturnType.SUCCESS)
			{
				Dialogs.showDialog(AlertType.WARNING, "Warning", "Failed to send button", "API returned " + type);
			}
			modeLabel.setText(driver.getLastMode().name());
			tempLabel.setText(Integer.toString(driver.getLastTemperature()));
			fanLabel.setText(driver.getLastFanMode().name() + " / " + driver.getLastState().name());
		}
		catch (Exception e)
		{
			Dialogs.showExceptionDialog(AlertType.ERROR, "Error", "Failed to send button", e);
		}
	}
}

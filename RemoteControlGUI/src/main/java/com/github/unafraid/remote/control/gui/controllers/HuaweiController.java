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

import com.github.unafraid.remote.control.api.EnumerateDeviceResult;
import com.github.unafraid.remote.control.api.RCDriver;
import com.github.unafraid.remote.control.api.drivers.huawei.HuaweiButtonsType;
import com.github.unafraid.remote.control.api.drivers.huawei.HuaweiDriver;
import com.github.unafraid.remote.control.api.enums.RCHasEmitterReturnType;
import com.github.unafraid.remote.control.api.enums.RCReturnType;
import com.github.unafraid.remote.control.gui.util.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;

/**
 * @author UnAfraid
 */
public class HuaweiController
{
	private final HuaweiDriver driver = new HuaweiDriver();
	
	@FXML
	private void onPower(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_POWER);
	}
	
	@FXML
	private void onMute(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_MUTE);
	}
	
	@FXML
	private void onLang(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_LANG);
	}
	
	@FXML
	private void onSearch(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_SEARCH);
	}
	
	@FXML
	private void onEPG(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_EPG);
	}
	
	@FXML
	private void onVOD(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_VOD);
	}
	
	@FXML
	private void onPIP(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_PIP);
	}
	
	@FXML
	private void onAPP(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_APP);
	}
	
	@FXML
	private void onHelp(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_HELP);
	}
	
	@FXML
	private void onCHPlus(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_CHUP);
	}
	
	@FXML
	private void onInfo(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_INFO);
	}
	
	@FXML
	private void onVolMinus(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_VOLDN);
	}
	
	@FXML
	private void onOK(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_OK);
	}
	
	@FXML
	private void onVolPlus(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_VOLUP);
	}
	
	@FXML
	private void onMenu(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_MENU);
	}
	
	@FXML
	private void onCHMinus(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_CHDN);
	}
	
	@FXML
	private void onBack(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_BACK);
	}
	
	@FXML
	private void onPrev(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_PREV);
	}
	
	@FXML
	private void onNext(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_NEXT);
	}
	
	@FXML
	private void onRewind(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_REW);
	}
	
	@FXML
	private void onPlayPause(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_PLAY);
	}
	
	@FXML
	private void onFastForward(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_FF);
	}
	
	@FXML
	private void onRed(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_RED);
	}
	
	@FXML
	private void onGreen(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_GREEN);
	}
	
	@FXML
	private void onYellow(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_YELLOW);
	}
	
	@FXML
	private void onBlue(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_BLUE);
	}
	
	@FXML
	private void onOne(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_1);
	}
	
	@FXML
	private void onTwo(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_2);
	}
	
	@FXML
	private void onThree(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_3);
	}
	
	@FXML
	private void onFour(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_4);
	}
	
	@FXML
	private void onFive(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_5);
	}
	
	@FXML
	private void onSix(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_6);
	}
	
	@FXML
	private void onSeven(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_7);
	}
	
	@FXML
	private void onEight(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_8);
	}
	
	@FXML
	private void onNine(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_9);
	}
	
	@FXML
	private void onStar(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_STAR);
	}
	
	@FXML
	private void onZero(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_0);
	}
	
	@FXML
	private void onHashtag(ActionEvent event)
	{
		handleEvent(HuaweiButtonsType.BUTTON_HASH);
	}
	
	private void handleEvent(HuaweiButtonsType buttonType)
	{
		try
		{
			final EnumerateDeviceResult result = RCDriver.enumerateDevices();
			if (result.getResult() != RCHasEmitterReturnType.SUCCESS)
			{
				Dialogs.showDialog(AlertType.WARNING, "Warning", "Failed to send button", "enumerateDevices returned " + result.getResult());
				return;
			}
			
			final RCReturnType type = driver.sendButton(result.getDevice(), buttonType);
			if (type != RCReturnType.SUCCESS)
			{
				Dialogs.showDialog(AlertType.WARNING, "Warning", "Failed to send button", "API returned " + type);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Dialogs.showExceptionDialog(AlertType.ERROR, "Error", "Failed to send button", e);
		}
	}
}

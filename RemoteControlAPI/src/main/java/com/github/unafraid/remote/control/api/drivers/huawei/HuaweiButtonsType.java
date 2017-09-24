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
package com.github.unafraid.remote.control.api.drivers.huawei;

/**
 * @author UnAfraid
 */
public enum HuaweiButtonsType
{
	BUTTON_POWER(0x9c),
	
	BUTTON_MUTE(0x95),
	BUTTON_LANG(0xd1),
	BUTTON_SEARCH(0xdd),
	
	BUTTON_EPG(0x9b),
	BUTTON_VOD(0xc2),
	BUTTON_PIP(0x9a),
	BUTTON_APP(0xd7),
	
	BUTTON_HELP(0xd4),
	BUTTON_INFO(0xc7),
	
	BUTTON_CHDN(0xd2),
	BUTTON_CHUP(0xca),
	BUTTON_OK(0xce),
	BUTTON_VOLDN(0x99),
	BUTTON_VOLUP(0xc1),
	
	BUTTON_MENU(0x9d),
	BUTTON_BACK(0x90),
	
	BUTTON_PREV(0xcf),
	BUTTON_NEXT(0x98),
	
	BUTTON_REW(0xda),
	BUTTON_PLAY(0xc3),
	BUTTON_FF(0xd6),
	
	BUTTON_RED(0x84),
	BUTTON_GREEN(0x89),
	BUTTON_YELLOW(0xd9),
	BUTTON_BLUE(0x96),
	
	BUTTON_1(0x92),
	BUTTON_2(0x93),
	BUTTON_3(0xcc),
	BUTTON_4(0x8e),
	BUTTON_5(0x8f),
	BUTTON_6(0xc8),
	BUTTON_7(0x8a),
	BUTTON_8(0x8b),
	BUTTON_9(0xc4),
	BUTTON_STAR(0xd5),
	BUTTON_0(0x87),
	BUTTON_HASH(0x9f);
	
	private final byte _code;
	
	private HuaweiButtonsType(int code)
	{
		_code = (byte) code;
	}
	
	public byte getCode()
	{
		return _code;
	}
}

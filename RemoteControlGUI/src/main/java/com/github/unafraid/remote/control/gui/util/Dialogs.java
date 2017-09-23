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
package com.github.unafraid.remote.control.gui.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 * @author UnAfraid
 */
public class Dialogs
{
	public static void showDialog(AlertType alertType, String title, String header, String context)
	{
		final Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(context);
		alert.initStyle(StageStyle.UTILITY);
		alert.show();
	}
	
	public static void showExceptionDialog(AlertType alertType, String title, String header, Throwable throwable)
	{
		final Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(throwable.getMessage());
		
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		
		final Label label = new Label("The exception stacktrace was:");
		
		final TextArea textArea = new TextArea(sw.toString());
		textArea.setEditable(false);
		textArea.setWrapText(true);
		
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		
		final GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);
		
		alert.getDialogPane().setExpandableContent(expContent);
		alert.show();
	}
	
	public static <T> Optional<T> showChoiceDialog(String title, String header, String content, Collection<T> choices, T defaultOption)
	{
		final ChoiceDialog<T> dialog = new ChoiceDialog<>(defaultOption, choices);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		return dialog.showAndWait();
	}
	
	public static Optional<String> showInputDialog(String title, String header, String content, String defaultOption)
	{
		final TextInputDialog dialog = new TextInputDialog(defaultOption);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		return dialog.showAndWait();
	}
	
	public static Optional<ButtonType> showButtonDialog(String title, String header, String content, List<ButtonType> buttons)
	{
		final Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		dialog.getButtonTypes().setAll(buttons);
		return dialog.showAndWait();
	}
	
	public static boolean showConfirmationDialog(String title, String header, String content)
	{
		final Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		
		final Optional<ButtonType> result = dialog.showAndWait();
		return result.isPresent() && (result.get() == ButtonType.OK);
	}
}

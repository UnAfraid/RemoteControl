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
package com.github.unafraid.remote.control.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author UnAfraid
 */
public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		final Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
		final Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/app.jpg")));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Remote Controller");
		primaryStage.setOnCloseRequest(event -> shutdown());
		primaryStage.show();
	}
	
	public static void shutdown()
	{
	}
	
	public static void main(String... args)
	{
		Application.launch(args);
	}
}

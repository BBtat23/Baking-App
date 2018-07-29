package com.example.bea.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.widget.RemoteViewsService;

import com.example.bea.bakingapp.activities.RecipeMainActivity;
import com.example.bea.bakingapp.fragment.RecipeFragment;

public class WidgetService extends RemoteViewsService {
	/*
	 * So pretty simple just defining the Adapter of the listview
	 * here Adapter is ListProvider
	 * */

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		int appWidgetId = intent.getIntExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);

		return (new ListProvider(this.getApplicationContext(), intent));
	}

}

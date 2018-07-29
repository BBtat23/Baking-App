package com.example.bea.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.bea.bakingapp.R;
import com.example.bea.bakingapp.data.Ingredients;
import com.example.bea.bakingapp.data.Result;
import com.example.bea.bakingapp.utils.Constants;
import com.google.gson.Gson;

import java.util.List;


public class WidgetProvider extends AppWidgetProvider {

	SharedPreferences sharedPreferences;

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
									   int appWidgetId, String recipeName, List<Ingredients> ingredientList) {


		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		views.setTextViewText(R.id.recipe_name_text_view, recipeName);
		views.removeAllViews(R.id.widget_ingredients_container);
		for (Ingredients ingredient : ingredientList) {
			RemoteViews ingredientView = new RemoteViews(context.getPackageName(),
					R.layout.list_row);
			ingredientView.setTextViewText(R.id.ingredient_name_text_view,
					String.valueOf(ingredient.getIngredient()) + " " +
							String.valueOf(ingredient.getMeasure()));
			views.addView(R.id.widget_ingredients_container, ingredientView);
		}
		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES,
				Context.MODE_PRIVATE);
		String result = sharedPreferences.getString(Constants.WIDGET_RESULT, null);
		Gson gson = new Gson();
		Result recipe = gson.fromJson(result, Result.class);
		Log.d("RecipeWidget", recipe + "");
		String recipeName = recipe.getName();
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId, recipeName, recipe.getIngredients());
		}
	}


	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first widget is created
	}

	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
	}
}


/*
	 * this method is called every 30 mins as specified on widgetinfo.xml
	 * this method is also called on every phone reboot
	 */
//	@Override
//	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
//			int[] appWidgetIds) {
//		final int N = appWidgetIds.length;
//		/*int[] appWidgetIds holds ids of multiple instance of your widget
//		 * meaning you are placing more than one widgets on your homescreen*/
//		for (int i = 0; i < N; ++i) {
//			RemoteViews remoteViews = updateWidgetListView(context,
//					appWidgetIds[i]);
//			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
//		}
//		super.onUpdate(context, appWidgetManager, appWidgetIds);
//	}
//
//	private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

//		//which layout to show on widget
//		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
//				com.example.bea.bakingapp.R.layout.widget_layout);
//
//		//RemoteViews Service needed to provide adapter for ListView
//		Intent svcIntent = new Intent(context, WidgetService.class);
//		//passing app widget id to that RemoteViews Service
//		svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//		//setting a unique Uri to the intent
//		//don't know its purpose to me right now
//		svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
//		//setting adapter to listview of the widget
//		remoteViews.setRemoteAdapter(appWidgetId, com.example.bea.bakingapp.R.id.listViewWidget,
//				svcIntent);
//		//setting an empty view in case of no data
//		remoteViews.setEmptyView(com.example.bea.bakingapp.R.id.listViewWidget, com.example.bea.bakingapp.R.id.empty_view);
//		return remoteViews;
//	}
//
//}

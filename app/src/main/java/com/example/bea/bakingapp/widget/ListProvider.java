package com.example.bea.bakingapp.widget;

import java.util.ArrayList;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import com.example.bea.bakingapp.R;
import com.example.bea.bakingapp.data.Ingredients;
import com.example.bea.bakingapp.data.Recipe;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 * 
 */
public class ListProvider implements RemoteViewsFactory {
	private ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
	private Context context = null;
	private int appWidgetId;
	private ArrayList<Ingredients> mIngredients;
		    String contentItemString = "";
		    StringBuilder builder = new StringBuilder();

	public ListProvider(Context context, Intent intent) {
		this.context = context;
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);

		populateListItem();
	}

	private void populateListItem() {
		Recipe recipe = new Recipe();
		Log.d("ListProvider", "recipes" + recipe.getName());
		String recipeName = recipe.getName();
		ArrayList<Ingredients> ingredients= recipe.getIngredientsArrayList();
		for (Ingredients ingredient : ingredients){
			contentItemString = String.valueOf(builder.append("- " + ingredient + "\n"));
		}
		ListItem listItem = new ListItem();
		listItem.heading = recipeName;
		listItem.content = contentItemString;
//    for (int i = 0; i < 10; i++) {
//    ListItem listItem = new ListItem();
//    listItem.heading = "Heading" + i;
//    listItem.content = i
//    + " This is the content of the app widget listview.Nice content though";
		listItemList.add(listItem);
	}

	@Override
	public int getCount() {
		return listItemList.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 *Similar to getView of Adapter where instead of View
	 *we return RemoteViews 
	 * 
	 */
	@Override
	public RemoteViews getViewAt(int position) {
		final RemoteViews remoteView = new RemoteViews(
				context.getPackageName(), R.layout.list_row);
		ListItem listItem = listItemList.get(position);
//		remoteView.setTextViewText(R.id.heading, listItem.heading);
		remoteView.setTextViewText(R.id.content, builder.toString());
		remoteView.setTextViewText(R.id.heading, listItem.heading);

		return remoteView;
	}
	

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public void onCreate() {
		RemoteViews views = new RemoteViews(
				context.getPackageName(), R.layout.widget_layout);
		Intent intent = new Intent(context, ListProvider.class);
		views.setRemoteAdapter(R.id.listViewWidget, intent);
	}

	@Override
	public void onDataSetChanged() {
	}

	@Override
	public void onDestroy() {
	}

}

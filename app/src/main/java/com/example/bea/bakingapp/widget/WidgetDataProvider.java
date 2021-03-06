package com.example.bea.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bea.bakingapp.data.Ingredients;
import com.example.bea.bakingapp.data.Recipe;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ary on 10/9/17.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

	public static final String SELECTED_RECIPE = "com.example.ary.mimobakingapp.SELECTED_RECIPE";

	private Context context;
	private Intent intent;
	private List<Recipe> recipeList = new ArrayList<>();
	private List<Ingredients> ingredientsList = new ArrayList<>();

	public WidgetDataProvider(Context context, Intent intent){
		this.context = context;
		this.intent = intent;
	}

	void initData(){
		String sRecipe = intent.getStringExtra(SELECTED_RECIPE);
		Recipe recipe = new GsonBuilder().create().fromJson(sRecipe, Recipe.class);
		ingredientsList = recipe.getIngredientsArrayList();
	}

	@Override
	public void onCreate() {
		initData();
	}

	@Override
	public void onDataSetChanged() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public int getCount() {
		return ingredientsList.size();
	}

	@Override
	public RemoteViews getViewAt(int position) {
		Ingredients ingredient = ingredientsList.get(position);
		RemoteViews views = new RemoteViews(context.getPackageName(), android.R.layout.simple_list_item_1);
		//
		// views.setTextViewText(android.R.id.text1, String.format(context.getString(R.string.ingredients_detail)
		//        , ingredient.getQuantity(),ingredient.getMeasure(), ingredient.getIngredient()));
		return views;
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
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}
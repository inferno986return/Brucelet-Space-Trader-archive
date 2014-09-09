/*
 *     Copyright (C) 2014 Russell Wolf, All Rights Reserved
 *     
 *     Based on code by Pieter Spronck
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *     
 *     You can contact the author at spacetrader@brucelet.com
 */
package com.brucelet.spacetrader;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.brucelet.spacetrader.datatypes.GameState;
import com.brucelet.spacetrader.enumtypes.ScreenType;
import com.brucelet.spacetrader.enumtypes.XmlString;

public abstract class BaseScreen extends Fragment implements ConvenienceMethods, View.OnClickListener, OnSingleClickListener {
	
//	private ScreenPagerAdapter mAdapter;
	
	public void onAttach(Activity activity) {
		if (!(activity instanceof MainActivity)) {
			throw new IllegalArgumentException(BaseScreen.class.getSimpleName()+" Fragment must attach to spacetrader "+MainActivity.class.getSimpleName()+" class");
		}
		super.onAttach(activity);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		onShowScreen();
		onRefreshScreen();
	}

	@Override
	public MainActivity getGameManager() {
		return ((MainActivity) getActivity());
//		return ((MainActivity) mAdapter.getActivity());
	}

	@Override
	public GameState getGameState() {
		return ((MainActivity) getActivity()).getGameState();
//		return ((MainActivity) mAdapter.getActivity()).getGameState();
	}
	
	@Override
	public void setViewTextById(int viewId, int textId) {
		((TextView) getView().findViewById(viewId)).setText(textId);
	}
	
	@Override
	public void setViewTextById(int viewId, int textId, Object... args) {
		
		// A bit of processing so we can drop XmlString enums as format arguments directly without needing to call toXmlString()
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof XmlString) {
				args[i] = ((XmlString) args[i]).toXmlString(getResources());
			}
		}
		
		((TextView) getView().findViewById(viewId)).setText(getResources().getString(textId, args));
	}

	@Override
	public void setViewTextById(int viewId, CharSequence text) {
		((TextView) getView().findViewById(viewId)).setText(text);
	}

	@Override
	public void setViewTextById(int viewId, XmlString item) {
		((TextView) getView().findViewById(viewId)).setText(item.toXmlString(getResources()));		
	}

	@Override
	public void setViewVisibilityById(int viewId, boolean visOrInvis, boolean invisOrGone) {
		getView().findViewById(viewId).setVisibility(visOrInvis? View.VISIBLE : invisOrGone? View.INVISIBLE : View.GONE);
	}	
	
	@Override
	public void setViewVisibilityById(int viewId, boolean visOrInvis) {
		setViewVisibilityById(viewId, visOrInvis, true);
	}
	
//	@Override
//	public View getView() {
//		View view = super.getView();
//		if (view != null) return view;
//		
//		ViewPager vp = getGameManager().getViewPager();
//		int index = vp.getCurrentItem();
//		return vp.getChildAt(index);
//	}
	
	
	@Override
	public final void onClick(View view) {
		if (getGameManager().isClicking()) return;
		if (getGameManager().finishMenuActionMode()) return;
		
		getGameManager().startClick();
		onSingleClick(view);
		getGameManager().finishClick();
	}
	
	public void setAdapter(ScreenPagerAdapter adapter) {
//		mAdapter = adapter;
	}

	
	/**
	 * Called when this Screen is made the active View of the root ViewFlipper.
	 */
	public void onRefreshScreen() {}
	
	public void onShowScreen() {}
	
	public abstract int getHelpTextResId();
	
	public abstract ScreenType getType();
}

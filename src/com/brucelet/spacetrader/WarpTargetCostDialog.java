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


public class WarpTargetCostDialog extends BaseDialog {

	public static WarpTargetCostDialog newInstance() {
		return new WarpTargetCostDialog();
	}
	public WarpTargetCostDialog() {}
	
	@Override
	public void onBuildDialog(Builder builder) {
		builder.setTitle(R.string.screen_warp_target_cost_specific_title);
		builder.setView(R.layout.screen_warp_target_cost);
		builder.setPositiveButton(R.string.generic_ok);
	}
	
	@Override
	public void onRefreshDialog() {
		getGameState().showSpecificationForm();
	}

	@Override
	public int getHelpTextResId() {
		return R.string.help_specification;
	}

}
